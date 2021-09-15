package com.pk.engineering.publisher.util;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationFeature;
import com.fasterxml.jackson.datatype.jsr310.JSR310Module;
import com.pk.engineering.publisher.exception.ObjectToJsonStringParseException;

public class ObjectMapperUtil {

	private ObjectMapperUtil() {
	}

	@SuppressWarnings("deprecation")
	private static final ObjectMapper mapper = new ObjectMapper().registerModule(new JSR310Module()).configure(SerializationFeature.WRITE_DATES_AS_TIMESTAMPS, false);
		
	public static String writeValueAsString(Object dataObject) {
		try {
			return mapper.writeValueAsString(dataObject);
		} catch (JsonProcessingException e) {
			e.printStackTrace();
			throw new ObjectToJsonStringParseException(e);
			
		}
	}

}
