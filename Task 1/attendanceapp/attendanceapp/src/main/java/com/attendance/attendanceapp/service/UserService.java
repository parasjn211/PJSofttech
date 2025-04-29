package com.attendance.attendanceapp.service;

import com.attendance.attendanceapp.dto.UserRegistrationDTO;
import com.attendance.attendanceapp.dto.UserUpdateDTO;
import com.attendance.attendanceapp.model.Attendance;
import com.attendance.attendanceapp.model.User;

import java.util.List;

public interface UserService {
    User registerUser(UserRegistrationDTO userRegistrationDTO);
    void updateUserCredentials(Long userId, UserUpdateDTO userUpdateDTO);

}
