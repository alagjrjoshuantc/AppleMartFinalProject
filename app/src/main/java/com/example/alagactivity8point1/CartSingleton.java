package com.example.alagactivity8point1;

import java.util.ArrayList;

public class CartSingleton {
    private static CartSingleton instance;
    private ArrayList<CartItem> cartItems;

    private CartSingleton() {
        cartItems = new ArrayList<>();
    }

    public static synchronized CartSingleton getInstance() {
        if (instance == null) {
            instance = new CartSingleton();
        }
        return instance;
    }

    public ArrayList<CartItem> getCartItems() {
        return cartItems;
    }

    public void addItem(CartItem item) {
        for (CartItem cartItem : cartItems) {
            if (cartItem.getName().equals(item.getName())) {
                cartItem.setQuantity(cartItem.getQuantity() + 1);
                return;
            }
        }
        cartItems.add(item);
    }

    public void removeItem(CartItem item) {
        cartItems.remove(item);
    }
}
