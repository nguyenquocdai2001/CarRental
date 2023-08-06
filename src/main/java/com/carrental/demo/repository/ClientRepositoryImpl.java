package com.carrental.demo.repository;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.concurrent.ExecutionException;
import java.util.stream.Collectors;

import org.springframework.stereotype.Repository;

import com.carrental.demo.model.Client;
import com.google.cloud.firestore.CollectionReference;
import com.google.cloud.firestore.DocumentSnapshot;
import com.google.cloud.firestore.Firestore;
import com.google.cloud.firestore.Query;
import com.google.cloud.firestore.QuerySnapshot;
import com.google.firebase.cloud.FirestoreClient;

@Repository
public class ClientRepositoryImpl {
	
	public void saveClient(Client client) throws InterruptedException, ExecutionException {
		Firestore dbFirestore = FirestoreClient.getFirestore();
		dbFirestore.collection("client").document(client.getId()).set(client);
	}

	public Optional<Client> getClientByEmail(String email) throws ExecutionException, InterruptedException {
		Firestore firestore = FirestoreClient.getFirestore();
        CollectionReference clientCollection = firestore.collection("client");

        Query query = clientCollection.whereEqualTo("email", email);
        QuerySnapshot querySnapshot = query.get().get();

        if (!querySnapshot.isEmpty()) {
            DocumentSnapshot document = querySnapshot.getDocuments().get(0);
            Client client = document.toObject(Client.class);
            return Optional.ofNullable(client);
        }

        return Optional.empty();
	}
	
	public Client getClientyId(String id) throws ExecutionException, InterruptedException {
        Firestore firestore = FirestoreClient.getFirestore();
        DocumentSnapshot document = firestore.collection("client").document(id).get().get();

        if (document.exists()) {
            return document.toObject(Client.class);
        }

        return null;
    }


	public List<Client> getAllClients() throws ExecutionException, InterruptedException {
		 Firestore firestore = FirestoreClient.getFirestore();
	        CollectionReference usersCollection = firestore.collection("client");

	        return usersCollection.get().get().getDocuments().stream()
	                .map(document -> document.toObject(Client.class))
	                .collect(Collectors.toList());
	}
	
	public List<Client> getAllClientsExceptOneByEmail(String email) throws ExecutionException, InterruptedException {
        Firestore firestore = FirestoreClient.getFirestore();
        CollectionReference clientCollection = firestore.collection("client");

        List<Client> clientList = new ArrayList<>();
        clientCollection
                .get()
                .get()
                .forEach(documentSnapshot -> {
                    Client client = documentSnapshot.toObject(Client.class);
                    if (!client.getEmail().equals(email)) {
                        clientList.add(client);
                    }
                });

        return clientList;
    }
}
