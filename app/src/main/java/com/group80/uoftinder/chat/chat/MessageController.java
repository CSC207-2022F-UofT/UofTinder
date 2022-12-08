package com.group80.uoftinder.chat.chat;

import java.util.LinkedList;
import java.util.List;

public class MessageController {
    List<Message> messageList;

    public MessageController(List<Message> messageList) {
        this.messageList = messageList;
    }

    public MessageController() {
        this.messageList = new LinkedList<>();
    }

    public void clearMessagesList() {
        this.messageList.clear();
    }

    public void addMessage(Message message) {
        this.messageList.add(message);
    }

    public String getMessageText(int position) {
        return messageList.get(position).getMessage();
    }

    public String getMessageTime(int position) {
        return messageList.get(position).getCurrentTime();
    }

    public boolean isSent(int position, String uid) {
        return this.messageList.get(position).getSenderId().equals(uid);
    }

    public int getMessageCount() {
        return messageList.size();
    }
}
