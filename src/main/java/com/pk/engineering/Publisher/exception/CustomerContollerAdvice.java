package com.pk.engineering.Publisher.exception;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.ServletRequestBindingException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.NoHandlerFoundException;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import com.pk.engineering.Publisher.model.FailureResponse;

@ControllerAdvice
public class CustomerContollerAdvice extends ResponseEntityExceptionHandler {

    @Override
	public ResponseEntity<Object> handleServletRequestBindingException(
			ServletRequestBindingException ex, HttpHeaders headers, HttpStatus status, WebRequest request) {

        FailureResponse failureResponse = new FailureResponse();
        failureResponse.setStatus("failed");
        failureResponse.setMessage(ex.getMessage());
        failureResponse.setErrorType("Invalid Header Exception");
        return new ResponseEntity<>(failureResponse, HttpStatus.BAD_REQUEST);
	}
	
    @Override
	protected ResponseEntity<Object> handleNoHandlerFoundException(
			NoHandlerFoundException ex, HttpHeaders headers, HttpStatus status, WebRequest request) {

        FailureResponse failureResponse = new FailureResponse();
        failureResponse.setStatus("failed");
        failureResponse.setMessage(ex.getMessage());
        failureResponse.setErrorType("Invalid Handler Exception");
        return new ResponseEntity<>(failureResponse, HttpStatus.NOT_FOUND);
	}

	
    @Override
    public ResponseEntity<Object>
    handleMethodArgumentNotValid(MethodArgumentNotValidException ex,
                                 HttpHeaders headers,
                                 HttpStatus status, WebRequest request) {

    	FailureResponse failureResponse = new FailureResponse();
        failureResponse.setStatus("failed");

        List<String> errors = ex.getBindingResult()
                .getFieldErrors()
                .stream()
                .map(x -> x.getDefaultMessage())
                .collect(Collectors.toList());
        
        failureResponse.setMessage(errors.toString());
        failureResponse.setErrorType("Invalid Request Exception");

        return new ResponseEntity<>(failureResponse, HttpStatus.BAD_REQUEST);
        
    }
    
    @ExceptionHandler(value = Exception.class)
    public ResponseEntity<Object> handleNullPointerException(Exception ex)
    {
        FailureResponse failureResponse = new FailureResponse();
        failureResponse.setStatus("failed");
        failureResponse.setMessage(ex.getMessage());
        failureResponse.setErrorType("General Exception");
        return new ResponseEntity<>(failureResponse, HttpStatus.INTERNAL_SERVER_ERROR);
    }
    
}
