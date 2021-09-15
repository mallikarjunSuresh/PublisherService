package com.pk.engineering.publisher.model;

import java.util.Objects;
import java.util.UUID;

public class CustomerQueuePayload {

    private UUID activityId;
    
    private UUID transactionId;

    private Request customerRequest;

	public CustomerQueuePayload(UUID activityId, UUID transactionId, Request customerRequest) {
		this.activityId = activityId;
		this.transactionId = transactionId;
		this.customerRequest = customerRequest;
	}


	public UUID getActivityId() {
		return activityId;
	}


	public void setActivityId(UUID activityId) {
		this.activityId = activityId;
	}


	public UUID getTransactionId() {
		return transactionId;
	}


	public void setTransactionId(UUID transactionId) {
		this.transactionId = transactionId;
	}


	public Request getCustomerRequest() {
		return customerRequest;
	}


	public void setCustomerRequest(Request customerRequest) {
		this.customerRequest = customerRequest;
	}


	@Override
	public int hashCode() {
		return Objects.hash(activityId, transactionId, customerRequest);
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
		return Objects.equals(activityId, other.activityId) && Objects.equals(transactionId, other.transactionId)
				&& Objects.equals(customerRequest, other.customerRequest);
	}

	@Override
	public String toString() {
		return "CustomerQueuePayload [activityId=" + activityId + ", transactionId=" + transactionId
				+ ", customerRequest=" + customerRequest + "]";
	}



    
    
}
