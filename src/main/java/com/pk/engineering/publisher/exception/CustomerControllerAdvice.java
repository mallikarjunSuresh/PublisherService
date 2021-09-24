package com.pk.engineering.publisher.exception;

import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.ServletRequestBindingException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.RequestAttributes;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.NoHandlerFoundException;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;
import com.pk.engineering.publisher.controller.CustomerController;
import com.pk.engineering.publisher.model.CustomerPayload;
import com.pk.engineering.publisher.model.FailureResponse;
import com.pk.engineering.publisher.model.GenericKafkaEvent;
import com.pk.engineering.publisher.model.Request;
import com.pk.engineering.publisher.service.KafkaPayloadServiceImpl;
import com.pk.engineering.publisher.service.MqPublisherService;
import com.pk.engineering.publisher.util.ObjectMapperUtil;

@ControllerAdvice
public class CustomerControllerAdvice extends ResponseEntityExceptionHandler {

  private static final Logger log = LoggerFactory.getLogger(CustomerControllerAdvice.class);

  private static String failed = "failed";

  @Autowired
  private KafkaPayloadServiceImpl<CustomerPayload> payloadService;

  @Autowired
  private MqPublisherService mqPublisherService;

  @Override
  public ResponseEntity<Object> handleServletRequestBindingException(
      ServletRequestBindingException ex, HttpHeaders headers, HttpStatus status,
      WebRequest request) {

    FailureResponse failureResponse = new FailureResponse();
    failureResponse.setStatus(failed);
    failureResponse.setMessage(ex.getMessage());
    failureResponse.setErrorType("Invalid Header Exception");

    publishToMQ(request, failureResponse);

    return new ResponseEntity<>(failureResponse, HttpStatus.BAD_REQUEST);
  }

  @Override
  protected ResponseEntity<Object> handleNoHandlerFoundException(NoHandlerFoundException ex,
      HttpHeaders headers, HttpStatus status, WebRequest request) {

    FailureResponse failureResponse = new FailureResponse();
    failureResponse.setStatus(failed);
    failureResponse.setMessage(ex.getMessage());
    failureResponse.setErrorType("Invalid Handler Exception");

    publishToMQ(request, failureResponse);

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

    publishToMQ(request, failureResponse);

    return new ResponseEntity<>(failureResponse, HttpStatus.BAD_REQUEST);

  }

  @ExceptionHandler(value = Exception.class)
  public ResponseEntity<Object> handleNullPointerException(Exception ex, HttpHeaders headers,
      WebRequest request) {

    FailureResponse failureResponse = new FailureResponse();
    failureResponse.setStatus(failed);
    failureResponse.setMessage(ex.getMessage());
    failureResponse.setErrorType("General Exception");

    publishToMQ(request, failureResponse);

    return new ResponseEntity<>(failureResponse, HttpStatus.INTERNAL_SERVER_ERROR);
  }

  private void publishToMQ(WebRequest request,
      FailureResponse failureResponse) {

    GenericKafkaEvent<CustomerPayload> failurePayload =
        payloadService.generateFailurePayload(failureResponse, getCustomerPayload(request));
    
    mqPublisherService.publishMessage(CustomerController.CUSTOMER_TOPIC,
        ObjectMapperUtil.writeValueAsString(failurePayload));
  }

  private CustomerPayload getCustomerPayload(WebRequest request) {

    CustomerPayload payload = new CustomerPayload();

    payload.setCustomerRequest(
        (Request) request.getAttribute("customerRequest", RequestAttributes.SCOPE_REQUEST));
    if (request.getHeader("Activity-Id") != null) {
      payload.setActivityId(UUID.fromString(request.getHeader("Activity-Id")));
    }
    if (request.getHeader("Transaction-Id") != null) {
      payload.setActivityId(UUID.fromString(request.getHeader("Transaction-Id")));
    }

    return payload;

  }
}
