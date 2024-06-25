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
public class WishlistAdapter extends RecyclerView.Adapter<WishlistAdapter.WishlistViewHolder> {
    private ArrayList<WishlistItem> wishlistItems;
    private OnQuantityChangeListener onQuantityChangeListener;

    public WishlistAdapter(ArrayList<WishlistItem> wishlistItems, OnQuantityChangeListener onQuantityChangeListener) {
        this.wishlistItems = wishlistItems;
        this.onQuantityChangeListener = onQuantityChangeListener;
    }

    @NonNull
    @Override
    public WishlistViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.wishlist_item_v2, parent, false);
        return new WishlistViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull WishlistViewHolder holder, int position) {
        WishlistItem item = wishlistItems.get(position);
        holder.itemNameTextView.setText(item.getName());
        holder.itemPriceTextView.setText("₱" + item.getPrice());
        holder.itemImageView.setImageResource(item.getImageResource());

        holder.removeButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                wishlistItems.remove(item);
                notifyDataSetChanged();
                onQuantityChangeListener.onQuantityChange();
            }
        });
    }

    @Override
    public int getItemCount() {
        return wishlistItems.size();
    }

    public static class WishlistViewHolder extends RecyclerView.ViewHolder {
        TextView itemNameTextView, itemPriceTextView;
        ImageView itemImageView;
        ImageButton removeButton;

        public WishlistViewHolder(@NonNull View itemView) {
            super(itemView);
            itemNameTextView = itemView.findViewById(R.id.itemNameTextView);
            itemPriceTextView = itemView.findViewById(R.id.itemPriceTextView);
            itemImageView = itemView.findViewById(R.id.itemImageView);
            removeButton = itemView.findViewById(R.id.removeButton);
        }
    }

    public interface OnQuantityChangeListener {
        void onQuantityChange();
    }
}
