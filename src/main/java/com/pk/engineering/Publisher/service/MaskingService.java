package com.pk.engineering.Publisher.service;

import com.pk.engineering.Publisher.model.Request;

public interface MaskingService {

	public static String MASK_CHAR = "*";
	
	Request doMasking(Request customerDetails);

}