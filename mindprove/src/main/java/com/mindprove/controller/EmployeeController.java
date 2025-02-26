package com.mindprove.controller;

import com.mindprove.dto.EmployeeDto;
import com.mindprove.entity.Employee;
import com.mindprove.response.ApiResponse;
import com.mindprove.response.ResponseHandler;
import com.mindprove.service.IEmployeeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.FileSystemResource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.io.File;
import java.io.IOException;
import java.util.List;

@RestController
@RequestMapping("/api/employees")
public class EmployeeController {

	@Autowired
	private IEmployeeService employeeService;

	@PostMapping
	public ResponseEntity<ApiResponse> saveEmployee(@RequestBody EmployeeDto employeeDto) {
		Employee employee = employeeService.saveEmployee(employeeDto);
		return ResponseHandler.generateResponse("Employee saved successfully", HttpStatus.CREATED, employee);
	}

	@PutMapping("/{id}")
	public ResponseEntity<ApiResponse> updateEmployee(@PathVariable Long id, @RequestBody EmployeeDto employeeDto) {
		Employee updatedEmployee = employeeService.updateEmployee(id, employeeDto);

		return ResponseHandler.generateResponse("Employee updated successfully", HttpStatus.OK, updatedEmployee);
	}

	@DeleteMapping("/{id}")
	public ResponseEntity<ApiResponse> deleteEmployee(@PathVariable Long id) {
		employeeService.deleteEmployee(id);
		return ResponseHandler.generateResponse("Employee deleted successfully", HttpStatus.OK, null);
	}

	@GetMapping("/{id}")
	public ResponseEntity<ApiResponse> getEmployeeById(@PathVariable Long id) {
		Employee employee = employeeService.getEmployeeById(id);
		return ResponseHandler.generateResponse("Employee retrieved successfully", HttpStatus.OK, employee);
	}

	@GetMapping
	public ResponseEntity<ApiResponse> getAllEmployees() {
		List<Employee> employees = employeeService.getAllEmployees();
		return ResponseHandler.generateResponse("All employees retrieved successfully", HttpStatus.OK, employees);
	}

	@GetMapping("/downloadAll")
	public ResponseEntity<?> downloadAllEmployees() {
		try {

			employeeService.downloadAllEmployees();

			    File file = new File(System.getProperty("java.io.tmpdir") + "/employees.csv");

			if (file.exists()) {
				FileSystemResource resource = new FileSystemResource(file);

				return ResponseEntity.ok()
						.header(HttpHeaders.CONTENT_DISPOSITION, "attachment; filename=" + file.getName())
						.contentType(MediaType.APPLICATION_OCTET_STREAM).body(resource);
			} else {
				return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("File generation failed");
			}
		} catch (IOException e) {
			e.printStackTrace();
			return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Error generating file");
		}
	}

}
