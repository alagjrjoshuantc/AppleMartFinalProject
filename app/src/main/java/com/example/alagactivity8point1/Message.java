package com.example.alagactivity8point1;

public class Message {
    private String messageText;
    private boolean isSentByUser;
    private long timestamp;

    public Message(String messageText, boolean isSentByUser, long timestamp) {
        this.messageText = messageText;
        this.isSentByUser = isSentByUser;
        this.timestamp = timestamp;
    }

    public String getMessageText() {
        return messageText;
    }

    public boolean isSentByUser() {
        return isSentByUser;
    }

    public long getTimestamp() {
        return timestamp;
    }
}