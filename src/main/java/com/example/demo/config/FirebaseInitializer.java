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

            FirebaseOptions options;

            // Try to get credentials from environment variable
            String credentialsJson = System.getenv("GOOGLE_CREDENTIALS");

            if (credentialsJson != null && !credentialsJson.isEmpty()) {
                // Render or cloud environment
                InputStream credentialsStream = new ByteArrayInputStream(credentialsJson.getBytes());

                options = FirebaseOptions.builder()
                    .setCredentials(GoogleCredentials.fromStream(credentialsStream))
                    .setDatabaseUrl(System.getenv("FIREBASE_DB_URL"))
                    .build();

                System.out.println(" Firebase initialized from environment variable.");
            } else {
                // Local development
                FileInputStream serviceAccount = new FileInputStream(
                    "src/main/resources/firebase/serviceAccountKey.json"
                );

                options = FirebaseOptions.builder()
                    .setCredentials(GoogleCredentials.fromStream(serviceAccount))
                    .setDatabaseUrl(System.getenv("FIREBASE_DB_URL"))
                    .build();

                System.out.println(" Firebase initialized from local file.");
            }

            FirebaseApp.initializeApp(options);

        } else {
            System.out.println(" Firebase already initialized.");
        }
    } catch (IOException e) {
        e.printStackTrace();
    }
}
               
