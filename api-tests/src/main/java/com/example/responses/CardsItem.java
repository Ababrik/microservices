package com.example.responses;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Getter;
import lombok.Setter;

import javax.annotation.Generated;

@Getter
@Setter
@Generated("com.robohorse.robopojogenerator")
public class CardsItem {

    @JsonProperty("expires")
    private String expires;

    @JsonProperty("longNum")
    private String longNum;

    @JsonProperty("ccv")
    private String ccv;

    @JsonProperty("id")
    private String id;

    @Override
    public String toString() {
        return
                "CardsItem{" +
                        "expires = '" + expires + '\'' +
                        ",longNum = '" + longNum + '\'' +
                        ",ccv = '" + ccv + '\'' +
                        ",id = '" + id + '\'' +
                        "}";
    }
}