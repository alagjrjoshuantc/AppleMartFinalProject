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

public class AudioItems extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_audio_items);

        findViewById(R.id.addToCartAirpodsPro2ndGen).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                CartItem item = new CartItem("AirPods Pro (2nd generation) with MagSafe Charging Case (USB‑C)", 14690, 1, R.drawable.airpods_pro_2ndgen); // Replace with actual drawable resource
                addToCart(item);
                Toast.makeText(AudioItems.this, "Added to cart: " + item.getName(), Toast.LENGTH_SHORT).show();
            }
        });

        findViewById(R.id.addToWishlistAirpods2ndGen).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                WishlistItem item = new WishlistItem("AirPods Pro (2nd generation) with MagSafe Charging Case (USB‑C)", 14690, R.drawable.airpods_pro_2ndgen); // Replace with actual drawable resource
                addToWishlist(item);
                Toast.makeText(AudioItems.this, "Added to wishlist: " + item.getName(), Toast.LENGTH_SHORT).show();
            }
        });

        findViewById(R.id.addToCartEarpodsUSBC).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                CartItem item = new CartItem("EarPods with USB-C", 1190, 1, R.drawable.earpods_usbc); // Replace with actual drawable resource
                addToCart(item);
                Toast.makeText(AudioItems.this, "Added to cart: " + item.getName(), Toast.LENGTH_SHORT).show();
            }
        });

        findViewById(R.id.addToWishlistEarpodsUSBC).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                WishlistItem item = new WishlistItem("EarPods with USB-C", 1190, R.drawable.earpods_usbc); // Replace with actual drawable resource
                addToWishlist(item);
                Toast.makeText(AudioItems.this, "Added to wishlist: " + item.getName(), Toast.LENGTH_SHORT).show();
            }
        });

        findViewById(R.id.addToCartEarpodsLightning).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                CartItem item = new CartItem("EarPods with Lightning Connector", 1390, 1, R.drawable.earpods_lightning); // Replace with actual drawable resource
                addToCart(item);
                Toast.makeText(AudioItems.this, "Added to cart: " + item.getName(), Toast.LENGTH_SHORT).show();
            }
        });

        findViewById(R.id.addToWishlistEarpodsLightning).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                WishlistItem item = new WishlistItem("EarPods with Lightning Connector", 1390, R.drawable.earpods_lightning); // Replace with actual drawable resource
                addToWishlist(item);
                Toast.makeText(AudioItems.this, "Added to wishlist: " + item.getName(), Toast.LENGTH_SHORT).show();
            }
        });

        findViewById(R.id.addToCartAirpods2ndGen).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                CartItem item = new CartItem("AirPods 2nd Gen with Standard Charging Case", 6290, 1, R.drawable.airpods_2ndgen); // Replace with actual drawable resource
                addToCart(item);
                Toast.makeText(AudioItems.this, "Added to cart: " + item.getName(), Toast.LENGTH_SHORT).show();
            }
        });

        findViewById(R.id.addToWishlistAirpodsPro2ndGen).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                WishlistItem item = new WishlistItem("AirPods 2nd Gen with Standard Charging Case", 6290, R.drawable.airpods_2ndgen); // Replace with actual drawable resource
                addToWishlist(item);
                Toast.makeText(AudioItems.this, "Added to wishlist: " + item.getName(), Toast.LENGTH_SHORT).show();
            }
        });

        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });

        ImageView backToGadgetShop = findViewById(R.id.backToGadgetShop);
        backToGadgetShop.setOnClickListener(v -> {
            Intent intent = new Intent(AudioItems.this, GadgetShop.class);
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