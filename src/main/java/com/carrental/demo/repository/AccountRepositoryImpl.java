package com.carrental.demo.repository;

import java.util.Optional;
import java.util.UUID;
import java.util.concurrent.ExecutionException;

import org.springframework.stereotype.Repository;

import com.carrental.demo.model.Account;
import com.google.cloud.firestore.CollectionReference;
import com.google.cloud.firestore.DocumentSnapshot;
import com.google.cloud.firestore.Firestore;
import com.google.cloud.firestore.Query;
import com.google.cloud.firestore.QuerySnapshot;
import com.google.firebase.cloud.FirestoreClient;

@Repository
public class AccountRepositoryImpl {
	public void saveAccount(Account account) throws InterruptedException, ExecutionException {
		Firestore dbFirestore = FirestoreClient.getFirestore();
		dbFirestore.collection("account").document(account.getId()).set(account);
	}

	public Optional<Account> getAccountByEmail(String email) throws ExecutionException, InterruptedException {

		Firestore firestore = FirestoreClient.getFirestore();
		CollectionReference accountCollection = firestore.collection("account");

		Query query = accountCollection.whereEqualTo("email", email);
		QuerySnapshot querySnapshot = query.get().get();

		if (!querySnapshot.isEmpty()) {
			DocumentSnapshot document = querySnapshot.getDocuments().get(0);
			Account account = document.toObject(Account.class);
			return Optional.ofNullable(account);
		}

		return Optional.empty();
	}
}
