package com.pk.engineering.publisher.service;

import java.util.UUID;

import org.springframework.stereotype.Service;

import com.pk.engineering.publisher.model.CustomerQueuePayload;
import com.pk.engineering.publisher.model.Request;
import com.pk.engineering.publisher.util.ObjectMapperUtil;

@Service
public class CustomerQueuePayloadService implements PayloadService {

	@Override
	public String generatePayload(UUID activityId, UUID transactionalId, Request customerResponse) {
		CustomerQueuePayload payload = new CustomerQueuePayload(activityId, transactionalId, customerResponse);
		return ObjectMapperUtil.writeValueAsString(payload);
	}
	
}
