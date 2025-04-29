package com.attendance.attendanceapp.service;

import com.attendance.attendanceapp.dto.AdminRegistrationDTO;
import com.attendance.attendanceapp.dto.AdminUpdateDTO;
import com.attendance.attendanceapp.model.Admin;
import com.attendance.attendanceapp.model.Attendance;

import java.util.List;

public interface AdminService {
    void updateAdminCredentials(Long adminId, AdminUpdateDTO adminUpdateDTO);
    Admin registerAdmin(AdminRegistrationDTO adminDTO);

}
