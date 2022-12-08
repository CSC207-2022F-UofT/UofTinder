package com.group80.uoftinder.use_case_layer.chat;

import com.group80.uoftinder.entities_layer.Message;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.Locale;

/**
 * Creates new messages
 */
public class MessageFactory {
    private static final SimpleDateFormat simpleDateFormat = new SimpleDateFormat("hh:mm a", Locale.CANADA);

    /**
     * Generates a Message class
     *
     * @param message   the message body
     * @param senderUid the uid of the sender of the message
     * @return a Message class created based on the given information and the current local time
     */
    public static Message createMessage(String message, String senderUid) {
        Date date = new Date();
        Calendar calendar = Calendar.getInstance();
        return new Message(message, senderUid, date.getTime(), simpleDateFormat.format(calendar.getTime()));
    }
}