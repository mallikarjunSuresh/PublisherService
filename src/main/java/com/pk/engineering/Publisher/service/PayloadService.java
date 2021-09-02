package com.pk.engineering.Publisher.service;

import java.util.UUID;

import com.pk.engineering.Publisher.model.Request;

public interface PayloadService {

	String generatePayload(UUID ActivityId, UUID TransactionalId, Request customerResponse);

}