package com.group80.uoftinder;

import android.content.Intent;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.content.ContextCompat;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.firebase.ui.firestore.FirestoreRecyclerAdapter;
import com.firebase.ui.firestore.FirestoreRecyclerOptions;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.Query;
import com.group80.uoftinder.chat.ContactModel;
import com.group80.uoftinder.chat.ContactPresenter;
import com.group80.uoftinder.chat.ContactViewHolder;
import com.group80.uoftinder.chat.ContactsView;

/**
 * A window that displays all the contacts to chat with
 */
public class ContactsActivity extends AppCompatActivity implements ContactsView {
    FirestoreRecyclerAdapter<ContactModel, ContactViewHolder> contactAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_contacts);

        // TODO: remove after having functioning log-in ********************************************
        FirebaseAuth firebaseAuth = FirebaseAuth.getInstance();
        firebaseAuth.signInWithEmailAndPassword("csc207.group80.uoftinder@gmail.com", "CSC207Group80!").addOnFailureListener(Throwable::printStackTrace);
//        firebaseAuth.signInWithEmailAndPassword("csc207.group80.uoftinder.bot@gmail.com", "12345678").addOnFailureListener(Throwable::printStackTrace);

        // TODO: wrap-up FirebaseFirestore
        FirebaseFirestore firebaseFirestore = FirebaseFirestore.getInstance();
        String uid = firebaseAuth.getCurrentUser().getUid();
        Query query = firebaseFirestore.collection("Users").whereArrayContains("contacts", uid);
        //******************************************************************************************

        ContactPresenter presenter = new ContactPresenter(this);

        FirestoreRecyclerOptions<ContactModel> contacts = new FirestoreRecyclerOptions.Builder<ContactModel>().setQuery(query, ContactModel.class).build();
        contactAdapter = new FirestoreRecyclerAdapter<ContactModel, ContactViewHolder>(contacts) {
            @Override
            protected void onBindViewHolder(@NonNull ContactViewHolder holder, int position, @NonNull ContactModel contactModel) {
                Drawable defaultProfilePic = ContextCompat.getDrawable(getApplicationContext(), R.drawable.ic_launcher_foreground);
                presenter.setContactInfo(holder, contactModel, defaultProfilePic);
                holder.itemView.setOnClickListener(view -> presenter.enterChatActivity(contactModel));
            }

            @NonNull
            @Override
            public ContactViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
                View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.uoftinder_contact_list_contact_layout, parent, false);
                return new ContactViewHolder(view);
            }
        };

        RecyclerView recyclerView = findViewById(R.id.contactActivityRecyclerView);
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(this);
        recyclerView.setHasFixedSize(true);
        recyclerView.setLayoutManager(linearLayoutManager);
        recyclerView.setAdapter(contactAdapter);
    }

    @Override
    public void onStart() {
        super.onStart();
        contactAdapter.startListening();
    }

    @Override
    public void onStop() {
        super.onStop();
        if (contactAdapter != null) {
            contactAdapter.stopListening();
        }
    }

    @Override
    public void enterChatView(ContactModel contactModel) {
        Intent intent = new Intent(this.getApplicationContext(), ChatActivity.class);
        intent.putExtra("name", contactModel.getName());
        intent.putExtra("contactUid", contactModel.getUid());
        startActivity(intent);
    }
}