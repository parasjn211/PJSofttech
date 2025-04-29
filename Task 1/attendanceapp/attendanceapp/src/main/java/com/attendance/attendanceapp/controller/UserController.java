package com.attendance.attendanceapp.controller;

import com.attendance.attendanceapp.dto.UserRegistrationDTO;
import com.attendance.attendanceapp.dto.UserUpdateDTO;
import com.attendance.attendanceapp.model.User;
import com.attendance.attendanceapp.service.UserService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.Map;

@RestController
@RequestMapping("/api/user")
public class UserController {
    @Autowired
    private UserService userService;

    @PutMapping("/update/{userId}")
    public ResponseEntity<String> updateUser(@PathVariable Long userId, @RequestBody UserUpdateDTO userUpdateDTO) {
        userService.updateUserCredentials(userId, userUpdateDTO);
        return ResponseEntity.ok("User credentials updated successfully");
    }
    // Registration method
    @PostMapping("/register")
    public ResponseEntity<?> registerUser(@Valid @RequestBody UserRegistrationDTO userRegistrationDTO, BindingResult result) {
        // Handle validation errors
        if (result.hasErrors()) {
            Map<String, String> errors = new HashMap<>();
            for (FieldError error : result.getFieldErrors()) {
                errors.put(error.getField(), error.getDefaultMessage());
            }
            return new ResponseEntity<>(errors, HttpStatus.BAD_REQUEST);
        }

        // Save the user
        User savedUser = userService.registerUser(userRegistrationDTO);
        return new ResponseEntity<>(savedUser, HttpStatus.CREATED);
    }
}
