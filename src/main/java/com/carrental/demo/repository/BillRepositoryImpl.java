package com.carrental.demo.repository;

import java.math.BigDecimal;
import java.text.NumberFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.Date;
import java.util.List;
import java.util.Locale;
import java.util.concurrent.ExecutionException;
import java.util.stream.Collectors;

import org.springframework.stereotype.Repository;

import com.carrental.demo.model.Bill;
import com.google.cloud.firestore.CollectionReference;
import com.google.cloud.firestore.DocumentSnapshot;
import com.google.cloud.firestore.Firestore;
import com.google.firebase.cloud.FirestoreClient;
import java.util.concurrent.ExecutionException;

@Repository
public class BillRepositoryImpl {
	public void saveBill(Bill bill) {
		Firestore firestore = FirestoreClient.getFirestore();
		firestore.collection("bill").document(bill.getId()).set(bill);
	}

	public Bill getBillById(String id) throws ExecutionException, InterruptedException {
		Firestore firestore = FirestoreClient.getFirestore();
		DocumentSnapshot document = firestore.collection("bill").document(id).get().get();

		if (document.exists()) {
			return document.toObject(Bill.class);
		}

		return null;
	}

	public List<Bill> getAllBills() throws ExecutionException, InterruptedException {
		Firestore firestore = FirestoreClient.getFirestore();
		CollectionReference billCollection = firestore.collection("bill");

		return billCollection.get().get().getDocuments().stream().map(document -> document.toObject(Bill.class))
				.collect(Collectors.toList());
	}
	
	public List<Bill> getListBillSortByDate() throws ExecutionException, InterruptedException, ParseException  {
        Firestore firestore = FirestoreClient.getFirestore();
        CollectionReference billCollection = firestore.collection("bill");

        List<Bill> incomeList = new ArrayList<>();
        billCollection
                .get()
                .get()
                .forEach(documentSnapshot -> incomeList.add(documentSnapshot.toObject(Bill.class)));

     // Sắp xếp danh sách theo tháng và năm
        incomeList.sort(Comparator.comparing(Bill::getDate, Comparator.reverseOrder()));


        return incomeList;
    }
	
	public String getSumTotalPrice() throws ExecutionException, InterruptedException {
        Firestore firestore = FirestoreClient.getFirestore();
        CollectionReference incomeCollection = firestore.collection("bill");

        BigDecimal sumTotalPrice = BigDecimal.ZERO;

        for (DocumentSnapshot documentSnapshot : incomeCollection.get().get().getDocuments()) {
            Bill income = documentSnapshot.toObject(Bill.class);
            BigDecimal totalPrice = new BigDecimal(income.getTotal_price());
            sumTotalPrice = sumTotalPrice.add(totalPrice);
        }
        NumberFormat numberFormat = NumberFormat.getNumberInstance(Locale.US);
        numberFormat.setGroupingUsed(true);
        String formattedSumTotalPrice = numberFormat.format(sumTotalPrice);

        return formattedSumTotalPrice;
    }
	
	
}
