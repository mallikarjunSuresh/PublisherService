package com.pk.engineering.publisher.exception;

public class ObjectToJsonStringParseException extends RuntimeException {

  private static final long serialVersionUID = 1L;

  public ObjectToJsonStringParseException(Throwable e) {
    super("Unable to parse from object to json string", e);
  }
}

