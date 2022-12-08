package com.group80.uoftinder.chat.chat;

import java.util.LinkedList;
import java.util.List;

/**
 * A controller controls the messages
 */
public class MessageController {
    List<Message> messageList;

    /**
     * Constructor, creates the controller
     */
    public MessageController() {
        this.messageList = new LinkedList<>();
    }

    /**
     * Clears the message list
     */
    public void clearMessagesList() {
        this.messageList.clear();
    }

    /**
     * Adds a new message to the message list
     *
     * @param message the messages to be added
     */
    public void addMessage(Message message) {
        this.messageList.add(message);
    }

    /**
     * Returns the body of the message at the given location in the message list
     *
     * @param position the position of the message
     * @return the body of the message at the given location
     */
    public String getMessageText(int position) {
        return messageList.get(position).getMessage();
    }

    /**
     * Returns the time of the message at the given location in the message list
     *
     * @param position the position of the message
     * @return the time of the message at the given location
     */
    public String getMessageTime(int position) {
        return messageList.get(position).getCurrentTime();
    }

    /**
     * Determines if the message at the given location is sent or received by the user of the given
     * uid
     *
     * @param position the position of the message
     * @param uid      the uid of the user
     * @return true if the message at the given position is sent by the user of the uid, false if
     * received
     */
    public boolean isSent(int position, String uid) {
        return this.messageList.get(position).getSenderId().equals(uid);
    }

    /**
     * Returns the number of messages
     *
     * @return the number of messages
     */
    public int getMessageCount() {
        return messageList.size();
    }
}
