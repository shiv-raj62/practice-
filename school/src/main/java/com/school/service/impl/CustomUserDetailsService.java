package com.school.service.impl;

import com.school.entity.Student;
import com.school.repository.RegistrationRepository;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
public class CustomUserDetailsService implements UserDetailsService {

    private final RegistrationRepository studentRepository;

    public CustomUserDetailsService(RegistrationRepository studentRepository) {
        this.studentRepository = studentRepository;
    }

    @Override
    public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
        Student student = studentRepository.findByEmail(email)
                .orElseThrow(() -> new UsernameNotFoundException("User not found with email: " + email));

        return User.builder()
                .username(student.getEmail())
                .password(student.getPassword())
                .roles(student.getRoles().stream()
                        .map(role -> role.getName())
                        .toArray(String[]::new))
                .build();
    }
}
