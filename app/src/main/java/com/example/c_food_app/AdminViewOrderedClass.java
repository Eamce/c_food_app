package com.example.c_food_app;

public class AdminViewOrderedClass {

    private String price;
    private String quantity;
    private String total;
    private String user_id;
    private String username;
    private String address;
    private String contact;
    private String status;
    private String cat_image;

    public AdminViewOrderedClass(String price, String quantity, String total, String user_id, String username, String address, String contact, String status, String cat_image) {
        this.price = price;
        this.quantity = quantity;
        this.total = total;
        this.user_id = user_id;
        this.username = username;
        this.address = address;
        this.contact = contact;
        this.status = status;
        this.cat_image = cat_image;
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

    public String getUser_id() {
        return user_id;
    }

    public void setUser_id(String user_id) {
        this.user_id = user_id;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getContact() {
        return contact;
    }

    public void setContact(String contact) {
        this.contact = contact;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getCat_image() {
        return cat_image;
    }

    public void setCat_image(String cat_image) {
        this.cat_image = cat_image;
    }
}
