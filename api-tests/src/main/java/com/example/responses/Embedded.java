package com.example.responses;

import java.util.List;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Getter;
import lombok.Setter;

import javax.annotation.Generated;

@Setter
@Getter
@Generated("com.robohorse.robopojogenerator")
public class Embedded {

    @JsonProperty("customer")
    private List<CustomerItem> customer;

    @JsonProperty("address")
    private List<AddressItem> address;

    @JsonProperty("card")
    private List<CardItem> card;


    public String embededCustomerToString() {
        return "Embedded{" +
                "customer = '" + customer + '\'' +
                "}".toString();
    }

    public String embededAddressTostring() {
        return "Embedded{" +
                "address = '" + address + '\'' +
                "}".toString();
    }

}