package com.atom.tasksProject.service.impl;

import com.atom.tasksProject.dto.TaskDTO;
import com.atom.tasksProject.repository.TaskRepository;
import com.atom.tasksProject.service.TaskService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.*;
import java.util.concurrent.ExecutionException;

@Service
public class TaskServiceImpl implements TaskService {

    private final TaskRepository taskRepository;

    @Autowired
    public TaskServiceImpl(TaskRepository taskRepository) {
        this.taskRepository = taskRepository;
    }

    @Override
    public List<TaskDTO> getUserTasks(String userEmail) throws ExecutionException, InterruptedException {
        return taskRepository.getTasks(userEmail);
    }

    @Override
    public void addTask(String userEmail, Map<String, Object> taskData) throws ExecutionException, InterruptedException {
        LocalDateTime ldt = LocalDateTime.now();
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd-MM-yyyy hh:mm", Locale.ENGLISH);
        String createdAt = formatter.format(ldt);
        taskData.put("createdAt", createdAt);
        taskRepository.addTask(userEmail, taskData);
    }

    @Override
    public void updateTask(String userEmail, String taskId, Map<String, Object> updatedData) throws ExecutionException, InterruptedException {
        taskRepository.updateTask(userEmail, taskId, updatedData);
    }

    @Override
    public void deleteTask(String userEmail, String taskId) throws ExecutionException, InterruptedException {
        taskRepository.deleteTask(userEmail, taskId);
    }
}



