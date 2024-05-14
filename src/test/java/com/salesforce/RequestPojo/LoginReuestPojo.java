package com.salesforce.RequestPojo;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;

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
public class LoginReuestPojo {
	@JsonProperty("username")
	private String username;
	@JsonProperty("password")
    private String password;
	@JsonProperty("client_id")
    private String client_id;
	@JsonProperty("client_secret")
    private String client_secret;
	@JsonProperty("grant_type")
	private String grant_type;

}
