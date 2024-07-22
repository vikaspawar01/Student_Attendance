package com.mycompany.attendance.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.mycompany.attendance.models.Attendance;
import com.mycompany.attendance.models.Student;
import com.mycompany.attendance.services.AttendanceService;

@RestController
@RequestMapping("/api")
public class AttendanceController {
	
	@Autowired
	AttendanceService attendanceService;
	
	@GetMapping("/students")
	    public List<Student> getAllStudents() {
	        return attendanceService.getAllStudents();
	    }
	
	 @GetMapping("/students/{id}")
	    public ResponseEntity<Object> getStudentById(@PathVariable Long id) {
	        Student student = attendanceService.getStudentById(id);
	        if (student != null) {
	            return ResponseEntity.ok(student);
	        } else {
	        	return ResponseEntity.status(HttpStatus.NOT_FOUND).body("No student found with ID: " + id);
	        }
	    }

	 @PostMapping("/students")
	    public ResponseEntity<Student> saveStudent(@RequestBody Student student) {
	        Student savedStudent = attendanceService.saveStudent(student);
	        return ResponseEntity.status(HttpStatus.CREATED).body(savedStudent);
	    }
	 
	 @DeleteMapping("/students/{id}")
	    public ResponseEntity<Object> deleteStudentById(@PathVariable Long id) {
	        boolean isDeleted = attendanceService.deleteStudentById(id);
	        if (isDeleted) {
	            return ResponseEntity.status(HttpStatus.OK).body("Student Id has been deleted successfully: " + "id "+id);
	        } else {
	            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("No student found with ID: " +id);
	        }
	    }
	 
	 @PutMapping("/students/{id}")
	 public ResponseEntity<Object> updateStudent(@PathVariable Long id, @RequestBody Student studentDetails) {
	     Student updatedStudent = attendanceService.updateStudent(id, studentDetails);
	     if (updatedStudent != null) {
	         return ResponseEntity.ok(updatedStudent);
	     } else {
	         return ResponseEntity.status(HttpStatus.NOT_FOUND).body("No student found with ID: " + id);
	     }
	 }

	 
    @GetMapping("/attendance")
    public List<Attendance> getAllAttendance() {
        return attendanceService.getAllAttendance();
    }

    
    @PostMapping("/attendance")
    public ResponseEntity<Attendance> saveAttendance(@RequestBody Attendance attendance) {
        Attendance savedAttendance = attendanceService.saveAttendance(attendance);
        return ResponseEntity.status(HttpStatus.CREATED).body(savedAttendance);
    }
    
    @GetMapping("/attendance/student/{studentId}")
    public ResponseEntity<Object> getAttendanceByStudentId(@PathVariable Long studentId) {
        List<Attendance> attendanceList = attendanceService.getAttendanceByStudentId(studentId);
        if (attendanceList != null && !attendanceList.isEmpty()) {
            return ResponseEntity.ok(attendanceList);
        } else {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("No attendance records found for student ID: " + studentId);
        }
    }
    
    @PutMapping("/attendance/{id}")
    public ResponseEntity<Object> updateAttendance(@PathVariable Long id, @RequestBody Attendance attendanceDetails) {
        Attendance updatedAttendance = attendanceService.updateAttendance(id, attendanceDetails);
        if (updatedAttendance != null) {
            return ResponseEntity.ok(updatedAttendance);
        } else {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("No attendance record found with ID: " + id);
        }
    }

    
    @DeleteMapping("/attendance/{id}")
    public ResponseEntity<Object> deleteAttendanceById(@PathVariable Long id) {
        boolean isDeleted = attendanceService.deleteAttendanceById(id);
        if (isDeleted) {
            return ResponseEntity.status(HttpStatus.OK).body("Deleted the attendance successfully...");
        } else {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("No attendance record found with ID: " + id);
        }
    }
}
