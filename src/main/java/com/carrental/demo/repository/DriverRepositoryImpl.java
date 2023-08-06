package com.carrental.demo.repository;

import java.util.List;
import java.util.concurrent.ExecutionException;
import java.util.stream.Collectors;

import org.springframework.stereotype.Repository;


import com.carrental.demo.model.Driver;
import com.google.cloud.firestore.CollectionReference;
import com.google.cloud.firestore.DocumentSnapshot;
import com.google.cloud.firestore.Firestore;
import com.google.firebase.cloud.FirestoreClient;

@Repository
public class DriverRepositoryImpl {
	public void saveDriver(Driver driver) {
		Firestore firestore = FirestoreClient.getFirestore();
		firestore.collection("driver").document(driver.getId()).set(driver);
	}
	
	public Driver getDriverById(String id) throws ExecutionException, InterruptedException {
		Firestore firestore = FirestoreClient.getFirestore();
		DocumentSnapshot document = firestore.collection("driver").document(id).get().get();

		if (document.exists()) {
			return document.toObject(Driver.class);
		}

		return null;
	}
	
	public List<Driver> getAllDrivers() throws ExecutionException, InterruptedException {
		 Firestore firestore = FirestoreClient.getFirestore();
	        CollectionReference driversCollection = firestore.collection("driver");

	        return driversCollection.get().get().getDocuments().stream()
	                .map(document -> document.toObject(Driver.class))
	                .collect(Collectors.toList());
	}

}
