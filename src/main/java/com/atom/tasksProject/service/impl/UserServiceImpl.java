package com.atom.tasksProject.service.impl;

import com.atom.tasksProject.service.UserService;
import com.google.api.core.ApiFuture;
import com.google.cloud.firestore.*;
import com.google.firebase.cloud.FirestoreClient;
import org.springframework.stereotype.Service;

import java.util.*;
import java.util.concurrent.ExecutionException;

@Service
public class UserServiceImpl implements UserService {

    private static final String COLLECTION_NAME = "users";

    /**
     * 🔹 Obtener todos los usuarios
     * @return
     * @throws ExecutionException
     * @throws InterruptedException
     */
    public List<String> getAllUsers() throws ExecutionException, InterruptedException {
        Firestore db = FirestoreClient.getFirestore();
        CollectionReference users = db.collection(COLLECTION_NAME);

        ApiFuture<QuerySnapshot> querySnapshot = users.get();
        List<String> userList = new ArrayList<>();

        for (DocumentSnapshot document : querySnapshot.get().getDocuments()) {
            userList.add(document.getString("email"));
        }
        return userList;
    }

    /**
     * 🔹 Obtener un usuario por su email
     * @param email
     * @return
     * @throws ExecutionException
     * @throws InterruptedException
     */
    public boolean existUser(String email) throws ExecutionException, InterruptedException {
        Firestore db = FirestoreClient.getFirestore();
        CollectionReference usersRef = db.collection(COLLECTION_NAME);
        Query query = usersRef.whereEqualTo("email", email);
        ApiFuture<QuerySnapshot> querySnapshot = query.get();

        List<QueryDocumentSnapshot> documents = querySnapshot.get().getDocuments();
        return !documents.isEmpty();
    }

    /**
     * 🔹 Agregar un usuario
     * @param email identificador unico del usuario
     * @return
     * @throws ExecutionException
     * @throws InterruptedException
     */
    public void addUser(String email) throws ExecutionException, InterruptedException {
        Firestore db = FirestoreClient.getFirestore();
        DocumentReference docRef = db.collection(COLLECTION_NAME).document(String.valueOf(UUID.randomUUID().toString()));

        Map<String, Object> user = new HashMap<>();
        user.put("email", email);

        // Guardar en Firestore
        ApiFuture<WriteResult> future = docRef.set(user);

        // Esperar a que se complete la operación
        future.get();
    }
}

