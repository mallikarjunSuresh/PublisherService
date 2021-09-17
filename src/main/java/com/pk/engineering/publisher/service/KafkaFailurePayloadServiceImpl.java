package com.pk.engineering.publisher.service;

import org.springframework.stereotype.Service;
import com.pk.engineering.publisher.model.CustomerPayload;
import com.pk.engineering.publisher.model.FailureResponse;
import com.pk.engineering.publisher.model.KafkaQueuePayload;
import com.pk.engineering.publisher.model.KafkaQueuePayload.Level;
import com.pk.engineering.publisher.util.ObjectMapperUtil;

@Service
public class KafkaFailurePayloadServiceImpl implements FailurePayloadService {

  @Override
  public String generatePayload(FailureResponse response, CustomerPayload payload) {

    KafkaQueuePayload queuePayload = new KafkaQueuePayload();
    queuePayload.setLevel(Level.ERROR);
    queuePayload.setErrorDesc(response.getMessage());
    queuePayload.setErrorType(response.getErrorType());
    queuePayload.setCustomerPayload(payload);

    return ObjectMapperUtil.writeValueAsString(queuePayload);
  }

}
