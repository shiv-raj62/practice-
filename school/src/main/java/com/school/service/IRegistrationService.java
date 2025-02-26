package com.school.service;

import com.school.dto.StudentDto;
import com.school.entity.Student;

public interface IRegistrationService {
	
	Student registration(StudentDto studentDto);

}
