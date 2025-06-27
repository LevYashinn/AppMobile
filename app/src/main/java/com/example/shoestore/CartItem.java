package com.example.shoestore;

import java.io.Serializable;

public class CartItem implements Serializable {
    private String name;
    private String price;
    private int imageResId;
    private int quantity;
    private String color;

    public CartItem(String name, String price, int imageResId, int quantity, String color) {
        this.name = name;
        this.price = price;
        this.imageResId = imageResId;
        this.quantity = quantity;
        this.color = color;
    }

    public String getName() {
        return name;
    }

    public String getPrice() {
        return price;
    }

    public int getImageResId() {
        return imageResId;
    }

    public int getQuantity() {
        return quantity;
    }

    public String getColor() {
        return color;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }
}