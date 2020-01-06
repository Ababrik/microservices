package com.example.responses;

import com.fasterxml.jackson.annotation.JsonProperty;
import javax.annotation.Generated;

@Generated("com.robohorse.robopojogenerator")
public class UserSingleAddressResponse{

	@JsonProperty("number")
	private String number;

	@JsonProperty("country")
	private String country;

	@JsonProperty("city")
	private String city;

	@JsonProperty("_links")
	private Links links;

	@JsonProperty("street")
	private String street;

	@JsonProperty("postcode")
	private String postcode;

	public void setNumber(String number){
		this.number = number;
	}

	public String getNumber(){
		return number;
	}

	public void setCountry(String country){
		this.country = country;
	}

	public String getCountry(){
		return country;
	}

	public void setCity(String city){
		this.city = city;
	}

	public String getCity(){
		return city;
	}

	public void setLinks(Links links){
		this.links = links;
	}

	public Links getLinks(){
		return links;
	}

	public void setStreet(String street){
		this.street = street;
	}

	public String getStreet(){
		return street;
	}

	public void setPostcode(String postcode){
		this.postcode = postcode;
	}

	public String getPostcode(){
		return postcode;
	}

	@Override
 	public String toString(){
		return 
			"UserSingleAddressResponse{" + 
			"number = '" + number + '\'' + 
			",country = '" + country + '\'' + 
			",city = '" + city + '\'' + 
			",_links = '" + links + '\'' + 
			",street = '" + street + '\'' + 
			",postcode = '" + postcode + '\'' + 
			"}";
		}
}