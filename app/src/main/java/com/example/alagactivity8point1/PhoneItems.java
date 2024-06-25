package com.example.alagactivity8point1;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

public class PhoneItems extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_phone_items);

        findViewById(R.id.addToCartIphone15Plus).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                CartItem item = new CartItem("Iphone 15 Plus", 54290, 1, R.drawable.iphone15_plus); // Replace with actual drawable resource
                addToCart(item);
                Toast.makeText(PhoneItems.this, "Added to cart: " + item.getName(), Toast.LENGTH_SHORT).show();
            }
        });

        findViewById(R.id.addToWishlistIphone15Plus).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                WishlistItem item = new WishlistItem("Iphone 15 Plus", 54290, R.drawable.iphone15_plus); // Replace with actual drawable resource
                addToWishlist(item);
                Toast.makeText(PhoneItems.this, "Added to wishlist: " + item.getName(), Toast.LENGTH_SHORT).show();
            }
        });

        findViewById(R.id.addToCartIphone14).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                CartItem item = new CartItem("Iphone 14", 39890, 1, R.drawable.iphone14); // Replace with actual drawable resource
                addToCart(item);
                Toast.makeText(PhoneItems.this, "Added to cart: " + item.getName(), Toast.LENGTH_SHORT).show();
            }
        });

        findViewById(R.id.addToWishlistIphone14).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                WishlistItem item = new WishlistItem("Iphone 14", 39890, R.drawable.iphone14); // Replace with actual drawable resource
                addToWishlist(item);
                Toast.makeText(PhoneItems.this, "Added to wishlist: " + item.getName(), Toast.LENGTH_SHORT).show();
            }
        });

        findViewById(R.id.addToCartIphone11).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                CartItem item = new CartItem("Iphone 11", 19850, 1, R.drawable.iphone11); // Replace with actual drawable resource
                addToCart(item);
                Toast.makeText(PhoneItems.this, "Added to cart: " + item.getName(), Toast.LENGTH_SHORT).show();
            }
        });

        findViewById(R.id.addToWishlistIphone11).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                WishlistItem item = new WishlistItem("Iphone 11", 19850, R.drawable.iphone11); // Replace with actual drawable resource
                addToWishlist(item);
                Toast.makeText(PhoneItems.this, "Added to wishlist: " + item.getName(), Toast.LENGTH_SHORT).show();
            }
        });

        findViewById(R.id.addToCartIphone12).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                CartItem item = new CartItem("Iphone 12", 30890, 1, R.drawable.iphone12); // Replace with actual drawable resource
                addToCart(item);
                Toast.makeText(PhoneItems.this, "Added to cart: " + item.getName(), Toast.LENGTH_SHORT).show();
            }
        });

        findViewById(R.id.addToWishlistIphone12).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                WishlistItem item = new WishlistItem("Iphone 12", 30890, R.drawable.iphone12); // Replace with actual drawable resource
                addToWishlist(item);
                Toast.makeText(PhoneItems.this, "Added to wishlist: " + item.getName(), Toast.LENGTH_SHORT).show();
            }
        });

        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });

        ImageView backToGadgetShop = findViewById(R.id.backToGadgetShop);
        backToGadgetShop.setOnClickListener(v -> {
            Intent intent = new Intent(PhoneItems.this, GadgetShop.class);
            startActivity(intent);
        });
    }

    private void addToCart(CartItem item) {
        CartSingleton.getInstance().addItem(item);
    }

    private void addToWishlist(WishlistItem item) {
        WishlistSingleton.getInstance().addItem(item);
    }

}