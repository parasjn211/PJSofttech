package com.attendance.attendanceapp.service;

import com.attendance.attendanceapp.dto.AdminRegistrationDTO;
import com.attendance.attendanceapp.dto.AdminUpdateDTO;
import com.attendance.attendanceapp.model.Admin;
import com.attendance.attendanceapp.repository.AdminRepository;
import com.attendance.attendanceapp.repository.AttendanceRepository;
import com.attendance.attendanceapp.repository.UserRepository;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;


@Service
@Transactional
public class AdminServiceImpl implements  AdminService{
    @Autowired
    private AttendanceRepository attendanceRepository;

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private AdminRepository adminRepository;
    @Autowired
    private BCryptPasswordEncoder passwordEncoder;

    @Override
    public Admin registerAdmin(AdminRegistrationDTO adminDTO) {
        Admin admin = new Admin();
        admin.setUsername(adminDTO.getUsername());
        admin.setPassword(passwordEncoder.encode(adminDTO.getPassword()));
        return adminRepository.save(admin);
    }


    @Override
    public void updateAdminCredentials(Long adminId, AdminUpdateDTO adminUpdateDTO) {
        Admin admin = adminRepository.findById(adminId)
                .orElseThrow(() -> new RuntimeException("Admin not found"));

        admin.setUsername(adminUpdateDTO.getUsername());
        admin.setPassword(passwordEncoder.encode(adminUpdateDTO.getPassword())); // Encrypt password

        adminRepository.save(admin);
    }

}
