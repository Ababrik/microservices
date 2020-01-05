package com.example.responses;

import java.util.List;
import com.fasterxml.jackson.annotation.JsonProperty;
import javax.annotation.Generated;

@Generated("com.robohorse.robopojogenerator")
public class Embedded{

	@JsonProperty("customer")
	private List<CustomerItem> customer;

	@JsonProperty("address")
	private List<AddressItem> address;


	public void setCustomer(List<CustomerItem> customer){
		this.customer = customer;
	}

	public List<CustomerItem> getCustomer(){
		return customer;
	}

	public void setAddress(List<AddressItem> address){
		this.address = address;
	}

	public List<AddressItem> getAddress(){
		return address;
	}

	public String embededCustomerToString(){
		return "Embedded{" +
				"customer = '" + customer + '\'' +
				"}".toString();
	}

	public String embededAddressTostring(){
		return "Embedded{" +
				"address = '" + address + '\'' +
				"}".toString();
	}

}