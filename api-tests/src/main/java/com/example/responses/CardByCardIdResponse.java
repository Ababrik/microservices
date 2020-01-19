package com.example.responses;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Getter;
import lombok.Setter;

import javax.annotation.Generated;


@Setter
@Getter
@Generated("com.robohorse.robopojogenerator")
public class CardByCardIdResponse {

    @JsonProperty("expires")
    private String expires;

    @JsonProperty("longNum")
    private String longNum;

    @JsonProperty("_links")
    private Links links;

    @JsonProperty("ccv")
    private String ccv;


    @Override
    public String toString() {
        return
                "CardByCardIdResponse{" +
                        "expires = '" + expires + '\'' +
                        ",longNum = '" + longNum + '\'' +
                        ",_links = '" + links + '\'' +
                        ",ccv = '" + ccv + '\'' +
                        "}";
    }
}