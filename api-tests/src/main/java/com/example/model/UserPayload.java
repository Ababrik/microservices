package com.example.model;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Builder;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;
import lombok.experimental.Accessors;
import javax.annotation.Generated;


@Accessors(chain = true)
@Setter
@Getter
@Generated("com.robohorse.robopojogenerator")
public class UserPayload {

    @JsonProperty("password")
    private String password;

    @JsonProperty("email")
    private String email;

    @JsonProperty("username")
    private String username;



    @Override
    public String toString() {
        return
                "UserPayload{" +
                        "password = '" + password + '\'' +
                        ",email = '" + email + '\'' +
                        ",username = '" + username + '\'' +
                        "}";
    }

}
