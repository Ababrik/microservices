package com.example.responses;

import lombok.Getter;
import lombok.Setter;
import javax.annotation.Generated;
import java.util.List;

@Setter
@Getter
@Generated("com.robohorse.robopojogenerator")
public class GetCatalogueResponse{
	private int price;
	private List<String> imageUrl;
	private String name;
	private int count;
	private String description;
	private String id;
	private List<String> tag;



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