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

public class PcItemsV2 extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_pc_items_v2);

        findViewById(R.id.addToCartImacM3).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                CartItem item = new CartItem("iMac M3 (2023)", 84990, 1, R.drawable.imac_m3); // Replace with actual drawable resource
                addToCart(item);
                Toast.makeText(PcItemsV2.this, "Added to cart: " + item.getName(), Toast.LENGTH_SHORT).show();
            }
        });

        findViewById(R.id.addToWishlistImacM3).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                WishlistItem item = new WishlistItem("iMac M3 (2023)", 84990, R.drawable.imac_m3); // Replace with actual drawable resource
                addToWishlist(item);
                Toast.makeText(PcItemsV2.this, "Added to wishlist: " + item.getName(), Toast.LENGTH_SHORT).show();
            }
        });

        findViewById(R.id.addToCartMacStudio).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                CartItem item = new CartItem("Mac Studio (2023)", 129990, 1, R.drawable.mac_studio); // Replace with actual drawable resource
                addToCart(item);
                Toast.makeText(PcItemsV2.this, "Added to cart: " + item.getName(), Toast.LENGTH_SHORT).show();
            }
        });

        findViewById(R.id.addToWishlistMacStudio).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                WishlistItem item = new WishlistItem("Mac Studio (2023)", 129990, R.drawable.mac_studio); // Replace with actual drawable resource
                addToWishlist(item);
                Toast.makeText(PcItemsV2.this, "Added to wishlist: " + item.getName(), Toast.LENGTH_SHORT).show();
            }
        });

        findViewById(R.id.addToCartStudioDisplay).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                CartItem item = new CartItem("Studio Display", 126469.80, 1, R.drawable.studio_display); // Replace with actual drawable resource
                addToCart(item);
                Toast.makeText(PcItemsV2.this, "Added to cart: " + item.getName(), Toast.LENGTH_SHORT).show();
            }
        });

        findViewById(R.id.addToWishlistStudioDisplay).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                WishlistItem item = new WishlistItem("Studio Display", 126469.80, R.drawable.studio_display); // Replace with actual drawable resource
                addToWishlist(item);
                Toast.makeText(PcItemsV2.this, "Added to wishlist: " + item.getName(), Toast.LENGTH_SHORT).show();
            }
        });

        findViewById(R.id.addToCartMacbookAir).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                CartItem item = new CartItem("M2 chip 8-Core CPU 10-Core GPU 8GB", 71890, 1, R.drawable.macbook_air); // Replace with actual drawable resource
                addToCart(item);
                Toast.makeText(PcItemsV2.this, "Added to cart: " + item.getName(), Toast.LENGTH_SHORT).show();
            }
        });

        findViewById(R.id.addToWishlistMacbookAir).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                WishlistItem item = new WishlistItem("M2 chip 8-Core CPU 10-Core GPU 8GB", 71890, R.drawable.macbook_air); // Replace with actual drawable resource
                addToWishlist(item);
                Toast.makeText(PcItemsV2.this, "Added to wishlist: " + item.getName(), Toast.LENGTH_SHORT).show();
            }
        });

        findViewById(R.id.addToCartMacMini).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                CartItem item = new CartItem("Mac Mini", 36990, 1, R.drawable.mac_mini); // Replace with actual drawable resource
                addToCart(item);
                Toast.makeText(PcItemsV2.this, "Added to cart: " + item.getName(), Toast.LENGTH_SHORT).show();
            }
        });

        findViewById(R.id.addToWishlistMacMini).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                WishlistItem item = new WishlistItem("Mac Mini", 36990, R.drawable.mac_mini); // Replace with actual drawable resource
                addToWishlist(item);
                Toast.makeText(PcItemsV2.this, "Added to wishlist: " + item.getName(), Toast.LENGTH_SHORT).show();
            }
        });

        findViewById(R.id.addToCartMacM1).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                CartItem item = new CartItem("iMac with Apple M1 chip", 79990, 1, R.drawable.mac_m1); // Replace with actual drawable resource
                addToCart(item);
                Toast.makeText(PcItemsV2.this, "Added to cart: " + item.getName(), Toast.LENGTH_SHORT).show();
            }
        });

        findViewById(R.id.addToWishlistMacM1).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                WishlistItem item = new WishlistItem("iMac with Apple M1 chip", 79990, R.drawable.mac_m1); // Replace with actual drawable resource
                addToWishlist(item);
                Toast.makeText(PcItemsV2.this, "Added to wishlist: " + item.getName(), Toast.LENGTH_SHORT).show();
            }
        });

        findViewById(R.id.backToGadgetShop).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(PcItemsV2.this, CartActivityV2.class);
                startActivity(intent);
            }
        });

        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });

        ImageView backToGadgetShop = findViewById(R.id.backToGadgetShop);
        backToGadgetShop.setOnClickListener(v -> {
            Intent intent = new Intent(PcItemsV2.this, GadgetShop.class);
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