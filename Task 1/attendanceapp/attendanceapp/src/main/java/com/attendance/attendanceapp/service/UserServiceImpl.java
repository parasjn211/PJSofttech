package com.attendance.attendanceapp.service;

import com.attendance.attendanceapp.dto.UserRegistrationDTO;
import com.attendance.attendanceapp.dto.UserUpdateDTO;
import com.attendance.attendanceapp.model.User;
import com.attendance.attendanceapp.repository.AttendanceRepository;
import com.attendance.attendanceapp.repository.UserRepository;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

@Service
@Transactional
public class UserServiceImpl implements UserService {
    @Autowired
    private UserRepository userRepository;

    @Autowired
    private BCryptPasswordEncoder passwordEncoder;

    @Autowired
    private AttendanceRepository attendanceRepository;
    @Override
    public User registerUser(UserRegistrationDTO userRegistrationDTO) {
        // Create new User entity
        User newUser = new User();
        newUser.setUsername(userRegistrationDTO.getUsername());
        newUser.setEmail(userRegistrationDTO.getEmail());
        newUser.setPhone(userRegistrationDTO.getPhone());
        // Encrypt the password before saving
        newUser.setPassword(passwordEncoder.encode(userRegistrationDTO.getPassword()));

        // Save the user to the database
        return userRepository.save(newUser);
    }

    @Override
    public void updateUserCredentials(Long userId, UserUpdateDTO userUpdateDTO) {
        User user = userRepository.findById(userId)
                .orElseThrow(() -> new RuntimeException("User not found"));

        user.setUsername(userUpdateDTO.getUsername());
        user.setPassword(passwordEncoder.encode(userUpdateDTO.getPassword())); // Encrypt password

        userRepository.save(user);
    }
}
