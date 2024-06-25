package com.example.alagactivity8point1;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class ChatActivity extends AppCompatActivity {

    private RecyclerView recyclerView;
    private EditText messageEditText;
    private Button sendButton;
    private MessageAdapter messageAdapter;
    private List<Message> messageList;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_chat);

        recyclerView = findViewById(R.id.recycler_view_chat);
        messageEditText = findViewById(R.id.edit_text_message);
        sendButton = findViewById(R.id.button_send);

        messageList = new ArrayList<>();
        messageAdapter = new MessageAdapter(messageList);
        LinearLayoutManager layoutManager = new LinearLayoutManager(this);
        recyclerView.setLayoutManager(layoutManager);
        recyclerView.setAdapter(messageAdapter);

        // Define your margin size in resources (dimens.xml)
        int marginInPixels = getResources().getDimensionPixelSize(R.dimen.item_margin);

        // Create and apply the item decoration
        ChatItemDecoration itemDecoration = new ChatItemDecoration(this, marginInPixels);
        recyclerView.addItemDecoration(itemDecoration);

        sendButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                sendMessage();
            }
        });

        ImageView backToProfile = findViewById(R.id.backToProfile);
        backToProfile.setOnClickListener(v -> {
            Intent intent = new Intent(ChatActivity.this, Profile.class);
            startActivity(intent);
        });
    }

    private void sendMessage() {
        String messageText = messageEditText.getText().toString().trim();
        if (messageText.isEmpty()) {
            Toast.makeText(this, "Please enter a message", Toast.LENGTH_SHORT).show();
            return;
        }

        Message message = new Message(messageText, true, System.currentTimeMillis());
        messageList.add(message);
        messageAdapter.notifyItemInserted(messageList.size() - 1);
        scrollToBottom();
        simulateResponse(); // Simulate a response from the support side
        messageEditText.getText().clear();
    }

    private void scrollToBottom() {
        recyclerView.scrollToPosition(messageAdapter.getItemCount() - 1);
    }

    private void simulateResponse() {
        // Define a list of responses
        String[] responses = {
                "Hello! How can I assist you today?",
                "I'm happy to help you with that!",
                "What can I do for you?",
                "Let me know how I can assist you further.",
                "Feel free to ask me anything!",
                "I'm here to provide support.",
                "How may I help you today?",
                "Let's solve your problem together.",
                "I'm here to help you with any questions you may have.",
                "What's on your mind? I'm here to listen."
        };

        // Generate a random index to pick a response
        Random random = new Random();
        int index = random.nextInt(responses.length);
        String randomResponse = responses[index];

        // Simulate a response after a short delay
        recyclerView.postDelayed(new Runnable() {
            @Override
            public void run() {
                Message response = new Message(randomResponse, false, System.currentTimeMillis());
                messageList.add(response);
                messageAdapter.notifyItemInserted(messageList.size() - 1);
                scrollToBottom();
            }
        }, 1000); // 1 second delay
    }
}