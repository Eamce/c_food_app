package com.example.c_food_app;

import android.graphics.Bitmap;
import android.graphics.drawable.Drawable;

public class AdminViewItemClass {

    private String cat_name;
    private String cat_img;
    private String price;
    private String prod_id;

    public AdminViewItemClass(String cat_name, String cat_img, String price, String prod_id) {
        this.cat_name = cat_name;
        this.cat_img = cat_img;
        this.price = price;
        this.prod_id = prod_id;
    }

    public String getCat_name() {
        return cat_name;
    }

    public void setCat_name(String cat_name) {
        this.cat_name = cat_name;
    }

    public String getCat_img() {
        return cat_img;
    }

    public void setCat_img(String cat_img) {
        this.cat_img = cat_img;
    }

    public String getPrice() {
        return price;
    }

    public void setPrice(String price) {
        this.price = price;
    }

    public String getProd_id() {
        return prod_id;
    }

    public void setProd_id(String prod_id) {
        this.prod_id = prod_id;
    }
}
