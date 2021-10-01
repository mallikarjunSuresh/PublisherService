package com.pk.engineering.publisher.exception;

import java.util.List;
import java.util.stream.Collectors;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.kafka.KafkaException;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.ServletRequestBindingException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.NoHandlerFoundException;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;
import com.pk.engineering.publisher.model.FailureResponse;

@ControllerAdvice
public class CustomerControllerAdvice extends ResponseEntityExceptionHandler {

  private static String failed = "failed";

  @Override
  public ResponseEntity<Object> handleServletRequestBindingException(
      ServletRequestBindingException ex, HttpHeaders headers, HttpStatus status,
      WebRequest request) {

    FailureResponse failureResponse = new FailureResponse();
    failureResponse.setStatus(failed);
    failureResponse.setMessage(ex.getMessage());
    failureResponse.setErrorType("Invalid Header Exception");

    return new ResponseEntity<>(failureResponse, HttpStatus.BAD_REQUEST);
  }

  @Override
  protected ResponseEntity<Object> handleNoHandlerFoundException(NoHandlerFoundException ex,
      HttpHeaders headers, HttpStatus status, WebRequest request) {

    FailureResponse failureResponse = new FailureResponse();
    failureResponse.setStatus(failed);
    failureResponse.setMessage(ex.getMessage());
    failureResponse.setErrorType("Invalid Handler Exception");


    return new ResponseEntity<>(failureResponse, HttpStatus.NOT_FOUND);
  }


  @Override
  public ResponseEntity<Object> handleMethodArgumentNotValid(MethodArgumentNotValidException ex,
      HttpHeaders headers, HttpStatus status, WebRequest request) {

    FailureResponse failureResponse = new FailureResponse();
    failureResponse.setStatus(failed);

    List<String> errors = ex.getBindingResult().getFieldErrors().stream()
        .map(FieldError::getDefaultMessage).collect(Collectors.toList());

    failureResponse.setMessage(errors.toString());
    failureResponse.setErrorType("Invalid Request Exception");

    return new ResponseEntity<>(failureResponse, HttpStatus.BAD_REQUEST);

  }

  @ExceptionHandler(value = Exception.class)
  public ResponseEntity<Object> handleNullPointerException(Exception ex, HttpHeaders headers,
      WebRequest request) {

    FailureResponse failureResponse = new FailureResponse();
    failureResponse.setStatus(failed);
    failureResponse.setMessage(ex.getMessage());
    failureResponse.setErrorType("General Exception");

    return new ResponseEntity<>(failureResponse, HttpStatus.INTERNAL_SERVER_ERROR);
  }

  @ExceptionHandler(value = KafkaException.class)
  public ResponseEntity<Object> serviceNotAvailableHandler(Exception ex) {

    FailureResponse failureResponse = new FailureResponse();
    failureResponse.setStatus(failed);
    failureResponse.setMessage(ex.getMessage());
    failureResponse.setErrorType("Service unavailable Exception");

    return new ResponseEntity<>(failureResponse, HttpStatus.SERVICE_UNAVAILABLE);
  }
}
