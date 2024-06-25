// OrderHistoryActivity.java
package com.example.alagactivity8point1;

import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.widget.ImageView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;
import java.util.List;

public class OrderHistoryActivity extends AppCompatActivity {

    private RecyclerView orderHistoryRecyclerView;
    private OrderDatabaseHelper dbHelper;
    private OrderHistoryAdapter adapter;
    private List<Order> orderList;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_order_history);

        orderHistoryRecyclerView = findViewById(R.id.orderHistoryRecyclerView);
        dbHelper = new OrderDatabaseHelper(this);
        orderList = new ArrayList<>();
        adapter = new OrderHistoryAdapter(this, orderList);

        LinearLayoutManager layoutManager = new LinearLayoutManager(this);
        orderHistoryRecyclerView.setLayoutManager(layoutManager);
        orderHistoryRecyclerView.setAdapter(adapter);

        loadOrderHistory();

        ImageView backToProfile = findViewById(R.id.backToProfile);
        if (backToProfile == null) {
            showToast("Back to profile button not found");
            return;
        }

        backToProfile.setOnClickListener(v -> {
            Intent intent = new Intent(OrderHistoryActivity.this, Profile.class);
            if (intent == null) {
                showToast("Failed to create intent");
                return;
            }
            startActivity(intent);
        });
    }

    private void loadOrderHistory() {
        SQLiteDatabase db = dbHelper.getReadableDatabase();

        String[] projection = {"_id", "order_details", "order_date", "name", "email", "address", "contactNumber", "deliveryAddress"};

        Cursor cursor = db.query(
                "orders",
                projection,
                null,
                null,
                null,
                null,
                "_id DESC"
        );

        while (cursor.moveToNext()) {
            int id = cursor.getInt(cursor.getColumnIndexOrThrow("_id"));
            String orderDetails = cursor.getString(cursor.getColumnIndexOrThrow("order_details"));
            String orderDate = cursor.getString(cursor.getColumnIndexOrThrow("order_date"));
            String name = cursor.getString(cursor.getColumnIndexOrThrow("name"));
            String email = cursor.getString(cursor.getColumnIndexOrThrow("email"));
            String address = cursor.getString(cursor.getColumnIndexOrThrow("address"));
            String contactNumber = cursor.getString(cursor.getColumnIndexOrThrow("contactNumber"));
            String deliveryAddress = cursor.getString(cursor.getColumnIndexOrThrow("deliveryAddress"));
            String expectedDeliveryDate = calculateExpectedDeliveryDate(orderDate);

            orderList.add(new Order(id, orderDetails, orderDate, expectedDeliveryDate, name, email, address, contactNumber, deliveryAddress));
        }

        cursor.close();
        adapter.notifyDataSetChanged();
    }



    private String calculateExpectedDeliveryDate(String orderDate) {
        // Sample logic: Assuming delivery is 5 days from order date
        // This is a placeholder, replace with your actual logic
        return "Delivery in 5 days"; // Replace with actual logic
    }

    private void showToast(String message) {
        Toast.makeText(this, message, Toast.LENGTH_SHORT).show();
    }
}
