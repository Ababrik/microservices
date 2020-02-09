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

    @JsonProperty("card")
    private List<CardItem> card;


    @JsonProperty("customer")
    private List<CustomerItem> customer;

    @JsonProperty("address")
    private List<AddressItem> address;

    @Override
    public String toString() {
        return "Embedded{" +
                "card=" + card +
                ", customer=" + customer +
                ", address=" + address +
                '}';
    }

    //    @Override
//    public String toString() {
//        if (!getCustomer().isEmpty()) {
//            return "Embedded{" +
//                    "customer=" + customer +
//                    '}';
//        } else if (!getAddress().isEmpty()) {
//            return "Embedded{" +
//                    "address=" + address +
//                    '}';
//        } else if (!getCard().isEmpty()){
//            return "Embedded{" +
//                    "card=" + card +
//                    '}';
//        } else return "Invalid object field!";
//    }


}