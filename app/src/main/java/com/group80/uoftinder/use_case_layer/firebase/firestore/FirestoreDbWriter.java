package com.group80.uoftinder.use_case_layer.firebase.firestore;

import com.google.firebase.firestore.FieldValue;
import com.google.firebase.firestore.FirebaseFirestore;
import com.group80.uoftinder.interface_adapter_layer.chat.ContactModel;
import com.group80.uoftinder.entities_layer.User;

import java.util.ArrayList;

/**
 * Class to write contact information for the current user to the Firestore database
 */
public class FirestoreDbWriter {
    /**
     * Uploads the given {@link User} to Firebase Firestore as a {@link ContactModel}.
     *
     * @param currentUser the user whose information is to be uploaded
     */
    public static void uploadUser(User currentUser) {
        FirebaseFirestore db = FirebaseFirestore.getInstance();
        ContactModel contact = new ContactModel(currentUser.getName(), currentUser.getUid(), new ArrayList<>());
        db.collection("Users").document(currentUser.getUid()).set(contact);
    }

    /**
     * Update both users' contacts lists so that they contain each other
     * <p>
     * Precondition:
     * - user1 and user2 must be matched with each other
     *
     * @param user1 the uid of one user
     * @param user2 the uid of the other user
     */
    public static void updateContactsLists(String user1, String user2) {
        FirebaseFirestore db = FirebaseFirestore.getInstance();
        db.collection("Users").document(user1).update("matches", FieldValue.arrayUnion(user2));
        db.collection("Users").document(user2).update("matches", FieldValue.arrayUnion(user1));
    }
}
