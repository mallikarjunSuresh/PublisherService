package com.pk.engineering.publisher.service;

import com.pk.engineering.publisher.model.CustomerRequest;

public interface MaskingService {

  public static final String EMAIL_MASK = "(?<=.)[^@\\n](?=[^@\\n]*?@)|(?:(?<=@.)|(?!^)\\G(?=[^@\\n]*$)).(?=.*\\.)";
  public static final String CUSTOMERID_MASK = "(?<=^.{0,3}).";
  public static final String BIRTHDATE_MASK = "(?:\\d{2})-(?:\\d{2})";

  CustomerRequest doMasking(CustomerRequest customerDetails);

}
