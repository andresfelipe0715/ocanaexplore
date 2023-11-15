package com.proyecto_turismo_ufpso.turismo.person.dto;


import com.fasterxml.jackson.annotation.JsonProperty;

public class UserCreateDto {

    @JsonProperty(value = "user_name")
    private String userName;

    @JsonProperty(value = "password")
    private String password;

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }
}
