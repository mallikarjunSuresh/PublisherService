package com.pk.engineering.Publisher.service;

import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.pk.engineering.Publisher.model.CustomerQueuePayload;
import com.pk.engineering.Publisher.model.Request;

@Service
public class CustomerQueuePayloadService implements PayloadService {

    private final ObjectMapper objectMapper;

	@Autowired
   	public CustomerQueuePayloadService() {
		this.objectMapper = new ObjectMapper();

	}

	@Override
	public String generatePayload(UUID ActivityId, UUID TransactionalId, Request customerResponse) {
		CustomerQueuePayload payload = new CustomerQueuePayload(ActivityId, TransactionalId, customerResponse);
		try {
			return objectMapper.writeValueAsString(payload);
		} catch (JsonProcessingException e) {
			e.printStackTrace();
			return payload.toString();
		}
	}
	
}
