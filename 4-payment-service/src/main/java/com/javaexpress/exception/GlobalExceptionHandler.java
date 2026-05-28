package com.javaexpress.exception;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.http.converter.HttpMessageNotReadableException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class GlobalExceptionHandler {

	// agar json data shi se nhi aa r h to 
	@ExceptionHandler(HttpMessageNotReadableException.class) 
	public ResponseEntity<ErrorAPI> handleException(HttpMessageNotReadableException ex)
	{
		ErrorAPI errorAPI = new ErrorAPI();
		errorAPI.setError(ex.getMessage());
		errorAPI.setStatus(HttpStatus.BAD_REQUEST.getReasonPhrase());
		errorAPI.setMessage("Malformed JSON Data"); 
		return new ResponseEntity<>(errorAPI,HttpStatus.BAD_REQUEST);
	}
	
	// khi bhi product not found exception call hoga to control yha aaega
	@ExceptionHandler(ResourceNotFoundException.class) 
	public ResponseEntity<ErrorAPI> handleException(ResourceNotFoundException ex) 
	{
		ErrorAPI errorAPI = new ErrorAPI();
		errorAPI.setError(ex.getMessage());
		errorAPI.setStatus(HttpStatus.BAD_REQUEST.getReasonPhrase());
		errorAPI.setMessage("Client side error");
		return new ResponseEntity<>(errorAPI,HttpStatus.BAD_REQUEST);
//		return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(errorAPI);
	}
	
	@ExceptionHandler(OrderServiceException.class) 
	public ResponseEntity<ErrorAPI> handleException(OrderServiceException ex) 
	{
		ErrorAPI errorAPI = new ErrorAPI();
		errorAPI.setError(ex.getMessage());
		errorAPI.setStatus(HttpStatus.SERVICE_UNAVAILABLE.getReasonPhrase());
		errorAPI.setMessage("Order Service is not available.");
//		return new ResponseEntity<>(errorAPI,HttpStatus.BAD_REQUEST);
		return ResponseEntity.status(HttpStatus.SERVICE_UNAVAILABLE).body(errorAPI);
	}
	
	@ExceptionHandler(Exception.class)
	public ResponseEntity<ErrorAPI> handleException(Exception ex)
	{
		ErrorAPI errorAPI = new ErrorAPI();
		errorAPI.setError(ex.getMessage());
		errorAPI.setStatus(HttpStatus.INTERNAL_SERVER_ERROR.getReasonPhrase());
		errorAPI.setMessage("Something went wrong...");
		return new ResponseEntity<>(errorAPI,HttpStatus.INTERNAL_SERVER_ERROR);
	}
}
