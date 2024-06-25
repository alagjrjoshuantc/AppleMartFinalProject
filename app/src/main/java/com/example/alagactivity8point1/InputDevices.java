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

public class InputDevices extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_input_devices);

        findViewById(R.id.addToCartMagicKeyboardNumericKeypad).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                CartItem item = new CartItem("Magic Keyboard with Numeric Keypad - US English", 7490, 1, R.drawable.magic_keyboard_numeric_keypad); // Replace with actual drawable resource
                addToCart(item);
                Toast.makeText(InputDevices.this, "Added to cart: " + item.getName(), Toast.LENGTH_SHORT).show();
            }
        });

        findViewById(R.id.addToWishlistMagicKeyboardNumericKeypad).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                WishlistItem item = new WishlistItem("Magic Keyboard with Numeric Keypad - US English", 7490, R.drawable.magic_keyboard_numeric_keypad); // Replace with actual drawable resource
                addToWishlist(item);
                Toast.makeText(InputDevices.this, "Added to wishlist: " + item.getName(), Toast.LENGTH_SHORT).show();
            }
        });

        findViewById(R.id.addToCartMagicKeyboardIPadPro11).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                CartItem item = new CartItem("Magic Keyboard for iPad Pro 11 (3rd-4th Gen) and iPad Air (4th-5th Gen)", 18490, 1, R.drawable.magic_keyboard_ipad_pro11); // Replace with actual drawable resource
                addToCart(item);
                Toast.makeText(InputDevices.this, "Added to cart: " + item.getName(), Toast.LENGTH_SHORT).show();
            }
        });

        findViewById(R.id.addToWishlistMagicKeyboardIPadPro11).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                WishlistItem item = new WishlistItem("Magic Keyboard for iPad Pro 11 (3rd-4th Gen) and iPad Air (4th-5th Gen)", 18490, R.drawable.magic_keyboard_ipad_pro11); // Replace with actual drawable resource
                addToWishlist(item);
                Toast.makeText(InputDevices.this, "Added to wishlist: " + item.getName(), Toast.LENGTH_SHORT).show();
            }
        });

        findViewById(R.id.addToCartTypeCPort).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                CartItem item = new CartItem("Type-C 2-in-1 3 Port USB 3.0 Hub & Ethernet", 2290, 1, R.drawable.typec_port); // Replace with actual drawable resource
                addToCart(item);
                Toast.makeText(InputDevices.this, "Added to cart: " + item.getName(), Toast.LENGTH_SHORT).show();
            }
        });

        findViewById(R.id.addToWishlistTypeCPort).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                WishlistItem item = new WishlistItem("Type-C 2-in-1 3 Port USB 3.0 Hub & Ethernet", 2290, R.drawable.typec_port); // Replace with actual drawable resource
                addToWishlist(item);
                Toast.makeText(InputDevices.this, "Added to wishlist: " + item.getName(), Toast.LENGTH_SHORT).show();
            }
        });

        findViewById(R.id.addToCartMagicMouse).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                CartItem item = new CartItem("Magic Mouse (2021)", 4490, 1, R.drawable.magic_mouse); // Replace with actual drawable resource
                addToCart(item);
                Toast.makeText(InputDevices.this, "Added to cart: " + item.getName(), Toast.LENGTH_SHORT).show();
            }
        });

        findViewById(R.id.addToWishlistMagicMouse).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                WishlistItem item = new WishlistItem("Magic Mouse (2021)", 4490, R.drawable.magic_mouse); // Replace with actual drawable resource
                addToWishlist(item);
                Toast.makeText(InputDevices.this, "Added to wishlist: " + item.getName(), Toast.LENGTH_SHORT).show();
            }
        });

        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });

        ImageView backToGadgetShop = findViewById(R.id.backToGadgetShop);
        backToGadgetShop.setOnClickListener(v -> {
            Intent intent = new Intent(InputDevices.this, GadgetShop.class);
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