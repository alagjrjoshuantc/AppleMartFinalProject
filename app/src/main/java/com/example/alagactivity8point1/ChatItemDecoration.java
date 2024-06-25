package com.example.alagactivity8point1;

import android.content.Context;
import android.graphics.Rect;
import android.view.View;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

public class ChatItemDecoration extends RecyclerView.ItemDecoration {
    private final int margin;

    public ChatItemDecoration(Context context, int margin) {
        this.margin = margin;
    }

    @Override
    public void getItemOffsets(@NonNull Rect outRect, @NonNull View view, @NonNull RecyclerView parent, @NonNull RecyclerView.State state) {
        super.getItemOffsets(outRect, view, parent, state);
        outRect.left = margin;
        outRect.right = margin;
        outRect.bottom = margin;

        // Add top margin for the first item to avoid double spacing between items
        if (parent.getChildAdapterPosition(view) == 0) {
            outRect.top = margin;
        } else {
            outRect.top = 0;
        }
    }
}

