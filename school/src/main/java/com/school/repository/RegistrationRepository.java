package com.school.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.school.entity.Student;

@Repository
public interface RegistrationRepository extends JpaRepository<Student, Long> {

	 Optional<Student> findByEmail(String email);
}
