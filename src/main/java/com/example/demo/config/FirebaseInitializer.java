package com.example.demo.config;

import com.google.auth.oauth2.GoogleCredentials;
import com.google.firebase.FirebaseApp;
import com.google.firebase.FirebaseOptions;
import org.springframework.stereotype.Service;
import javax.annotation.PostConstruct;
import java.io.FileInputStream;
import java.io.IOException;

@Service
public class FirebaseInitializer {

	@PostConstruct
	public void initialize() {
		try {

			if (FirebaseApp.getApps().isEmpty()) {
				FileInputStream serviceAccount = new FileInputStream(
						"src/main/resources/firebase/serviceAccountKey.json");

				FirebaseOptions options = FirebaseOptions.builder()
						.setCredentials(GoogleCredentials.fromStream(serviceAccount))
						.setDatabaseUrl("FIREBASE_DB_URL").build();

				FirebaseApp.initializeApp(options);
				System.out.println(" Firebase Initialized.");
			} else {
				System.out.println("Firebase already initialized.");
			}
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
}
