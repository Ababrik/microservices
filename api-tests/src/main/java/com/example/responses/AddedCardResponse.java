package com.example.responses;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Getter;
import lombok.Setter;

import javax.annotation.Generated;

@Setter
@Getter
@Generated("com.robohorse.robopojogenerator")
public class AddedCardResponse {

	@JsonProperty("id")
	private String id;


	@Override
 	public String toString(){
		return 
			"AddedCardResponse{" +
			"id = '" + id + '\'' + 
			"}";
		}
}