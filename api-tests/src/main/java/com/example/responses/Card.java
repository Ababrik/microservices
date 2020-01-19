package com.example.responses;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Getter;
import lombok.Setter;

import javax.annotation.Generated;


@Setter
@Getter
@Generated("com.robohorse.robopojogenerator")
public class Card{

	@JsonProperty("href")
	private String href;


	@Override
 	public String toString(){
		return 
			"Card{" + 
			"href = '" + href + '\'' + 
			"}";
		}
}