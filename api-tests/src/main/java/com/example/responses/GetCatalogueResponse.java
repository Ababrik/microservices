package com.example.responses;

import java.util.List;

public class GetCatalogueResponse{
	private int price;
	private List<String> imageUrl;
	private String name;
	private int count;
	private String description;
	private String id;
	private List<String> tag;

	public void setPrice(int price){
		this.price = price;
	}

	public int getPrice(){
		return price;
	}

	public void setImageUrl(List<String> imageUrl){
		this.imageUrl = imageUrl;
	}

	public List<String> getImageUrl(){
		return imageUrl;
	}

	public void setName(String name){
		this.name = name;
	}

	public String getName(){
		return name;
	}

	public void setCount(int count){
		this.count = count;
	}

	public int getCount(){
		return count;
	}

	public void setDescription(String description){
		this.description = description;
	}

	public String getDescription(){
		return description;
	}

	public void setId(String id){
		this.id = id;
	}

	public String getId(){
		return id;
	}

	public void setTag(List<String> tag){
		this.tag = tag;
	}

	public List<String> getTag(){
		return tag;
	}

	@Override
 	public String toString(){
		return 
			"GetCatalogueResponse{" + 
			"price = '" + price + '\'' + 
			",imageUrl = '" + imageUrl + '\'' + 
			",name = '" + name + '\'' + 
			",count = '" + count + '\'' + 
			",description = '" + description + '\'' + 
			",id = '" + id + '\'' + 
			",tag = '" + tag + '\'' + 
			"}";
		}
}