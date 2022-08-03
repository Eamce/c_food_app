package com.example.c_food_app;

import android.graphics.Bitmap;
import android.graphics.drawable.Drawable;

public class My_Order_Class {
//    private Drawable image;
//    private String description;
//    private String price;
//    private String cart_id;
//    private String total;
//    private String quantity;
//    private Bitmap img_bitmap;
    private String id, description,price,quantity,total,username,str_address,contact;
    Bitmap cat_image;

    public My_Order_Class(String id, String description, String price, String quantity, String total, String username, String str_address, Bitmap cat_image, String contact) {
        this.id = id;
        this.description = description;
        this.price = price;
        this.quantity = quantity;
        this.total = total;
        this.username = username;
        this.str_address = str_address;
        this.cat_image = cat_image;
        this.contact = contact;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
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

    public String getQuantity() {
        return quantity;
    }

    public void setQuantity(String quantity) {
        this.quantity = quantity;
    }

    public String getTotal() {
        return total;
    }

    public void setTotal(String total) {
        this.total = total;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getStr_address() {
        return str_address;
    }

    public void setStr_address(String str_address) {
        this.str_address = str_address;
    }

    public Bitmap getCat_image() {
        return cat_image;
    }

    public void setCat_image(Bitmap cat_image) {
        this.cat_image = cat_image;
    }

    public String getContact() {
        return contact;
    }

    public void setContact(String contact) {
        this.contact = contact;
    }
//    public My_Order_Class(String cart_id, Bitmap img_bitmap, String description, String price, String total, String quantity) {
//        this.img_bitmap = img_bitmap;
//        this.description = description;
//        this.price = price;
//        this.cart_id = cart_id;
//        this.total=total;
//        this.quantity=quantity;
//    }
//
//    public Drawable getImage() {
//        return image;
//    }
//    public void setImage(Drawable image) {
//        this.image = image;
//    }
//    public String getDescription() {
//        return description;
//    }
//    public void setDescription(String description) {
//        this.description = description;
//    }
//    public String getPrice() {
//        return price;
//    }
//    public void setPrice(String price) {
//        this.price = price;
//    }
//    public String getCart_id() {
//        return cart_id;
//    }
//    public void setCart_id(String cart_id) {
//        this.cart_id = cart_id;
//    }
//    public Bitmap getImg_bitmap() {
//        return img_bitmap;
//    }
//    public void setImg_bitmap(Bitmap img_bitmap) {
//        this.img_bitmap = img_bitmap;
//    }
//    public String getTotal() {
//        return total;
//    }
//    public void setTotal(String total) {
//        this.total = total;
//    }
//    public String getQuantity() {
//        return quantity;
//    }
//    public void setQuantity(String quantity) {
//        this.quantity = quantity;
//    }
}
