package com.school.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.school.dto.StudentDto;
import com.school.entity.Student;
import com.school.repository.RegistrationRepository;
import com.school.service.IRegistrationService;

import lombok.extern.slf4j.Slf4j;

import com.school.exception.ResourceNotFoundException;
import com.school.exception.ValidationException;

import java.util.Objects;


@Service
@Slf4j
public class RegistrationServiceImpl implements IRegistrationService {

	@Autowired
	private RegistrationRepository studentRepository;

	@Autowired
	private PasswordEncoder passwordEncoder;

	@Override
	public Student registration(StudentDto studentDto) {

		log.info("Started registration for: {}", studentDto.getEmail());

		if (!Objects.equals(studentDto.getPassword(), studentDto.getConfirmPassword())) {
			throw new ValidationException("Passwords do not match");
		}

		try {
			Student student = new Student();
			student.setFirstName(studentDto.getFirstName());
			student.setLastName(studentDto.getLastName());
			student.setEmail(studentDto.getEmail());
			student.setAge(studentDto.getAge());
			student.setPhoneNumber(studentDto.getPhoneNumber());
			student.setGrade(studentDto.getGrade());
			student.setRoles(studentDto.getRoles());
			student.setPassword(passwordEncoder.encode(studentDto.getPassword()));

			student = studentRepository.save(student);
			log.info("Registration successful for: {}", student.getEmail());
			return student;

		} catch (ResourceNotFoundException ex) {
			log.warn("Validation error: {}", ex.getMessage());
			throw ex;
		} catch (Exception ex) {
			log.error("Registration failed: {}", ex.getMessage());
			throw new ResourceNotFoundException("Registration failed");
		}
	}
}
