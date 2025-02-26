package com.examination.dto;

import java.time.LocalDate;
import java.util.List;

import lombok.Data;

@Data
public class ExamDTO {


	private String name;
	private LocalDate date;
	private String subject;
	private List<Long> studentIds;

}
