package com.example.responses;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Getter;
import lombok.Setter;

import javax.annotation.Generated;
import java.util.List;
@Setter
@Getter
@Generated("com.robohorse.robopojogenerator")
@JsonIgnoreProperties(ignoreUnknown = true)
public class Get_CustomerByCustomerId_Response {

    @JsonProperty("firstName")
    private String firstName;
    @JsonProperty("lastName")
    private String lastName;
    @JsonProperty("links")
    private Links links;
    @JsonProperty("username")
    private String username;


    @Override
    public String toString() {
        return
                "Get_CustomerByCustomerId_Response{" +
                        "firstName = '" + firstName + '\'' +
                        ",lastName = '" + lastName + '\'' +
                        ",_links = '" + links + '\'' +
                        ",username = '" + username + '\'' +
                        "}";
    }
}
