package com.carrental.demo.controller;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;

import java.util.UUID;
import java.util.concurrent.ExecutionException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;

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
import com.carrental.demo.repository.CarRepositoryImpl;
import com.carrental.demo.service.CarService;

import org.springframework.ui.Model;
import jakarta.servlet.ServletContext;
import jakarta.servlet.http.HttpSession;

@Controller
@RequestMapping("/")
public class CarController {
	
	@Autowired
	private CarRepositoryImpl carRepositoryImpl;
	
	
	@Autowired
	private ServletContext servletContext;
	
	
	@GetMapping("add-car")
    public String addCarForm(Model model, HttpSession session){
		if (session.getAttribute("userSession") != null) {
			Account loggedInUser = (Account) session.getAttribute("userSession");
			if (loggedInUser.getRole().equals("admin")) {
				model.addAttribute("nameFunction", "Xe");
				return "./car/add-car";
				
			} else {
				return "redirect:/clientpage";
			}
		}
		return "./login";
    }
	
	@PostMapping("add-car-post")
	public String addCar(@ModelAttribute("car") Car car, @RequestParam("file") MultipartFile file) throws IOException, ExecutionException, InterruptedException {
		String uniqueID = UUID.randomUUID().toString();
		// Lưu file vào thư mục trong storage của firebase
		String nameFile = file.getOriginalFilename();
		carRepositoryImpl.uploadCarImage(file, uniqueID);
		
		car.setImage("https://firebasestorage.googleapis.com/v0/b/thyan-b9327.appspot.com/o/car%2F" + uniqueID + "?alt=media");
		car.setId(uniqueID);
		carRepositoryImpl.saveCar(car);	
		return "redirect:/list-car";
	}
	
	@GetMapping("/edit-car/{id}")
	public String showEditForm(@PathVariable("id") String idCar, Model model, HttpSession session) throws ExecutionException, InterruptedException {
		if (session.getAttribute("userSession") != null) {
			Account loggedInUser = (Account) session.getAttribute("userSession");
			if (loggedInUser.getRole().equals("admin")) {
				Car car = carRepositoryImpl.getCarById(idCar);
				
				// Đưa xe vào model để hiển thị trong biểu mẫu
				model.addAttribute("car", car);	
				model.addAttribute("nameFunction", "Xe");
				return "./car/edit-car";
				
			} else {
				return "redirect:/clientpage";
			}
		}
		return "./login";
	}
	
	@PostMapping("/edit-car/{id}/edit")
	public String editCar(@ModelAttribute("car") Car car, @RequestParam("file") MultipartFile file) throws IOException, ExecutionException, InterruptedException {
		String nameFile = file.getOriginalFilename();
		if(!(nameFile.isEmpty())) {
			carRepositoryImpl.uploadCarImage(file, car.getId());
			car.setImage("https://firebasestorage.googleapis.com/v0/b/thyan-b9327.appspot.com/o/car%2F" + car.getId() + "?alt=media");
		}else
		{
			car.setImage(car.getImage());
		}
		carRepositoryImpl.saveCar(car);
		return "redirect:/edit-car/{id}";
	}
	
	@RequestMapping(value = "/list-car", method = RequestMethod.GET)
	public String listCar(Model model, HttpSession session) throws ExecutionException, InterruptedException {
		if (session.getAttribute("userSession") != null) {
			Account loggedInUser = (Account) session.getAttribute("userSession");
			if (loggedInUser.getRole().equals("admin")) {
				List<Car> listCar = new ArrayList<Car>();
				listCar = carRepositoryImpl.getAllCars();
				model.addAttribute("listCar", listCar);
				model.addAttribute("nameFunction", "Xe");
				return "car/car-list";
				
			} else {
				return "redirect:/clientpage";
			}
		}
		return "./login";	
	}
	
	
	private String saveImageInProject(MultipartFile file) {
		// Lưu file vào thư mục trong project
		String uploadDir = servletContext.getRealPath("/template/admin/upload/");
		String fileName = file.getOriginalFilename();
		String filePath = uploadDir + File.separator + fileName;
		System.out.println(uploadDir);
		try {
			byte[] bytes = file.getBytes();
			Path path = Paths.get(filePath);	
			Files.write(path, bytes);
		} catch (IOException e) {
			e.printStackTrace();
		}
		return filePath;
	}
}
