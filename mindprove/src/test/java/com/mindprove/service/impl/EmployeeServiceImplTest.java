package com.mindprove.service.impl;

import com.mindprove.dto.EmployeeDto;
import com.mindprove.entity.Employee;
import com.mindprove.exception.ResourceNotFoundException;
import com.mindprove.exception.ValidationException;
import com.mindprove.repository.EmployeeRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyLong;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
public class EmployeeServiceImplTest {

	@Mock
	private EmployeeRepository employeeRepository;

	@InjectMocks
	private EmployeeServiceImpl employeeService;

	@Test
	public void shouldSaveEmployee() {
		EmployeeDto employeeDto = new EmployeeDto("shivraj", 50000.0, "Mindprove", 123456L);
		Employee employee = new Employee(1L, "shivraj", 50000.0, "Mindprove", 123456L);

		when(employeeRepository.save(any(Employee.class))).thenReturn(employee);

		Employee savedEmployee = employeeService.saveEmployee(employeeDto);

		
		assertEquals("shivraj", savedEmployee.getName());
		assertEquals(50000.0, savedEmployee.getSalary());
		assertEquals("Mindprove", savedEmployee.getCompanyName());
		assertEquals(123456L, savedEmployee.getAccountNo());
	}

	@Test
	public void shouldThrowValidationExceptionForInvalidName() {
		EmployeeDto employeeDto = new EmployeeDto("shivraj", 50000.0, "Mindprove", 123456L);
		Employee employee = new Employee(1L, "shivraj", 50000.0, "Mindprove", 123456L);

		employeeDto.setName("");

		ValidationException exception = assertThrows(ValidationException.class,
				() -> employeeService.saveEmployee(employeeDto));
		assertEquals("Employee name is required.", exception.getMessage());
	}

	@Test
	public void shouldThrowValidationExceptionForInvalidSalary() {
		EmployeeDto employeeDto = new EmployeeDto("shivraj", 50000.0, "Mindprove", 123456L);
		Employee employee = new Employee(1L, "shivraj", 50000.0, "Mindprove", 123456L);

		employeeDto.setSalary(0.0);

		ValidationException exception = assertThrows(ValidationException.class,
				() -> employeeService.saveEmployee(employeeDto));
		assertEquals("Salary must be greater than zero", exception.getMessage());
	}

	@Test
	public void shouldUpdateEmployee() {
		EmployeeDto employeeDto = new EmployeeDto("shivraj", 50000.0, "Mindprove", 123456L);
		Employee employee = new Employee(1L, "shivraj", 50000.0, "Mindprove", 123456L);

		when(employeeRepository.findById(anyLong())).thenReturn(Optional.of(employee));
		when(employeeRepository.save(any(Employee.class))).thenReturn(employee);

		Employee updatedEmployee = employeeService.updateEmployee(1L, employeeDto);

		
		assertEquals("shivraj", updatedEmployee.getName());
		assertEquals(50000.0, updatedEmployee.getSalary());
		assertEquals("Mindprove", updatedEmployee.getCompanyName());
		assertEquals(123456L, updatedEmployee.getAccountNo());
	}

	@Test
	public void shouldThrowExceptionWhenUpdatingNonExistentEmployee() {
		EmployeeDto employeeDto = new EmployeeDto("shivraj", 50000.0, "Mindprove", 123456L);
		Employee employee = new Employee(1L, "shivraj", 50000.0, "Mindprove", 123456L);

		when(employeeRepository.findById(anyLong())).thenReturn(Optional.empty());

		ResourceNotFoundException exception = assertThrows(ResourceNotFoundException.class,
				() -> employeeService.updateEmployee(1L, employeeDto));
		assertEquals("Employee not found with id: ", exception.getMessage());
	}

	@Test
	public void shouldDeleteEmployee() {
		EmployeeDto employeeDto = new EmployeeDto("shivraj", 50000.0, "Mindprove", 123456L);
		Employee employee = new Employee(1L, "shivraj", 50000.0, "Mindprove", 123456L);

		doNothing().when(employeeRepository).deleteById(anyLong());

		employeeService.deleteEmployee(1L);

		verify(employeeRepository, times(1)).deleteById(1L);
	}

	@Test
	public void shouldFetchEmployeeById() {
		EmployeeDto employeeDto = new EmployeeDto("shivraj", 50000.0, "Mindprove", 123456L);
		Employee employee = new Employee(1L, "shivraj", 50000.0, "Mindprove", 123456L);

		when(employeeRepository.findById(anyLong())).thenReturn(Optional.of(employee));

		Employee fetchedEmployee = employeeService.getEmployeeById(1L);

		
		assertEquals("shivraj", fetchedEmployee.getName());
		assertEquals(50000.0, fetchedEmployee.getSalary());
		assertEquals("Mindprove", fetchedEmployee.getCompanyName());
		assertEquals(123456L, fetchedEmployee.getAccountNo());
	}

	@Test
	public void shouldThrowExceptionWhenEmployeeNotFoundById() {
		EmployeeDto employeeDto = new EmployeeDto("shivraj", 50000.0, "Mindprove", 123456L);
		Employee employee = new Employee(1L, "shivraj", 50000.0, "Mindprove", 123456L);

		when(employeeRepository.findById(anyLong())).thenReturn(Optional.empty());

		ResourceNotFoundException exception = assertThrows(ResourceNotFoundException.class,
				() -> employeeService.getEmployeeById(1L));
		assertEquals("Employee not found with id:", exception.getMessage());
	}

	@Test
	public void shouldFetchAllEmployees() {
		EmployeeDto employeeDto = new EmployeeDto("shivraj", 50000.0, "Mindprove", 123456L);
		Employee employee = new Employee(1L, "shivraj", 50000.0, "Mindprove", 123456L);

		List<Employee> employees = new ArrayList<>();
		employees.add(employee);
		when(employeeRepository.findAll()).thenReturn(employees);

		List<Employee> fetchedEmployees = employeeService.getAllEmployees();

		
		assertEquals(1, fetchedEmployees.size());
		assertEquals("shivraj", fetchedEmployees.get(0).getName());
	}
}
