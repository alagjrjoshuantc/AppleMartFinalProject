package com.example.alagactivity8point1;

import android.content.ContentValues;
import android.content.Intent;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.Locale;

public class CheckoutActivity extends AppCompatActivity {
    private EditText deliveryAddressEditText;
    private EditText receiverNameEditText;
    private EditText contactNumberEditText;
    private EditText emailEditText;
    private RecyclerView itemsRecyclerView;
    private Spinner paymentMethodSpinner;
    private EditText voucherEditText;
    private Button applyVoucherButton;
    private TextView voucherDetailsTextView;
    private TextView totalAmountTextView;
    private TextView taxAmountTextView;
    private TextView shippingFeeTextView;
    private Button placeOrderButton;
    private ArrayList<CartItem> cartItems;
    private CartAdapter cartAdapter;
    private double totalAmount;
    private double taxAmount;
    private double shippingFee = 50.0; // example shipping fee
    private double discountAmount;
    private OrderDatabaseHelper dbHelper;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_checkout);

        deliveryAddressEditText = findViewById(R.id.deliveryAddressEditText);
        receiverNameEditText = findViewById(R.id.receiverNameEditText);
        contactNumberEditText = findViewById(R.id.contactNumberEditText);
        emailEditText = findViewById(R.id.emailEditText);
        itemsRecyclerView = findViewById(R.id.itemsRecyclerView);
        paymentMethodSpinner = findViewById(R.id.paymentMethodSpinner);
        voucherEditText = findViewById(R.id.voucherEditText);
        applyVoucherButton = findViewById(R.id.applyVoucherButton);
        voucherDetailsTextView = findViewById(R.id.voucherDetailsTextView);
        totalAmountTextView = findViewById(R.id.totalAmountTextView);
        taxAmountTextView = findViewById(R.id.taxAmountTextView);
        shippingFeeTextView = findViewById(R.id.shippingFeeTextView);
        placeOrderButton = findViewById(R.id.placeOrderButton);

        cartItems = CartSingleton.getInstance().getCartItems();
        cartAdapter = new CartAdapter(cartItems, null);

        itemsRecyclerView.setLayoutManager(new LinearLayoutManager(this));
        itemsRecyclerView.setAdapter(cartAdapter);

        ArrayAdapter<CharSequence> adapter = ArrayAdapter.createFromResource(this, R.array.payment_methods, android.R.layout.simple_spinner_item);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        paymentMethodSpinner.setAdapter(adapter);

        applyVoucherButton.setOnClickListener(v -> applyVoucher());

        placeOrderButton.setOnClickListener(v -> {
            if (validateFields()) {
                String orderDetails = generateOrderDetails();
                saveOrderToDatabase(orderDetails); // Save order to database
                Intent intent = new Intent(CheckoutActivity.this, OrderHistoryActivity.class);
                intent.putExtra("orderDetails", orderDetails);
                startActivity(intent);
            }
        });

        voucherEditText.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {}

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {}

            @Override
            public void afterTextChanged(Editable s) {
                voucherDetailsTextView.setVisibility(android.view.View.GONE);
                updateTotalAmount();
            }
        });

        updateTotalAmount();

        dbHelper = new OrderDatabaseHelper(this);

        ImageView backtoCart = findViewById(R.id.backtoCart);
        backtoCart.setOnClickListener(v -> {
            Intent intent = new Intent(CheckoutActivity.this, CartActivityV2.class);
            startActivity(intent);
        });
    }

    private void applyVoucher() {
        String voucherCode = voucherEditText.getText().toString().trim();

        if (!voucherCode.isEmpty()) {
            discountAmount = applyVoucher(voucherCode);
            updateTotalAmount();
            voucherDetailsTextView.setVisibility(android.view.View.VISIBLE);
            voucherDetailsTextView.setText("Applied voucher: " + voucherCode + " - Discount: ₱" + String.format("%.2f", discountAmount));
        } else {
            discountAmount = 0;
            updateTotalAmount();
            voucherDetailsTextView.setVisibility(android.view.View.GONE);
        }
    }

    private void updateTotalAmount() {
        totalAmount = 0;
        for (CartItem item : cartItems) {
            totalAmount += item.getPrice() * item.getQuantity();
        }

        taxAmount = totalAmount * 0.12; // 12% tax
        double finalAmount = totalAmount + taxAmount + shippingFee - discountAmount;

        taxAmountTextView.setText("₱" + String.format("%.2f", taxAmount));
        shippingFeeTextView.setText("₱" + String.format("%.2f", shippingFee));
        totalAmountTextView.setText("₱" + String.format("%.2f", finalAmount));
    }

    private double applyVoucher(String voucher) {
        // Example voucher logic
        if (voucher.equals("DISCOUNT10")) {
            return totalAmount * 0.10;
        }
        if (voucher.equals("BENEQT15")){
            return totalAmount * 0.15;
        }
        return 0;
    }

    private String generateOrderDetails() {
        StringBuilder orderDetails = new StringBuilder();
        orderDetails.append("Order Summary:\n");
        for (CartItem item : cartItems) {
            orderDetails.append(item.getName())
                    .append(" - Quantity: ")
                    .append(item.getQuantity())
                    .append(" - Price: ₱")
                    .append(String.format("%.2f", item.getPrice()))
                    .append("\n");
        }
        orderDetails.append("\nTotal Amount: ₱")
                .append(String.format("%.2f", totalAmount + taxAmount + shippingFee - discountAmount));
        orderDetails.append("\nTax: ₱").append(String.format("%.2f", taxAmount));
        orderDetails.append("\nShipping Fee: ₱").append(String.format("%.2f", shippingFee));
        if (discountAmount > 0) {
            orderDetails.append("\nDiscount: ₱").append(String.format("%.2f", discountAmount));
        }
        orderDetails.append("\nFinal Amount: ₱").append(String.format("%.2f", totalAmount + taxAmount + shippingFee - discountAmount));
        return orderDetails.toString();
    }

    private void saveOrderToDatabase(String orderDetails) {
        SQLiteDatabase db = dbHelper.getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put("order_details", orderDetails);
        values.put("order_date", getCurrentDateTime()); // Store current date/time

        long newRowId = db.insert("orders", null, values);
        if (newRowId == -1) {
            Toast.makeText(this, "Failed to save order", Toast.LENGTH_SHORT).show();
        } else {
            Toast.makeText(this, "Order saved successfully", Toast.LENGTH_SHORT).show();
        }
    }

    private String getCurrentDateTime() {
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss", Locale.getDefault());
        return sdf.format(new Date());
    }

    private boolean validateFields() {
        if (deliveryAddressEditText.getText().toString().isEmpty()) {
            Toast.makeText(this, "Please enter a delivery address", Toast.LENGTH_SHORT).show();
            return false;
        }

        if (receiverNameEditText.getText().toString().isEmpty()) {
            Toast.makeText(this, "Please enter the receiver's name", Toast.LENGTH_SHORT).show();
            return false;
        }

        if (contactNumberEditText.getText().toString().isEmpty()) {
            Toast.makeText(this, "Please enter a contact number", Toast.LENGTH_SHORT).show();
            return false;
        }

        if (emailEditText.getText().toString().isEmpty()) {
            Toast.makeText(this, "Please enter an email address", Toast.LENGTH_SHORT).show();
            return false;
        }

        return true;
    }
}