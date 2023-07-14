package com.carrental.demo.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.ui.Model;

import com.carrental.demo.model.Account;
import com.carrental.demo.model.Car;
import com.carrental.demo.repository.AccountRepository;
import com.carrental.demo.repository.CarRepository;
import com.carrental.demo.validator.Message;

public class CarServiceImpl implements CarService{
	 @Autowired
	 private  CarRepository carRepository;
	 
}
