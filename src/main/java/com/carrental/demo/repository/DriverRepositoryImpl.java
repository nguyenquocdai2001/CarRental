package com.carrental.demo.repository;

import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.List;
import java.util.concurrent.ExecutionException;
import java.util.stream.Collectors;

import org.springframework.stereotype.Repository;
import org.springframework.web.multipart.MultipartFile;

import com.carrental.demo.model.Driver;
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
	
	public String uploadDriverImage(MultipartFile imageFile, String idDriver) throws IOException, ExecutionException, InterruptedException {
	 	String imageName = imageFile.getOriginalFilename();
	 	
	 	 // Load Google Cloud Credentials
        InputStream credentialsStream = new FileInputStream("serviceAccount.json"); // Thay thế đúng đường dẫn tới file credentials JSON
        GoogleCredentials credentials = GoogleCredentials.fromStream(credentialsStream);

        // Initialize Storage
        Storage s = StorageOptions.newBuilder().setProjectId("thyan-b9327").setCredentials(credentials).build().getService();
       
        FirebaseApp.getInstance();
        
	 	//s = StorageOptions.getDefaultInstance().getService();
        BlobId blobId = BlobId.of("thyan-b9327.appspot.com", "driver/" + idDriver); // Đổi tên bucket
        BlobInfo blobInfo = BlobInfo.newBuilder(blobId).setContentType(imageFile.getContentType()).build();
        

        // Upload hình ảnh lên Firestore Storage
        byte[] imageBytes = imageFile.getBytes();
        Blob blob = s.create(blobInfo, imageBytes);

        // Lấy URL của hình ảnh trong Firestore Storage
        String imageLink = blob.getMediaLink();
        return imageLink;
}


}
