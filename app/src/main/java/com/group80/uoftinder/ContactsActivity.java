package com.group80.uoftinder;

import android.content.Context;
import android.content.Intent;
import android.graphics.BitmapFactory;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.content.res.AppCompatResources;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.firebase.ui.firestore.FirestoreRecyclerAdapter;
import com.firebase.ui.firestore.FirestoreRecyclerOptions;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.Query;
import com.group80.uoftinder.chat.ContactModel;
import com.group80.uoftinder.firebase.storage.ImageStorageDbFacade;
import com.group80.uoftinder.firebase.storage.StorageDbDownloadable;

/**
 * A window that displays all the contacts to chat with
 */
public class ContactsActivity extends AppCompatActivity {
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

        FirestoreRecyclerOptions<ContactModel> contacts = new FirestoreRecyclerOptions.Builder<ContactModel>().setQuery(query, ContactModel.class).build();
        contactAdapter = new FirestoreRecyclerAdapter<ContactModel, ContactViewHolder>(contacts) {
            @Override
            protected void onBindViewHolder(@NonNull ContactViewHolder holder, int position, @NonNull ContactModel contactModel) {
                holder.onBind(getApplicationContext(), position, contactModel);
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

    private class ContactViewHolder extends RecyclerView.ViewHolder {
        protected final TextView contactName;
        protected final ImageView contactPic;

        public ContactViewHolder(@NonNull View itemView) {
            super(itemView);
            contactName = itemView.findViewById(R.id.contactListContactName);
            contactPic = itemView.findViewById(R.id.contactListContactPic);
        }

        /**
         * Triggered when this View Holder is bind to an adapter
         * <p>
         * // TODO: this method may require architectural fix
         *
         * @param context      the global information about an application environment // TODO: architectural fix?
         * @param position     the position of this View Holder in the adapter
         * @param contactModel a model storing the contact information // TODO: architectural fix
         */
        protected void onBind(Context context, int position, @NonNull ContactModel contactModel) {
            contactName.setText(contactModel.getName());

            // TODO: extract the set-to-image-view process?
            ImageStorageDbFacade.downloadImage(
                    new String[]{contactModel.getUid(), "img", "_profile_img.jpg"},
                    new StorageDbDownloadable<byte[]>() {
                        @Override
                        public void onStorageDownloadSuccess(byte[] data) {
                            contactPic.setImageBitmap(BitmapFactory.decodeByteArray(data, 0, data.length));
                        }

                        @Override
                        public void onStorageDownloadFailure(@NonNull Exception exception) {
                            // Set to a default profile image
                            contactPic.setImageDrawable(AppCompatResources.getDrawable(getApplicationContext(), R.drawable.ic_baseline_account_circle_24));
                        }
                    }
            );

            // TODO: optimize architecture
            itemView.setOnClickListener(view -> {
                Intent intent = new Intent(context, ChatActivity.class);
                intent.putExtra("name", contactModel.getName());
                intent.putExtra("contactUid", contactModel.getUid());
                startActivity(intent);
            });
        }
    }
}