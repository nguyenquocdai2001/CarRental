package com.carrental.demo.repository;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.text.NumberFormat;
import java.text.ParseException;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;
import java.util.Locale;
import java.util.concurrent.ExecutionException;
import java.util.stream.Collectors;

import org.springframework.stereotype.Repository;

import com.carrental.demo.model.Bill;
import com.carrental.demo.model.DriverPayOut;
import com.google.api.core.ApiFuture;
import com.google.cloud.firestore.CollectionReference;
import com.google.cloud.firestore.DocumentSnapshot;
import com.google.cloud.firestore.Firestore;
import com.google.cloud.firestore.QuerySnapshot;
import com.google.firebase.cloud.FirestoreClient;

@Repository
public class DriverPayOutRepositoryImpl {
	public void saveDriverPayOut(DriverPayOut driverPayOut) {
		Firestore firestore = FirestoreClient.getFirestore();
		firestore.collection("driverPayOut").document(driverPayOut.getId()).set(driverPayOut);
	}
	
	public DriverPayOut getDriverPayOutById(String id) throws ExecutionException, InterruptedException {
		Firestore firestore = FirestoreClient.getFirestore();
		DocumentSnapshot document = firestore.collection("driverPayOut").document(id).get().get();

		if (document.exists()) {
			return document.toObject(DriverPayOut.class);
		}

		return null;
	}
	
	public List<DriverPayOut> getAllDriverPayOuts() throws ExecutionException, InterruptedException {
		 Firestore firestore = FirestoreClient.getFirestore();
	        CollectionReference driverPayOutsCollection = firestore.collection("driverPayOut");

	        return driverPayOutsCollection.get().get().getDocuments().stream()
	                .map(document -> document.toObject(DriverPayOut.class))
	                .collect(Collectors.toList());
	}
	
	public List<DriverPayOut> getDriverPayOutsByYearMonth(String yearMonth) throws ExecutionException, InterruptedException, ParseException {
        List<DriverPayOut> result = new ArrayList<>();
        
        Firestore firestore = FirestoreClient.getFirestore();
        CollectionReference invoicesCollection = firestore.collection("driverPayOut");
        ApiFuture<QuerySnapshot> querySnapshot = invoicesCollection.get();
        for (DocumentSnapshot document : querySnapshot.get().getDocuments()) {
        	DriverPayOut driverPayOut = document.toObject(DriverPayOut.class);

            String driverPayOutYearMonth = driverPayOut.getDate().substring(3, 10); // Lấy YYYY/MM từ date
            if (driverPayOutYearMonth.equals(yearMonth)) {
                result.add(driverPayOut);
            }
        }

        return result;
    }
	
	public String calculateTotalPaymentByMonthYear(String monthYear) throws ExecutionException, InterruptedException {
        String[] parts = monthYear.split("/");
        int month = Integer.parseInt(parts[0]);
        int year = Integer.parseInt(parts[1]);
        
        List<DriverPayOut> payouts = getAllDriverPayOuts();
        BigDecimal totalPayment = BigDecimal.ZERO;

        for (DriverPayOut payout : payouts) {
            String[] payoutParts = payout.getDate().split("/");
            int payoutMonth = Integer.parseInt(payoutParts[1]);
            int payoutYear = Integer.parseInt(payoutParts[2]);

            if (payoutMonth == month && payoutYear == year) {
                BigDecimal payoutAmount = new BigDecimal(payout.getMoney());
                totalPayment = totalPayment.add(payoutAmount);
            }
        }
     // Optional: Làm tròn tổng tiền đến 2 chữ số thập phân
        totalPayment = totalPayment.setScale(2, RoundingMode.HALF_UP);
        
        NumberFormat numberFormat = NumberFormat.getNumberInstance(Locale.US);
        numberFormat.setGroupingUsed(true);
        String formattedSumTotalPrice = numberFormat.format(totalPayment);
        return formattedSumTotalPrice;
        
    }
	
	 public String formatDate(String inputDate) {
	        DateTimeFormatter inputFormatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
	        DateTimeFormatter outputFormatter = DateTimeFormatter.ofPattern("dd/MM/yyyy");
	        
	        LocalDate date = LocalDate.parse(inputDate, inputFormatter);
	        String formattedDate = date.format(outputFormatter);
	        
	        return formattedDate;
	    }
	 
	 public String getSumTotalPrice() throws ExecutionException, InterruptedException {
	        Firestore firestore = FirestoreClient.getFirestore();
	        CollectionReference incomeCollection = firestore.collection("driverPayOut");

	        BigDecimal sumTotalPrice = BigDecimal.ZERO;

	        for (DocumentSnapshot documentSnapshot : incomeCollection.get().get().getDocuments()) {
	        	DriverPayOut pay = documentSnapshot.toObject(DriverPayOut.class);
	            BigDecimal totalPrice = new BigDecimal(pay.getMoney());
	            sumTotalPrice = sumTotalPrice.add(totalPrice);
	        }
	        NumberFormat numberFormat = NumberFormat.getNumberInstance(Locale.US);
	        numberFormat.setGroupingUsed(true);
	        String formattedSumTotalPrice = numberFormat.format(sumTotalPrice);

	        return formattedSumTotalPrice;
	    }
}
