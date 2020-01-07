package com.example.responses;

public class UserDeletedAddressResponse{
	private boolean status;

	public void setStatus(boolean status){
		this.status = status;
	}

	public boolean isStatus(){
		return status;
	}

	@Override
 	public String toString(){
		return 
			"UserDeletedAddressResponse{" + 
			"status = '" + status + '\'' + 
			"}";
		}
}
