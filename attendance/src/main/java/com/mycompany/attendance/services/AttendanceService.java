package com.mycompany.attendance.services;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.mycompany.attendance.models.Attendance;
import com.mycompany.attendance.models.Student;
import com.mycompany.attendance.repo.AttendanceRepo;
import com.mycompany.attendance.repo.StudentRepo;

@Service
public class AttendanceService {
	
	@Autowired
	StudentRepo studentRepo;
	
	@Autowired
	AttendanceRepo attendanceRepo;
	
	public List<Student> getAllStudents() {
        return studentRepo.findAll();
    }
	
	public Student getStudentById(Long id) {
        return studentRepo.findById(id).orElse(null);
    }

    public Student saveStudent(Student student) {
        return studentRepo.save(student);
    }
    
    public Student updateStudent(Long id, Student studentDetails) {
        Optional<Student> studentOptional = studentRepo.findById(id);
        if (studentOptional.isPresent()) {
            Student student = studentOptional.get();
            student.setName(studentDetails.getName());
            student.setEmail(studentDetails.getEmail());
            return studentRepo.save(student);
        } else {
            return null;
        }
    }
    
    public boolean deleteStudentById(Long id) {
        if (studentRepo.existsById(id)) {
            studentRepo.deleteById(id);
            return true;
        }
        return false;
    }
	
	public List<Attendance> getAllAttendance() {
        return attendanceRepo.findAll();
    }

    public Attendance saveAttendance(Attendance attendance) {
        return attendanceRepo.save(attendance);
    }
    
    public List<Attendance> getAttendanceByStudentId(Long studentId) {
        return attendanceRepo.findByStudentId(studentId);
    }
    
    public Attendance updateAttendance(Long id, Attendance attendanceDetails) {
        Optional<Attendance> attendanceOptional = attendanceRepo.findById(id);
        if (attendanceOptional.isPresent()) {
            Attendance attendance = attendanceOptional.get();
            attendance.setStudent(attendanceDetails.getStudent());
            attendance.setAttendance_date(attendanceDetails.getAttendance_date());
            attendance.setStatus(attendanceDetails.getStatus());
            return attendanceRepo.save(attendance);
        } else {
            return null;
        }
    }
    
    public boolean deleteAttendanceById(Long id) {
        if (attendanceRepo.existsById(id)) {
            attendanceRepo.deleteById(id);
            return true;
        }
        return false;
    }
    
}
