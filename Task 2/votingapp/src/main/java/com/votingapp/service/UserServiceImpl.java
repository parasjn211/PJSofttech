package com.votingapp.service;

import com.votingapp.model.User;
import com.votingapp.repository.UserRepository;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Optional;
@Service
public class UserServiceImpl implements UserService  {
    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;

    public UserServiceImpl(UserRepository userRepository, PasswordEncoder passwordEncoder) {
        this.userRepository = userRepository;
        this.passwordEncoder = passwordEncoder;
    }

    @Override
    public Optional<User> findByUsername(String username) {
        return userRepository.findByUsername(username);
    }

    @Override
    public boolean existsByUsername(String username) {
        return userRepository.existsByUsername(username);
    }

    @Override
    public boolean existsByEmail(String email) {
        return userRepository.existsByEmail(email);
    }

    @Override
    public User registerUser(String username, String password, String email, String phone, String role) {
        if (!role.equalsIgnoreCase("USER") && !role.equalsIgnoreCase("ADMIN")) {
            throw new IllegalArgumentException("Invalid role: " + role + ". Only 'USER' or 'ADMIN' are allowed.");
        }

        User user = new User();
        user.setUsername(username);
        user.setPassword(passwordEncoder.encode(password)); // Secure password
        user.setEmail(email);
        user.setPhone(phone);
        user.setRole(role.toUpperCase());
        return userRepository.save(user);
    }


    @Override
    public void assignUserRole(User user, String role) {
        if (!role.equalsIgnoreCase("USER") && !role.equalsIgnoreCase("ADMIN")) {
            throw new IllegalArgumentException("Invalid role: " + role + ". Only 'USER' or 'ADMIN' are allowed.");
        }
        user.setRole(role.toUpperCase());
        userRepository.save(user);
    }


    @Override
    public boolean hasUserVoted(Long userId) {
        Optional<User> user = userRepository.findById(userId);
        return user.map(User::isHasVoted).orElse(false);
    }

    @Override
    public void saveUser(User user) {
        userRepository.save(user);
    }
}
