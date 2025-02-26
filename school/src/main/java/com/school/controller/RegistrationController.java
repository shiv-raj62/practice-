package com.school.controller;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import com.school.dto.StudentDto;
import com.school.entity.Student;
import com.school.response.ApiResponse;
import com.school.response.ResponseHandler;
import com.school.service.IRegistrationService;

@RestController
@RequestMapping("/api/public")
public class RegistrationController {

	private final IRegistrationService iStudentService;

	public RegistrationController(IRegistrationService iStudentService) {
		this.iStudentService = iStudentService;

	}

	@PostMapping("/register")
	public ResponseEntity<ApiResponse> register(@RequestBody StudentDto studentDto) {

		Student student = iStudentService.registration(studentDto);
		return ResponseHandler.generateResponse("Registration successful!", HttpStatus.CREATED, student);
	}
}
