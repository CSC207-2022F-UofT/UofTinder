package com.group80.uoftinder.chat.chat;

/**
 * An entity class, stores the essential chat message data to be written to the database
 */
public class Message {
    /**
     * The body of the message
     */
    String message;
    /**
     * The uid of the sender of the message
     */
    String senderId;
    /**
     * The time when the message is created, as a long in number of milliseconds since January 1, 1970, 00:00:00 GMT
     */
    long timestamp;
    /**
     * The time when the message is created, as a string in the format of hh:mm am/pm
     */
    String currentTime;

    /**
     * Constructor, creates the `Message` object
     *
     * @param message     the message body
     * @param senderId    the uid of the sender
     * @param timestamp   timestamp in millisecond
     * @param currentTime time in hh:mm a
     */
    public Message(String message, String senderId, long timestamp, String currentTime) {
        this.message = message;
        this.senderId = senderId;
        this.timestamp = timestamp;
        this.currentTime = currentTime;
    }

    /**
     * Explicit no-argument constructor to avoid errors
     * <p>
     * Important: DO NOT REMOVE EVEN IF IT'S NEVER USED
     */
    public Message() {

    }

    /**
     * Returns the message body
     *
     * @return the message body
     */
    public String getMessage() {
        return message;
    }

    /**
     * Sets the message body
     *
     * @param message the message body
     */
    public void setMessage(String message) {
        this.message = message;
    }

    /**
     * Returns the uid of the sender of the message
     *
     * @return the uid of the sender of the message
     */
    public String getSenderId() {
        return senderId;
    }

    /**
     * Sets the uid of the sender of the message
     *
     * @param senderId the uid of the sender of the message
     */
    public void setSenderId(String senderId) {
        this.senderId = senderId;
    }

    /**
     * Returns the timestamp when the message was created
     *
     * @return the timestamp when the message was created
     */
    public long getTimestamp() {
        return timestamp;
    }

    /**
     * Sets the timestamp of the creation of the message
     *
     * @param timestamp the timestamp of the creation of the message
     */
    public void setTimestamp(long timestamp) {
        this.timestamp = timestamp;
    }

    /**
     * Returns the time of the message creation, in hh:mm a
     *
     * @return the times of the message creation, in hh:mm a
     */
    public String getCurrentTime() {
        return currentTime;
    }

    /**
     * Sets the time of the message creation, in hh:mm a
     *
     * @param currentTime the time of the message creation, in hh:mm a
     */
    public void setCurrentTime(String currentTime) {
        this.currentTime = currentTime;
    }
}
