package com.example.alagactivity8point1;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.util.HashMap;
import java.util.Map;

public class PcItems extends AppCompatActivity {

    private DatabaseReference mCartDatabase;
    private DatabaseReference mWishlistDatabase;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_pc_items);

        mCartDatabase = FirebaseDatabase.getInstance().getReference("Cart");
        mWishlistDatabase = FirebaseDatabase.getInstance().getReference("Wishlist");

        ImageView backToGadgetShop = findViewById(R.id.backToGadgetShop);
        backToGadgetShop.setOnClickListener(v -> {
            Intent intent = new Intent(PcItems.this, GadgetShop.class);
            startActivity(intent);
        });

        setupAddToCartButtons();
        setupAddToWishlistButtons();

        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });
    }

    private void setupAddToCartButtons() {
        findViewById(R.id.addToCartImacM3).setOnClickListener(v -> addItemToCart("iMac M3", "₱84,990"));
        findViewById(R.id.addToCartMacStudio).setOnClickListener(v -> addItemToCart("Mac Studio", "₱129,990"));
        findViewById(R.id.addToCartStudioDisplay).setOnClickListener(v -> addItemToCart("Studio Display", "₱126,469.80"));
        findViewById(R.id.addToCartMacbookAir).setOnClickListener(v -> addItemToCart("MacBook Air", "₱71,890"));
        findViewById(R.id.addToCartMacMini).setOnClickListener(v -> addItemToCart("Mac Mini", "₱36,990"));
        findViewById(R.id.addToCartMacM1).setOnClickListener(v -> addItemToCart("iMac with Apple M1 chip", "₱79,990"));
    }

    private void setupAddToWishlistButtons() {
        findViewById(R.id.addToWishlistImacM3).setOnClickListener(v -> addItemToWishlist("iMac M3", "₱84,990"));
        findViewById(R.id.addToWishlistMacStudio).setOnClickListener(v -> addItemToWishlist("Mac Studio", "₱129,990"));
        findViewById(R.id.addToWishlistStudioDisplay).setOnClickListener(v -> addItemToWishlist("Studio Display", "₱126,469.80"));
        findViewById(R.id.addToWishlistMacbookAir).setOnClickListener(v -> addItemToWishlist("MacBook Air", "₱71,890"));
        findViewById(R.id.addToWishlistMacMini).setOnClickListener(v -> addItemToWishlist("Mac Mini", "₱36,990"));
        findViewById(R.id.addToWishlistMacM1).setOnClickListener(v -> addItemToWishlist("iMac with Apple M1 chip", "₱79,990"));
    }

    private void addItemToCart(String itemName, String itemPrice) {
        String userId = "user1";  // Replace with actual user ID

        Map<String, String> item = new HashMap<>();
        item.put("name", itemName);
        item.put("price", itemPrice);

        mCartDatabase.child(userId).push().setValue(item).addOnCompleteListener(task -> {
            if (task.isSuccessful()) {
                Toast.makeText(PcItems.this, itemName + " added to cart", Toast.LENGTH_SHORT).show();
            } else {
                Toast.makeText(PcItems.this, "Failed to add item to cart", Toast.LENGTH_SHORT).show();
            }
        });
    }

    private void addItemToWishlist(String itemName, String itemPrice) {
        String userId = "user1";  // Replace with actual user ID

        Map<String, String> item = new HashMap<>();
        item.put("name", itemName);
        item.put("price", itemPrice);

        mWishlistDatabase.child(userId).push().setValue(item).addOnCompleteListener(task -> {
            if (task.isSuccessful()) {
                Toast.makeText(PcItems.this, itemName + " added to wishlist", Toast.LENGTH_SHORT).show();
            } else {
                Toast.makeText(PcItems.this, "Failed to add item to wishlist", Toast.LENGTH_SHORT).show();
            }
        });
    }
}
