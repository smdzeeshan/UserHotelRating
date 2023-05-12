 package com.mac.UserService.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import com.mac.UserService.payload.ApiResponse;

@ControllerAdvice
public class GlobalExceptionHandler {
	
	@ExceptionHandler(ResourceNotFoundException.class)
	public ResponseEntity<ApiResponse> handlerResourceNotFoundException(Exception ex){
		ApiResponse response = new ApiResponse(ex.getMessage(), false, HttpStatus.NOT_FOUND);
		return new ResponseEntity<ApiResponse>(response, HttpStatus.NOT_FOUND);
	}
}
