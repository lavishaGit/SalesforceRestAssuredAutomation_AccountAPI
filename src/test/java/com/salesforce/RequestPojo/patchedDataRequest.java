package com.salesforce.RequestPojo;

import java.util.ArrayList;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.salesforce.ResponsePojo.AlldataRoot;
import com.salesforce.ResponsePojo.RecentItem;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
@JsonInclude(JsonInclude.Include.NON_NULL)
@JsonIgnoreProperties(ignoreUnknown=true)
public class patchedDataRequest {


    @JsonProperty("name") 
    public String name;
    @JsonProperty("type") 
    public String type;
    @JsonProperty("parentId") 
    public Object parentId;
    @JsonProperty("billingStreet") 
    public String billingStreet;
    @JsonProperty("billingCity") 
    public String billingCity;
    @JsonProperty("billingState") 
    public String billingState;

}