package com.example.responses;

import com.fasterxml.jackson.annotation.JsonProperty;

public class Address{

	@JsonProperty("_href")
	private String href;

	public void setHref(String href){
		this.href = href;
	}

	public String getHref(){
		return href;
	}

	@Override
 	public String toString(){
		return 
			"Address{" + 
			"href = '" + href + '\'' + 
			"}";
		}
}
