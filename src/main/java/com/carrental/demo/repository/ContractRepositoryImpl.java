package com.carrental.demo.repository;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ExecutionException;
import java.util.stream.Collectors;

import org.springframework.stereotype.Repository;

import com.carrental.demo.model.Contract;
import com.google.cloud.firestore.CollectionReference;
import com.google.cloud.firestore.DocumentReference;
import com.google.cloud.firestore.DocumentSnapshot;
import com.google.cloud.firestore.Firestore;
import com.google.firebase.cloud.FirestoreClient;

@Repository
public class ContractRepositoryImpl {
	public void saveContract(Contract contract) {
		Firestore firestore = FirestoreClient.getFirestore();
		firestore.collection("contract").document(contract.getId()).set(contract);
	}

	public Contract getContractById(String id) throws ExecutionException, InterruptedException {
		Firestore firestore = FirestoreClient.getFirestore();
		DocumentSnapshot document = firestore.collection("contract").document(id).get().get();

		if (document.exists()) {
			return document.toObject(Contract.class);
		}

		return null;
	}

	public List<Contract> getAllContracts() throws ExecutionException, InterruptedException {
		Firestore firestore = FirestoreClient.getFirestore();
		CollectionReference contractCollection = firestore.collection("contract");

		return contractCollection.get().get().getDocuments().stream().map(document -> document.toObject(Contract.class))
				.collect(Collectors.toList());
	}
	
	public void editStatusContractById(String id, String newStatus) {
        Firestore firestore = FirestoreClient.getFirestore();
        DocumentReference documentRef = firestore.collection("contract").document(id);

        Map<String, Object> updates = new HashMap<>();
        updates.put("status", newStatus);

        documentRef.update(updates);
    }
}
