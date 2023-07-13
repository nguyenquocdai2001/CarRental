package com.carrental.demo.controller;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.ModelAndView;

import com.carrental.demo.model.Car;
import com.carrental.demo.repository.CarRepository;

import org.springframework.ui.Model;
import jakarta.servlet.ServletContext;

@Controller
@RequestMapping("/")
public class CarController {
	@Autowired
	private CarRepository carRepository;
	
	@Autowired
	private ServletContext servletContext;
	
	@GetMapping("add-car")
    public String addCarForm(){
        return "./car/add-car";
    }
	
	@PostMapping("add-car-post")
	public String addCar(@ModelAttribute("car") Car car, @RequestParam("file") MultipartFile file) {
		// Lưu file vào thư mục trong project
		String nameFile = file.getOriginalFilename();
		saveImageInProject(file);
		car.setImage(nameFile);
		carRepository.insert(car);
		return "redirect:/add-car";
	}
	
	@GetMapping("/edit-car/{id}")
	public ModelAndView showEditForm(@PathVariable("id") String idCar, Model model) {
		Optional<Car> car = carRepository.findById(idCar);
		
		// Đưa xe vào model để hiển thị trong biểu mẫu
		model.addAttribute("car", car.get());
		
		ModelAndView mav = new ModelAndView("./car/edit-car");	
		return mav;
	}
	
	@PostMapping("/edit-car/{id}/edit")
	public String editProduct(@ModelAttribute("car") Car car, @RequestParam("file") MultipartFile file) {
		String nameFile = file.getOriginalFilename();
		if(!(nameFile.isEmpty())) {
			saveImageInProject(file);
			car.setImage(nameFile);
		}else
		{
			car.setImage(car.getImage());
		}
		carRepository.save(car);
		return "redirect:/list-car";
	}
	
	@RequestMapping(value = "/list-car", method = RequestMethod.GET)
	public ModelAndView productPage(ModelMap modelmap) {
		List<Car> listCar = new ArrayList<Car>();
		listCar = carRepository.findAll();

		modelmap.addAttribute("listCar", listCar);
		ModelAndView mav = new ModelAndView("car/car-list");
		return mav;
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
