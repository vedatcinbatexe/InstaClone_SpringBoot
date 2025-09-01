package com.example.instaclone.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.instaclone.entity.User;
import com.example.instaclone.repository.UserRepository;

@Service
public class UserService {
    @Autowired
    private UserRepository userRepository;

    // CREATE
    public User createUser(User user) {
        if(userRepository.existsByUsername(user.getUsername())) {
            throw new RuntimeException("Username already exists");
        }
        if(userRepository.existsByEmail(user.getEmail())) {
            throw new RuntimeException("Email already exists");
        }
        return userRepository.save(user);
    }

    // READ - Get all users
    public List<User> getAllUsers() {
        return userRepository.findAll();
    }

    // READ - Get user by ID
    public Optional<User> getUserById(Long id) {
        return userRepository.findById(id);
    }

    // READ - Get user by username
    public Optional<User> getUserByUsername(String username) {
        return userRepository.findByUsername(username);
    }

    // UPDATE
    public User updateUser(Long id, User userDetails) {
        User user = userRepository.findById(id)
            .orElseThrow(() -> new RuntimeException("User not found"));

        user.setUsername(userDetails.getUsername());
        user.setEmail(userDetails.getEmail());
        user.setPassword(userDetails.getPassword());
        user.setFirstName(userDetails.getFirstName());
        user.setLastName(userDetails.getLastName());
        user.setFullName(userDetails.getFullName());
        user.setBio(userDetails.getBio());

        return userRepository.save(user);
    }

    // DELETE
    public void deleteUser(Long id) {
        User user = userRepository.findById(id)
            .orElseThrow(() -> new RuntimeException("User not found"));

        userRepository.delete(user);
    }
}