package com.example.springdata.security;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;

public abstract class SimpleGrantedAuthorityJsonCreator{

    @JsonCreator
    public SimpleGrantedAuthorityJsonCreator(@JsonProperty("authorities") String role){

    }

}
