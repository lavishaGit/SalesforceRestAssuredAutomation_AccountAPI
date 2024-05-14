package com.salesforce.ResponsePojo;

import java.util.ArrayList;
import java.util.Date;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
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
@JsonIgnoreProperties(ignoreUnknown=true)
public class AccountResponsePojo {
	 public AttributeResonsePojo attributes;
	    @JsonProperty("Id") 
	    public String id;
	    @JsonProperty("isDeleted") 
	    public boolean isDeleted;
	    @JsonProperty("MasterRecordId") 
	    public Object masterRecordId;
	    @JsonProperty("Name") 
	    private String name;
	    @JsonProperty("Type") 
	    public String type;
	    @JsonProperty("ParentId") 
	    public Object parentId;
/*	    @JsonProperty("BillingStreet") 
	    public String billingStreet;
	    @JsonProperty("BillingCity") 
	    public String billingCity;
	    @JsonProperty("BillingState") 
	    public String billingState;
	    @JsonProperty("BillingPostalCode") 
	    public Object billingPostalCode;
	    @JsonProperty("BillingCountry") 
	    public Object billingCountry;
	    @JsonProperty("BillingLatitude") 
	    public Object billingLatitude;
	    @JsonProperty("BillingLongitude") 
	    public Object billingLongitude;
	    @JsonProperty("BillingGeocodeAccuracy") 
    public Object billingGeocodeAccuracy;*/
	    @JsonProperty("BillingResponsePojo") 
	    public BillingResponsePojo billingAddress;
	 /*   @JsonProperty("ShippingStreet") 
	    public String shippingStreet;
	    @JsonProperty("ShippingCity") 
	    public Object shippingCity;
	    @JsonProperty("ShippingState") 
	    public Object shippingState;
	    @JsonProperty("ShippingPostalCode") 
	    public Object shippingPostalCode;
	    @JsonProperty("ShippingCountry") 
	    public Object shippingCountry;
	    @JsonProperty("ShippingLatitude") 
	    public Object shippingLatitude;
	    @JsonProperty("ShippingLongitude") 
	    public Object shippingLongitude;
	    @JsonProperty("ShippingGeocodeAccuracy") 
	    public Object shippingGeocodeAccuracy;
	 //   @JsonProperty("ShippingAddress") 
	 //   public ShippingAddress shippingAddress;
	    @JsonProperty("Phone") 
	    public String phone;
	    @JsonProperty("Fax") 
	    public String fax;
	    @JsonProperty("AccountNumber") 
	    public String accountNumber;
	    @JsonProperty("Website") 
	    public String website;
	    @JsonProperty("PhotoUrl") 
	    public String photoUrl;
	    @JsonProperty("Sic") 
	    public String sic;
	    @JsonProperty("Industry") 
	    public String industry;
	    @JsonProperty("AnnualRevenue") 
	    public double annualRevenue;
	    @JsonProperty("NumberOfEmployees") 
	    public int numberOfEmployees;
	    @JsonProperty("Ownership") 
	    public String ownership;
	    @JsonProperty("TickerSymbol") 
	    public String tickerSymbol;
	    @JsonProperty("Description") 
	    public String description;
	    @JsonProperty("Rating") 
	    public String rating;
	    @JsonProperty("Site") 
	    public Object site;
	    @JsonProperty("OwnerId") 
	    public String ownerId;
	    @JsonProperty("CreatedDate") 
	    public Date createdDate;
	    @JsonProperty("CreatedById") 
	    public String createdById;
	    @JsonProperty("LastModifiedDate") 
	    public Date lastModifiedDate;
	    @JsonProperty("LastModifiedById") 
	    public String lastModifiedById;
	    @JsonProperty("SystemModstamp") 
	    public Date systemModstamp;
	    @JsonProperty("LastActivityDate") 
	    public Object lastActivityDate;
	    @JsonProperty("LastViewedDate") 
	    public Date lastViewedDate;
	    @JsonProperty("LastReferencedDate") 
	    public Date lastReferencedDate;
	    @JsonProperty("Jigsaw") 
	    public Object jigsaw;
	    @JsonProperty("JigsawCompanyId") 
	    public Object jigsawCompanyId;
	    @JsonProperty("CleanStatus") 
	    public String cleanStatus;
	    @JsonProperty("AccountSource") 
	    public Object accountSource;
	    @JsonProperty("DunsNumber") 
	    public Object dunsNumber;
	    @JsonProperty("Tradestyle") 
	    public Object tradestyle;
	    @JsonProperty("NaicsCode") 
	    public Object naicsCode;
	    @JsonProperty("NaicsDesc") 
	    public Object naicsDesc;
	    @JsonProperty("YearStarted") 
	    public Object yearStarted;
	    @JsonProperty("SicDesc") 
	    public Object sicDesc;
	    @JsonProperty("DandbCompanyId") 
	    public Object dandbCompanyId;
	    @JsonProperty("OperatingHoursId") 
	    public Object operatingHoursId;
	    @JsonProperty("CustomerPriority__c") 
	    public String customerPriority__c;
	    @JsonProperty("SLA__c") 
	    public String sLA__c;
	    @JsonProperty("Active__c") 
	    public String active__c;
	    @JsonProperty("NumberofLocations__c") 
	    public double numberofLocations__c;
	    @JsonProperty("UpsellOpportunity__c") 
	    public String upsellOpportunity__c;
	    @JsonProperty("SLASerialNumber__c") 
	    public String sLASerialNumber__c;
	    @JsonProperty("SLAExpirationDate__c") 
	    public String sLAExpirationDate__c;
	    
	    */
}
