package com.example.responses;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Getter;
import lombok.Setter;

import javax.annotation.Generated;

@Getter
@Setter
@Generated("com.robohorse.robopojogenerator")
public class DeleteCardResponse {

    @JsonProperty("status")
    private boolean status;

    @Override
    public String toString() {
        return
                "DeleteCardResponse{" +
                        "status = '" + status + '\'' +
                        "}";
    }
}