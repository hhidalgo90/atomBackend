package com.atom.tasksProject.controller;

import com.atom.tasksProject.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ExecutionException;


@RestController
@RequestMapping("/api/users/v1")
@CrossOrigin(origins = "*")
public class UserController {

    @Autowired
    private UserService userService;

    @GetMapping("/{email}")
    public boolean getUser(@PathVariable String email) throws ExecutionException, InterruptedException {
        return userService.existUser(email);
    }

    @PostMapping(produces = "application/json")
    public ResponseEntity<Map<String, String>> addUser(@RequestParam("email") String email) {
        Map<String, String> respuesta = new HashMap<>();
        try {
            if (userService.existUser(email)) {
                respuesta.put("mensaje", "El usuario ya existe");
                return ResponseEntity.badRequest().body(respuesta);
            }
            userService.addUser(email);
        } catch (ExecutionException | InterruptedException e) {
            respuesta.put("mensaje", "Error al agregar el usuario");
            respuesta.put("error", e.getLocalizedMessage());
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(respuesta);
        }
        respuesta.put("mensaje", "Usuario agregado correctamente");
        return ResponseEntity.ok().body(respuesta);
    }

    @GetMapping
    public List<String> getUsers() throws ExecutionException, InterruptedException {
        return userService.getAllUsers();
    }
}
