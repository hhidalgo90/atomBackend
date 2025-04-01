package com.atom.tasksProject.service;

import org.springframework.stereotype.Service;

import java.util.*;
import java.util.concurrent.ExecutionException;

@Service
public interface UserService {


    /**
     * Get all users from the database
     * @return
     * @throws ExecutionException
     * @throws InterruptedException
     */
    List<String> getAllUsers() throws ExecutionException, InterruptedException ;

    /**
     * Check if user already exists
     * @param email
     * @return
     * @throws ExecutionException
     * @throws InterruptedException
     */
    boolean userExists(String email) throws ExecutionException, InterruptedException ;

    /**
     * Add a user to the database
     * @param email
     * @throws ExecutionException
     * @throws InterruptedException
     */
    void addUser(String email) throws ExecutionException, InterruptedException ;
}
