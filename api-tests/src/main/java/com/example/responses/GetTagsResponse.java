package com.example.responses;

import java.util.List;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
import jdk.nashorn.internal.ir.annotations.Ignore;
import lombok.Getter;
import lombok.Setter;

import javax.annotation.Generated;

@Getter
@Setter
@Generated("com.robohorse.robopojogenerator")
public class GetTagsResponse {

    @JsonProperty("tags")
    private List<String> tags;

    @Override
    public String toString() {
        return
                "GetTagsResponse{" +
                        "tags = '" + tags + '\'' +
                        "}";
    }
}