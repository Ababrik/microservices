package com.example.responses;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Getter;
import lombok.Setter;

import javax.annotation.Generated;
@Getter
@Setter
@Generated("com.robohorse.robopojogenerator")
public class CardsListResponse{

	@JsonProperty("_embedded")
	private Embedded embedded;

	@JsonProperty("_links")
	private Links links;

	@JsonProperty("page")
	private Page page;


	@Override
 	public String toString(){
		return 
			"CardsListResponse{" + 
			"_embedded = '" + embedded + '\'' + 
			",_links = '" + links + '\'' + 
			",page = '" + page + '\'' + 
			"}";
		}
}