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

import com.carrental.demo.model.Driver;
import com.carrental.demo.model.DriverPayOut;

import com.carrental.demo.repository.DriverPayOutRepositoryImpl;
import com.carrental.demo.repository.DriverRepositoryImpl;

import jakarta.servlet.http.HttpSession;

@Controller
@RequestMapping("/")
public class DriverPayOutController {
	
	@Autowired
	private DriverPayOutRepositoryImpl driverPayOutRepositoryImpl;
	
	@Autowired
	private DriverRepositoryImpl driverRepositoryImpl;
	
	@RequestMapping(value = "/list-driverPayOut", method = RequestMethod.GET)
	public String listDriverPayOut(Model model, HttpSession session) throws ExecutionException, InterruptedException {
		if (session.getAttribute("userSession") != null) {
			Account loggedInUser = (Account) session.getAttribute("userSession");
			if (loggedInUser.getRole().equals("admin")) {
				List<DriverPayOut> listDriverPayOut = new ArrayList<DriverPayOut>();
				listDriverPayOut = driverPayOutRepositoryImpl.getAllDriverPayOuts();
				String total = driverPayOutRepositoryImpl.getSumTotalPrice();
				
				model.addAttribute("totalDriverPayOut", total);
				model.addAttribute("listDriverPayOut", listDriverPayOut);
				model.addAttribute("nameFunction", "Khoản chi tài xế");
				return "driverPayOut/list-driverPayOut";
				
			} else {
				return "redirect:/clientpage";
			}
		}
		return "./login";	
	}
	
	@GetMapping("add-driverPayOut")
    public String addDriverPayOutForm(Model model, HttpSession session) throws ExecutionException, InterruptedException{
		if (session.getAttribute("userSession") != null) {
			Account loggedInUser = (Account) session.getAttribute("userSession");
			if (loggedInUser.getRole().equals("admin")) {
				List<Driver> listDriver = new ArrayList<Driver>();
				listDriver = driverRepositoryImpl.getAllDrivers();
				model.addAttribute("listDriver", listDriver);
				model.addAttribute("nameFunction", "Khoản chi tài xế");
				return "./driverPayOut/add-driverPayOut";
				
			} else {
				return "redirect:/clientpage";
			}
		}
		return "./login";
    }
	
	@PostMapping("add-driverPayOut-post")
	public String addDriver(@ModelAttribute("driver") DriverPayOut driverPayOut) throws IOException, ExecutionException, InterruptedException {
		String uniqueID = UUID.randomUUID().toString();
		
		driverPayOut.setId(uniqueID);
		driverPayOut.setDate(driverPayOutRepositoryImpl.formatDate(driverPayOut.getDate()));
		driverPayOutRepositoryImpl.saveDriverPayOut(driverPayOut);
		return "redirect:/list-driverPayOut";
	}
	
	@GetMapping("/edit-driverPayOut/{id}")
	public String showEditForm(@PathVariable("id") String idDriverPayOut, Model model, HttpSession session) throws ExecutionException, InterruptedException {
		if (session.getAttribute("userSession") != null) {
			Account loggedInUser = (Account) session.getAttribute("userSession");
			if (loggedInUser.getRole().equals("admin")) {
				DriverPayOut driverPayOut = driverPayOutRepositoryImpl.getDriverPayOutById(idDriverPayOut);
				
				// Đưa đơn vào model để hiển thị trong biểu mẫu
				model.addAttribute("driverPayOut", driverPayOut);	

				List<Driver> listDriver = new ArrayList<Driver>();
				listDriver = driverRepositoryImpl.getAllDrivers();
				model.addAttribute("listDriver", listDriver);
				model.addAttribute("nameFunction", "Khoản chi tài xế");
				return "./driverPayOut/edit-driverPayOut";
				
			} else {
				return "redirect:/clientpage";
			}
		}
		return "./login";
	}
	
	@PostMapping("/edit-driverPayOut/{id}/edit")
	public String editDriverPayOut(@ModelAttribute("driverPayOut") DriverPayOut driverPayOut) {
		driverPayOutRepositoryImpl.saveDriverPayOut(driverPayOut);
		return "redirect:/edit-driverPayOut/{id}";
	}
	
	@GetMapping("search-driverPayOut")
	public String searchdriverPayOutForm(Model model, HttpSession session, @ModelAttribute("driverPayOut") DriverPayOut driverPayOut) throws ExecutionException, InterruptedException, ParseException {
		if (session.getAttribute("userSession") != null) {
			Account loggedInUser = (Account) session.getAttribute("userSession");
			if (loggedInUser.getRole().equals("admin")) {

				model.addAttribute("nameFunction", "Khoản chi tài xế");
				return "./driverPayOut/search-driverPayOut";

			} else {
				return "redirect:/clientpage";
			}
		}
		return "./login";
	}
	
	@PostMapping("search-driverPayOut-post")
	public String searchDriverPayOut(Model model, HttpSession session, @ModelAttribute("driverPayOut") DriverPayOut driverPayOut) throws ExecutionException, InterruptedException, ParseException {
		if (session.getAttribute("userSession") != null) {
			Account loggedInUser = (Account) session.getAttribute("userSession");
			if (loggedInUser.getRole().equals("admin")) {
				
				List<DriverPayOut> listdriverPayOut = driverPayOutRepositoryImpl.getDriverPayOutsByYearMonth(driverPayOut.getDate());
				String total = driverPayOutRepositoryImpl.calculateTotalPaymentByMonthYear(driverPayOut.getDate());
				
				// Đưa bill vào model để hiển thị trong biểu mẫu
				model.addAttribute("listdriverPayOutSearch", listdriverPayOut);
				model.addAttribute("totalDriverPayOutSearch", total);
				model.addAttribute("nameFunction", "Khoản chi tài xế");
				return "./driverPayOut/list-driverPayOut-search";

			} else {
				return "redirect:/clientpage";
			}
		}
		return "./login";
	}
	
	@RequestMapping(value = "/list-driverPayOut-search", method = RequestMethod.GET)
	public String listDriverPayOutSearch(Model model, HttpSession session) throws ExecutionException, InterruptedException, ParseException {
		if (session.getAttribute("userSession") != null) {
			Account loggedInUser = (Account) session.getAttribute("userSession");
			if (loggedInUser.getRole().equals("admin")) {
				model.addAttribute("nameFunction", "Khoản chi tài xế");
				return "redirect:/driverPayOut/list-driverPayOut-search";
				
			} else {
				return "redirect:/clientpage";
			}
		}
		return "./login";	
	}

}
