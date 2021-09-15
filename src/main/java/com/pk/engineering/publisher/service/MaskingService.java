package com.pk.engineering.publisher.service;

import com.pk.engineering.publisher.model.Request;

public interface MaskingService {

	public static String MASK_CHAR = "*";
	
	Request doMasking(Request customerDetails);

}