package com.example.shoestore;

import java.util.ArrayList;
import java.util.List;

public class Cart {
    private static final List<CartItem> cartItems = new ArrayList<>();

    public static void addItem(CartItem item) {
        cartItems.add(item);
    }

    public static List<CartItem> getCartItems() {
        return cartItems;
    }

    public static void removeItem(int position) {
        if (position >= 0 && position < cartItems.size()) {
            cartItems.remove(position);
        }
    }

    public static void clearCart() {
        cartItems.clear();
    }
}