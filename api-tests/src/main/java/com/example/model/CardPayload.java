package com.example.model;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Getter;
import lombok.Setter;
import lombok.experimental.Accessors;

import javax.annotation.Generated;


@Accessors(chain = true)
@Setter
@Getter
@Generated("com.robohorse.robopojogenerator")
public class CardPayload {

    @JsonProperty("expires")
    private String expires;

    @JsonProperty("longNum")
    private String longNum;

    @JsonProperty("ccv")
    private String ccv;

    @JsonProperty("userID")
    private String userID;


      @Override
    public String toString() {
        return
                "CardPayload{" +
                        "expires = '" + expires + '\'' +
                        ",longNum = '" + longNum + '\'' +
                        ",ccv = '" + ccv + '\'' +
                        ",userID = '" + userID + '\'' +
                        "}";
    }
}