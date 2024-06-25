package com.example.alagactivity8point1;

public class WishlistItem {
    private String name;
    private double price;
    private int imageResource; // Assuming you use local drawable resources for images

    public WishlistItem(String name, double price, int imageResource) {
        this.name = name;
        this.price = price;
        this.imageResource = imageResource;
    }

    public String getName() {
        return name;
    }

    public double getPrice() {
        return price;
    }

    public int getImageResource() {
        return imageResource;
    }
}
