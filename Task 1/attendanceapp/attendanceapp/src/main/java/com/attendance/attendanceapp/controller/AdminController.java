package com.attendance.attendanceapp.controller;

import com.attendance.attendanceapp.dto.AdminRegistrationDTO;
import com.attendance.attendanceapp.dto.AdminUpdateDTO;
import com.attendance.attendanceapp.service.AdminService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/admin")
public class AdminController {
    @Autowired
    private AdminService adminService;


    @PutMapping("/update/{adminId}")
    public ResponseEntity<String> updateAdmin(@PathVariable Long adminId, @RequestBody AdminUpdateDTO adminUpdateDTO) {
        adminService.updateAdminCredentials(adminId, adminUpdateDTO);
        return ResponseEntity.ok("Admin credentials updated successfully");
    }
    @PostMapping("/register")
    public ResponseEntity<String> registerAdmin(@RequestBody AdminRegistrationDTO adminDTO) {
        adminService.registerAdmin(adminDTO);
        return ResponseEntity.status(HttpStatus.CREATED).body("Admin registered successfully");
    }
}
