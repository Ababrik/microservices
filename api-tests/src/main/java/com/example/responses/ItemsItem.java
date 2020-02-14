package com.example.responses;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Getter;
import lombok.Setter;

import javax.annotation.Generated;

@Getter
@Setter
@Generated("com.robohorse.robopojogenerator")
public class ItemsItem {

    @JsonProperty("unitPrice")
    private int unitPrice;

    @JsonProperty("itemId")
    private String itemId;

    @JsonProperty("quantity")
    private int quantity;

    @JsonProperty("id")
    private String id;

    @Override
    public String toString() {
        return
                "ItemsItem{" +
                        "unitPrice = '" + unitPrice + '\'' +
                        ",itemId = '" + itemId + '\'' +
                        ",quantity = '" + quantity + '\'' +
                        ",id = '" + id + '\'' +
                        "}";
    }
}