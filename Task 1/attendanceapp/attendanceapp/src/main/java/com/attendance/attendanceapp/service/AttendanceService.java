package com.attendance.attendanceapp.service;

import com.attendance.attendanceapp.model.Admin;
import com.attendance.attendanceapp.model.Attendance;
import com.attendance.attendanceapp.model.User;

import java.util.List;

public interface AttendanceService {
    void userSignIn(User user);
    void userSignOut(User user);
    void adminSignIn(Admin admin);
    void adminSignOut(Admin admin);
    List<Attendance> getUserAttendance(Long userId);
    List<Attendance> getAdminAttendance(Long adminId);
}
