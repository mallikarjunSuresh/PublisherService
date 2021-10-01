package com.pk.engineering.publisher.model;

import java.util.Objects;

public class CustomerPayload {

  private String activityId;

  private String transactionId;

  private CustomerRequest customerRequest;

  public CustomerPayload(String activityId, String transactionId, CustomerRequest customerRequest) {
    this.activityId = activityId;
    this.transactionId = transactionId;
    this.customerRequest = customerRequest;
  }


  public CustomerPayload() {}


  public String getActivityId() {
    return activityId;
  }


  public void setActivityId(String activityId) {
    this.activityId = activityId;
  }


  public String getTransactionId() {
    return transactionId;
  }


  public void setTransactionId(String transactionId) {
    this.transactionId = transactionId;
  }


  public CustomerRequest getCustomerRequest() {
    return customerRequest;
  }


  public void setCustomerRequest(CustomerRequest customerRequest) {
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
    CustomerPayload other = (CustomerPayload) obj;
    return Objects.equals(activityId, other.activityId)
        && Objects.equals(transactionId, other.transactionId)
        && Objects.equals(customerRequest, other.customerRequest);
  }

  @Override
  public String toString() {
    return "CustomerQueuePayload [activityId=" + activityId + ", transactionId=" + transactionId
        + ", customerRequest=" + customerRequest + "]";
  }



}
