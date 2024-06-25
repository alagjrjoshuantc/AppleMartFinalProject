package com.example.alagactivity8point1;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

public class GadgetShop extends AppCompatActivity {

    FirebaseAuth auth;
    FirebaseUser user;
    TextView textView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_gadget_shop);

        auth = FirebaseAuth.getInstance();
        user = auth.getCurrentUser();
        textView = findViewById(R.id.emailuser);

        if (user == null) {
            Intent intent = new Intent(getApplicationContext(), MainActivity.class);
            startActivity(intent);
            finish();
        }
        else {
            textView.setText(user.getEmail());
        }

        TextView txtSignOut = findViewById(R.id.txtSignOut);
        txtSignOut.setOnClickListener(v -> {
            FirebaseAuth.getInstance().signOut();

            Intent intent = new Intent(getApplicationContext(), MainActivity.class);
            startActivity(intent);
            finish();
            Toast.makeText(GadgetShop.this, "Logged Out", Toast.LENGTH_SHORT).show();
        });

        ImageView ivItemDetail1 = findViewById(R.id.ivItemDetail1);
        ivItemDetail1.setOnClickListener(v -> {
            Intent intent = new Intent(GadgetShop.this, ItemDetails.class);
            startActivity(intent);
        });

        ImageView ivItemDetail2 = findViewById(R.id.ivItemDetail2);
        ivItemDetail2.setOnClickListener(v -> {
            Intent intent = new Intent(GadgetShop.this, ItemDetailWatch.class);
            startActivity(intent);
        });

        ImageView ivItemDetail3 = findViewById(R.id.ivItemDetail3);
        ivItemDetail3.setOnClickListener(v -> {
            Intent intent = new Intent(GadgetShop.this, ItemDetailsAirpods.class);
            startActivity(intent);
        });

        ImageView cart = findViewById(R.id.cart);
        cart.setOnClickListener(v -> {
            Intent intent = new Intent(GadgetShop.this, CartActivityV2.class);
            startActivity(intent);
        });

        ImageView wishlist = findViewById(R.id.wishlist);
        wishlist.setOnClickListener(v -> {
            Intent intent = new Intent(GadgetShop.this, WishlistActivityV2.class);
            startActivity(intent);
        });

        LinearLayout pcItems = findViewById(R.id.pcItems);
        pcItems.setOnClickListener(v -> {
            Intent intent = new Intent(GadgetShop.this, PcItemsV2.class);
            startActivity(intent);
        });

        LinearLayout phoneItems = findViewById(R.id.phoneItems);
        phoneItems.setOnClickListener(v -> {
            Intent intent = new Intent(GadgetShop.this, PhoneItems.class);
            startActivity(intent);
        });

        LinearLayout audioItems = findViewById(R.id.audioItems);
        audioItems.setOnClickListener(v -> {
            Intent intent = new Intent(GadgetShop.this, AudioItems.class);
            startActivity(intent);
        });

        LinearLayout inputDevices = findViewById(R.id.inputDevices);
        inputDevices.setOnClickListener(v -> {
            Intent intent = new Intent(GadgetShop.this, InputDevices.class);
            startActivity(intent);
        });

        LinearLayout moreItems = findViewById(R.id.moreItems);
        moreItems.setOnClickListener(v -> {
            Intent intent = new Intent(GadgetShop.this, MoreItems.class);
            startActivity(intent);
        });

        ImageView profile = findViewById(R.id.Profile);
        profile.setOnClickListener(v -> {
            Intent intent = new Intent(GadgetShop.this, Profile.class);
            startActivity(intent);
        });

        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main),
                (v, insets) -> {

                    Insets systemBars =

                            insets.getInsets(WindowInsetsCompat.Type.systemBars());

                    v.setPadding(systemBars.left, systemBars.top, systemBars.right,

                            systemBars.bottom);
                    return insets;
                });
    }
}