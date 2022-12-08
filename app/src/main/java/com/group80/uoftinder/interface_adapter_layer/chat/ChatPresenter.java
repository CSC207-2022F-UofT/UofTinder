package com.group80.uoftinder.interface_adapter_layer.chat;

import androidx.annotation.NonNull;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.group80.uoftinder.view_layer.chat.ChatActivity;
import com.group80.uoftinder.use_case_layer.chat.MessageFactory;
import com.group80.uoftinder.entities_layer.Message;
import com.group80.uoftinder.use_case_layer.firebase.realtime.RealtimeDbValueObserver;
import com.group80.uoftinder.use_case_layer.firebase.realtime.ChatMessageWriter;

/**
 * A presenter corroborating with {@link ChatViewInterface}
 */
public class ChatPresenter {
    private final ChatViewInterface view;

    private final ChatMessageWriter chatMessageWriter;

    /**
     * Constructor, initializes the presenter
     *
     * @param view       the view this presenter is interacting with
     * @param controller the controller that controls chat messages
     * @param chatRoom   the identifier of the chat room between the two users
     */
    public ChatPresenter(ChatViewInterface view, MessageController controller, String chatRoom) {
        this.view = view;

        // Register database value chane observer behaviour
        this.chatMessageWriter = new ChatMessageWriter(new RealtimeDbValueObserver() {
            @Override
            public void onRealtimeDbDataChange(@NonNull DataSnapshot snapshot) {
                controller.clearMessagesList();
                for (DataSnapshot childSnapshot : snapshot.getChildren())
                    controller.addMessage(childSnapshot.getValue(Message.class));
                view.notifyMessageAdded();
            }

            @Override
            public void onRealtimeDbCancelled(@NonNull DatabaseError error) {
                // TODO: handle error
            }
        }, chatRoom);
    }

    /**
     * Enters the {@link ChatActivity}
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
        chatMessageWriter.write(MessageFactory.createMessage(newMessage, senderUid));
        view.displayNewMessage(newMessage);
    }
}
