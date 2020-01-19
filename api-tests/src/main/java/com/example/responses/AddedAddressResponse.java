package com.example.responses;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Getter;
import lombok.Setter;

import javax.annotation.Generated;

@Setter
@Getter
@Generated("com.robohorse.robopojogenerator")
@JsonIgnoreProperties(ignoreUnknown = true)
public class AddedAddressResponse {

	@JsonProperty("id")
	private String id;



	@Override
 	public String toString(){
		return 
			"AddedAddressResponse{" +
			"id = '" + id + '\'' + 
			"}";
		}
}