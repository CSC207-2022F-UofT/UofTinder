package com.group80.uoftinder.chat;

import androidx.annotation.NonNull;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.group80.uoftinder.firebase.realtime.RealtimeDbValueObserver;
import com.group80.uoftinder.firebase.realtime.ucChatMessageWriter;

import java.util.List;

public class ChatPresenter {
    private final ChatView view;
    private final List<Message> msgLst;

    private final ucChatMessageWriter chatMessageWriter;

    /**
     * Constructor, initializes the presenter
     *
     * @param view        the view this presenter is interacting with
     * @param messageList the messages between the two users
     * @param chatRoom    the identifier of the chat room between the two users
     */
    public ChatPresenter(ChatView view, List<Message> messageList, String chatRoom) {
        this.view = view;
        this.msgLst = messageList;

        // Register database value chane observer behaviour
        this.chatMessageWriter = new ucChatMessageWriter(new RealtimeDbValueObserver() {
            @Override
            public void onRealtimeDbDataChange(@NonNull DataSnapshot snapshot) {
                msgLst.clear();
                for (DataSnapshot childSnapshot : snapshot.getChildren())
                    msgLst.add(childSnapshot.getValue(Message.class));
                view.notifyMessageAdded();
            }

            @Override
            public void onRealtimeDbCancelled(@NonNull DatabaseError error) {
                // TODO: handle error
            }
        }, chatRoom);
    }

    /**
     * Enters the {@link com.group80.uoftinder.ChatActivity}
     */
    public void enterChatActivity() {
        view.enterContactView();
    }

    /**
     * Shows the information of the given contact
     *
     * @param contactName the name of the contact
     * @param contactUid  the uid of the contact
     */
    public void showContactInfo(String contactName, String contactUid) {
        view.showContactInfo(contactName, contactUid);
    }

    /**
     * Updates the chat message when a new {@link Message} is sent / received
     *
     * @param newMessage the body of the new message
     * @param senderUid  the UID of the sender of the message
     */
    public void updateChatMessage(String newMessage, String senderUid) {
        view.displayNewMessage(newMessage);
        chatMessageWriter.write(MessageFactory.createMessage(newMessage, senderUid));
    }
}
