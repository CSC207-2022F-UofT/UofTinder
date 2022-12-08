package com.group80.uoftinder;

import android.util.Log;

import androidx.annotation.NonNull;

import com.google.android.gms.tasks.Task;
import com.google.android.gms.tasks.Tasks;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.group80.uoftinder.chat.chat.Message;
import com.group80.uoftinder.chat.chat.MessageFactory;
import com.group80.uoftinder.firebase.realtime.RealtimeDbValueObserver;
import com.group80.uoftinder.firebase.realtime.ucChatMessageWriter;

import org.junit.Test;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.TimeUnit;

/**
 * Unit tests for the chat system
 */
public class ChatSystemUnitTest {
    @Test
    public void testDatabaseUpdatedOnMessage() {
        String selfUid = "ChatTestUser1";
        String contactUid = "ChatTestUser2";
        String chatRoom = selfUid + contactUid;

        List<Message> originalList = new ArrayList<>();
        DatabaseReference reference = FirebaseDatabase.getInstance().getReference().child("chats").child(chatRoom);
        Task<DataSnapshot> task = reference.get();

        // get list of chat messages before sending new message
        Thread thread = new Thread(() -> {
            try {
                DataSnapshot dataSnapshot = Tasks.await(task, 2000, TimeUnit.MILLISECONDS);
                for (DataSnapshot child : dataSnapshot.getChildren())
                    originalList.add(child.getValue(Message.class));
            } catch (Exception e) {
                e.printStackTrace();
            }
        });

        thread.start();
        try {
            thread.join();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        ucChatMessageWriter chatMessageWriter = new ucChatMessageWriter(new RealtimeDbValueObserver() {
            @Override
            public void onRealtimeDbDataChange(@NonNull DataSnapshot snapshot) {
                List<Message> chatList = new ArrayList<>();
                for (DataSnapshot child : snapshot.getChildren())
                    chatList.add(child.getValue(Message.class));
                // remove messages that existed in original message list
                chatList.removeAll(originalList);
                // check that the new message equals the message sent
                Log.d("CHAT_TEST", chatList.get(0).getMessage());
                assert chatList.get(0).getMessage().equals("Test message!");
            }

            @Override
            public void onRealtimeDbCancelled(@NonNull DatabaseError error) {
            }
        }, chatRoom);

        thread = new Thread(() -> {
            chatMessageWriter.write(MessageFactory.createMessage("Test message!", selfUid));
            try {
                Thread.sleep(2000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        });

        thread.start();
        try {
            thread.join();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}
