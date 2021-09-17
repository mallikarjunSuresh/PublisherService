package com.pk.engineering.publisher.service;

import com.pk.engineering.publisher.model.CustomerPayload;

public interface SuccessPayloadService {

  String generatePayload(CustomerPayload payload);

}
