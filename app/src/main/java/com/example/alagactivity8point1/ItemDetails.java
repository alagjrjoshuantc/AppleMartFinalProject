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

public class ItemDetails extends AppCompatActivity {

    private DatabaseReference mDatabase;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_item_details);

        mDatabase = FirebaseDatabase.getInstance().getReference("Cart");

        ImageView backToGadgetShop = findViewById(R.id.backToGadgetShop);
        backToGadgetShop.setOnClickListener(v -> {
            Intent intent = new Intent(ItemDetails.this, GadgetShop.class);
            startActivity(intent);
        });

        findViewById(R.id.addToCartIpadAir5thGen).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                CartItem item = new CartItem("Ipad Air 5th Gen", 39790, 1, R.drawable.ipad_air5); // Replace with actual drawable resource
                addToCart(item);
                Toast.makeText(ItemDetails.this, "Added to cart: " + item.getName(), Toast.LENGTH_SHORT).show();
            }
        });

        findViewById(R.id.addToWishlistIpadAir5thGen).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                WishlistItem item = new WishlistItem("Iphone 12", 39790, R.drawable.ipad_air5); // Replace with actual drawable resource
                addToWishlist(item);
                Toast.makeText(ItemDetails.this, "Added to wishlist: " + item.getName(), Toast.LENGTH_SHORT).show();
            }
        });


        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });
    }
    private void addToCart(CartItem item) {
        CartSingleton.getInstance().addItem(item);
    }

    private void addToWishlist(WishlistItem item) {
        WishlistSingleton.getInstance().addItem(item);
    }
}
