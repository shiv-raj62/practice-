package com.school.entity;


import jakarta.persistence.*;
import lombok.Data;

@Entity
@Data
@Table (name = "role")
public class Role {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(unique = true, nullable = false)
    private String name;

  
}

