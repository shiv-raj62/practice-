package com.mindprove.entity;

import java.util.HashSet;
import java.util.Set;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;



@Table(name="employee")
@Entity
@Builder
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Employee {


    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "name", nullable = false, length = 100)
    private String name;

    @Column(name = "salary", nullable = false)
    private double salary;

    @Column(name = "company_name", nullable = false, length = 150)
    private String companyName;

    @Column(name = "account_no", nullable = false, unique = true)
    private Long accountNo;
    
   
 
}
