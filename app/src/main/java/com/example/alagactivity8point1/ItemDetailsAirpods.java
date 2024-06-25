package com.example.alagactivity8point1;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
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

public class ItemDetailsAirpods extends AppCompatActivity {

    private DatabaseReference mDatabase;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_item_detail_airpods);

        mDatabase = FirebaseDatabase.getInstance().getReference("Cart");

        ImageView backToGadgetShop = findViewById(R.id.backToGadgetShop);
        backToGadgetShop.setOnClickListener(v -> {
            Intent intent = new Intent(ItemDetailsAirpods.this, GadgetShop.class);
            startActivity(intent);
        });

        findViewById(R.id.addToCartAirpodsMax).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                CartItem item = new CartItem("Airpods Max", 34990, 1, R.drawable.airpods_max); // Replace with actual drawable resource
                addToCart(item);
                Toast.makeText(ItemDetailsAirpods.this, "Added to cart: " + item.getName(), Toast.LENGTH_SHORT).show();
            }
        });

        findViewById(R.id.addToWishlistAirpodsMax).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                WishlistItem item = new WishlistItem("Airpods Max", 34990, R.drawable.airpods_max); // Replace with actual drawable resource
                addToWishlist(item);
                Toast.makeText(ItemDetailsAirpods.this, "Added to wishlist: " + item.getName(), Toast.LENGTH_SHORT).show();
            }
        });

    }

    private void addToCart(CartItem item) {
        CartSingleton.getInstance().addItem(item);
    }

    private void addToWishlist(WishlistItem item) {
        WishlistSingleton.getInstance().addItem(item);
    }
}
