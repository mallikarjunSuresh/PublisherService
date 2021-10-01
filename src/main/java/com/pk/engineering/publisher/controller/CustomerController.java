package com.pk.engineering.publisher.controller;

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
import com.pk.engineering.publisher.model.CustomerPayload;
import com.pk.engineering.publisher.model.CustomerRequest;
import com.pk.engineering.publisher.model.GenericKafkaEvent;
import com.pk.engineering.publisher.model.SuccessResponse;
import com.pk.engineering.publisher.service.KafkaPayloadService;
import com.pk.engineering.publisher.service.KafkaPublisherService;
import com.pk.engineering.publisher.service.MaskingService;
import com.pk.engineering.publisher.util.CreateSuccessResponse;
import com.pk.engineering.publisher.util.ObjectMapperUtil;


@RestController
@RequestMapping(value = "v1")
public class CustomerController {

  private static final Logger log = LoggerFactory.getLogger(CustomerController.class);

  public static final String CUSTOMER_TOPIC = "customer";

  private MaskingService maskingService;

  private KafkaPayloadService<CustomerPayload> payloadService;

  private KafkaPublisherService kafkaPublisherService;

  @Autowired
  public CustomerController(MaskingService maskingService, KafkaPayloadService<CustomerPayload> payloadService,
      KafkaPublisherService mqPublisherService, KafkaPublisherService kafkaPublisherService) {
    this.maskingService = maskingService;
    this.payloadService = payloadService;
    this.kafkaPublisherService = kafkaPublisherService;
  }

  @PostMapping(value = "/customer", produces = {"application/json"},
      consumes = {"application/json"})
  public ResponseEntity<SuccessResponse> addCustomer(
      @RequestHeader(value = "Transaction-Id", required = true) String transactionId,
      @RequestHeader(value = "Activity-Id", required = true) String activityId, @RequestHeader(value = "Authorization", required = true) String authorization,
      @Valid @RequestBody CustomerRequest request) {

    String customerRequest = ObjectMapperUtil.writeValueAsString(maskingService.doMasking(request));
    log.info("Incoming request {0}.", customerRequest);

    CustomerPayload payload = new CustomerPayload(activityId, transactionId, request);
    GenericKafkaEvent<CustomerPayload> payLoadJson = payloadService.generateSucessPayload(payload);
    kafkaPublisherService.publishMessage(CUSTOMER_TOPIC, payLoadJson);

    SuccessResponse response = CreateSuccessResponse.getSucessResponse(customerRequest);
    String customerResponse = ObjectMapperUtil.writeValueAsString(response);
    log.info("Outgoing request {0}.", customerResponse);

    return new ResponseEntity<>(response, HttpStatus.OK);
  }

}
