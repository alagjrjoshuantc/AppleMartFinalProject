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

public class MoreItems extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_more_items);

        findViewById(R.id.addToCartSiliconCaseIPhone15).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                CartItem item = new CartItem("Silicon Case with MagSafe for iPhone 15 Series", 3390, 1, R.drawable.silicon_case_iphone15); // Replace with actual drawable resource
                addToCart(item);
                Toast.makeText(MoreItems.this, "Added to cart: " + item.getName(), Toast.LENGTH_SHORT).show();
            }
        });

        findViewById(R.id.addToWishlistSiliconCaseIPhone15).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                WishlistItem item = new WishlistItem("Silicon Case with MagSafe for iPhone 15 Series", 3390, R.drawable.silicon_case_iphone15); // Replace with actual drawable resource
                addToWishlist(item);
                Toast.makeText(MoreItems.this, "Added to wishlist: " + item.getName(), Toast.LENGTH_SHORT).show();
            }
        });

        findViewById(R.id.addToCartWalletMagsafe).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                CartItem item = new CartItem("Finewoven Wallet with MagSafe", 3990, 1, R.drawable.wallet_magsafe); // Replace with actual drawable resource
                addToCart(item);
                Toast.makeText(MoreItems.this, "Added to cart: " + item.getName(), Toast.LENGTH_SHORT).show();
            }
        });

        findViewById(R.id.addToWishlistWalletMagsafe).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                WishlistItem item = new WishlistItem("Finewoven Wallet with MagSafe", 3990, R.drawable.wallet_magsafe); // Replace with actual drawable resource
                addToWishlist(item);
                Toast.makeText(MoreItems.this, "Added to wishlist: " + item.getName(), Toast.LENGTH_SHORT).show();
            }
        });

        findViewById(R.id.addToCartMagsafeFloral).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                CartItem item = new CartItem("Clear Case MagSafe Floral for iPhone 14 Series - Daisies", 3250, 1, R.drawable.magsafe_floral); // Replace with actual drawable resource
                addToCart(item);
                Toast.makeText(MoreItems.this, "Added to cart: " + item.getName(), Toast.LENGTH_SHORT).show();
            }
        });

        findViewById(R.id.addToWishlisttMagsafeFloral).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                WishlistItem item = new WishlistItem("Clear Case MagSafe Floral for iPhone 14 Series - Daisies", 3250, R.drawable.magsafe_floral); // Replace with actual drawable resource
                addToWishlist(item);
                Toast.makeText(MoreItems.this, "Added to wishlist: " + item.getName(), Toast.LENGTH_SHORT).show();
            }
        });

        findViewById(R.id.addToCartCameraCase).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                CartItem item = new CartItem("Impact Case MagSafe Camera for iPhone 14 Series - Camera Case Classic", 3250, 1, R.drawable.camera_case); // Replace with actual drawable resource
                addToCart(item);
                Toast.makeText(MoreItems.this, "Added to cart: " + item.getName(), Toast.LENGTH_SHORT).show();
            }
        });

        findViewById(R.id.addToWishlistCameraCase).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                WishlistItem item = new WishlistItem("Clear Case MagSafe Floral for iPhone 14 Series - Daisies", 3250, R.drawable.camera_case); // Replace with actual drawable resource
                addToWishlist(item);
                Toast.makeText(MoreItems.this, "Added to wishlist: " + item.getName(), Toast.LENGTH_SHORT).show();
            }
        });

        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });

        ImageView backToGadgetShop = findViewById(R.id.backToGadgetShop);
        backToGadgetShop.setOnClickListener(v -> {
            Intent intent = new Intent(MoreItems.this, GadgetShop.class);
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