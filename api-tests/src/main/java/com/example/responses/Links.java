package com.example.responses;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Getter;
import lombok.Setter;

import javax.annotation.Generated;

@Setter
@Getter
@Generated("com.robohorse.robopojogenerator")
public class Links {

    @JsonProperty("addresses")
    private Addresses addresses;

    @JsonProperty("address")
    private Addresses address;

    @JsonProperty("cards")
    private Cards cards;

    @JsonProperty("self")
    private Self self;

    @JsonProperty("customer")
    private Customer customer;


    public String linksCustomertoString() {
        return
                "Links{" +
                        "addresses = '" + addresses + '\'' +
                        ",cards = '" + cards + '\'' +
                        ",self = '" + self + '\'' +
                        ",customer = '" + customer + '\'' +
                        "}";
    }

    public String linksAddressToString() {
        return
                "Links{" +
                        ",self = '" + self + '\'' +
                        "addresses = '" + addresses + '\'' +
                        "}";
    }

}