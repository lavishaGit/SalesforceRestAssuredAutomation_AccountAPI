package com.salesforce.ResponsePojo;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.salesforce.RequestPojo.LoginReuestPojo;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@AllArgsConstructor //Generates an all-args constructor. An all-args constructor requires one argument for every field in the class.
@NoArgsConstructor
@JsonIgnoreProperties(ignoreUnknown=true)
@JsonInclude(JsonInclude.Include.NON_NULL)
public class LoginResponsePojo {
	@JsonProperty("access_token")

	private  String access_token;
	@JsonProperty("instance_url")

private String instance_url;
	@JsonProperty("id")

private String id;
	@JsonProperty("token_type")

private String token_type;
	@JsonProperty("issued_at")

private String issued_at;
	@JsonProperty("signature")

private String signature;
}