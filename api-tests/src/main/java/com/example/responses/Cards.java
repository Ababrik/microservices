package com.example.responses;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Getter;
import lombok.Setter;

import javax.annotation.Generated;


@Setter
@Getter
@Generated("com.robohorse.robopojogenerator")
public class Cards{

	@JsonProperty("href")
	private String href;

		@Override
 	public String toString(){
		return 
			"Cards{" + 
			"href = '" + href + '\'' + 
			"}";
		}
}