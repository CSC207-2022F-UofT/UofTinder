package com.group80.uoftinder.chat;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.Locale;

/**
 * Creates new messages
 */
public class MessageFactory {
    private static final Date date = new Date();
    private static final Calendar calendar = Calendar.getInstance();
    private static final SimpleDateFormat simpleDateFormat = new SimpleDateFormat("hh:mm a", Locale.CANADA);

    public static Message createMessage(String message, String senderUid) {
        return new Message(message, senderUid, date.getTime(), simpleDateFormat.format(calendar.getTime()));
    }
}
