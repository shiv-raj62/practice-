package com.examination.controller;

import com.examination.dto.ExamDTO;
import com.examination.entity.Exam;
import com.examination.service.IExamService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/exams")
public class ExamController {

    @Autowired
    private IExamService examService;

    @PostMapping
    public ResponseEntity<Exam> createExam(@RequestBody ExamDTO examDto) {
        return ResponseEntity.ok(examService.createExam(examDto));
    }

    @GetMapping
    public ResponseEntity<List<Exam>> getAllExams() {
        return ResponseEntity.ok(examService.getAllExams());
    }

    @PostMapping("/{examId}/assign-student/{studentId}")
    public ResponseEntity<Exam> assignStudentToExam(
            @PathVariable Long examId,
            @PathVariable Long studentId) {
        return ResponseEntity.ok(examService.assignStudentToExam(examId, studentId));
    }
}
