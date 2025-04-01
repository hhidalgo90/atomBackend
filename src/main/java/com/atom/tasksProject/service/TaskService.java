package com.atom.tasksProject.service;

import com.atom.tasksProject.dto.TaskDTO;
import org.springframework.stereotype.Service;

import java.util.*;
import java.util.concurrent.ExecutionException;

@Service
public interface TaskService {

    /**
     * Get all taks for one user
     * @param userEmail
     * @return List with a set of tasks objects
     * @throws ExecutionException
     * @throws InterruptedException
     */
    List<TaskDTO> getUserTasks(String userEmail) throws ExecutionException, InterruptedException ;

    /**
     * Add a task to the database
     * @param userEmail
     * @param taskData
     * @throws ExecutionException
     * @throws InterruptedException
     */
    void addTask(String userEmail, Map<String, Object> taskData) throws ExecutionException, InterruptedException ;


    /**
     * Update an existing task
     * @param userEmail
     * @param taskId
     * @param updatedData
     * @throws ExecutionException
     * @throws InterruptedException
     */
    void updateTask(String userEmail, String taskId, Map<String, Object> updatedData) throws ExecutionException, InterruptedException ;


    /**
     * Delete a task
     * @param userEmail
     * @param taskId
     * @throws ExecutionException
     * @throws InterruptedException
     */
    void deleteTask(String userEmail, String taskId) throws ExecutionException, InterruptedException ;
}

