package com.votingapp.service;

import com.votingapp.model.User;

import java.util.Optional;

public interface UserService {
    // Method to find a user by username
    Optional<User> findByUsername(String username);

    // Method to check if a user exists by username
    boolean existsByUsername(String username);

    // Method to check if a user exists by email
    boolean existsByEmail(String email);

    // Method to register a new user
    User registerUser(String username, String password, String email, String phone, String role);

    // Method to enable or set user role to 'USER'
    void assignUserRole(User user, String role);

    // Method to check if user has voted
    boolean hasUserVoted(Long userId);

    // Method to save user after voting
    void saveUser(User user);
}
