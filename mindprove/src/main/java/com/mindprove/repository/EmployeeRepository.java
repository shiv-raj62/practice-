package com.mindprove.repository;

import com.mindprove.entity.Employee;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.stereotype.Repository;

@Repository
public interface EmployeeRepository extends JpaRepository<Employee, Long> {
	Optional<Employee> findByAccountNo(Long accountNo);

	Optional<Employee> findByName(String name);

}

//    Optional<Employee> findById(Long id);
