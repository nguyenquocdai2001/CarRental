package com.carrental.demo.controller;


import java.io.IOException;

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
import com.carrental.demo.model.Driver;

import com.carrental.demo.repository.DriverRepositoryImpl;
import jakarta.servlet.http.HttpSession;


@Controller
@RequestMapping("/")
public class DriverController {
	
	@Autowired
	private DriverRepositoryImpl driverRepositoryImpl;
	
	
	@RequestMapping(value = "/list-driver", method = RequestMethod.GET)
	public String listDriver(Model model, HttpSession session) throws ExecutionException, InterruptedException {
		if (session.getAttribute("userSession") != null) {
			Account loggedInUser = (Account) session.getAttribute("userSession");
			if (loggedInUser.getRole().equals("admin")) {
				List<Driver> listDriver = new ArrayList<Driver>();
				listDriver = driverRepositoryImpl.getAllDrivers();

				model.addAttribute("listDriver", listDriver);
				model.addAttribute("nameFunction", "Tài xế");
				return "driver/driver-list";
				
			} else {
				return "redirect:/clientpage";
			}
		}
		return "./login";	
	}
	
	@GetMapping("add-driver")
    public String addDriverForm(Model model, HttpSession session){
		if (session.getAttribute("userSession") != null) {
			Account loggedInUser = (Account) session.getAttribute("userSession");
			if (loggedInUser.getRole().equals("admin")) {
				model.addAttribute("nameFunction", "Tài xế");
				return "./driver/add-driver";
				
			} else {
				return "redirect:/clientpage";
			}
		}
		return "./login";
    }
	
	@PostMapping("add-driver-post")
	public String addDriver(@ModelAttribute("driver") Driver driver, @RequestParam("file") MultipartFile file) throws IOException, ExecutionException, InterruptedException {
		String uniqueID = UUID.randomUUID().toString();
		// Lưu file vào storage
		String nameFile = file.getOriginalFilename();
		driverRepositoryImpl.uploadDriverImage(file, uniqueID);
		driver.setImage("https://firebasestorage.googleapis.com/v0/b/thyan-b9327.appspot.com/o/driver%2F" + uniqueID + "?alt=media");
		driver.setId(uniqueID);
		driverRepositoryImpl.saveDriver(driver);
		return "redirect:/list-driver";
	}
	
	@GetMapping("/edit-driver/{id}")
	public String showEditForm(@PathVariable("id") String idDriver, Model model, HttpSession session) throws ExecutionException, InterruptedException {
		if (session.getAttribute("userSession") != null) {
			Account loggedInUser = (Account) session.getAttribute("userSession");
			if (loggedInUser.getRole().equals("admin")) {
				Driver driver = driverRepositoryImpl.getDriverById(idDriver);
				
				// Đưa xe vào model để hiển thị trong biểu mẫu
				model.addAttribute("driver", driver);	
				model.addAttribute("nameFunction", "Tài xế");
				return "./driver/edit-driver";
				
			} else {
				return "redirect:/clientpage";
			}
		}
		return "./login";
	}
	
	@PostMapping("/edit-driver/{id}/edit")
	public String editDriver(@ModelAttribute("driver") Driver driver, @RequestParam("file") MultipartFile file) throws IOException, ExecutionException, InterruptedException {
		String nameFile = file.getOriginalFilename();
		if(!(nameFile.isEmpty())) {
			driverRepositoryImpl.uploadDriverImage(file, driver.getId());
			driver.setImage("https://firebasestorage.googleapis.com/v0/b/thyan-b9327.appspot.com/o/driver%2F" + driver.getId() + "?alt=media");
		}else
		{
			driver.setImage(driver.getImage());
		}
		driverRepositoryImpl.saveDriver(driver);
		return "redirect:/edit-driver/{id}";
	}
	
//	private String saveImageInProject(MultipartFile file) {
//		// Lưu file vào thư mục trong project
//		String uploadDir = servletContext.getRealPath("/template/admin/upload/");
//		String fileName = file.getOriginalFilename();
//		String filePath = uploadDir + File.separator + fileName;
//		System.out.println(uploadDir);
//		try {
//			byte[] bytes = file.getBytes();
//			Path path = Paths.get(filePath);	
//			Files.write(path, bytes);
//		} catch (IOException e) {
//			e.printStackTrace();
//		}
//		return filePath;
//	}
}
