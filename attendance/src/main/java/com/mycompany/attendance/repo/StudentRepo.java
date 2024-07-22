package com.mycompany.attendance.repo;

import org.springframework.data.jpa.repository.JpaRepository;

import com.mycompany.attendance.models.Student;

public interface StudentRepo extends JpaRepository<Student, Long>{

}
