package com.atom.tasksProject.controller;

import com.atom.tasksProject.dto.TaskDTO;
import com.atom.tasksProject.service.TaskService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ExecutionException;

@RestController
@RequestMapping("/api/tasks/v1")
@CrossOrigin(origins = "*")
public class TaksController {

    @Autowired
    private TaskService taskService;


    @GetMapping("/{userEmail}")
    public List<TaskDTO> getUserTasks(@PathVariable String userEmail) throws ExecutionException, InterruptedException {
        return taskService.getUserTasks(userEmail);
    }

    @PostMapping("/{userEmail}")
    public ResponseEntity<Map<String, String>> addTask(@PathVariable String userEmail, @RequestBody Map<String, Object> taskData) {
        Map<String, String> respuesta = new HashMap<>();
        try {
            taskService.addTask(userEmail, taskData);
        } catch (ExecutionException | InterruptedException e) {
            respuesta.put("mensaje", "Error al agregar el usuario");
            respuesta.put("error", e.getLocalizedMessage());
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(respuesta);
        }
        respuesta.put("mensaje", "Tarea agregada correctamente");
        return ResponseEntity.ok().body(respuesta);
    }

    @PutMapping("/{userEmail}/{taskId}")
    public ResponseEntity<Map<String, String>> updateTask(@PathVariable String userEmail, @PathVariable String taskId,
                             @RequestBody Map<String, Object> updatedData) {
        Map<String, String> respuesta = new HashMap<>();
        try {
            taskService.updateTask(userEmail, taskId, updatedData);
        } catch (ExecutionException | InterruptedException e) {
            respuesta.put("mensaje", "Error al agregar el usuario");
            respuesta.put("error", e.getLocalizedMessage());
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(respuesta);
        }
        respuesta.put("mensaje", "Tarea actualizada correctamente");
        return ResponseEntity.ok().body(respuesta);
    }

    @DeleteMapping("/{userEmail}/{taskId}")
    public ResponseEntity<Map<String, String>> deleteTask(@PathVariable String userEmail, @PathVariable String taskId) {
        Map<String, String> respuesta = new HashMap<>();
        try {
            taskService.deleteTask(userEmail, taskId);
        } catch (ExecutionException | InterruptedException e) {
            respuesta.put("mensaje", "Error al agregar el usuario");
            respuesta.put("error", e.getLocalizedMessage());
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(respuesta);
        }
        respuesta.put("mensaje", "Tarea eliminada correctamente");
        return ResponseEntity.ok().body(respuesta);
    }
}
