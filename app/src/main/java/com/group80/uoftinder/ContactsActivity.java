package com.group80.uoftinder;

import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.ViewGroup;
import android.widget.ImageButton;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.firebase.ui.firestore.FirestoreRecyclerAdapter;
import com.group80.uoftinder.chat.ContactModel;
import com.group80.uoftinder.chat.ContactPresenter;
import com.group80.uoftinder.chat.ContactViewHolder;
import com.group80.uoftinder.chat.ContactsView;
import com.group80.uoftinder.feed.RecommendationView;

/**
 * A window that displays all the contacts to chat with
 */
public class ContactsActivity extends AppCompatActivity implements ContactsView {
    FirestoreRecyclerAdapter<ContactModel, ContactViewHolder> contactAdapter;

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
        setContentView(R.layout.activity_contacts);

        ContactPresenter presenter = new ContactPresenter(this);

        ImageButton button = findViewById(R.id.contactsActivityBackButton);
        button.setOnClickListener(view -> presenter.enterRecommendationActivity());

        Toolbar toolbar = findViewById(R.id.contactsActivityToolBar);
        setSupportActionBar(toolbar);

        contactAdapter = new FirestoreRecyclerAdapter<ContactModel, ContactViewHolder>(presenter.setRecyclerAdapterOption()) {
            @Override
            protected void onBindViewHolder(@NonNull ContactViewHolder holder, int position, @NonNull ContactModel contactModel) {
                holder.displayContactInfo(getApplicationContext(), contactModel);
                holder.itemView.setOnClickListener(view -> presenter.enterChatActivity(contactModel));
            }

            @NonNull
            @Override
            public ContactViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
                return new ContactViewHolder(LayoutInflater.from(parent.getContext()).inflate(R.layout.uoftinder_contact_list_contact_layout, parent, false));
            }
        };

        // Sets the layout
        RecyclerView recyclerView = findViewById(R.id.contactsActivityRecyclerView);
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(this);
        recyclerView.setHasFixedSize(true);
        recyclerView.setLayoutManager(linearLayoutManager);
        recyclerView.setAdapter(contactAdapter);
    }

    /**
     * Called when the activity is becoming visible to the user.
     * <p>
     * Followed by `onResume()` if the activity comes to the foreground, or `onStop()` if it becomes
     * hidden.
     */
    @Override
    public void onStart() {
        super.onStart();
        contactAdapter.startListening();
    }

    /**
     * Called when the activity is no longer visible to the user. This may happen either because a
     * new activity is being started on top, an existing one is being brought in front of this one,
     * or this one is being destroyed. This is typically used to stop animations and refreshing the
     * UI, etc.
     * <p>
     * Followed by either `onRestart()` if this activity is coming back to interact with the user,
     * or `onDestroy()` if this activity is going away.
     */
    @Override
    public void onStop() {
        super.onStop();
        if (contactAdapter != null) {
            contactAdapter.stopListening();
        }
    }

    /**
     * Enters {@link ChatActivity} with the given contact
     *
     * @param contactModel a model storing the information of a contact
     */
    @Override
    public void enterChatView(ContactModel contactModel) {
        Intent intent = new Intent(ContactsActivity.this, ChatActivity.class);
        intent.putExtra("name", contactModel.getName());
        intent.putExtra("contactUid", contactModel.getUid());
        intent.putExtra(Constants.CURRENT_USER_STRING, getIntent().getSerializableExtra(Constants.CURRENT_USER_STRING));
        startActivity(intent);
    }

    /**
     * Enters the recommendation view
     */
    @Override
    public void enterRecommendationView() {
        Intent intent = new Intent(ContactsActivity.this, RecommendationView.class);
        intent.putExtra(Constants.CURRENT_USER_STRING, getIntent().getSerializableExtra(Constants.CURRENT_USER_STRING));
        startActivity(intent);
    }
}