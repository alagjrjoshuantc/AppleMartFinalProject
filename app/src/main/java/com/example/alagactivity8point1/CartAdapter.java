package com.example.alagactivity8point1;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;
import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;
import java.util.ArrayList;

public class CartAdapter extends RecyclerView.Adapter<CartAdapter.CartViewHolder> {
    private ArrayList<CartItem> cartItems;
    private OnQuantityChangeListener onQuantityChangeListener;

    public CartAdapter(ArrayList<CartItem> cartItems, OnQuantityChangeListener onQuantityChangeListener) {
        this.cartItems = cartItems;
        this.onQuantityChangeListener = onQuantityChangeListener;
    }

    @NonNull
    @Override
    public CartViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.cart_item_v2, parent, false);
        return new CartViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull CartViewHolder holder, int position) {
        CartItem item = cartItems.get(position);
        holder.itemNameTextView.setText(item.getName());
        holder.itemPriceTextView.setText("₱" + item.getPrice());
        holder.quantityTextView.setText(String.valueOf(item.getQuantity()));
        holder.itemImageView.setImageResource(item.getImageResource());

        holder.increaseQuantityButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                item.setQuantity(item.getQuantity() + 1);
                notifyDataSetChanged();
                onQuantityChangeListener.onQuantityChange();
            }
        });

        holder.decreaseQuantityButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (item.getQuantity() > 1) {
                    item.setQuantity(item.getQuantity() - 1);
                    notifyDataSetChanged();
                    onQuantityChangeListener.onQuantityChange();
                }
            }
        });

        holder.removeButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                cartItems.remove(item);
                notifyDataSetChanged();
                onQuantityChangeListener.onQuantityChange();
            }
        });
    }

    @Override
    public int getItemCount() {
        return cartItems.size();
    }

    public static class CartViewHolder extends RecyclerView.ViewHolder {
        TextView itemNameTextView, itemPriceTextView, quantityTextView;
        ImageView itemImageView;
        ImageButton increaseQuantityButton, decreaseQuantityButton, removeButton;

        public CartViewHolder(@NonNull View itemView) {
            super(itemView);
            itemNameTextView = itemView.findViewById(R.id.itemNameTextView);
            itemPriceTextView = itemView.findViewById(R.id.itemPriceTextView);
            quantityTextView = itemView.findViewById(R.id.quantityTextView);
            itemImageView = itemView.findViewById(R.id.itemImageView);
            increaseQuantityButton = itemView.findViewById(R.id.increaseQuantityButton);
            decreaseQuantityButton = itemView.findViewById(R.id.decreaseQuantityButton);
            removeButton = itemView.findViewById(R.id.removeButton);
        }
    }

    public interface OnQuantityChangeListener {
        void onQuantityChange();
    }
}

