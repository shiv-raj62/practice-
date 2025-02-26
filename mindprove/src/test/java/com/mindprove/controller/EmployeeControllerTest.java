package com.mindprove.controller;

import com.mindprove.dto.EmployeeDto;
import com.mindprove.entity.Employee;
import com.mindprove.service.IEmployeeService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import java.util.List;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.eq;
import static org.mockito.Mockito.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@ExtendWith(MockitoExtension.class)
public class EmployeeControllerTest {

    @Mock
    private IEmployeeService employeeService;

    @InjectMocks
    private EmployeeController employeeController;

 
    @Test
    public void shouldSaveEmployee() throws Exception {
    	Employee employee = new Employee(1L, "shivraj", 50000.0, "Mindprove", 123456L);
      	 EmployeeDto   employeeDto = new EmployeeDto("shivraj", 50000.0, "Mindprove", 123456L);
      	 MockMvc  mockMvc = MockMvcBuilders.standaloneSetup(employeeController).build();
      	 
      	 when(employeeService.saveEmployee(any(EmployeeDto.class))).thenReturn(employee);

        mockMvc.perform(post("/api/employees")
                        .contentType(MediaType.APPLICATION_JSON_VALUE)
                        .content("{\"name\":\"shivraj\",\"salary\":50000.0,\"companyName\":\"Mindprove\",\"accountNo\":123456}"))
                .andExpect(status().isCreated())
                .andExpect(jsonPath("$.message").value("Employee saved successfully"))
                .andExpect(jsonPath("$.status").value(201));

        verify(employeeService, times(1)).saveEmployee(any(EmployeeDto.class));
    }

    @Test
    public void shouldUpdateEmployee() throws Exception {
    	Employee employee = new Employee(1L, "shivraj", 50000.0, "Mindprove", 123456L);
     	 EmployeeDto   employeeDto = new EmployeeDto("shivraj", 50000.0, "Mindprove", 123456L);
     	 MockMvc  mockMvc = MockMvcBuilders.standaloneSetup(employeeController).build();
     	 
        mockMvc.perform(put("/api/employees/1")
                        .contentType(MediaType.APPLICATION_JSON_VALUE)
                        .content("{\"name\":\"shivraj\",\"salary\":50000.0,\"companyName\":\"Mindprove\",\"accountNo\":123456}"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.message").value("Employee updated successfully"))
                .andExpect(jsonPath("$.status").value(200));

        verify(employeeService, times(1)).updateEmployee(eq(1L), any(EmployeeDto.class));
    }

    @Test
    public void shouldDeleteEmployee() throws Exception {
    	Employee employee = new Employee(1L, "shivraj", 50000.0, "Mindprove", 123456L);
     	 EmployeeDto   employeeDto = new EmployeeDto("shivraj", 50000.0, "Mindprove", 123456L);
     	 MockMvc  mockMvc = MockMvcBuilders.standaloneSetup(employeeController).build();
     	 
        doNothing().when(employeeService).deleteEmployee(1L);

        mockMvc.perform(delete("/api/employees/1"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.message").value("Employee deleted successfully"))
                .andExpect(jsonPath("$.status").value(200));

        verify(employeeService, times(1)).deleteEmployee(1L);
    }

    @Test
    public void shouldGetEmployeeById() throws Exception {
    	Employee employee = new Employee(1L, "shivraj", 50000.0, "Mindprove", 123456L);
     	 EmployeeDto   employeeDto = new EmployeeDto("shivraj", 50000.0, "Mindprove", 123456L);
     	 MockMvc  mockMvc = MockMvcBuilders.standaloneSetup(employeeController).build();
     	 
        when(employeeService.getEmployeeById(1L)).thenReturn(employee);

        mockMvc.perform(get("/api/employees/1"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.message").value("Employee retrieved successfully"))
                .andExpect(jsonPath("$.status").value(200))
                .andExpect(jsonPath("$.data.name").value("shivraj"));

        verify(employeeService, times(1)).getEmployeeById(1L);
    }

    @Test
    public void shouldGetAllEmployees() throws Exception {
    	Employee employee = new Employee(1L, "shivraj", 50000.0, "Mindprove", 123456L);
     	 EmployeeDto   employeeDto = new EmployeeDto("shivraj", 50000.0, "Mindprove", 123456L);
     	 MockMvc  mockMvc = MockMvcBuilders.standaloneSetup(employeeController).build();
     	 
        when(employeeService.getAllEmployees()).thenReturn(List.of(employee));

        mockMvc.perform(get("/api/employees"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.message").value("All employees retrieved successfully"))
                .andExpect(jsonPath("$.status").value(200))
                .andExpect(jsonPath("$.data[0].name").value("shivraj"));

        verify(employeeService, times(1)).getAllEmployees();
    }
}
