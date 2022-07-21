package com.example.c_food_app;

import android.graphics.Bitmap;
import android.graphics.drawable.Drawable;

public class Category {
    private String cat_name;
    private int cat_img;
    private String price;
    private String prod_id;
    private Drawable img;
    private Bitmap bitmap_img;
    private int cart_icon_add;
    public Category(String cat_name,  Drawable img ,String price,int cart_icon_add, String prod_id/* Bitmap bitmap_img*/ ) {
        this.cat_name = cat_name;
        this.cat_img = cat_img;
        this.price=price;
        this.cart_icon_add=cart_icon_add;
        this.img = img;
        this.prod_id=prod_id;
    }
    public String getCat_name() {
        return cat_name;
    }
    public void setCat_name(String cat_name) {
        this.cat_name = cat_name;
    }
    public int getCat_img() {
        return cat_img;
    }
    public void setCat_img(int cat_img) {
        this.cat_img = cat_img;
    }
    public String getPrice() {
        return price;
    }
    public void setPrice(String price) {
        this.price = price;
    }
    public int getCart_icon_add() {
        return cart_icon_add;
    }
    public void setCart_icon_add(int cart_icon_add) {
        this.cart_icon_add = cart_icon_add;
    }
    public Drawable getImg() {
        return img;
    }
    public void setImg(Drawable img) {
        this.img = img;
    }
    public Bitmap getBitmap_img() {
        return bitmap_img;
    }
    public void setBitmap_img(Bitmap bitmap_img) {
        this.bitmap_img = bitmap_img;
    }

    public String getProd_id() {
        return prod_id;
    }

    public void setProd_id(String prod_id) {
        this.prod_id = prod_id;
    }
}
