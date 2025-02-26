package com.mindprove.repository;

import com.mindprove.entity.Employee;
import com.mindprove.service.impl.EmployeeServiceImpl;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyLong;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
public class EmployeeRepositoryTest {

	@Mock
	private EmployeeRepository employeeRepository;

	@InjectMocks
	private EmployeeServiceImpl employeeService;

	@Test
	public void shouldSaveEmployee() {

		Employee employee = new Employee(1L, "shivraj", 50000.0, "Mindprove", 123456L);
		when(employeeRepository.save(any())).thenReturn(employee);

		Employee savedEmployee = employeeRepository.save(employee);

		assertNotNull(savedEmployee, "Employee should not be null");
		assertEquals("shivraj", savedEmployee.getName());
		assertEquals(50000.0, savedEmployee.getSalary());
		assertEquals("Mindprove", savedEmployee.getCompanyName());
		assertEquals(123456L, savedEmployee.getAccountNo());
	}

	@Test
	public void shouldFindEmployeeById() {

		Employee employee = new Employee(1L, "shivraj", 50000.0, "Mindprove", 123456L);
		when(employeeRepository.findById(anyLong())).thenReturn(Optional.of(employee));

		Optional<Employee> foundEmployee = employeeRepository.findById(1L);

		assertNotNull(foundEmployee, "Optional should not be null");
		assertEquals(true, foundEmployee.isPresent(), "Employee should be found");
		assertEquals("shivraj", foundEmployee.get().getName());
		assertEquals(50000.0, foundEmployee.get().getSalary());
		assertEquals("Mindprove", foundEmployee.get().getCompanyName());
		assertEquals(123456L, foundEmployee.get().getAccountNo());
	}

	@Test
	public void shouldNotFindEmployeeById() {

		when(employeeRepository.findById(anyLong())).thenReturn(Optional.empty());

		Optional<Employee> foundEmployee = employeeRepository.findById(2L);

		assertNotNull(foundEmployee, "Optional should not be null");
		assertEquals(false, foundEmployee.isPresent(), "Employee should not be found");
	}

	@Test
	public void shouldDeleteEmployee() {

		employeeRepository.deleteById(1L);

		verify(employeeRepository, times(1)).deleteById(1L);
	}
}
