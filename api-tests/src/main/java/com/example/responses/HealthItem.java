package com.example.responses;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Getter;
import lombok.Setter;

import javax.annotation.Generated;

@Setter
@Getter
@Generated("com.robohorse.robopojogenerator")
public class HealthItem{

	@JsonProperty("service")
	private String service;

	@JsonProperty("time")
	private String time;

	@JsonProperty("status")
	private String status;

	public void setService(String service){
		this.service = service;
	}

	public String getService(){
		return service;
	}

	public void setTime(String time){
		this.time = time;
	}

	public String getTime(){
		return time;
	}

	public void setStatus(String status){
		this.status = status;
	}

	public String getStatus(){
		return status;
	}

	@Override
 	public String toString(){
		return 
			"HealthItem{" + 
			"service = '" + service + '\'' + 
			",time = '" + time + '\'' + 
			",status = '" + status + '\'' + 
			"}";
		}
}