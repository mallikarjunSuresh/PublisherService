package com.pk.engineering.publisher.service;

import java.util.UUID;

import com.pk.engineering.publisher.model.Request;

public interface PayloadService {

	String generatePayload(UUID activityId, UUID transactionalId, Request customerResponse);

}