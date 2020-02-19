package com.example.responses;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Getter;
import lombok.Setter;

import javax.annotation.Generated;
@Getter
@Setter
@Generated("com.robohorse.robopojogenerator")
public class PostAddressResponse {

    @JsonProperty("id")
    private String id;

    @Override
    public String toString() {
        return
                "PostAddressResponse{" +
                        "id = '" + id + '\'' +
                        "}";
    }
}