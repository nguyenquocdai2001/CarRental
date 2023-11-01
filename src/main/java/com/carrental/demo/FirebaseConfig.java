package com.carrental.demo;


import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import com.google.api.client.util.Value;


import com.google.cloud.storage.Storage;
import com.google.cloud.storage.StorageOptions;

@Configuration
public class FirebaseConfig {
	@Value("${serviceAccount.json}")
	private String firebaseCredentialsPath;


	@Bean
    public Storage storage() {
        return StorageOptions.getDefaultInstance().getService();
    }
}
