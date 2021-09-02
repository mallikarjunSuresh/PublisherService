package com.pk.engineering.Publisher.model;

import java.util.Objects;
import java.util.UUID;

public class CustomerQueuePayload {

    private UUID ActivityId;
    
    private UUID TransactionId;

    private Request customerRequest;

	public CustomerQueuePayload(UUID activityId, UUID transactionId, Request customerRequest) {
		this.ActivityId = activityId;
		this.TransactionId = transactionId;
		this.customerRequest = customerRequest;
	}

	public UUID getActivityId() {
		return ActivityId;
	}

	public void setActivityId(UUID activityId) {
		ActivityId = activityId;
	}

	public UUID getTransactionId() {
		return TransactionId;
	}

	public void setTransactionId(UUID transactionId) {
		TransactionId = transactionId;
	}

	public Request getCustomerRequest() {
		return customerRequest;
	}

	public void setCustomerRequest(Request customerRequest) {
		this.customerRequest = customerRequest;
	}

	@Override
	public int hashCode() {
		return Objects.hash(ActivityId, TransactionId, customerRequest);
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		CustomerQueuePayload other = (CustomerQueuePayload) obj;
		return Objects.equals(ActivityId, other.ActivityId) && Objects.equals(TransactionId, other.TransactionId)
				&& Objects.equals(customerRequest, other.customerRequest);
	}

	@Override
	public String toString() {
		return "CustomerQueuePayload [ActivityId=" + ActivityId + ", TransactionId=" + TransactionId
				+ ", customerRequest=" + customerRequest + "]";
	}

    
    
}
