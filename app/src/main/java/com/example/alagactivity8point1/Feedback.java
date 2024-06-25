package com.example.alagactivity8point1;

import android.content.Intent;
import android.graphics.Bitmap;
import android.net.Uri;
import android.os.Bundle;
import android.provider.MediaStore;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import java.io.IOException;

public class Feedback extends AppCompatActivity {

    private static final int PICK_IMAGE_REQUEST = 1;
    private Uri imageUri;
    private ImageView uploadedPhoto;
    private EditText feedbackName, feedbackEmail, feedbackMessage;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_feedback);

        // Initialize views
        feedbackName = findViewById(R.id.feedbackName);
        feedbackEmail = findViewById(R.id.feedbackEmail);
        feedbackMessage = findViewById(R.id.feedbackMessage);
        Button uploadPhoto = findViewById(R.id.uploadPhoto);
        Button submitFeedback = findViewById(R.id.submitFeedback);
        uploadedPhoto = findViewById(R.id.uploadedPhoto);

        // Set click listener for upload photo button
        uploadPhoto.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                openFileChooser();
            }
        });

        // Set click listener for submit feedback button
        submitFeedback.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (validateFields()) {
                    // Clear fields
                    feedbackName.setText("");
                    feedbackEmail.setText("");
                    feedbackMessage.setText("");

                    // Clear photo
                    uploadedPhoto.setImageResource(0); // or uploadedPhoto.setImageBitmap(null);

                    Toast.makeText(Feedback.this, "Feedback submitted. Thank you!", Toast.LENGTH_SHORT).show();
                }
            }
        });

        // Set click listener for delete photo button
        ImageButton deletePhoto = findViewById(R.id.deletePhoto);
        deletePhoto.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                uploadedPhoto.setImageResource(0); // Clear the image
            }
        });

        // Apply window insets listener to adjust layout with system bars
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });

        // Navigate back to profile activity
        ImageView backToProfile = findViewById(R.id.backToProfile);
        backToProfile.setOnClickListener(v -> {
            Intent intent = new Intent(Feedback.this, Profile.class);
            startActivity(intent);
        });
    }

    // Method to handle file chooser intent
    private void openFileChooser() {
        Intent intent = new Intent();
        intent.setType("image/*");
        intent.setAction(Intent.ACTION_GET_CONTENT);
        startActivityForResult(intent, PICK_IMAGE_REQUEST);
    }

    // Method to handle result from file chooser intent
    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == PICK_IMAGE_REQUEST && resultCode == RESULT_OK && data != null && data.getData() != null) {
            imageUri = data.getData();
            try {
                Bitmap bitmap = MediaStore.Images.Media.getBitmap(getContentResolver(), imageUri);
                uploadedPhoto.setImageBitmap(bitmap);
                uploadedPhoto.setVisibility(View.VISIBLE);
                Toast.makeText(this, "Photo selected.", Toast.LENGTH_SHORT).show();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    // Method to validate input fields
    private boolean validateFields() {
        String name = feedbackName.getText().toString().trim();
        String email = feedbackEmail.getText().toString().trim();
        String message = feedbackMessage.getText().toString().trim();

        if (name.isEmpty()) {
            feedbackName.setError("Name is required");
            feedbackName.requestFocus();
            return false;
        }

        if (email.isEmpty()) {
            feedbackEmail.setError("Email is required");
            feedbackEmail.requestFocus();
            return false;
        } else if (!isValidEmail(email)) {
            feedbackEmail.setError("Invalid email format");
            feedbackEmail.requestFocus();
            return false;
        }

        if (message.isEmpty()) {
            feedbackMessage.setError("Message is required");
            feedbackMessage.requestFocus();
            return false;
        }

        // All fields are valid
        return true;
    }

    // Method to validate email format
    private boolean isValidEmail(String email) {
        return android.util.Patterns.EMAIL_ADDRESS.matcher(email).matches();
    }
}
