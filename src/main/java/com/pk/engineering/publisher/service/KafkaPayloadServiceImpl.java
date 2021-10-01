package com.pk.engineering.publisher.service;

import org.springframework.stereotype.Service;
import com.pk.engineering.publisher.model.GenericKafkaEvent;

@Service
public class KafkaPayloadServiceImpl<T> implements KafkaPayloadService<T> {

  @Override
  public GenericKafkaEvent<T> generateSucessPayload(T payload) {
    GenericKafkaEvent<T> queuePayload = new GenericKafkaEvent<>();
    queuePayload.setCustomerPayload(payload);

    return queuePayload;
  }




}
