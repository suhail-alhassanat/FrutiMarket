package com.suhail.frutimarket.models;

public class OptionsItem {
    private int img_res_id;
    private String text;

    public OptionsItem(int img_res_id, String text) {
        this.img_res_id = img_res_id;
        this.text = text;
    }

    public int getImg_res_id() {
        return img_res_id;
    }

    public void setImg_res_id(int img_res_id) {
        this.img_res_id = img_res_id;
    }

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }
}
