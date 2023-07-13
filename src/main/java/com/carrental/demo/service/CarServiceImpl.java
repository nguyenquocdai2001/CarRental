package com.carrental.demo.service;

import org.springframework.beans.factory.annotation.Autowired;

import com.carrental.demo.model.Account;
import com.carrental.demo.model.Car;
import com.carrental.demo.repository.AccountRepository;
import com.carrental.demo.repository.CarRepository;

public class CarServiceImpl implements CarService{
	 @Autowired
	    private  CarRepository carRepository;

	    @Override
	    public void addCar(Car car) {
	    	carRepository.insert(car);
	    }
}
