package com.jrtp.advice;

import javax.management.InvalidAttributeValueException;

import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.HttpRequestMethodNotSupportedException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import com.jrtp.exception.InvalidSsnException;



@ControllerAdvice
public class MyControllerAdvice extends ResponseEntityExceptionHandler{

  //User defined or Custom exception 
  @ExceptionHandler(InvalidSsnException.class)
  public ResponseEntity<String> handleInavlidSsnException(InvalidSsnException ex)
  {
	  return new ResponseEntity<>("Invalid SSN occurred",HttpStatus.BAD_REQUEST);
  }
  
  // PRE defined exceptions handling.
   @Override
	protected ResponseEntity<Object> handleHttpRequestMethodNotSupported(HttpRequestMethodNotSupportedException ex,
			HttpHeaders headers, HttpStatus status, WebRequest request) {
		// TODO Auto-generated method stub
		return new ResponseEntity<>("Method not supported",HttpStatus.METHOD_NOT_ALLOWED);
	}
   
   //JVM defined exception customizing
     @ExceptionHandler(InvalidAttributeValueException.class)
   public ResponseEntity<String> handleInvalidAttribute(InvalidAttributeValueException ex){
	   return new ResponseEntity<>("Invalid Id passed"+ ex.getMessage(),HttpStatus.BAD_REQUEST);
   }
   
   
   

}
