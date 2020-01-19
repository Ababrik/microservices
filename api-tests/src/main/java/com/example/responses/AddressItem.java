package com.example.responses;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Getter;
import lombok.Setter;

import javax.annotation.Generated;


@Setter
@Getter
@Generated("com.robohorse.robopojogenerator")
public class AddressItem{

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



	@Override
 	public String toString(){
		return 
			"AddressItem{" + 
			"number = '" + number + '\'' + 
			",country = '" + country + '\'' + 
			",city = '" + city + '\'' + 
			",_links = '" + links + '\'' + 
			",street = '" + street + '\'' + 
			",postcode = '" + postcode + '\'' + 
			"}";
		}
}