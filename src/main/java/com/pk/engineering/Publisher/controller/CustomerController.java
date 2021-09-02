package com.pk.engineering.Publisher.controller;

import java.util.UUID;

import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.pk.engineering.Publisher.model.Request;
import com.pk.engineering.Publisher.model.SuccessResponse;
import com.pk.engineering.Publisher.service.MQPublisherService;
import com.pk.engineering.Publisher.service.MaskingService;
import com.pk.engineering.Publisher.service.PayloadService;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.enums.ParameterIn;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;

@RestController
public class CustomerController {
	
    private static final Logger log = LoggerFactory.getLogger(CustomerController.class);

    private final ObjectMapper objectMapper;

    private final HttpServletRequest request;
    
    private final MaskingService maskingService;
    
    private final PayloadService payloadService;
    
    private final MQPublisherService mqPublisherService;

    @Autowired
    public CustomerController(ObjectMapper objectMapper, HttpServletRequest request, MaskingService maskingService, PayloadService payloadService, MQPublisherService mqPublisherService) {
        this.objectMapper = objectMapper;
        this.request = request;
        this.maskingService = maskingService;
        this.payloadService = payloadService;
        this.mqPublisherService = mqPublisherService;
    }

    @Operation(summary = "Add a new customer", description = "", security = {@SecurityRequirement(name = "bearerAuth")    }, tags={  })
    @RequestMapping(value = "/customer", produces = { "application/json" }, consumes = { "application/json" }, method = RequestMethod.POST)
    public ResponseEntity<SuccessResponse> addCustomer(@Parameter(in = ParameterIn.HEADER, description = "" ,required=true,schema=@Schema()) @RequestHeader(value="Transaction-Id", required=true) UUID transactionId,
    		@Parameter(in = ParameterIn.HEADER, description = "" ,required=true,schema=@Schema()) @RequestHeader(value="Activity-Id", required=true) UUID activityId,
    		@Parameter(in = ParameterIn.DEFAULT, description = "", required=true, schema=@Schema()) @Valid @RequestBody Request request)  {
    	
    	String customerRequest = null;		
    	try {
			customerRequest = objectMapper.writeValueAsString(maskingService.doMasking(request));
		} catch (JsonProcessingException e) {
			e.printStackTrace();
			customerRequest = request.toString();
		}    	
    	log.info("Incoming request", customerRequest);
    		
    
    	String payLoadJson = payloadService.generatePayload(activityId, transactionId, request);
    	mqPublisherService.publishMessage(payLoadJson);
    	
        SuccessResponse response = new SuccessResponse();
        response.setMessage(customerRequest);
        response.setStatus("success");
        
    	String customerResponse = null;
    	try {
			customerResponse = objectMapper.writeValueAsString(response);
		} catch (JsonProcessingException e) {
			e.printStackTrace();
			customerResponse = response.toString();
		}	    	
    	log.info("Outgoing request", customerResponse);
    	
        return new ResponseEntity<SuccessResponse>(response,HttpStatus.OK);
    }
	
}
