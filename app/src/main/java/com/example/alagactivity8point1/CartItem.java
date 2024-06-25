package com.example.alagactivity8point1;

public class CartItem {
    private String name;
    private double price;
    private int quantity;
    private int imageResource; // Assuming you use local drawable resources for images

    public CartItem(String name, double price, int quantity, int imageResource) {
        this.name = name;
        this.price = price;
        this.quantity = quantity;
        this.imageResource = imageResource;
    }

    public String getName() {
        return name;
    }

    public double getPrice() {
        return price;
    }

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

    public int getImageResource() {
        return imageResource;
    }
}
