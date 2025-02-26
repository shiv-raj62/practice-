package com.examination.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.examination.dto.ExamDTO;
import com.examination.entity.Exam;
import com.examination.entity.Student;
import com.examination.repository.ExamRepository;
import com.examination.repository.StudentRepository;

@Service
public class ExamServiceImpl implements IExamService{

	
	
	@Autowired
    private ExamRepository examRepository;

    @Autowired
    private StudentRepository studentRepository;

    public Exam createExam(ExamDTO examDto) {
    	
    	Exam exam=new Exam();
    	exam.setName(examDto.getName());
    	exam.setSubject(examDto.getSubject());
    	exam.setDate(examDto.getDate());
    	//exam.setStudents(examDto.getStudentIds());
        return examRepository.save(exam);
    }

    public List<Exam> getAllExams() {
        return examRepository.findAll();
    }

    public Exam assignStudentToExam(Long examId, Long studentId) {
        Exam exam = examRepository.findById(examId).orElseThrow(() -> new RuntimeException("Exam not found"));
        Student student = studentRepository.findById(studentId).orElseThrow(() -> new RuntimeException("Student not found"));
        exam.getStudents().add(student);
        return examRepository.save(exam);
    }
}
