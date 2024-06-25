// OrderHistoryAdapter.java
package com.example.alagactivity8point1;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

public class OrderHistoryAdapter extends RecyclerView.Adapter<OrderHistoryAdapter.OrderViewHolder> {

    private Context context;
    private List<Order> orderList;
    private OrderDatabaseHelper dbHelper;

    public OrderHistoryAdapter(Context context, List<Order> orderList) {
        this.context = context;
        this.orderList = orderList;
        this.dbHelper = new OrderDatabaseHelper(context);
    }

    @NonNull
    @Override
    public OrderViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.order_history_item, parent, false);
        return new OrderViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull OrderViewHolder holder, int position) {
        Order order = orderList.get(position);
        holder.orderDateTextView.setText(order.getOrderDate());
        holder.orderDetailsTextView.setText(order.getOrderDetails());
        holder.expectedDeliveryDateTextView.setText(order.getExpectedDeliveryDate());
        holder.nameTextView.setText(order.getName());
        holder.emailTextView.setText(order.getEmail());
        holder.addressTextView.setText(order.getAddress());
        holder.contactNumberTextView.setText(order.getContactNumber());
        holder.deliveryAddressTextView.setText(order.getDeliveryAddress());

        holder.deleteButton.setOnClickListener(v -> {
            // Delete order from database
            dbHelper.deleteOrder(order.getId());
            // Remove order from list and notify adapter
            orderList.remove(position);
            notifyItemRemoved(position);
            notifyItemRangeChanged(position, orderList.size());
        });
    }

    @Override
    public int getItemCount() {
        return orderList.size();
    }

    public static class OrderViewHolder extends RecyclerView.ViewHolder {
        TextView orderDateTextView, orderDetailsTextView, expectedDeliveryDateTextView,
                nameTextView, emailTextView, addressTextView, contactNumberTextView, deliveryAddressTextView;
        Button deleteButton;

        public OrderViewHolder(@NonNull View itemView) {
            super(itemView);
            orderDateTextView = itemView.findViewById(R.id.orderDateTextView);
            orderDetailsTextView = itemView.findViewById(R.id.orderDetailsTextView);
            expectedDeliveryDateTextView = itemView.findViewById(R.id.expectedDeliveryDateTextView);
            nameTextView = itemView.findViewById(R.id.nameTextView);
            emailTextView = itemView.findViewById(R.id.emailTextView);
            addressTextView = itemView.findViewById(R.id.addressTextView);
            contactNumberTextView = itemView.findViewById(R.id.contactNumberTextView);
            deliveryAddressTextView = itemView.findViewById(R.id.deliveryAddressTextView);
            deleteButton = itemView.findViewById(R.id.deleteButton);
        }
    }
}
