package com.mindprove.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class EmployeeDto {

    private String name;


    private double salary;


    private String companyName;


    private Long accountNo;

   
}
