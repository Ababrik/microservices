package com.example.conditions;

import lombok.experimental.UtilityClass;

@UtilityClass
public class Conditions {
    public static StatusCodeCondition statusCode (int code){
        return new StatusCodeCondition(code);
    }


}

