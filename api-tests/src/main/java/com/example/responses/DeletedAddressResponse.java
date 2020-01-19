package com.example.responses;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.*;

import javax.annotation.Generated;



@Setter
@Getter
@Generated("com.robohorse.robopojogenerator")
public class DeletedAddressResponse {

	@JsonProperty
	private String status;



	@Override
 	public String toString(){
		return 
			"DeletedAddressResponse{" +
			"status = '" + status + '\'' + 
			"}";
		}
}