package com.example.c_food_app;

import android.graphics.Bitmap;
import android.graphics.drawable.Drawable;
public class Cart {
    private Drawable image;
    private String description;
    private String price;
    private String cart_id;
    private String total;
    private String quantity;
    private Bitmap img_bitmap;

    public Cart(String cart_id, Bitmap img_bitmap, String description, String price, String total, String quantity) {
        this.img_bitmap = img_bitmap;
        this.description = description;
        this.price = price;
        this.cart_id = cart_id;
        this.total=total;
        this.quantity=quantity;
    }

    public Drawable getImage() {
        return image;
    }
    public void setImage(Drawable image) {
        this.image = image;
    }
    public String getDescription() {
        return description;
    }
    public void setDescription(String description) {
        this.description = description;
    }
    public String getPrice() {
        return price;
    }
    public void setPrice(String price) {
        this.price = price;
    }
    public String getCart_id() {
        return cart_id;
    }
    public void setCart_id(String cart_id) {
        this.cart_id = cart_id;
    }
    public Bitmap getImg_bitmap() {
        return img_bitmap;
    }
    public void setImg_bitmap(Bitmap img_bitmap) {
        this.img_bitmap = img_bitmap;
    }
    public String getTotal() {
        return total;
    }
    public void setTotal(String total) {
        this.total = total;
    }
    public String getQuantity() {
        return quantity;
    }
    public void setQuantity(String quantity) {
        this.quantity = quantity;
    }
}