package com.example.alagactivity8point1;

import android.content.Intent;
import android.os.Bundle;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.Switch;
import android.widget.Toast;

import android.content.SharedPreferences;

public class Notifications extends AppCompatActivity {

    private Switch notificationSwitch;
    private Button saveButton;
    private SharedPreferences sharedPreferences;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_notifications);

        sharedPreferences = getSharedPreferences("notifications", MODE_PRIVATE);

        notificationSwitch = findViewById(R.id.notificationSwitch);
        saveButton = findViewById(R.id.saveButton);

        if (notificationSwitch == null) {
            showToast("Notification switch not found");
            return;
        }

        if (saveButton == null) {
            showToast("Save button not found");
            return;
        }

        boolean notificationsEnabled = sharedPreferences.getBoolean("notificationsEnabled", true);
        notificationSwitch.setChecked(notificationsEnabled);

        saveButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                boolean notificationsEnabled = notificationSwitch.isChecked();
                sharedPreferences.edit().putBoolean("notificationsEnabled", notificationsEnabled).apply();

                if (notificationsEnabled) {
                    showToast("Notifications turned on");
                } else {
                    showToast("Notifications turned off");
                }
            }
        });

        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });

        ImageView backToProfile = findViewById(R.id.backToProfile);
        if (backToProfile == null) {
            showToast("Back to profile button not found");
            return;
        }

        backToProfile.setOnClickListener(v -> {
            Intent intent = new Intent(Notifications.this, Profile.class);
            if (intent == null) {
                showToast("Failed to create intent");
                return;
            }
            startActivity(intent);
        });
    }

    private void showToast(String message) {
        Toast.makeText(this, message, Toast.LENGTH_SHORT).show();
    }
}