package com.atom.tasksProject.service;

import org.springframework.stereotype.Service;

import java.util.*;
import java.util.concurrent.ExecutionException;

@Service
public interface UserService {

    List<String> getAllUsers() throws ExecutionException, InterruptedException ;

    boolean existUser(String email) throws ExecutionException, InterruptedException ;

    void addUser(String email) throws ExecutionException, InterruptedException ;
}
