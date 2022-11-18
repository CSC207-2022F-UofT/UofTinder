package com.group80.uoftinder;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Bundle;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.StorageReference;
import com.group80.uoftinder.chat.Message;
import com.group80.uoftinder.chat.MessageAdapter;
import com.group80.uoftinder.firebase.realtime.RealtimeDbValueObserver;
import com.group80.uoftinder.firebase.realtime.ucChatMessageWriter;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.Locale;

public class ChatActivity extends AppCompatActivity implements RealtimeDbValueObserver {
    private String currentTime;
    private Calendar calendar;
    private SimpleDateFormat simpleDateFormat;

    private MessageAdapter messageAdapter;
    private List<Message> messageList;
    private String inputMessage;

    //    private FirebaseDatabase firebaseDatabase;
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
        ucChatMessageWriter chatMessageWriter = new ucChatMessageWriter(this, chatRoom);

        calendar = Calendar.getInstance();
        simpleDateFormat = new SimpleDateFormat("hh:mm a", Locale.CANADA);

        backButton.setOnClickListener(view -> {
            // TODO: wrap-up firebaseAuth
//            FirebaseAuth.getInstance().signOut();
            finish();
        });

        contactName.setText(contactNameStr);
        // TODO: wrap-up the image showing process
        FirebaseStorage storage = FirebaseStorage.getInstance();
        StorageReference imgRef = storage.getReference().child(contactUid).child("img").child("_profile_img.jpg");
        imgRef.getBytes(5120 * 5120).addOnCompleteListener(task -> {
            byte[] imageData = task.getResult();
            Bitmap profileImage = BitmapFactory.decodeByteArray(imageData, 0, imageData.length);
            contactProfilePic.setImageBitmap(profileImage);
        });

        sendMessageButton.setOnClickListener(view -> {
            inputMessage = messageEditText.getText().toString();
            if (inputMessage.isEmpty()) return;

            // TODO: simplify this
            Date date = new Date();
            currentTime = simpleDateFormat.format(calendar.getTime());
            // TODO: wrap-up firebaseAuth
            Message message = new Message(inputMessage, firebaseAuth.getUid(), date.getTime(), currentTime);
            chatMessageWriter.write(message);
            messageEditText.setText(null);
        });

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

    @Override
    public void onRealtimeDbDataChange(@NonNull DataSnapshot snapshot) {
        messageList.clear();
        for (DataSnapshot childSnapshot : snapshot.getChildren())
            this.messageList.add(childSnapshot.getValue(Message.class));
        messageAdapter.notifyItemInserted(messageAdapter.getItemCount() - 1);
    }

    @Override
    public void onRealtimeDbCancelled(@NonNull DatabaseError error) {
        // TODO: handle error
    }
}