package com.example.alagactivity8point1;

import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.Toast;

import androidx.appcompat.app.AlertDialog;

import java.util.ArrayList;

public class WishlistActivityV2 extends AppCompatActivity implements WishlistAdapter.OnQuantityChangeListener {
    private RecyclerView wishlistRecyclerView;
    private WishlistAdapter wishlistAdapter;
    private ArrayList<WishlistItem> wishlistItems;
    private Button clearButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_wishlist_v2);

        wishlistRecyclerView = findViewById(R.id.wishlistRecyclerView);
        clearButton = findViewById(R.id.clearButton);

        wishlistItems = WishlistSingleton.getInstance().getWishlistItems();

        if (wishlistItems == null || wishlistItems.isEmpty()) {
            Toast.makeText(this, "Your wishlist is empty!", Toast.LENGTH_SHORT).show();
        }

        // Initialize wishlistAdapter with wishlistItems and OnQuantityChangeListener
        wishlistAdapter = new WishlistAdapter(wishlistItems, this);

        wishlistRecyclerView.setLayoutManager(new LinearLayoutManager(this));
        wishlistRecyclerView.setAdapter(wishlistAdapter);

        clearButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (wishlistItems != null && !wishlistItems.isEmpty()) {
                    // Show confirmation dialog before clearing wishlist
                    showClearWishlistConfirmationDialog();
                } else {
                    Toast.makeText(WishlistActivityV2.this, "Your wishlist is already empty!", Toast.LENGTH_SHORT).show();
                }
            }
        });

        ImageView backToGadgetShop = findViewById(R.id.backToGadgetShop);
        backToGadgetShop.setOnClickListener(v -> {
            Intent intent = new Intent(WishlistActivityV2.this, GadgetShop.class);
            startActivity(intent);
        });
    }

    private void showClearWishlistConfirmationDialog() {
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setTitle("Clear Wishlist");
        builder.setMessage("Are you sure you want to clear your wishlist?");
        builder.setPositiveButton("Yes", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                // Clear wishlist
                wishlistItems.clear();
                wishlistAdapter.notifyDataSetChanged();
                onQuantityChange();
                Toast.makeText(WishlistActivityV2.this, "Wishlist cleared!", Toast.LENGTH_SHORT).show();
            }
        });
        builder.setNegativeButton("No", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                // Do nothing
            }
        });
        builder.show();
    }

    @Override
    public void onQuantityChange() {
        // Handle quantity change (e.g., update UI, show a message, etc.)
        Toast.makeText(this, "Item Removed", Toast.LENGTH_SHORT).show();
    }
}