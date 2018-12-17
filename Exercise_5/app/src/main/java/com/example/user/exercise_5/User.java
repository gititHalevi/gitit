package com.example.user.exercise_5;

import java.io.Serializable;
import java.security.InvalidParameterException;

public class User implements Serializable {
    public static final String DELIMIER = "&";
    private String userName, password;

    public User(String userName, String password){
        this.userName = userName;
        this.password = password;
    }
    public User(){
    }
    public User(String userAsString){
        if (userAsString== null)
            throw new InvalidParameterException();
        String[] parts = userAsString.split(DELIMIER);
        if (parts.length != 2)
            throw new InvalidParameterException("must have two parts");
        this.userName = parts[0];
        this.password = parts[1];
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

    @Override
    public String toString() {
        return userName + DELIMIER + password;
    }

}
