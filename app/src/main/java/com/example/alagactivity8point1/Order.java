// Order.java
package com.example.alagactivity8point1;

public class Order {
    private int id;
    private String orderDetails;
    private String orderDate;
    private String expectedDeliveryDate;
    private String name;
    private String email;
    private String address;
    private String contactNumber;
    private String deliveryAddress;

    public Order(int id, String orderDetails, String orderDate, String expectedDeliveryDate, String name, String email, String address, String contactNumber, String deliveryAddress) {
        this.id = id;
        this.orderDetails = orderDetails;
        this.orderDate = orderDate;
        this.expectedDeliveryDate = expectedDeliveryDate;
        this.name = name;
        this.email = email;
        this.address = address;
        this.contactNumber = contactNumber;
        this.deliveryAddress = deliveryAddress;
    }

    public int getId() {
        return id;
    }

    public String getOrderDetails() {
        return orderDetails;
    }

    public String getOrderDate() {
        return orderDate;
    }

    public String getExpectedDeliveryDate() {
        return expectedDeliveryDate;
    }

    public String getName() {
        return name;
    }

    public String getEmail() {
        return email;
    }

    public String getAddress() {
        return address;
    }

    public String getContactNumber() {
        return contactNumber;
    }

    public String getDeliveryAddress() {
        return deliveryAddress;
    }
}
