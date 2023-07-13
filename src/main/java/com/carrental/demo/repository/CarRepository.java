package com.carrental.demo.repository;

import java.util.Optional;

import org.springframework.data.mongodb.repository.MongoRepository;

import com.carrental.demo.model.Car;

public interface CarRepository extends MongoRepository<Car, String>{
	Optional<Car> findById(String id);
}