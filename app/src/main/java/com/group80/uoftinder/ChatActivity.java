package com.group80.uoftinder;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.content.res.AppCompatResources;
import androidx.appcompat.widget.Toolbar;
import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.google.firebase.auth.FirebaseAuth;
import com.group80.uoftinder.chat.ChatPresenter;
import com.group80.uoftinder.chat.ChatView;
import com.group80.uoftinder.chat.Message;
import com.group80.uoftinder.chat.MessageAdapter;
import com.group80.uoftinder.firebase.ImageViewImagePresenter;

import java.util.ArrayList;
import java.util.List;

/**
 * The chat window to a specific contact
 */
public class ChatActivity extends AppCompatActivity implements ChatView {
    private MessageAdapter messageAdapter;
    private RecyclerView messagesRecyclerView;

    private EditText messageEditText;
    private TextView contactName;
    private ImageView contactProfilePic;

    /**
     * Called when the activity is first created. This is where you should do all of your normal
     * static set up: create views, bind data to lists, etc. This method also provides you with a
     * Bundle containing the activity's previously frozen state, if there was one.
     * <p>
     * Always followed by `onStart()`.
     *
     * @param savedInstanceState If the activity is being re-initialized after previously being
     *                           shut down then this Bundle contains the data it most recently
     *                           supplied in `onSaveInstanceState(Bundle)`.
     *                           <p>
     *                           Note: Otherwise it is null.
     */
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_chat);

        // Connect UI elements
        ImageButton backButton = findViewById(R.id.chatActivityBackButton);
        contactProfilePic = findViewById(R.id.chatActivityContactProfilePic);
        contactName = findViewById(R.id.chatActivityContactName);
        messagesRecyclerView = findViewById(R.id.chatActivityMessagesRecyclerView);
        messageEditText = findViewById(R.id.chatActivityMessageEditText);
        CardView sendMessageCardView = findViewById(R.id.chatActivitySendMessageCardView);
        ImageButton sendMessageButton = findViewById(R.id.chatActivitySendMessageButton);

        Toolbar toolbar = findViewById(R.id.chatActivityToolBar);
        setSupportActionBar(toolbar);

        // Setup message display container
        List<Message> messageList = new ArrayList<>();
        messageAdapter = new MessageAdapter(messageList);

        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(this);
        linearLayoutManager.setStackFromEnd(true);
        messagesRecyclerView.setLayoutManager(linearLayoutManager);
        messagesRecyclerView.setAdapter(messageAdapter);

        String contactUid = getIntent().getStringExtra("contactUid");
        String chatRoom = ChatView.getChatRoom(FirebaseAuth.getInstance().getCurrentUser().getUid(), contactUid);

        ChatPresenter presenter = new ChatPresenter(this, messageList, chatRoom);
        presenter.showContactInfo(getIntent().getStringExtra("name"), contactUid);

        backButton.setOnClickListener(view -> presenter.enterChatActivity());
        sendMessageButton.setOnClickListener(view -> presenter.updateChatMessage(messageEditText.getText().toString(), FirebaseAuth.getInstance().getCurrentUser().getUid()));

        // When keyboard is up / down, scroll the message list to the bottom for better display
        messagesRecyclerView.addOnLayoutChangeListener((view, left, top, right, bottom, oldLeft, oldTop, oldRight, oldBottom) -> {
            recalculateMessagePosition();
        });
    }

    /**
     * Called after `onCreate(Bundle)` — or after `onRestart()` when the activity had been stopped,
     * but is now again being displayed to the user. It will usually be followed by `onResume()`.
     * This is a good place to begin drawing visual elements, running animations, etc.
     */
    @Override
    protected void onStart() {
        super.onStart();
        messageAdapter.notifyItemInserted(messageAdapter.getItemCount() - 1);
    }

    /**
     * Called when you are no longer visible to the user. You will next receive either
     * `onRestart()`, `onDestroy()`, or nothing, depending on later user activity. This is a good
     * place to stop refreshing UI, running animations and other visual things.
     * <p>
     * Derived classes must call through to the super class's implementation of this method. If they
     * do not, an exception will be thrown.
     */
    @Override
    public void onStop() {
        super.onStop();
        if (messageAdapter != null)
            messageAdapter.notifyDataSetChanged();
    }

    /**
     * Displays the contact information of the given contact name
     *
     * @param contactName the name of the contact
     * @param contactUid  the uid of the contact
     */
    @Override
    public void showContactInfo(String contactName, String contactUid) {
        this.contactName.setText(contactName);
        ImageViewImagePresenter.downloadBitmapToImageView(new String[]{contactUid, "img", "_profile_img.jpg"}, this.contactProfilePic, AppCompatResources.getDrawable(getApplicationContext(), R.drawable.ic_baseline_account_circle_24));
    }

    /**
     * Notifies the listeners that message(s) have been added
     */
    @Override
    public void notifyMessageAdded() {
        Log.d("DEBUGGING", "notifyMessageAdded: get new message! Totally: " + messageAdapter.getItemCount());
        messageAdapter.notifyItemInserted(messageAdapter.getItemCount() - 1);
    }

    /**
     * Displays a new message
     *
     * @param message the message body of the new message
     */
    @Override
    public void displayNewMessage(String message) {
        if (message == null || message.isEmpty())
            return;
        messageEditText.setText(null);
        Log.d("DEBUGGING", "displayNewMessage: " + message);
        this.notifyMessageAdded();
    }

    /**
     * Enters the {@link com.group80.uoftinder.ContactsActivity}
     */
    @Override
    public void enterContactView() {
        Intent intent = new Intent(ChatActivity.this, ContactsActivity.class);
        intent.putExtra(Constants.CURRENT_USER_STRING, getIntent().getSerializableExtra(Constants.CURRENT_USER_STRING));
        startActivity(intent);
        finish();
    }

    /**
     * Scrolls the message recycle view to the bottom when showing / hiding keyboard for better
     * display
     */
    private void recalculateMessagePosition() {
        if (messagesRecyclerView.getAdapter() != null && messagesRecyclerView.getAdapter().getItemCount() > 0)
            messagesRecyclerView.smoothScrollToPosition(messagesRecyclerView.getAdapter().getItemCount() - 1);
    }
}