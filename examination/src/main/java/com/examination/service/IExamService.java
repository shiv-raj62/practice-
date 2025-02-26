package com.examination.service;

import java.util.List;

import com.examination.dto.ExamDTO;
import com.examination.entity.Exam;

public interface IExamService {

	Exam createExam(ExamDTO examDto);

	List<Exam> getAllExams();

	Exam assignStudentToExam(Long examId, Long studentId);
}
