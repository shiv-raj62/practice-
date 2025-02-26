package com.mindprove.response;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@NoArgsConstructor
@Getter
@Setter
@Data
@AllArgsConstructor
public class ApiResponse {

	private int status;
	private String message;
	private Object data;


}
