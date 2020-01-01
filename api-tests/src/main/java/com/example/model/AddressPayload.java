package com.example.model;

import com.fasterxml.jackson.annotation.JsonProperty;
import javax.annotation.Generated;
import lombok.Getter;
import lombok.Setter;
import lombok.experimental.Accessors;


@Accessors(chain = true)
@Setter
@Getter
@Generated("com.robohorse.robopojogenerator")
public class AddressPayload{

	@JsonProperty("number")
	private String number;

	@JsonProperty("country")
	private String country;

	@JsonProperty("city")
	private String city;

	@JsonProperty("street")
	private String street;

	@JsonProperty("postcode")
	private String postcode;

	@JsonProperty("userID")
	private String userID;

	@Override
 	public String toString(){
		return 
			"AddressPayload{" + 
			"number = '" + number + '\'' + 
			",country = '" + country + '\'' + 
			",city = '" + city + '\'' + 
			",street = '" + street + '\'' + 
			",postcode = '" + postcode + '\'' + 
			",userID = '" + userID + '\'' + 
			"}";
		}
}