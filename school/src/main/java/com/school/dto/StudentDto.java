package com.school.dto;

import java.util.Set;

import com.school.entity.Role;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class StudentDto {

	private String firstName;

	private String lastName;

	private String email;

	private String password;

	private String confirmPassword;

	private String phoneNumber;

	private int age;

	private String grade;
	
	private Set<Role> roles;

}
