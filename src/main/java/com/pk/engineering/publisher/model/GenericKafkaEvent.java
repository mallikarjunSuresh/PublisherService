package com.pk.engineering.publisher.model;

import java.util.Objects;

public class GenericKafkaEvent<T> {

  private T customerPayload;


  public T getCustomerPayload() {
    return customerPayload;
  }

  public void setCustomerPayload(T customerPayload) {
    this.customerPayload = customerPayload;
  }

  @Override
  public int hashCode() {
    return Objects.hash(customerPayload);
  }

  @Override
  public boolean equals(Object obj) {
    if (this == obj)
      return true;
    if (obj == null)
      return false;
    if (getClass() != obj.getClass())
      return false;
    GenericKafkaEvent other = (GenericKafkaEvent) obj;
    return Objects.equals(customerPayload, other.customerPayload);
  }

  @Override
  public String toString() {
    return "GenericKafkaEvent [customerPayload=" + customerPayload + "]";
  }


}
