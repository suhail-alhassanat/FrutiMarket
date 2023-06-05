package com.suhail.frutimarket.models;

import java.io.Serializable;

public class LoginResponse implements Serializable {
    public boolean status;
    public String message;
    public UserDetails object;

    public boolean isStatus() {
        return status;
    }

    public void setStatus(boolean status) {
        this.status = status;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public UserDetails getObject() {
        return object;
    }

    public void setObject(UserDetails object) {
        this.object = object;
    }
}
