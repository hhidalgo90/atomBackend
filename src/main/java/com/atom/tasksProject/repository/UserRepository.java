package com.atom.tasksProject.repository;

import com.google.api.core.ApiFuture;
import com.google.cloud.firestore.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.*;
import java.util.concurrent.ExecutionException;

@Repository
public class UserRepository {
    private final Firestore db;
    private static final String USERS_COLLECTION = "users";

    @Autowired
    public UserRepository(Firestore db) {
        this.db = db;
    }

    /**
     * 🔹 Get all users
     * @return
     * @throws ExecutionException
     * @throws InterruptedException
     */
    public List<String> getAllUsers() throws ExecutionException, InterruptedException {
        CollectionReference users = db.collection(USERS_COLLECTION);

        ApiFuture<QuerySnapshot> querySnapshot = users.get();
        List<String> userList = new ArrayList<>();

        for (DocumentSnapshot document : querySnapshot.get().getDocuments()) {
            userList.add(document.getString("email"));
        }
        return userList;
    }

    /**
     * 🔹 Get user by email
     * @param email
     * @return
     * @throws ExecutionException
     * @throws InterruptedException
     */
    public boolean userExists(String email) throws ExecutionException, InterruptedException {
        CollectionReference usersRef = db.collection(USERS_COLLECTION);
        Query query = usersRef.whereEqualTo("email", email);
        ApiFuture<QuerySnapshot> querySnapshot = query.get();

        List<QueryDocumentSnapshot> documents = querySnapshot.get().getDocuments();
        return !documents.isEmpty();
    }

    /**
     * 🔹 Add a user
     * @param email identificador unico del usuario
     * @return
     * @throws ExecutionException
     * @throws InterruptedException
     */
    public void saveUser(String email) throws ExecutionException, InterruptedException {
        DocumentReference docRef = db.collection(USERS_COLLECTION).document(String.valueOf(UUID.randomUUID().toString()));

        Map<String, Object> user = new HashMap<>();
        user.put("email", email);

        // Guardar en Firestore
        ApiFuture<WriteResult> future = docRef.set(user);

        // Esperar a que se complete la operación
        future.get();
    }
}
