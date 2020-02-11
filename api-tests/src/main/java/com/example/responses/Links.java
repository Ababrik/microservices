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
    private Address address;

    @JsonProperty("cards")
    private Cards cards;

    @JsonProperty("card")
    private Card card;

    @JsonProperty("self")
    private Self self;

    @JsonProperty("customer")
    private Customer customer;

    @JsonProperty("profile")
    private Profile profile;

    @JsonProperty("search")
    private Search search;

//    @Override
//    public String toString() {
//                if (!(getSelf()==null & getCustomer() == null & getAddress() == null & getCards() == null)) {
//            return "Links{" +
//                    "self=" + self +
//                    ", customer=" + customer +
//                    "addresses=" + addresses +
//                    ", cards=" + cards +
//                    '}';
//        } else if (!(getSelf() == null & getCard() == null)) {
//            return "Links{" +
//                    "self=" + self +
//                    ", card=" + card +
//                    '}';
//        } else if (!(getSelf() == null & getAddress() == null)) {
//            return "Links{" +
//                    "self=" + self +
//                    "address=" + address +
//                    '}';
//        } else return "Invalid object field!";
//    }

    @Override
    public String toString() {
        return "Links{" +
                "addresses=" + addresses +
                ", address=" + address +
                ", cards=" + cards +
                ", card=" + card +
                ", self=" + self +
                ", customer=" + customer +
                '}';
    }
}