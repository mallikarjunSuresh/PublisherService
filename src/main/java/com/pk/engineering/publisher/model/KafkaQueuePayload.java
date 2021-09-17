package com.pk.engineering.publisher.model;

import java.util.Objects;

public class KafkaQueuePayload {

  public enum Level {

    INFO("info"), ERROR("error");

    private String value;

    Level(String value) {
      this.value = value;
    }

    public String getValue() {
      return value;
    }

  };

  private Level level;

  private String errorType;

  private String errorDesc;

  private CustomerPayload customerPayload;

  public Level getLevel() {
    return level;
  }

  public void setLevel(Level level) {
    this.level = level;
  }

  public String getErrorType() {
    return errorType;
  }

  public void setErrorType(String errorType) {
    this.errorType = errorType;
  }

  public String getErrorDesc() {
    return errorDesc;
  }

  public void setErrorDesc(String errorDesc) {
    this.errorDesc = errorDesc;
  }

  public CustomerPayload getCustomerPayload() {
    return customerPayload;
  }

  public void setCustomerPayload(CustomerPayload customerPayload) {
    this.customerPayload = customerPayload;
  }

  @Override
  public int hashCode() {
    return Objects.hash(customerPayload, errorDesc, errorType, level);
  }

  @Override
  public boolean equals(Object obj) {
    if (this == obj)
      return true;
    if (obj == null)
      return false;
    if (getClass() != obj.getClass())
      return false;
    KafkaQueuePayload other = (KafkaQueuePayload) obj;
    return Objects.equals(customerPayload, other.customerPayload)
        && Objects.equals(errorDesc, other.errorDesc) && Objects.equals(errorType, other.errorType)
        && level == other.level;
  }

  @Override
  public String toString() {
    return "KafkaQueuePayload [level=" + level + ", errorType=" + errorType + ", errorDesc="
        + errorDesc + ", customerPayload=" + customerPayload + "]";
  }


}
