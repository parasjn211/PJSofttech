package com.attendance.attendanceapp.repository;

import com.attendance.attendanceapp.model.Admin;
import com.attendance.attendanceapp.model.Attendance;
import com.attendance.attendanceapp.model.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

public interface AttendanceRepository extends JpaRepository<Attendance, Long> {
    Optional<Attendance> findByUserAndSignDate(User user, LocalDate date);
    Optional<Attendance> findByAdminAndSignDate(Admin admin, LocalDate date);
    List<Attendance> findByUser(User user);
    List<Attendance> findByAdmin(Admin admin);
}
