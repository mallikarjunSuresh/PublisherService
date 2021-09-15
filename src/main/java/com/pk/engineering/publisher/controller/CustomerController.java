package com.pk.engineering.publisher.controller;

import java.util.UUID;

import javax.validation.Valid;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.pk.engineering.publisher.model.Request;
import com.pk.engineering.publisher.model.SuccessResponse;
import com.pk.engineering.publisher.service.MQPublisherService;
import com.pk.engineering.publisher.service.MaskingService;
import com.pk.engineering.publisher.service.PayloadService;
import com.pk.engineering.publisher.util.ObjectMapperUtil;


@RestController
@RequestMapping(value="v1")
public class CustomerController {
	
    private static final Logger log = LoggerFactory.getLogger(CustomerController.class);
    
    private static final String CUSTOMER_TOPIC = "customer";
    
    private final MaskingService maskingService;
    
    private final PayloadService payloadService;
    
    private final MQPublisherService mqPublisherService;

    @Autowired
    public CustomerController(MaskingService maskingService, PayloadService payloadService, MQPublisherService mqPublisherService) {
        this.maskingService = maskingService;
        this.payloadService = payloadService;
        this.mqPublisherService = mqPublisherService;
    }

    @PostMapping(value = "/customer", produces = { "application/json" }, consumes = { "application/json" })
    public ResponseEntity<SuccessResponse> addCustomer(@RequestHeader(value="Transaction-Id", required=true) UUID transactionId, @RequestHeader(value="Activity-Id", required=true) UUID activityId,
    		@Valid @RequestBody Request request)  {
		
	    String customerRequest = ObjectMapperUtil.writeValueAsString(maskingService.doMasking(request));
	   	log.info("Incoming request {0}.",customerRequest);   
	   	
    	String payLoadJson = payloadService.generatePayload(activityId, transactionId, request);   	
    	mqPublisherService.publishMessage(CUSTOMER_TOPIC,payLoadJson);
    	
        SuccessResponse response = new SuccessResponse();
        response.setMessage(customerRequest);
        response.setStatus("success");       
	    String customerResponse = ObjectMapperUtil.writeValueAsString(response);
    	log.info("Outgoing request {0}.", customerResponse);
    	
        return new ResponseEntity<>(response,HttpStatus.OK);
    }
	
}
