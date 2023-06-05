package com.suhail.frutimarket.models;

import java.io.Serializable;
import java.util.List;

public class ProductResponse implements Serializable {
    private boolean status;
    private String message;
    private List<Product> data;

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

    public List<Product> getData() {
        return data;
    }

    public void setData(List<Product> data) {
        this.data = data;
    }
}
