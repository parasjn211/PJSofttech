package com.attendance.attendanceapp.service;

import com.attendance.attendanceapp.model.Admin;
import com.attendance.attendanceapp.model.Attendance;
import com.attendance.attendanceapp.model.User;
import com.attendance.attendanceapp.repository.AdminRepository;
import com.attendance.attendanceapp.repository.AttendanceRepository;
import com.attendance.attendanceapp.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import java.util.Collections;
import java.util.Optional;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;

@Service
public class AttendanceServiceImpl implements AttendanceService{

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private AdminRepository adminRepository;

    @Autowired
    private AttendanceRepository attendanceRepository;



    @Override
    public void userSignIn(User user) {
        User existingUser = userRepository.findByUsername(user.getUsername())
                .orElseThrow(() -> new RuntimeException("User not found"));

        Attendance attendance = new Attendance();
        attendance.setUser(existingUser);
        attendance.setSignInTime(LocalDateTime.now());
        attendance.setSignDate(LocalDate.now());
        attendanceRepository.save(attendance);
    }
    // User Sign Out Method
    @Override
    public void userSignOut(User user) {
        // Fetch existing user from database
        User existingUser = userRepository.findByUsername(user.getUsername())
                .orElseThrow(() -> new RuntimeException("User not found"));

        // Fetch today's attendance record
        Attendance attendance = attendanceRepository.findByUserAndSignDate(existingUser, LocalDate.now())
                .orElseThrow(() -> new RuntimeException("Sign-in record not found for today."));

        // Set sign-out time
        attendance.setSignOutTime(LocalDateTime.now());

        // Save updated attendance
        attendanceRepository.save(attendance);
    }



    @Override
    public void adminSignIn(Admin admin) {
        Admin existingAdmin = adminRepository.findByUsername(admin.getUsername())
                .orElseThrow(() -> new RuntimeException("Admin not found"));

        Attendance attendance = new Attendance();
        attendance.setAdmin(existingAdmin);
        attendance.setUser(null);
        attendance.setSignInTime(LocalDateTime.now());
        attendance.setSignDate(LocalDate.now());
        attendanceRepository.save(attendance);
    }


    @Override
    public void adminSignOut(Admin admin) {
        Admin existingAdmin = adminRepository.findByUsername(admin.getUsername())
                .orElseThrow(() -> new RuntimeException("Admin not found"));

        Attendance attendance = attendanceRepository
                .findByAdminAndSignDate(existingAdmin, LocalDate.now())
                .orElseThrow(() -> new RuntimeException("Sign-in record not found for today."));

        attendance.setSignOutTime(LocalDateTime.now());
        attendanceRepository.save(attendance);
    }


    @Override
    public List<Attendance> getUserAttendance(Long userId) {
        User user = userRepository.findById(userId)
                .orElseThrow(() -> new RuntimeException("User not found"));
        return attendanceRepository.findByUser(user);
    }

    @Override
    public List<Attendance> getAdminAttendance(Long adminId) {
        Admin admin = adminRepository.findById(adminId)
                .orElseThrow(() -> new RuntimeException("Admin not found"));
        return attendanceRepository.findByAdmin(admin);
    }

}
