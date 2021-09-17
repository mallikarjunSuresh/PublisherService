package com.pk.engineering.publisher.exception;

import java.io.IOException;
import java.io.OutputStream;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.AuthenticationEntryPoint;
import org.springframework.stereotype.Component;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.pk.engineering.publisher.model.FailureResponse;

@Component
public class AuthorizationHandler implements AuthenticationEntryPoint {

  @Override
  public void commence(HttpServletRequest httpServletRequest,
      HttpServletResponse httpServletResponse, AuthenticationException e)
      throws IOException, ServletException {

    FailureResponse failureResponse = new FailureResponse();
    failureResponse.setStatus("failed");
    failureResponse.setMessage(e.getMessage());
    failureResponse.setErrorType("Unauthorised token Exception");

    OutputStream out = httpServletResponse.getOutputStream();
    ObjectMapper mapper = new ObjectMapper();
    mapper.writeValue(out, new ResponseEntity<>(failureResponse, HttpStatus.UNAUTHORIZED));
    out.flush();
  }
}
