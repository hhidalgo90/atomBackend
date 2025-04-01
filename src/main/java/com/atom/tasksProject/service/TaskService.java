package com.atom.tasksProject.service;

import com.atom.tasksProject.dto.TaskDTO;
import org.springframework.stereotype.Service;

import java.util.*;
import java.util.concurrent.ExecutionException;

@Service
public interface TaskService {

    List<TaskDTO> getUserTasks(String userEmail) throws ExecutionException, InterruptedException ;

    void addTask(String userEmail, Map<String, Object> taskData) throws ExecutionException, InterruptedException ;


    void updateTask(String userEmail, String taskId, Map<String, Object> updatedData) throws ExecutionException, InterruptedException ;


    void deleteTask(String userEmail, String taskId) throws ExecutionException, InterruptedException ;
}

