package com.salesforce.ResponsePojo;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

	@Data
	@Builder
	@AllArgsConstructor
	@NoArgsConstructor
	@JsonInclude(JsonInclude.Include.NON_NULL)
	public class InvalidAccountResponsePojo {
		@JsonProperty("errorCode") 

	    private String errorCode;
		@JsonProperty("message") 

	   private String message;
	}


