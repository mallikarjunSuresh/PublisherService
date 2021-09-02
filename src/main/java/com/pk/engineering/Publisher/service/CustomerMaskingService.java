package com.pk.engineering.Publisher.service;

import org.springframework.stereotype.Service;

import com.pk.engineering.Publisher.model.Request;

@Service
public class CustomerMaskingService implements MaskingService {

	public CustomerMaskingService() {
	}

	@Override
	public Request doMasking(Request customerDetails) {
		
		String customerNumber = customerDetails.getCustomerNumber();
		String customerEmail = customerDetails.getEmail();

		int start = 0;
		int end = 0;
		
		start = 6;
		end = 10;
		String customerNumberMasked = valueToMaskConverter(start, end, customerNumber);

		start = 0;
		end = customerEmail.length() >= 4 ? 4 : customerEmail.length()+1 ;
		String customerEmailMasked = valueToMaskConverter(start, end, customerEmail);
				
		customerDetails.setCustomerNumber(customerNumberMasked);
		customerDetails.setEmail(customerEmailMasked);	
		
		return customerDetails;
	}

	private String valueToMaskConverter(int start, int end, String value) {
		StringBuffer strBuff = new StringBuffer(value);
		return strBuff.replace(start, end,  MASK_CHAR.repeat(end-start)).toString(); 

	}
}
