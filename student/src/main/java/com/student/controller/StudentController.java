package com.student.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import com.student.dto.StudentDTO;
import com.student.entity.Student;
import com.student.response.ApiResponse;
import com.student.response.ResponseHandler;
import com.student.service.IStudentService;

@RestController
@RequestMapping("/students")
public class StudentController {

	@Autowired
	private IStudentService studentService;

	
	@PostMapping
	public ResponseEntity<ApiResponse> createStudent(@RequestBody StudentDTO studentDTO) {
		Student savedStudent = studentService.saveStudent(studentDTO);
		return ResponseHandler.generateResponse("Student created successfully", HttpStatus.CREATED, savedStudent);
	}

	
	@GetMapping
	public ResponseEntity<ApiResponse> getAllStudents() {
		List<Student> students = studentService.getAllStudents();
		return ResponseHandler.generateResponse("All students retrieved", HttpStatus.OK, students);
	}

	
	@GetMapping("/{id}")
	public ResponseEntity<ApiResponse> getStudentById(@PathVariable Long id) {
		Student student = studentService.getStudentById(id);
		if (student == null) {
			return ResponseHandler.generateResponse("Student not found", HttpStatus.NOT_FOUND, null);
		}
		return ResponseHandler.generateResponse("Student retrieved successfully", HttpStatus.OK, student);
	}

	
	@PutMapping("/{id}")
	public ResponseEntity<ApiResponse> updateStudent(@PathVariable Long id, @RequestBody StudentDTO studentDTO) {
		Student updatedStudent = studentService.updateStudent(id, studentDTO);
		
			
		return ResponseHandler.generateResponse("Student updated successfully", HttpStatus.OK, updatedStudent);
	}


	@DeleteMapping("/{id}")
	public ResponseEntity<ApiResponse> deleteStudent(@PathVariable Long id) {
		studentService.deleteStudent(id);
		return ResponseHandler.generateResponse("Student deleted successfully", HttpStatus.NO_CONTENT, null);
	}
}
