package com.student.service;

import java.util.List;

import com.student.dto.StudentDTO;
import com.student.entity.Student;

public interface IStudentService {

	Student saveStudent(StudentDTO studentDTO);

	List<Student> getAllStudents();

	Student getStudentById(Long id);

	Student updateStudent(Long id, StudentDTO studentDTO);

	void deleteStudent(Long id);

}
