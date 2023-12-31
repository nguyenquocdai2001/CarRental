package com.carrental.demo.controller;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
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
import com.carrental.demo.model.Contract;
import com.carrental.demo.model.Driver;
import com.carrental.demo.model.PreOrder;
import com.carrental.demo.repository.CarRepositoryImpl;
import com.carrental.demo.repository.ContractRepositoryImpl;
import com.carrental.demo.repository.DriverRepositoryImpl;
import com.carrental.demo.repository.PreOrderRepositoryImpl;

import jakarta.servlet.http.HttpSession;

@Controller
@RequestMapping("/")
public class ContractController {
	@Autowired
	private ContractRepositoryImpl contractRepositoryImpl;

	@Autowired
	private PreOrderRepositoryImpl preOrderRepositoryImpl;

	@Autowired
	private DriverRepositoryImpl driverRepositoryImpl;

	@Autowired
	private CarRepositoryImpl carRepositoryImpl;

	@GetMapping("add-contract/{id}")
	public String addContractrForm(Model model, HttpSession session, @ModelAttribute("contract") Contract contract, @PathVariable("id") String idpreOrder)
			throws ExecutionException, InterruptedException {
		if (session.getAttribute("userSession") != null) {
			Account loggedInUser = (Account) session.getAttribute("userSession");
			if (loggedInUser.getRole().equals("admin")) {

				List<Car> listCar = new ArrayList<Car>();
				listCar = carRepositoryImpl.getAllCars();
				model.addAttribute("listCar", listCar);

				List<Driver> listDriver = new ArrayList<Driver>();
				listDriver = driverRepositoryImpl.getAllDrivers();
				model.addAttribute("listDriver", listDriver);

				List<PreOrder> lisPreOrders = new ArrayList<PreOrder>();
				lisPreOrders = preOrderRepositoryImpl.getAllPreOrders();
				model.addAttribute("lisPreOrders", lisPreOrders);

				PreOrder preOrder = preOrderRepositoryImpl.getPreOrderById(idpreOrder);

				// Đưa đơn vào model để hiển thị trong biểu mẫu
				model.addAttribute("preOrder", preOrder);
				model.addAttribute("nameFunction", "Hợp đồng");
				return "./contract/add-contract";

			} else {
				return "redirect:/clientpage";
			}
		}
		return "./login";
	}

	@PostMapping("/add-contract/{id}/add")
	public String addContract(@ModelAttribute("contract") Contract contract) {
		contractRepositoryImpl.saveContract(contract);
		//Chuyển status của đơn đặt trước thành "đã xong"
		preOrderRepositoryImpl.editStatusPreOrderById(contract.getId(), "Đã xong");
		return "redirect:/list-contract";
	}
	
	@GetMapping("edit-contract/{id}")
	public String editContractrForm(Model model, HttpSession session, @ModelAttribute("contract") Contract contract, @PathVariable("id") String id)
			throws ExecutionException, InterruptedException {
		if (session.getAttribute("userSession") != null) {
			Account loggedInUser = (Account) session.getAttribute("userSession");
			if (loggedInUser.getRole().equals("admin")) {

				List<Car> listCar = new ArrayList<Car>();
				listCar = carRepositoryImpl.getAllCars();
				model.addAttribute("listCar", listCar);

				List<Driver> listDriver = new ArrayList<Driver>();
				listDriver = driverRepositoryImpl.getAllDrivers();
				model.addAttribute("listDriver", listDriver);

				List<PreOrder> lisPreOrders = new ArrayList<PreOrder>();
				lisPreOrders = preOrderRepositoryImpl.getAllPreOrders();
				model.addAttribute("lisPreOrders", lisPreOrders);
				

				Contract con = contractRepositoryImpl.getContractById(id);

				// Đưa hợp đồng vào model để hiển thị trong biểu mẫu
				model.addAttribute("contract", con);
				model.addAttribute("nameFunction", "Hợp đồng");
				return "./contract/edit-contract";

			} else {
				return "redirect:/clientpage";
			}
		}
		return "./login";
	}

	@PostMapping("/edit-contract/{id}/edit")
	public String editContract(@ModelAttribute("contract") Contract contract,  @RequestParam("file") MultipartFile file, @PathVariable("id") String id) throws IOException, ExecutionException, InterruptedException {
		String nameFile = file.getOriginalFilename();
		if(!(nameFile.isEmpty())) {
			contractRepositoryImpl.uploadContractImage(file, id);
			contract.setImage("https://firebasestorage.googleapis.com/v0/b/thyan-b9327.appspot.com/o/contract%2F"  + id  + "?alt=media");
		}else
		{
			contract.setImage(contract.getImage());
		}
		contract.setId(id);
		contractRepositoryImpl.saveContract(contract);
		return "redirect:/edit-contract/{id}";
	}
	
	@GetMapping("/details-contract/{id}")
	public String showDetailsForm(@PathVariable("id") String id, Model model, HttpSession session) throws ExecutionException, InterruptedException {
		if (session.getAttribute("userSession") != null) {
			Account loggedInUser = (Account) session.getAttribute("userSession");
			if (loggedInUser.getRole().equals("admin")) {
				Contract contract = contractRepositoryImpl.getContractById(id);
				
				// Đưa đơn vào model để hiển thị trong biểu mẫu
				model.addAttribute("contract", contract);	
				model.addAttribute("nameFunction", "Hợp đồng");
				return "./contract/details-contract";
				
			} else {
				return "redirect:/clientpage";
			}
		}
		return "./login";
	}
	
	@RequestMapping(value = "/list-contract", method = RequestMethod.GET)
	public String listContract(Model model, HttpSession session) throws ExecutionException, InterruptedException {
		if (session.getAttribute("userSession") != null) {
			Account loggedInUser = (Account) session.getAttribute("userSession");
			if (loggedInUser.getRole().equals("admin")) {
				List<Contract> listContract = new ArrayList<Contract>();
				listContract = contractRepositoryImpl.getAllContracts();

				model.addAttribute("listContract", listContract);
				model.addAttribute("nameFunction", "Hợp đồng");
				return "contract/list-contract";
				
			} else {
				return "redirect:/clientpage";
			}
		}
		return "./login";	
	}
}
