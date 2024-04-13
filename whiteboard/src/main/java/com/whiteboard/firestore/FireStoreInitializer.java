package com.whiteboard.firestore;

import com.google.auth.oauth2.GoogleCredentials;
import com.google.firebase.FirebaseApp;
import com.google.firebase.FirebaseOptions;
import org.springframework.stereotype.Service;

import javax.annotation.PostConstruct;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Arrays;
import java.util.List;

@Service
public class FireStoreInitializer {

    private final List<String> possiblePaths = Arrays.asList(
            "serviceAccountKey.json", // Relative path
            "app/serviceAccountKey.json" // Absolute path within the Docker container
    );

    @PostConstruct
    public void initialization() {
        FileInputStream serviceAccount = null;
        for (String path : possiblePaths) {
            try {
                serviceAccount = new FileInputStream(path);
                break; // Found the key file, no need to check further
            } catch (FileNotFoundException e) {
                // Key file not found in this path, try the next one
            }
        }

        if (serviceAccount == null) {
            System.err.println("Service account key file not found in any of the specified locations.");
            return;
        }

        try {
            FirebaseOptions options = FirebaseOptions.builder()
                    .setCredentials(GoogleCredentials.fromStream(serviceAccount))
                    .build();

            FirebaseApp.initializeApp(options);
        } catch (IOException e) {
            System.err.println("Error initializing Firebase: " + e.getMessage());
        } finally {
            if (serviceAccount != null) {
                try {
                    serviceAccount.close();
                } catch (IOException e) {
                    System.err.println("Error closing service account key file: " + e.getMessage());
                }
            }
        }
    }
}
