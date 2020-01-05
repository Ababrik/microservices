package com.example.responses;

import com.fasterxml.jackson.annotation.JsonProperty;

import javax.annotation.Generated;

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

    public void setAddresses(Addresses addresses) {
        this.addresses = addresses;
    }

    public Addresses getAddresses() {
        return addresses;
    }

    public void setAddress(Addresses addresses) {
        this.addresses = addresses;
    }

    public Addresses getAddress() {
        return addresses;
    }


    public void setCards(Cards cards) {
        this.cards = cards;
    }

    public Cards getCards() {
        return cards;
    }

    public void setSelf(Self self) {
        this.self = self;
    }

    public Self getSelf() {
        return self;
    }

    public void setCustomer(Customer customer) {
        this.customer = customer;
    }

    public Customer getCustomer() {
        return customer;
    }

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