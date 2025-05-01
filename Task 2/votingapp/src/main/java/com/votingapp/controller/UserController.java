package com.votingapp.controller;

import com.votingapp.model.Candidate;
import com.votingapp.model.User;
import com.votingapp.service.CandidateService;
import com.votingapp.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.*;
import java.util.List;
import java.util.Optional;


@RestController
@RequestMapping("/api/user")
public class UserController {
    private UserService userService;
    private CandidateService candidateService;
    private PasswordEncoder passwordEncoder;

    @Autowired
    public UserController(UserService userService, CandidateService candidateService, PasswordEncoder passwordEncoder) {
        this.userService = userService;
        this.candidateService = candidateService;
        this.passwordEncoder = passwordEncoder;
    }

    // Register a new user
    @PostMapping("/register")
    public User registerUser(@RequestBody User user) {
        return userService.registerUser(user.getUsername(), user.getPassword(), user.getEmail(), user.getPhone(), user.getRole());
    }

    // Login functionality
    @PostMapping("/login")
    public String loginUser(@RequestBody User user) {
        Optional<User> foundUser = userService.findByUsername(user.getUsername());
        if (foundUser.isPresent()) {
            User existingUser = foundUser.get();
            if (passwordEncoder.matches(user.getPassword(), existingUser.getPassword())) {
                return "Login successful";
            } else {
                return "Invalid credentials";
            }
        } else {
            return "User not found";
        }
    }

    // Get list of candidates
    @GetMapping("/candidates")
    public List<Candidate> getCandidates() {
        return candidateService.getAllCandidates();
    }

    // User votes for a candidate
    @PostMapping("/vote")
    public String voteForCandidate(@RequestParam Long candidateId, @RequestParam String username) {
        Optional<User> user = userService.findByUsername(username);
        if (user.isPresent()) {
            if (user.get().isHasVoted()) {
                return "User has already voted";
            }
            candidateService.updateVoteCount(candidateId);
            user.get().setHasVoted(true);
            userService.saveUser(user.get());
            return "Vote successfully cast";
        } else {
            return "User not found";
        }
    }

    // Method to change the user's role
    @PostMapping("/assignRole")
    public String assignRoleToUser(@RequestParam String username, @RequestParam String role) {
        Optional<User> user = userService.findByUsername(username);
        if (user.isPresent()) {
            try {
                userService.assignUserRole(user.get(), role);
                return "Role updated successfully!";
            } catch (IllegalArgumentException e) {
                return e.getMessage();
            }
        } else {
            return "User not found!";
        }
    }
}
