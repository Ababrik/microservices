package com.example.responses;

import java.util.List;
import com.fasterxml.jackson.annotation.JsonProperty;
import javax.annotation.Generated;

@Generated("com.robohorse.robopojogenerator")
public class PostOrderResponse{

	@JsonProperty("date")
	private String date;

	@JsonProperty("total")
	private int total;

	@JsonProperty("address")
	private Address address;

	@JsonProperty("shipment")
	private Shipment shipment;

	@JsonProperty("customerId")
	private String customerId;

	@JsonProperty("id")
	private String id;

	@JsonProperty("items")
	private List<ItemsItem> items;

	@JsonProperty("card")
	private Card card;

	@JsonProperty("customer")
	private Customer customer;

	public void setDate(String date){
		this.date = date;
	}

	public String getDate(){
		return date;
	}

	public void setTotal(int total){
		this.total = total;
	}

	public int getTotal(){
		return total;
	}

	public void setAddress(Address address){
		this.address = address;
	}

	public Address getAddress(){
		return address;
	}

	public void setShipment(Shipment shipment){
		this.shipment = shipment;
	}

	public Shipment getShipment(){
		return shipment;
	}

	public void setCustomerId(String customerId){
		this.customerId = customerId;
	}

	public String getCustomerId(){
		return customerId;
	}

	public void setId(String id){
		this.id = id;
	}

	public String getId(){
		return id;
	}

	public void setItems(List<ItemsItem> items){
		this.items = items;
	}

	public List<ItemsItem> getItems(){
		return items;
	}

	public void setCard(Card card){
		this.card = card;
	}

	public Card getCard(){
		return card;
	}

	public void setCustomer(Customer customer){
		this.customer = customer;
	}

	public Customer getCustomer(){
		return customer;
	}

	@Override
 	public String toString(){
		return 
			"PostOrderResponse{" + 
			"date = '" + date + '\'' + 
			",total = '" + total + '\'' + 
			",address = '" + address + '\'' + 
			",shipment = '" + shipment + '\'' + 
			",customerId = '" + customerId + '\'' + 
			",id = '" + id + '\'' + 
			",items = '" + items + '\'' + 
			",card = '" + card + '\'' + 
			",customer = '" + customer + '\'' + 
			"}";
		}
}