package com.student.service.impl;

import com.student.dto.StudentDTO;
import com.student.entity.Student;
import com.student.exception.ResourceNotFoundException;
import com.student.exception.ValidationException;
import com.student.repository.StudentRepository;
import com.student.service.IStudentService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.List;
import java.util.Optional;

@Service
@Slf4j
public class StudentServiceImpl implements IStudentService {

	@Autowired
	private StudentRepository studentRepository;

	@Override
	public Student saveStudent(StudentDTO studentDTO) {
		try {
			log.info("Saving student with name: {}", studentDTO.getName());

			if (studentDTO.getName().isBlank()) {
				log.error("Student name cannot be blank");
				throw new ValidationException("Student name cannot be blank");
			}

			if (studentDTO.getEmail().isBlank()) {
				log.error("Student email cannot be blank");
				throw new ValidationException("Student email cannot be blank");
			}

//			Optional<Student> existingStudent = studentRepository.findByEmail(studentDTO.getEmail());
//			if (existingStudent.isPresent()) {
//				log.error("Student with email {} already exists", studentDTO.getEmail());
//				throw new ValidationException("Student with this email already exists");
//			}

			Student student = new Student();
			student.setName(studentDTO.getName());
			student.setEmail(studentDTO.getEmail());
			student.setNumber(studentDTO.getNumber());
			student.setPassword(studentDTO.getPassword());

			Student savedStudent = studentRepository.save(student);
			log.info("Student saved successfully with ID: {}", savedStudent.getId());

			return savedStudent;

		} catch (Exception e) {
			log.error("Error saving student: {}", e.getMessage());
			throw new RuntimeException("Failed to save student due to an internal error");
		}
	}

	@Override
	public List<Student> getAllStudents() {
		try {
			log.info("Fetching all students");

			List<Student> students = studentRepository.findAll();

			if (students.isEmpty()) {
				log.warn("No students found in the database");
				throw new ResourceNotFoundException("No students found");
			}

			log.info("Total students retrieved: {}", students.size());
			return students;

		} catch (Exception e) {
			log.error("Error fetching students: {}", e.getMessage());
			throw new RuntimeException("Failed to fetch students due to an internal error");
		}
	}

	@Override
	public Student getStudentById(Long id) {
		log.info("Fetching student with ID: {}", id);
		Optional<Student> student = studentRepository.findById(id);
		if (student.isPresent()) {
			log.info("Student found: {}", student.get().getName());
			return student.get();
		} else {
			log.info("Student not found with ID: {}", id);
			throw new ResourceNotFoundException("Student not found with ID: " + id);
		}
	}

	@Override
	public Student updateStudent(Long id, StudentDTO studentDTO) {
		log.info("Updating student with ID: {}", id);

		Optional<Student> existingStudent = studentRepository.findById(id);
		if (existingStudent.isPresent()) {
			Student student = existingStudent.get();
			student.setName(studentDTO.getName());
			student.setEmail(studentDTO.getEmail());
			student.setNumber(studentDTO.getNumber());
			student.setPassword(studentDTO.getPassword());

			Student updatedStudent = studentRepository.save(student);
			log.info("Student updated successfully with ID: {}", updatedStudent.getId());
			return updatedStudent;
		} else {
			log.info("Student not found with ID: {}", id);
			throw new ResourceNotFoundException("Student not found with ID: " + id);
		}
	}

	@Override
	public void deleteStudent(Long id) {
		log.info("Deleting student with ID: {}", id);

		Optional<Student> student = studentRepository.findById(id);
		if (student.isPresent()) {
			studentRepository.deleteById(id);
			log.info("Student deleted successfully with ID: {}", id);
		} else {
			log.info("Student not found with ID: {}", id);
			throw new ResourceNotFoundException("Student not found with ID: " + id);
		}
	}
}
