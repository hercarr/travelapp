package com.qacg.travelapp.models;

import com.google.gson.annotations.SerializedName;

/**
 * Modelo que representa al usuario de la aplicación.
 */
public class User {

    @SerializedName("username")
    private String userName;
    private String password;

    public User(String userName, String password) {
        this.userName = userName;
        this.password = password;
    }

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
