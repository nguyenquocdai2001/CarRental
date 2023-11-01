package com.carrental.demo.controller;

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
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

import com.carrental.demo.model.Account;
import com.carrental.demo.model.Car;
import com.carrental.demo.model.Driver;
import com.carrental.demo.model.PreOrder;
import com.carrental.demo.repository.CarRepositoryImpl;
import com.carrental.demo.repository.ClientRepositoryImpl;
import com.carrental.demo.repository.DriverRepositoryImpl;
import com.carrental.demo.repository.PreOrderRepositoryImpl;


import jakarta.servlet.http.HttpSession;

@Controller
@RequestMapping("/")
public class PreOrderController {
	
	@Autowired
	private PreOrderRepositoryImpl preOrderRepositoryImpl;
	
	@Autowired
	private DriverRepositoryImpl driverRepositoryImpl;
	
	@Autowired
	private CarRepositoryImpl carRepositoryImpl;
	
	
	
	@GetMapping("add-preOrder")
    public String addPreOrderForm(Model model, HttpSession session) throws ExecutionException, InterruptedException{
		if (session.getAttribute("userSession") != null) {
			Account loggedInUser = (Account) session.getAttribute("userSession");
			if (loggedInUser.getRole().equals("admin")) {
				
				List<Car> listCar = new ArrayList<Car>();
				listCar = carRepositoryImpl.getAllCars();
				model.addAttribute("listCar", listCar);
				
				List<Driver> listDriver = new ArrayList<Driver>();
				listDriver = driverRepositoryImpl.getAllDrivers();
				model.addAttribute("listDriver", listDriver);
				model.addAttribute("nameFunction", "Đơn đặt trước");
				return "./preOrder/add-preOrder";
				
			} else {
				return "redirect:/clientpage";
			}
		}
		return "./login";
    }
	
	@PostMapping("add-preOrder-post")
	public String addPreOrder(@ModelAttribute("preOrder") PreOrder preOrder) throws ExecutionException, InterruptedException {
		String uniqueID = UUID.randomUUID().toString();

		preOrder.setId(uniqueID);
		preOrder.setDate_going(preOrderRepositoryImpl.formatDate(preOrder.getDate_going()));
		preOrder.setDate_comback(preOrderRepositoryImpl.formatDate(preOrder.getDate_comback()));
		preOrder.setTime_pre_order(java.time.LocalDateTime.now().toString());
		 // Tách chuỗi theo dấu gạch ngang
        String[] parts = preOrder.getName_car_license_plate_brand_number_of_seats().split(" - ");
        // Lấy phần "name" (đầu tiên)
        String name_car = parts[0];
		preOrder.setId_car(preOrderRepositoryImpl.getIdCar(name_car));
		preOrderRepositoryImpl.savePreOrder(preOrder);	
		return "redirect:/list-preOrder";
	}
	
	@GetMapping("/edit-preOrder/{id}")
	public String showEditForm(@PathVariable("id") String idpreOrder, Model model, HttpSession session) throws ExecutionException, InterruptedException {
		if (session.getAttribute("userSession") != null) {
			Account loggedInUser = (Account) session.getAttribute("userSession");
			if (loggedInUser.getRole().equals("admin")) {
				PreOrder preOrder = preOrderRepositoryImpl.getPreOrderById(idpreOrder);
				
				// Đưa đơn vào model để hiển thị trong biểu mẫu
				model.addAttribute("preOrder", preOrder);	
				
				List<Car> listCar = new ArrayList<Car>();
				listCar = carRepositoryImpl.getAllCars();
				model.addAttribute("listCar", listCar);
				
				List<Driver> listDriver = new ArrayList<Driver>();
				listDriver = driverRepositoryImpl.getAllDrivers();
				model.addAttribute("listDriver", listDriver);
				model.addAttribute("nameFunction", "Đơn đặt trước");
				return "./preOrder/edit-preOrder";
				
			} else {
				return "redirect:/clientpage";
			}
		}
		return "./login";
	}
	
	@PostMapping("/edit-preOrder/{id}/edit")
	public String editPreOrder(@ModelAttribute("preOrder") PreOrder preOrder) {
		preOrderRepositoryImpl.savePreOrder(preOrder);
		return "redirect:/edit-preOrder/{id}";
	}
	
	@GetMapping("/details-preOrder/{id}")
	public String showDetailsForm(@PathVariable("id") String idpreOrder, Model model, HttpSession session) throws ExecutionException, InterruptedException {
		if (session.getAttribute("userSession") != null) {
			Account loggedInUser = (Account) session.getAttribute("userSession");
			if (loggedInUser.getRole().equals("admin")) {
				PreOrder preOrder = preOrderRepositoryImpl.getPreOrderById(idpreOrder);
				
				// Đưa đơn vào model để hiển thị trong biểu mẫu
				model.addAttribute("preOrder", preOrder);	
				model.addAttribute("nameFunction", "Đơn đặt trước");
				return "./preOrder/details-preOrder";
				
			} else {
				return "redirect:/clientpage";
			}
		}
		return "./login";
	}
	
	@RequestMapping(value = "/list-preOrder", method = RequestMethod.GET)
	public String listpreOrder(Model model, HttpSession session) throws ExecutionException, InterruptedException {
		if (session.getAttribute("userSession") != null) {
			Account loggedInUser = (Account) session.getAttribute("userSession");
			if (loggedInUser.getRole().equals("admin")) {
				List<PreOrder> listpreOrder = new ArrayList<PreOrder>();
				listpreOrder = preOrderRepositoryImpl.getAllPreOrders();

				model.addAttribute("listpreOrder", listpreOrder);
				model.addAttribute("nameFunction", "Đơn đặt trước");
				return "preOrder/pre-order-list";
				
			} else {
				return "redirect:/clientpage";
			}
		}
		return "./login";	
	}
	
	@RequestMapping(value = "/list-preOrder-start", method = RequestMethod.GET)
	public String listpreOrderStart(Model model, HttpSession session) throws ExecutionException, InterruptedException {
		if (session.getAttribute("userSession") != null) {
			Account loggedInUser = (Account) session.getAttribute("userSession");
			if (loggedInUser.getRole().equals("admin")) {
				List<PreOrder> listpreOrder = new ArrayList<PreOrder>();
				listpreOrder = preOrderRepositoryImpl.getAllPreOrders();

				model.addAttribute("listpreOrder", listpreOrder);
				model.addAttribute("nameFunction", "Đơn đặt trước");
				return "preOrder/pre-order-list-start";
				
			} else {
				return "redirect:/clientpage";
			}
		}
		return "./login";	
	}
}	
