package com.carrental.demo.controller;

import java.io.IOException;
import java.text.ParseException;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;
import java.util.concurrent.ExecutionException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.carrental.demo.model.Account;
import com.carrental.demo.model.Car;
import com.carrental.demo.model.CarPayOut;
import com.carrental.demo.repository.CarPayOutRepositoryImpl;
import com.carrental.demo.repository.CarRepositoryImpl;

import jakarta.servlet.http.HttpSession;

@Controller
public class CarPayOutController {
	
	@Autowired
	private CarPayOutRepositoryImpl carPayOutRepositoryImpl;
	
	@Autowired
	private CarRepositoryImpl carRepositoryImpl;
	
	@RequestMapping(value = "/list-carPayOut", method = RequestMethod.GET)
	public String listCarPayOut(Model model, HttpSession session) throws ExecutionException, InterruptedException {
		if (session.getAttribute("userSession") != null) {
			Account loggedInUser = (Account) session.getAttribute("userSession");
			if (loggedInUser.getRole().equals("admin")) {
				List<CarPayOut> listCarPayOut = new ArrayList<CarPayOut>();
				listCarPayOut = carPayOutRepositoryImpl.getAllCarPayOuts();
				String total = carPayOutRepositoryImpl.getSumTotalPrice();
				
				model.addAttribute("totalCarPayOut", total);
				model.addAttribute("listCarPayOut", listCarPayOut);
				model.addAttribute("nameFunction", "Khoản chi xe");
				return "carPayOut/list-carPayOut";
				
			} else {
				return "redirect:/clientpage";
			}
		}
		return "./login";	
	}
	
	@GetMapping("add-carPayOut")
    public String addDriverPayOutForm(Model model, HttpSession session) throws ExecutionException, InterruptedException{
		if (session.getAttribute("userSession") != null) {
			Account loggedInUser = (Account) session.getAttribute("userSession");
			if (loggedInUser.getRole().equals("admin")) {
				List<Car> listCar = new ArrayList<Car>();
				listCar = carRepositoryImpl.getAllCars();
				model.addAttribute("listCar", listCar);
				model.addAttribute("nameFunction", "Khoản chi xe");
				return "./carPayOut/add-carPayOut";
				
			} else {
				return "redirect:/clientpage";
			}
		}
		return "./login";
    }
	
	@PostMapping("add-carPayOut-post")
	public String addCar(@ModelAttribute("car") CarPayOut carPayOut) throws IOException, ExecutionException, InterruptedException {
		String uniqueID = UUID.randomUUID().toString();
		
		carPayOut.setId(uniqueID);
		carPayOut.setDate(carPayOutRepositoryImpl.formatDate(carPayOut.getDate()));
		carPayOutRepositoryImpl.saveCarPayOut(carPayOut);
		return "redirect:/list-carPayOut";
	}
	
	@GetMapping("/edit-carPayOut/{id}")
	public String showEditForm(@PathVariable("id") String idCarPayOut, Model model, HttpSession session) throws ExecutionException, InterruptedException {
		if (session.getAttribute("userSession") != null) {
			Account loggedInUser = (Account) session.getAttribute("userSession");
			if (loggedInUser.getRole().equals("admin")) {
				CarPayOut carPayOut = carPayOutRepositoryImpl.getCarPayOutById(idCarPayOut);
				
				// Đưa đơn vào model để hiển thị trong biểu mẫu
				model.addAttribute("carPayOut", carPayOut);	

				List<Car> listCar = new ArrayList<Car>();
				listCar = carRepositoryImpl.getAllCars();
				model.addAttribute("listCar", listCar);
				model.addAttribute("nameFunction", "Khoản chi xe");
				return "./carPayOut/edit-carPayOut";
				
			} else {
				return "redirect:/clientpage";
			}
		}
		return "./login";
	}
	
	@PostMapping("/edit-carPayOut/{id}/edit")
	public String editDriverPayOut(@ModelAttribute("carPayOut") CarPayOut carPayOut) {
		carPayOutRepositoryImpl.saveCarPayOut(carPayOut);
		return "redirect:/edit-carPayOut/{id}";
	}
	
	@GetMapping("search-carPayOut")
	public String searchcarPayOutForm(Model model, HttpSession session, @ModelAttribute("carPayOut") CarPayOut carPayOut) throws ExecutionException, InterruptedException, ParseException {
		if (session.getAttribute("userSession") != null) {
			Account loggedInUser = (Account) session.getAttribute("userSession");
			if (loggedInUser.getRole().equals("admin")) {

				model.addAttribute("nameFunction", "Khoản chi xe");
				return "./carPayOut/search-carPayOut";

			} else {
				return "redirect:/clientpage";
			}
		}
		return "./login";
	}
	
	@PostMapping("search-carPayOut-post")
	public String searchCarPayOut(Model model, HttpSession session, @ModelAttribute("carPayOut") CarPayOut carPayOut) throws ExecutionException, InterruptedException, ParseException {
		if (session.getAttribute("userSession") != null) {
			Account loggedInUser = (Account) session.getAttribute("userSession");
			if (loggedInUser.getRole().equals("admin")) {
				
				List<CarPayOut> listcarPayOut = carPayOutRepositoryImpl.getCarPayOutsByYearMonth(carPayOut.getDate());
				String total = carPayOutRepositoryImpl.calculateTotalPaymentByMonthYear(carPayOut.getDate());
				
				// Đưa bill vào model để hiển thị trong biểu mẫu
				model.addAttribute("listcarPayOutSearch", listcarPayOut);
				model.addAttribute("totalCarPayOutSearch", total);
				model.addAttribute("nameFunction", "Khoản chi xe");
				return "./carPayOut/list-carPayOut-search";

			} else {
				return "redirect:/clientpage";
			}
		}
		return "./login";
	}
	
	@RequestMapping(value = "/list-carPayOut-search", method = RequestMethod.GET)
	public String listCarPayOutSearch(Model model, HttpSession session) throws ExecutionException, InterruptedException, ParseException {
		if (session.getAttribute("userSession") != null) {
			Account loggedInUser = (Account) session.getAttribute("userSession");
			if (loggedInUser.getRole().equals("admin")) {
				model.addAttribute("nameFunction", "Khoản chi xe");
				return "redirect:/carPayOut/list-carPayOut-search";
				
			} else {
				return "redirect:/clientpage";
			}
		}
		return "./login";	
	}

}
