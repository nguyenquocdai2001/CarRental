package com.carrental.demo.repository;

import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.math.BigDecimal;
import java.math.RoundingMode;
import java.text.NumberFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.Date;
import java.util.List;
import java.util.Locale;
import java.util.concurrent.ExecutionException;
import java.util.regex.Pattern;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.web.multipart.MultipartFile;

import com.carrental.demo.model.Bill;
import com.google.auth.oauth2.GoogleCredentials;
import com.google.cloud.firestore.CollectionReference;
import com.google.cloud.firestore.DocumentSnapshot;
import com.google.cloud.firestore.Firestore;
import com.google.cloud.storage.Blob;
import com.google.cloud.storage.BlobId;
import com.google.cloud.storage.BlobInfo;
import com.google.cloud.storage.Storage;
import com.google.cloud.storage.StorageOptions;
import com.google.firebase.FirebaseApp;
import com.google.firebase.cloud.FirestoreClient;
import com.google.cloud.firestore.QueryDocumentSnapshot;
import com.google.cloud.firestore.QuerySnapshot;
import com.google.api.core.ApiFuture;
import com.google.api.gax.paging.Page;

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
	
	public String uploadBillImage(MultipartFile imageFile, String idBill) throws IOException, ExecutionException, InterruptedException {
	 	String imageName = imageFile.getOriginalFilename();
	 	
	 	 // Load Google Cloud Credentials
        InputStream credentialsStream = new FileInputStream("serviceAccount.json"); // Thay thế đúng đường dẫn tới file credentials JSON
        GoogleCredentials credentials = GoogleCredentials.fromStream(credentialsStream);

        // Initialize Storage
        Storage s = StorageOptions.newBuilder().setProjectId("thyan-b9327").setCredentials(credentials).build().getService();
       
        FirebaseApp.getInstance();
        
	 	//s = StorageOptions.getDefaultInstance().getService();
        BlobId blobId = BlobId.of("thyan-b9327.appspot.com", "bill/" + idBill); // Đổi tên bucket
        BlobInfo blobInfo = BlobInfo.newBuilder(blobId).setContentType(imageFile.getContentType()).build();
        

        // Upload hình ảnh lên Firestore Storage
        byte[] imageBytes = imageFile.getBytes();
        Blob blob = s.create(blobInfo, imageBytes);

        // Lấy URL của hình ảnh trong Firestore Storage
        String imageLink = blob.getMediaLink();
        return imageLink;
	}
	
	 public List<Bill> getBillsByYearMonth(String yearMonth) throws ExecutionException, InterruptedException, ParseException {
	        List<Bill> result = new ArrayList<>();
	        
	        Firestore firestore = FirestoreClient.getFirestore();
	        CollectionReference invoicesCollection = firestore.collection("bill");
	        ApiFuture<QuerySnapshot> querySnapshot = invoicesCollection.get();
	        for (DocumentSnapshot document : querySnapshot.get().getDocuments()) {
	        	Bill bill = document.toObject(Bill.class);

	            String invoiceYearMonth = bill.getDate().substring(3, 10); // Lấy YYYY/MM từ date
	            if (invoiceYearMonth.equals(yearMonth)) {
	                result.add(bill);
	            }
	        }

	        return result;
	    }
	 
	 public Date parseDateString(String dateString) throws ParseException {
	        SimpleDateFormat dateFormat = new SimpleDateFormat("dd-MM-yyyy");
	        return dateFormat.parse(dateString);
	    }
	 
	 public String calculateTotalPaymentByMonthYear(String monthYear) throws ExecutionException, InterruptedException {
	        String[] parts = monthYear.split("/");
	        int month = Integer.parseInt(parts[0]);
	        int year = Integer.parseInt(parts[1]);
	        
	        List<Bill> invoices = getAllBills();
	        BigDecimal totalPayment = BigDecimal.ZERO;

	        for (Bill invoice : invoices) {
	            String[] invoiceParts = invoice.getDate().split("/");
	            int invoiceMonth = Integer.parseInt(invoiceParts[1]);
	            int invoiceYear = Integer.parseInt(invoiceParts[2]);

	            if (invoiceMonth == month && invoiceYear == year) {
	                BigDecimal invoiceAmount = new BigDecimal(invoice.getTotal_price());
	                totalPayment = totalPayment.add(invoiceAmount);
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
	
}
