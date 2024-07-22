package com.mycompany.attendance.repo;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.mycompany.attendance.models.Attendance;

public interface AttendanceRepo extends JpaRepository<Attendance, Long>{

	List<Attendance> findByStudentId(Long studentId);

}
