package com.pk.engineering.publisher.service;

import org.springframework.stereotype.Service;
import com.pk.engineering.publisher.model.FailureResponse;
import com.pk.engineering.publisher.model.GenericKafkaEvent;
import com.pk.engineering.publisher.model.GenericKafkaEvent.Level;

@Service
public class KafkaPayloadServiceImpl<T> implements MqPayloadService<T> {

  @Override
  public GenericKafkaEvent<T> generateSucessPayload(T payload) {
    GenericKafkaEvent<T> queuePayload = new GenericKafkaEvent<>();
    queuePayload.setLevel(Level.INFO);
    queuePayload.setCustomerPayload(payload);

    return queuePayload;
  }

  @Override
  public GenericKafkaEvent<T> generateFailurePayload(FailureResponse response, T payload) {
    GenericKafkaEvent<T> queuePayload = new GenericKafkaEvent<>();
    queuePayload.setLevel(Level.ERROR);
    queuePayload.setErrorDesc(response.getMessage());
    queuePayload.setErrorType(response.getErrorType());
    queuePayload.setCustomerPayload(payload);
      
    return queuePayload;
  }



}
