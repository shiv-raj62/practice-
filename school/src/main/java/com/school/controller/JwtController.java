package com.school.controller;

import com.school.config.JwtTokenProvider;
import com.school.entity.Student;
import com.school.repository.RegistrationRepository;
import com.school.response.JwtResponse;

import java.util.stream.Collectors;

import org.springframework.http.ResponseEntity;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/public/token")
public class JwtController {

    private final JwtTokenProvider jwtTokenProvider;
    private final RegistrationRepository registrationRepository;

    public JwtController(JwtTokenProvider jwtTokenProvider, RegistrationRepository registrationRepository) {
        this.jwtTokenProvider = jwtTokenProvider;
        this.registrationRepository = registrationRepository;
    }

    @PostMapping
    public ResponseEntity<?> generateToken(@RequestBody Student studentRequest) {
       
        Student student = registrationRepository.findByEmail(studentRequest.getEmail())
                .orElseThrow(() -> new UsernameNotFoundException("User not found with email: " + studentRequest.getEmail()));
        String roles = student.getRoles().stream()
                .map(role -> role.getName())
                .collect(Collectors.joining(","));

        
        String token = jwtTokenProvider.generateToken(student.getEmail(), roles);

        return ResponseEntity.ok(new JwtResponse(token));
}}
