package com.example.alagactivity8point1;

import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.HashMap;
import java.util.Map;

public class RegisterStudentPersonalDetails extends AppCompatActivity {

    private EditText etFullName;
    private EditText etStudentID;
    private EditText etAddress;
    private EditText etEmail;
    private EditText etCellphoneNumber;
    private EditText etHobbies;
    private EditText etBirthdate;

    private DatabaseReference userRef;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_register_student_personal_details);

        // Initialize EditText fields
        etFullName = findViewById(R.id.etFullName);
        etStudentID = findViewById(R.id.etStudentID);
        etAddress = findViewById(R.id.etAddress);
        etEmail = findViewById(R.id.etEmail);
        etCellphoneNumber = findViewById(R.id.etCellphoneNumber);
        etHobbies = findViewById(R.id.etHobbies);
        etBirthdate = findViewById(R.id.etBirthdate);

        // Get current user and reference to user's data in Firebase
        FirebaseUser currentUser = FirebaseAuth.getInstance().getCurrentUser();
        if (currentUser != null) {
            String userId = currentUser.getUid();
            userRef = FirebaseDatabase.getInstance().getReference("Users").child(userId);
            loadUserDetails();
        }

        Button btnAdd = findViewById(R.id.btnAdd);
        btnAdd.setOnClickListener(v -> {
            if (validateInput()) {
                saveUserDetails();
                Toast.makeText(RegisterStudentPersonalDetails.this, "Successfully Added", Toast.LENGTH_SHORT).show();
            }
        });

        Button btnDelete = findViewById(R.id.btnDelete);
        btnDelete.setOnClickListener(v -> {
            clearFields();
            Toast.makeText(RegisterStudentPersonalDetails.this, "Fields cleared", Toast.LENGTH_SHORT).show();
        });

        Button btnSubmit = findViewById(R.id.btnSubmit);
        btnSubmit.setOnClickListener(v -> {
            if (validateInput()) {
                saveUserDetails();
                showSummaryDialog();
            }
        });

        ImageView backToProfile = findViewById(R.id.backToProfile);
        backToProfile.setOnClickListener(v -> {
            Intent intent = new Intent(RegisterStudentPersonalDetails.this, Profile.class);
            startActivity(intent);
        });

        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });
    }

    private void loadUserDetails() {
        userRef.addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                if (dataSnapshot.exists()) {
                    etFullName.setText(dataSnapshot.child("fullName").getValue(String.class));
                    etStudentID.setText(dataSnapshot.child("studentID").getValue(String.class));
                    etAddress.setText(dataSnapshot.child("address").getValue(String.class));
                    etEmail.setText(dataSnapshot.child("email").getValue(String.class));
                    etCellphoneNumber.setText(dataSnapshot.child("cellphoneNumber").getValue(String.class));
                    etHobbies.setText(dataSnapshot.child("hobbies").getValue(String.class));
                    etBirthdate.setText(dataSnapshot.child("birthdate").getValue(String.class));
                }
            }

            @Override
            public void onCancelled(DatabaseError databaseError) {
                Toast.makeText(RegisterStudentPersonalDetails.this, "Failed to load user details", Toast.LENGTH_SHORT).show();
            }
        });
    }

    private boolean validateInput() {
        String fullName = etFullName.getText().toString().trim();
        String studentID = etStudentID.getText().toString().trim();
        String address = etAddress.getText().toString().trim();
        String email = etEmail.getText().toString().trim();
        String cellphoneNumber = etCellphoneNumber.getText().toString().trim();
        String hobbies = etHobbies.getText().toString().trim();
        String birthdate = etBirthdate.getText().toString().trim();

        if (TextUtils.isEmpty(fullName) || TextUtils.isEmpty(studentID) || TextUtils.isEmpty(address) || TextUtils.isEmpty(email) ||
                TextUtils.isEmpty(cellphoneNumber) || TextUtils.isEmpty(hobbies) || TextUtils.isEmpty(birthdate)) {
            Toast.makeText(this, "Please fill in all fields", Toast.LENGTH_SHORT).show();
            return false;
        }

        return true;
    }

    private void clearFields() {
        etFullName.setText("");
        etStudentID.setText("");
        etAddress.setText("");
        etEmail.setText("");
        etCellphoneNumber.setText("");
        etHobbies.setText("");
        etBirthdate.setText("");
    }

    private void saveUserDetails() {
        String fullName = etFullName.getText().toString().trim();
        String studentID = etStudentID.getText().toString().trim();
        String address = etAddress.getText().toString().trim();
        String email = etEmail.getText().toString().trim();
        String cellphoneNumber = etCellphoneNumber.getText().toString().trim();
        String hobbies = etHobbies.getText().toString().trim();
        String birthdate = etBirthdate.getText().toString().trim();

        if (!validateInput()) {
            return; // Validation failed, do not proceed with saving
        }

        Map<String, String> userDetails = new HashMap<>();
        userDetails.put("fullName", fullName);
        userDetails.put("studentID", studentID);
        userDetails.put("address", address);
        userDetails.put("email", email);
        userDetails.put("cellphoneNumber", cellphoneNumber);
        userDetails.put("hobbies", hobbies);
        userDetails.put("birthdate", birthdate);

        userRef.setValue(userDetails).addOnCompleteListener(task -> {
            if (task.isSuccessful()) {
                Toast.makeText(RegisterStudentPersonalDetails.this, "Details saved successfully", Toast.LENGTH_SHORT).show();
            } else {
                Toast.makeText(RegisterStudentPersonalDetails.this, "Failed to save details", Toast.LENGTH_SHORT).show();
            }
        });
    }

    private void showSummaryDialog() {
        String summary = "Fullname: " + etFullName.getText().toString().trim() + "\n" +
                "Student ID: " + etStudentID.getText().toString().trim() + "\n" +
                "Address: " + etAddress.getText().toString().trim() + "\n" +
                "Email: " + etEmail.getText().toString().trim() + "\n" +
                "Cellphone Number: " + etCellphoneNumber.getText().toString().trim() + "\n" +
                "Hobbies: " + etHobbies.getText().toString().trim() + "\n" +
                "Birthdate: " + etBirthdate.getText().toString().trim() + "\n";

        AlertDialog.Builder builder = new AlertDialog.Builder(RegisterStudentPersonalDetails.this);
        builder.setTitle("Summary");
        builder.setMessage(summary);

        builder.setPositiveButton("OK", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                Toast.makeText(RegisterStudentPersonalDetails.this, "Successfully submitted", Toast.LENGTH_SHORT).show();
            }
        });

        builder.setNegativeButton("Cancel", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                dialog.dismiss();
            }
        });

        builder.show();
    }
}
