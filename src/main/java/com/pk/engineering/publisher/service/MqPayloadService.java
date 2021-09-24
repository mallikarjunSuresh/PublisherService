package com.pk.engineering.publisher.service;

import com.pk.engineering.publisher.model.FailureResponse;
import com.pk.engineering.publisher.model.GenericKafkaEvent;

public interface MqPayloadService<T> {

  GenericKafkaEvent<T> generateSucessPayload(T payload);
  
  GenericKafkaEvent<T> generateFailurePayload(FailureResponse response, T payload);

}
