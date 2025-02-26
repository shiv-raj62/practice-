package com.mindprove.service.impl;

import com.mindprove.dto.EmployeeDto;
import com.mindprove.entity.Employee;
import com.mindprove.exception.ResourceNotFoundException;
import com.mindprove.exception.ValidationException;
import com.mindprove.repository.EmployeeRepository;
import com.mindprove.service.IEmployeeService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.List;
import java.util.Optional;
import java.util.logging.Logger;

@Service
@Slf4j
public class EmployeeServiceImpl implements IEmployeeService {



    @Autowired
    private EmployeeRepository employeeRepository;


    @Override
    public Employee saveEmployee(EmployeeDto employeeDto) {

        log.info("saveEmployee started:");
        Optional.ofNullable(employeeDto.getName()).filter(name -> !name.trim().isEmpty()).orElseThrow(() -> {
            log.info("Employee name is invalid.");
            return new ValidationException("Employee name is required.");
        });
        if (employeeDto.getSalary() <= 0) {
            throw new ValidationException("Salary must be greater than zero");
        }
        try {

            Employee employee = new Employee();
            employee.setName(employeeDto.getName());
            employee.setCompanyName(employeeDto.getCompanyName());
            employee.setSalary(employeeDto.getSalary());
            employee.setAccountNo(employeeDto.getAccountNo());

            log.info("Saving employee: {}", employee.getName());
            return employeeRepository.save(employee);
        } catch (ValidationException ex) {

            throw new RuntimeException("Validation error while saving employee .");
        } catch (Exception ex) {

            throw new RuntimeException("An error occurred while saving the employee.");
        }
    }

    @Override
    public Employee updateEmployee(Long id, EmployeeDto employeeDto) {
        log.info("Updating employee with id: {}", id);
        try {
            Optional<Employee> existingEmployeeOpt = employeeRepository.findById(id);

            if (existingEmployeeOpt.isPresent()) {
                Employee existingEmployee = existingEmployeeOpt.get();

                existingEmployee.setName(employeeDto.getName());
                existingEmployee.setSalary(employeeDto.getSalary());
                existingEmployee.setCompanyName(employeeDto.getCompanyName());
                existingEmployee.setAccountNo(employeeDto.getAccountNo());

                log.info("Updating employee with id: {}", id);
                return employeeRepository.save(existingEmployee);
            } else {
                throw new ResourceNotFoundException("Employee not found with id: " + id);
            }
        } catch (ResourceNotFoundException ex) {

            throw new ResourceNotFoundException("Employee not found with id: ");

        } catch (Exception ex) {
             throw new ResourceNotFoundException("An error occurred while updating the employee.");
        }
    }

    @Override
    public void deleteEmployee(Long id) {
        log.info("Deleting employee with id: {}", id);
        try {
            employeeRepository.deleteById(id);
        } catch (Exception ex) {

            throw new RuntimeException("An error occurred while deleting the employee.");
        }
    }

    @Override
    public Employee getEmployeeById(Long id) {
        try {
            return employeeRepository.findById(id)
                    .orElseThrow(() -> new ResourceNotFoundException("Employee not found with id: " + id));
        } catch (ResourceNotFoundException ex) {

            throw new ResourceNotFoundException("Employee not found with id:");
        } catch (Exception ex) {

            throw new ResourceNotFoundException("An error occurred while fetching the employee.");
        }
    }

    @Override
    public List<Employee> getAllEmployees() {
        try {
            log.info("Fetching all employees");
            return employeeRepository.findAll();
        } catch (Exception ex) {

            throw new RuntimeException("An error occurred while fetching employees.");
        }
    }
    
    @Override
    public void downloadAllEmployees() throws IOException {
        List<Employee> employees = getAllEmployees();
        
       
        if (employees == null || employees.isEmpty()) {
            throw new IOException("No employees found");
        }
        
        File file = new File(System.getProperty("java.io.tmpdir") + "/employees.csv");

        
        System.out.println("File path: " + file.getAbsolutePath());

        try (FileWriter writer = new FileWriter(file)) {
            writer.append("ID,Name,Salary,Company,Account No\n");
            
            for (Employee employee : employees) {
                writer.append(String.valueOf(employee.getId()))
                      .append(",")
                      .append(employee.getName())
                      .append(",")
                      .append(String.valueOf(employee.getSalary()))
                      .append(",")
                      .append(employee.getCompanyName())
                      .append(",")
                      .append(String.valueOf(employee.getAccountNo()))
                      .append("\n");
            }
        } catch (IOException e) {
            e.printStackTrace();
            throw new IOException("Error writing to file", e);
        }
    }

}