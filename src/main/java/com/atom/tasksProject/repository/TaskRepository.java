package com.atom.tasksProject.repository;

import com.atom.tasksProject.dto.TaskDTO;
import com.google.api.core.ApiFuture;
import com.google.cloud.firestore.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.*;
import java.util.concurrent.ExecutionException;

import static com.atom.tasksProject.util.TaskMapper.fromDocument;

@Repository
public class TaskRepository {

    private final Firestore db;
    private static final String USERS_COLLECTION = "users";
    private static final String TASKS_SUBCOLLECTION = "tasks";

    @Autowired
    public TaskRepository(Firestore db) {
        this.db = db;
    }

    public List<TaskDTO> getTasks(String userEmail) throws ExecutionException, InterruptedException {
        CollectionReference tasksRef = db.collection(USERS_COLLECTION)
                .document(userEmail)
                .collection(TASKS_SUBCOLLECTION);
        ApiFuture<QuerySnapshot> querySnapshot = tasksRef.orderBy("createdAt", Query.Direction.ASCENDING).get();
        List<TaskDTO> tasks = new ArrayList<>();
        for (DocumentSnapshot doc : querySnapshot.get().getDocuments()) {
            //Map<String, Object> taskData = new HashMap<>(Objects.requireNonNull(doc.getData()));
            //taskData.put("id", doc.getId());
            tasks.add(fromDocument(doc));
        }
        return tasks;
    }

    public void addTask(String userEmail, Map<String, Object> taskData) throws ExecutionException, InterruptedException {
        CollectionReference tasksRef = db.collection(USERS_COLLECTION)
                .document(userEmail)
                .collection(TASKS_SUBCOLLECTION);
        tasksRef.add(taskData).get();
    }

    public void updateTask(String userEmail, String taskId, Map<String, Object> updatedData) throws ExecutionException, InterruptedException {
        DocumentReference taskDocRef = db.collection(USERS_COLLECTION)
                .document(userEmail)
                .collection(TASKS_SUBCOLLECTION)
                .document(taskId);
        taskDocRef.update(updatedData).get();
    }

    public void deleteTask(String userEmail, String taskId) throws ExecutionException, InterruptedException {
        DocumentReference taskDocRef = db.collection(USERS_COLLECTION)
                .document(userEmail)
                .collection(TASKS_SUBCOLLECTION)
                .document(taskId);
        taskDocRef.delete().get();
    }
}

