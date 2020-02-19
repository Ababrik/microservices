package com.example.responses;

import java.util.List;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Getter;
import lombok.Setter;

import javax.annotation.Generated;

@Getter
@Setter
@Generated("com.robohorse.robopojogenerator")
public class GetHealthResponse{

	@JsonProperty("health")
	private List<HealthItem> health;


	@Override
 	public String toString(){
		return 
			"GetHealthResponse{" + 
			"health = '" + health + '\'' + 
			"}";
		}
}