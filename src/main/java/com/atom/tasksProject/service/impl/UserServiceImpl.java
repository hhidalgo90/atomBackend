package com.atom.tasksProject.service.impl;

import com.atom.tasksProject.repository.UserRepository;
import com.atom.tasksProject.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.*;
import java.util.concurrent.ExecutionException;

@Service
public class UserServiceImpl implements UserService {

    private final UserRepository userRepository;

    @Autowired
    public UserServiceImpl(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    public List<String> getAllUsers() throws ExecutionException, InterruptedException {
        return userRepository.getAllUsers();
    }

    public boolean userExists(String email) throws ExecutionException, InterruptedException {
        return userRepository.userExists(email);
    }

    public void addUser(String email) throws ExecutionException, InterruptedException {
        userRepository.saveUser(email);
    }
}

