package com.example.responses;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Getter;
import lombok.Setter;

import javax.annotation.Generated;

@Getter
@Setter
@Generated("com.robohorse.robopojogenerator")
public class GetCatalogueSizeResponse {

	@JsonProperty("size")
	private int size;

	@Override
 	public String toString(){
		return 
			"GetCatalogueSizeResponse{" +
			"size = '" + size + '\'' + 
			"}";
		}
}