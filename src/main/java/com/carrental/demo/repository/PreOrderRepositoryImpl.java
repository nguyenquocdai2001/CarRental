package com.carrental.demo.repository;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ExecutionException;
import java.util.stream.Collectors;

import org.springframework.stereotype.Repository;

import com.carrental.demo.model.PreOrder;
import com.google.cloud.firestore.CollectionReference;
import com.google.cloud.firestore.DocumentReference;
import com.google.cloud.firestore.DocumentSnapshot;
import com.google.cloud.firestore.Firestore;
import com.google.firebase.cloud.FirestoreClient;

@Repository
public class PreOrderRepositoryImpl {
	public void savePreOrder(PreOrder preOrder) {
		Firestore firestore = FirestoreClient.getFirestore();
		firestore.collection("preOrder").document(preOrder.getId()).set(preOrder);
	}

	public PreOrder getPreOrderById(String id) throws ExecutionException, InterruptedException {
		Firestore firestore = FirestoreClient.getFirestore();
		DocumentSnapshot document = firestore.collection("preOrder").document(id).get().get();

		if (document.exists()) {
			return document.toObject(PreOrder.class);
		}

		return null;
	}

	public List<PreOrder> getAllPreOrders() throws ExecutionException, InterruptedException {
		Firestore firestore = FirestoreClient.getFirestore();
		CollectionReference preOrdersCollection = firestore.collection("preOrder");

		return preOrdersCollection.get().get().getDocuments().stream().map(document -> document.toObject(PreOrder.class))
				.collect(Collectors.toList());
	}
	
	public void editStatusPreOrderById(String id, String newStatus) {
        Firestore firestore = FirestoreClient.getFirestore();
        DocumentReference documentRef = firestore.collection("preOrder").document(id);

        Map<String, Object> updates = new HashMap<>();
        updates.put("status", newStatus);

        documentRef.update(updates);
    }
}
