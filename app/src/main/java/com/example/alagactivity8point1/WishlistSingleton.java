package com.example.alagactivity8point1;

import java.util.ArrayList;
public class WishlistSingleton {
    private static WishlistSingleton instance;
    private ArrayList<WishlistItem> wishlistItems;

    private WishlistSingleton() {
        wishlistItems = new ArrayList<>();
    }

    public static synchronized WishlistSingleton getInstance() {
        if (instance == null) {
            instance = new WishlistSingleton();
        }
        return instance;
    }

    public ArrayList<WishlistItem> getWishlistItems() {
        return wishlistItems;
    }

    public void addItem(WishlistItem item) {
        for (WishlistItem wishlistItem : wishlistItems) {
            if (wishlistItem.getName().equals(item.getName())) {
                return;
            }
        }
        wishlistItems.add(item);
    }

    public void removeItem(WishlistItem item) {
        wishlistItems.remove(item);
    }
}
