package com.koobe.common.model.message;

import com.fasterxml.jackson.annotation.JsonSubTypes;
import com.fasterxml.jackson.annotation.JsonSubTypes.Type;
import com.fasterxml.jackson.annotation.JsonTypeInfo;

/**
 * 
 * @author cloude
 * @since 2014-1-20
 */
@JsonTypeInfo(use=JsonTypeInfo.Id.NAME, include=JsonTypeInfo.As.PROPERTY, property="type")
@JsonSubTypes({
	@Type(value=ExtractEpubRequest.class, name="ExtractEpubRequest"),
	@Type(value=ConvertEpubRequest.class, name="ConvertEpubRequest")
})
public class GeneralRequest {
	
	private String action;

	public GeneralRequest(String action) {
		this.action = action;
	}
	
	public String getAction() {
		return action;
	}
	
//	public String toJson() throws JsonProcessingException {
//		ObjectMapper mapper = new ObjectMapper();
//		return mapper.writeValueAsString(this);
//	}
}
