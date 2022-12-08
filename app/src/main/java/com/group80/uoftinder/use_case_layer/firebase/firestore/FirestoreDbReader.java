package com.group80.uoftinder.use_case_layer.firebase.firestore;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.Query;

public class FirestoreDbReader {
    /**
     * Get the contacts of the user with the given uid as a {@link com.google.firebase.firestore.Query}
     *
     * @param uid the uid of the user whose contact list is to be returned
     * @return A {@link com.google.firebase.firestore.Query} of contacts of the given user
     */
    public static Query getContactsAsQuery(String uid) {
        // Get a collection of `documents` who has `uid` in their "contacts" array
        return FirebaseFirestore.getInstance()
                .collection("Users")
                .whereArrayContains("matches",
                        FirebaseAuth.getInstance().getCurrentUser().getUid());
    }
}
