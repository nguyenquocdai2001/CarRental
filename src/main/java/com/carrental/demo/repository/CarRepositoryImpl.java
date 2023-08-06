package com.carrental.demo.repository;

import java.util.List;
import java.util.concurrent.ExecutionException;
import java.util.stream.Collectors;

import org.springframework.stereotype.Repository;

import com.carrental.demo.model.Car;

import com.google.cloud.firestore.CollectionReference;
import com.google.cloud.firestore.DocumentSnapshot;
import com.google.cloud.firestore.Firestore;
import com.google.firebase.cloud.FirestoreClient;

@Repository
public class CarRepositoryImpl {
	public void saveCar(Car car) {
		Firestore firestore = FirestoreClient.getFirestore();
		firestore.collection("car").document(car.getId()).set(car);
	}

	public Car getCarById(String id) throws ExecutionException, InterruptedException {
		Firestore firestore = FirestoreClient.getFirestore();
		DocumentSnapshot document = firestore.collection("car").document(id).get().get();

		if (document.exists()) {
			return document.toObject(Car.class);
		}

		return null;
	}
	
	public List<Car> getAllCars() throws ExecutionException, InterruptedException {
		 Firestore firestore = FirestoreClient.getFirestore();
	        CollectionReference carsCollection = firestore.collection("car");

	        return carsCollection.get().get().getDocuments().stream()
	                .map(document -> document.toObject(Car.class))
	                .collect(Collectors.toList());
	}
}
