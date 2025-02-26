package com.mindprove.service;


import com.mindprove.dto.EmployeeDto;
import com.mindprove.entity.Employee;

import java.io.IOException;
import java.util.List;
import java.util.Optional;

public interface IEmployeeService {

    Employee saveEmployee(EmployeeDto employeeDto);
    Employee updateEmployee(Long id, EmployeeDto employeeDto);
    void deleteEmployee(Long id);
    Employee getEmployeeById(Long id);
    List<Employee> getAllEmployees();
    
    void downloadAllEmployees() throws IOException;

}
