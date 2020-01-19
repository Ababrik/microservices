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
public class UserAddressesResponse{

	@JsonProperty("_embedded")
	private Embedded embedded;

	@JsonProperty("_links")
	private Links links;

	@JsonProperty("page")
	private Page page;


	@Override
 	public String toString(){
		return 
			"UserAddressesResponse{" + 
			"_embedded = '" + embedded + '\'' + 
			",_links = '" + links + '\'' + 
			",page = '" + page + '\'' + 
			"}";
		}
}
