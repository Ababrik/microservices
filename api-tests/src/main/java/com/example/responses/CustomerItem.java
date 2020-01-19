package com.example.responses;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Getter;
import lombok.Setter;

import javax.annotation.Generated;


@Setter
@Getter
@Generated("com.robohorse.robopojogenerator")
public class CustomerItem {

    @JsonProperty("id")
    private String id;

    @JsonProperty("firstName")
    private String firstName;

    @JsonProperty("lastName")
    private String lastName;

    @JsonProperty("_links")
    private Links links;

    @JsonProperty("username")
    private String username;


    @Override
    public String toString() {
        return
                "CustomerItem{" +
                        "firstName = '" + firstName + '\'' +
                        ",lastName = '" + lastName + '\'' +
                        ",_links = '" + links + '\'' +
                        ",username = '" + username + '\'' +
                        "}";
    }
}