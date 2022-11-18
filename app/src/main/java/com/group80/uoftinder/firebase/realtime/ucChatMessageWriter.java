package com.group80.uoftinder.firebase.realtime;

import androidx.annotation.NonNull;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.group80.uoftinder.chat.Message;

public class ucChatMessageWriter {
    private final DatabaseReference databaseReference;

    public ucChatMessageWriter(RealtimeDbValueObserver observer, String chatRoom) {
        this.databaseReference = FirebaseDatabase.getInstance().getReference().child("chats").child(chatRoom);

        this.databaseReference.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                observer.onRealtimeDbDataChange(snapshot);
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {
                observer.onRealtimeDbCancelled(error);
            }
        });
    }

    public void write(Message message) {
        this.databaseReference.push().setValue(message);
    }
}
