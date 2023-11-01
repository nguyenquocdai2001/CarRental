package com.carrental.demo.repository;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ExecutionException;
import java.util.stream.Collectors;

import org.springframework.stereotype.Repository;

import com.carrental.demo.model.PreOrder;
import com.google.api.core.ApiFuture;
import com.google.cloud.firestore.CollectionReference;
import com.google.cloud.firestore.DocumentReference;
import com.google.cloud.firestore.DocumentSnapshot;
import com.google.cloud.firestore.Firestore;
import com.google.cloud.firestore.Query;
import com.google.cloud.firestore.QueryDocumentSnapshot;
import com.google.cloud.firestore.QuerySnapshot;
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
	
	 public String formatDate(String inputDate) {
	        DateTimeFormatter inputFormatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
	        DateTimeFormatter outputFormatter = DateTimeFormatter.ofPattern("dd/MM/yyyy");
	        
	        LocalDate date = LocalDate.parse(inputDate, inputFormatter);
	        String formattedDate = date.format(outputFormatter);
	        
	        return formattedDate;
	    }
	 
	 public String getIdCar(String name_car) throws ExecutionException, InterruptedException {
	        Firestore firestore = FirestoreClient.getFirestore();

	        // Tạo truy vấn dựa vào name_car
	        Query query = firestore.collection("car").whereEqualTo("name", name_car);
	        ApiFuture<QuerySnapshot> querySnapshot = query.get();

	        // Lấy kết quả truy vấn
	        for (QueryDocumentSnapshot document : querySnapshot.get().getDocuments()) {
	            return document.getId();
	        }

	        return null; // Không tìm thấy
	    }
}
