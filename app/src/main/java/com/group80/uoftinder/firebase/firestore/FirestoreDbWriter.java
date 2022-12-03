package com.group80.uoftinder.firebase.firestore;

import android.util.Log;

import com.google.firebase.firestore.FirebaseFirestore;
import com.group80.uoftinder.chat.ContactModel;
import com.group80.uoftinder.entities.User;

import java.util.ArrayList;
import java.util.List;

/**
 * Class to write contact information for the current user to the Firestore database
 */
public class FirestoreDbWriter {
    /**
     * Initialize the current user's contacts list in Firestore as soon as they have successfully
     * registered a new account
     * @param currentUser is the user whose contacts list needs to be initialized
     */
    public static void initializeContactsList(User currentUser) {
        FirebaseFirestore db = FirebaseFirestore.getInstance();
        ContactModel contact = new ContactModel(currentUser.getName(), currentUser.getUid(), new ArrayList<>());
        db.collection("Users").document(currentUser.getUid()).set(contact).addOnSuccessListener(unused -> Log.d("FIRESTORE", "Initialized user in Firestore Db!"));
    }

    /**
     * Update both users' contacts lists so that they contain each other
     * Precondition:
     *   - currentUser and newContact must be matched with each other
     * @param currentUser is the user using the app
     * @param newContact is the user they matched with
     */
    public static void updateContactsLists(User currentUser, User newContact) {
        FirebaseFirestore db = FirebaseFirestore.getInstance();
        db.collection("Users").document(currentUser.getUid()).get().addOnSuccessListener(documentSnapshot -> {
            Log.d("FIRESTORE", "Retrieved currentUser contacts successfully");
            ContactModel cm = documentSnapshot.toObject(ContactModel.class);
            List<String> contactList = cm.getMatches();
            contactList.add(newContact.getUid());
            db.collection("Users").document(currentUser.getUid()).update("matches", contactList)
                    .addOnSuccessListener(unused -> Log.d("FIRESTORE", "Updated currentUser contacts successfully"));
        });

        db.collection("Users").document(newContact.getUid()).get().addOnSuccessListener(documentSnapshot -> {
            Log.d("FIRESTORE", "Retrieved newContact contacts successfully");
            ContactModel cm = documentSnapshot.toObject(ContactModel.class);
            List<String> contactList = cm.getMatches();
            contactList.add(currentUser.getUid());
            db.collection("Users").document(newContact.getUid()).update("matches", contactList)
                    .addOnSuccessListener(unused -> Log.d("FIRESTORE", "Updated newContact contacts successfully"));
        });
    }
}
