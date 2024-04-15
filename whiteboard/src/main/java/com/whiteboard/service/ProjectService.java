package com.whiteboard.service;

import com.google.api.core.ApiFuture;
import com.google.cloud.firestore.DocumentReference;
import com.google.cloud.firestore.DocumentSnapshot;
import com.google.cloud.firestore.Firestore;
import com.google.firebase.cloud.FirestoreClient;
import com.google.gson.Gson;
import com.whiteboard.entity.Project;
import com.whiteboard.entity.Stroke;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.concurrent.ExecutionException;

@Service
public class ProjectService {

    private static final String COLLECTION_NAME = "project";
    private static Firestore firestore;

    private static Firestore getFirestore() {
        if (firestore == null) {
            firestore = FirestoreClient.getFirestore();
        }
        return firestore;
    }

    public String saveProject(Project project) throws ExecutionException, InterruptedException {

        Firestore firestore = getFirestore();
        ApiFuture<DocumentReference> documentReferenceApiFuture  = firestore.collection(COLLECTION_NAME).add(project);

        String documentId = documentReferenceApiFuture.get().getId();

        return documentId;
    }

    public Project getProject(String id) throws ExecutionException, InterruptedException {

        Firestore firestore = getFirestore();

        // Create a DocumentReference for the specified document ID
        DocumentReference documentReference = firestore.collection(COLLECTION_NAME).document(id);

        // Get the document snapshot using the DocumentReference
        ApiFuture<DocumentSnapshot> documentSnapshotApiFuture = documentReference.get();

        // Get the document snapshot from the future
        DocumentSnapshot documentSnapshot = documentSnapshotApiFuture.get();

        // Check if the document exists
        if (documentSnapshot.exists()) {
            // Convert the document snapshot to a Project object
            Project project = documentSnapshot.toObject(Project.class);
            return project;
        } else {
            // Document does not exist
            return null;
        }
    }

    public List<Stroke> updateProjectContent(String id, Project project) {

        Firestore firestore = getFirestore();

        // Update project content in Firestore
        firestore.collection("project")
                .document(id)
                .update("content", project.getContent());

        return project.getContent();
    }
}
