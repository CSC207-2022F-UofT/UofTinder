package com.group80.uoftinder;

import android.graphics.BitmapFactory;
import android.os.Bundle;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.content.res.AppCompatResources;
import androidx.appcompat.widget.Toolbar;
import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.group80.uoftinder.chat.Message;
import com.group80.uoftinder.chat.MessageAdapter;
import com.group80.uoftinder.chat.MessageFactory;
import com.group80.uoftinder.firebase.realtime.RealtimeDbValueObserver;
import com.group80.uoftinder.firebase.realtime.ucChatMessageWriter;
import com.group80.uoftinder.firebase.storage.ImageStorageDbFacade;
import com.group80.uoftinder.firebase.storage.StorageDbDownloadable;

import java.util.ArrayList;
import java.util.List;

/**
 * The chat window to a specific contact
 */
public class ChatActivity extends AppCompatActivity {
    private MessageAdapter messageAdapter;
    private List<Message> messageList;
    private String inputMessage;

    private FirebaseAuth firebaseAuth;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_chat);

        // Connect UI elements
        Toolbar toolbar = findViewById(R.id.chatActivityToolBar);
        ImageButton backButton = findViewById(R.id.chatActivityBackButton);
        ImageView contactProfilePic = findViewById(R.id.chatActivityContactProfilePic);
        TextView contactName = findViewById(R.id.chatActivityContactName);
        RecyclerView messagesRecyclerView = findViewById(R.id.chatActivityMessagesRecyclerView);
        EditText messageEditText = findViewById(R.id.chatActivityMessageEditText);
        CardView sendMessageCardView = findViewById(R.id.chatActivitySendMessageCardView);
        ImageButton sendMessageButton = findViewById(R.id.chatActivitySendMessageButton);

        // Setup message display container
        messageList = new ArrayList<>();
        messageAdapter = new MessageAdapter(ChatActivity.this, messageList);

        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(this);
        linearLayoutManager.setStackFromEnd(true);
        messagesRecyclerView.setLayoutManager(linearLayoutManager);
        messagesRecyclerView.setAdapter(messageAdapter);

        setSupportActionBar(toolbar);

        // TODO: remove such dependency
        String contactUid = getIntent().getStringExtra("contactUid");
        String contactNameStr = getIntent().getStringExtra("name");

        // TODO: remove after having functioning log-in ********************************************
        firebaseAuth = FirebaseAuth.getInstance();
        firebaseAuth.signInWithEmailAndPassword("csc207.group80.uoftinder@gmail.com", "CSC207Group80!").addOnFailureListener(Throwable::printStackTrace);
        String selfUid = firebaseAuth.getCurrentUser().getUid();
        //******************************************************************************************

        String chatRoom = selfUid.compareTo(contactUid) < 0 ? selfUid + contactUid : contactUid + selfUid;
        ucChatMessageWriter chatMessageWriter = new ucChatMessageWriter(new RealtimeDbValueObserver() {
            @Override
            public void onRealtimeDbDataChange(@NonNull DataSnapshot snapshot) {
                messageList.clear();
                for (DataSnapshot childSnapshot : snapshot.getChildren())
                    messageList.add(childSnapshot.getValue(Message.class));
                messageAdapter.notifyItemInserted(messageAdapter.getItemCount() - 1);
            }

            @Override
            public void onRealtimeDbCancelled(@NonNull DatabaseError error) {
                // TODO: handle error
            }
        }, chatRoom);

        backButton.setOnClickListener(view -> {
            finish();
        });

        contactName.setText(contactNameStr);
        ImageStorageDbFacade.downloadImage(new String[]{contactUid, "img", "_profile_img.jpg"},
                new StorageDbDownloadable<byte[]>() {
                    @Override
                    public void onStorageDownloadSuccess(byte[] data) {
                        contactProfilePic.setImageBitmap(BitmapFactory.decodeByteArray(data, 0, data.length));
                    }

                    @Override
                    public void onStorageDownloadFailure(@NonNull Exception exception) {
                        // Set to a default profile image
                        contactProfilePic.setImageDrawable(AppCompatResources.getDrawable(getApplicationContext(), R.drawable.ic_baseline_account_circle_24));
                    }
                });

        sendMessageButton.setOnClickListener(view -> {
            inputMessage = messageEditText.getText().toString();
            if (inputMessage.isEmpty()) return;

            // TODO: wrap-up firebaseAuth
            chatMessageWriter.write(MessageFactory.createMessage(inputMessage, firebaseAuth.getCurrentUser().getUid()));
            messageEditText.setText(null);
        });

        // When keyboard is up / down, scroll the message list to the bottom for better display
        messagesRecyclerView.addOnLayoutChangeListener((view, left, top, right, bottom, oldLeft, oldTop, oldRight, oldBottom) -> {
            if (messagesRecyclerView.getAdapter() != null && messagesRecyclerView.getAdapter().getItemCount() > 0)
                messagesRecyclerView.smoothScrollToPosition(messagesRecyclerView.getAdapter().getItemCount() - 1);
        });
    }

    @Override
    protected void onStart() {
        super.onStart();
        messageAdapter.notifyItemInserted(messageAdapter.getItemCount() - 1);
    }

    @Override
    public void onStop() {
        super.onStop();
        if (messageAdapter != null)
            messageAdapter.notifyDataSetChanged();
    }
}