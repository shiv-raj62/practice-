package com.school.exception;

import com.school.response.ApiResponse;
import com.school.response.ResponseHandler;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class GlobalExceptionHandler {


	@ExceptionHandler(ResourceNotFoundException.class)
	public ResponseEntity<ApiResponse> handleResourceNotFoundException(ResourceNotFoundException ex) {
		return ResponseHandler.generateResponse(ex.getMessage(), HttpStatus.NOT_FOUND, null);

	}


	@ExceptionHandler(ValidationException.class)
	public ResponseEntity<ApiResponse> handleValidationException(ValidationException ex) {
		return ResponseHandler.generateResponse(ex.getMessage(), HttpStatus.BAD_REQUEST, null);

	}


	@ExceptionHandler(Exception.class)
	public ResponseEntity<ApiResponse> handleGenericException(Exception ex) {
		return ResponseHandler.generateResponse("An unexpected error occurred: " + ex.getMessage(),
				HttpStatus.INTERNAL_SERVER_ERROR, null);

	}
}
