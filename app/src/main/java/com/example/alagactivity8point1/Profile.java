package com.example.alagactivity8point1;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import com.google.firebase.auth.FirebaseAuth;

public class Profile extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_profile);

        TextView btnHome = findViewById(R.id.txtHome);
        btnHome.setOnClickListener(v -> {
            Intent intent = new Intent(Profile.this, MainActivity.class);
            startActivity(intent);
        });

        Button btnProfile = findViewById(R.id.editProfile);
        btnProfile.setOnClickListener(v -> {
            Toast.makeText(Profile.this, "Profile", Toast.LENGTH_SHORT).show();
            Intent intent = new Intent(Profile.this, RegisterStudentPersonalDetails.class);
            startActivity(intent);
        });

        Button btnOrderHistory = findViewById(R.id.orderHistory);
        btnOrderHistory.setOnClickListener(v -> {
            Toast.makeText(Profile.this, "Order History", Toast.LENGTH_SHORT).show();
            Intent intent = new Intent(Profile.this, OrderHistoryActivity.class);
            startActivity(intent);
        });

        Button btnNotifications = findViewById(R.id.notifications);
        btnNotifications.setOnClickListener(v -> {
            Toast.makeText(Profile.this, "Notifications", Toast.LENGTH_SHORT).show();
            Intent intent = new Intent(Profile.this, Notifications.class);
            startActivity(intent);
            // Add your logic for notifications activity here
        });

        Button btnFeedback = findViewById(R.id.feedback);
        btnFeedback.setOnClickListener(v -> {
            Toast.makeText(Profile.this, "Feedback", Toast.LENGTH_SHORT).show();
            Intent intent = new Intent(Profile.this, Feedback.class);
            startActivity(intent);
            // Add your logic for notifications activity here
        });

        Button btnHelpSupport = findViewById(R.id.helpSupport);
        btnHelpSupport.setOnClickListener(v -> {
            Toast.makeText(Profile.this, "Help & Support", Toast.LENGTH_SHORT).show();
            Intent intent = new Intent(Profile.this, ChatActivity.class);
            startActivity(intent);
            // Add your logic for help & support activity here
        });

        Button btnTermsConditions = findViewById(R.id.termsConditions);
        btnTermsConditions.setOnClickListener(v -> {
            Toast.makeText(Profile.this, "Terms & Conditions", Toast.LENGTH_SHORT).show();
            Intent intent = new Intent(Profile.this, TermsAndConditions.class);
            startActivity(intent);
            // Add your logic for terms & conditions activity here
        });

        Button btnPrivacyPolicy = findViewById(R.id.privacyPolicy);
        btnPrivacyPolicy.setOnClickListener(v -> {
            Toast.makeText(Profile.this, "Privacy Policy", Toast.LENGTH_SHORT).show();
            Intent intent = new Intent(Profile.this, PrivacyPolicy.class);
            startActivity(intent);
            // Add your logic for privacy policy activity here
        });

        Button btnSignOut = findViewById(R.id.logOut);
        btnSignOut.setOnClickListener(v -> {
            // Create an AlertDialog
            new AlertDialog.Builder(this)
                    .setTitle("Log Out")
                    .setMessage("Are you sure you want to log out?")
                    .setPositiveButton("Yes", (dialog, which) -> {
                        // Sign out the user
                        FirebaseAuth.getInstance().signOut();

                        Intent intent = new Intent(getApplicationContext(), LandingPage.class);
                        startActivity(intent);
                        finish();
                        Toast.makeText(Profile.this, "Logged Out", Toast.LENGTH_SHORT).show();
                    })
                    .setNegativeButton("No", null)
                    .show();
        });

        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });
    }
}
