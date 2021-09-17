package com.pk.engineering.publisher.service;

import com.pk.engineering.publisher.model.CustomerPayload;
import com.pk.engineering.publisher.model.FailureResponse;

public interface FailurePayloadService {

  String generatePayload(FailureResponse response, CustomerPayload payload);
}
