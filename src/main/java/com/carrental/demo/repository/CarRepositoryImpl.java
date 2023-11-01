package com.carrental.demo.repository;

import java.io.IOException;
import java.util.List;
import java.util.concurrent.ExecutionException;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.web.multipart.MultipartFile;

import com.carrental.demo.model.Car;
import com.google.api.client.util.Value;
import com.google.auth.oauth2.GoogleCredentials;
import com.google.cloud.firestore.CollectionReference;

import com.google.cloud.firestore.DocumentSnapshot;
import com.google.cloud.firestore.Firestore;
import com.google.firebase.FirebaseApp;

import com.google.firebase.cloud.FirestoreClient;

import com.google.cloud.storage.Blob;
import com.google.cloud.storage.BlobId;
import com.google.cloud.storage.BlobInfo;

import com.google.cloud.storage.Storage;
import com.google.cloud.storage.StorageOptions;

import java.io.FileInputStream;
import java.io.InputStream;





@Repository
public class CarRepositoryImpl {
	private final Storage storage; // Inject Storage
	
	@Autowired
    public CarRepositoryImpl(Storage storage) {
        this.storage = storage;
    }
	
	@Value("${/demo/serviceAccount.json}")
    private String firebaseCredentialsPath;

    
	public void saveCar(Car car) {
		Firestore firestore = FirestoreClient.getFirestore();
		firestore.collection("car").document(car.getId()).set(car);
	}

	public Car getCarById(String id) throws ExecutionException, InterruptedException {
		Firestore firestore = FirestoreClient.getFirestore();
		DocumentSnapshot document = firestore.collection("car").document(id).get().get();

		if (document.exists()) {
			return document.toObject(Car.class);
		}

		return null;
	}
	
	public List<Car> getAllCars() throws ExecutionException, InterruptedException {
		 Firestore firestore = FirestoreClient.getFirestore();
	        CollectionReference carsCollection = firestore.collection("car");

	        return carsCollection.get().get().getDocuments().stream()
	                .map(document -> document.toObject(Car.class))
	                .collect(Collectors.toList());
	}
	
	public String uploadCarImage(MultipartFile imageFile, String idCar) throws IOException, ExecutionException, InterruptedException {
		 	String imageName = imageFile.getOriginalFilename();
		 	
		 	 // Load Google Cloud Credentials
	        InputStream credentialsStream = new FileInputStream("serviceAccount.json"); // Thay thế đúng đường dẫn tới file credentials JSON
	        GoogleCredentials credentials = GoogleCredentials.fromStream(credentialsStream);

	        // Initialize Storage
	        Storage s = StorageOptions.newBuilder().setProjectId("thyan-b9327").setCredentials(credentials).build().getService();
	       
            FirebaseApp.getInstance();
	        
		 	//s = StorageOptions.getDefaultInstance().getService();
	        BlobId blobId = BlobId.of("thyan-b9327.appspot.com", "car/" + idCar); // Đổi tên bucket
	        BlobInfo blobInfo = BlobInfo.newBuilder(blobId).setContentType(imageFile.getContentType()).build();
	        

	        // Upload hình ảnh lên Firestore Storage
	        byte[] imageBytes = imageFile.getBytes();
	        Blob blob = s.create(blobInfo, imageBytes);

	        // Lấy URL của hình ảnh trong Firestore Storage
	        String imageLink = blob.getMediaLink();
	        return imageLink;
    }
	
}
