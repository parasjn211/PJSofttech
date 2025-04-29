package com.attendance.attendanceapp.controller;

import com.attendance.attendanceapp.model.Admin;
import com.attendance.attendanceapp.model.Attendance;
import com.attendance.attendanceapp.model.User;
import com.attendance.attendanceapp.service.AdminService;
import com.attendance.attendanceapp.service.AttendanceService;
import com.attendance.attendanceapp.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.List;

@RestController
@RequestMapping("/api/attendance")
public class AttendanceController {
    @Autowired
    private UserService userService;

    @Autowired
    private AdminService adminService;

    @Autowired
    private AttendanceService attendanceService;
    // User Endpoints
    @PostMapping("/user/signin")
    public ResponseEntity<String> userSignIn(@RequestBody User user) {
        attendanceService.userSignIn(user);
        return ResponseEntity.ok("User signed in successfully");
    }


    @PostMapping("/user/signout")
    public ResponseEntity<String> userSignOut(@RequestBody User user) {
        attendanceService.userSignOut(user);
        return ResponseEntity.ok("User signed out successfully");
    }

    @GetMapping("/user/{userId}/getattendance")
    public ResponseEntity<List<Attendance>> getUserAttendance(@PathVariable Long userId) {
        List<Attendance> attendanceList = attendanceService.getUserAttendance(userId);
        return ResponseEntity.ok(attendanceList);
    }

    // Admin Endpoints
    @PostMapping("/admin/signin")
    public ResponseEntity<String> adminSignIn(@RequestBody Admin admin) {
        attendanceService.adminSignIn(admin);
        return ResponseEntity.ok("Admin signed in successfully");
    }

    @PostMapping("/admin/signout")
    public ResponseEntity<String> adminSignOut(@RequestBody Admin admin) {
        attendanceService.adminSignOut(admin);
        return ResponseEntity.ok("Admin signed out successfully");
    }

    @GetMapping("/admin/{adminId}/getattendance")
    public ResponseEntity<List<Attendance>> getAdminAttendance(@PathVariable Long adminId) {
        List<Attendance> attendanceList = attendanceService.getAdminAttendance(adminId);
        return ResponseEntity.ok(attendanceList);
    }
}
