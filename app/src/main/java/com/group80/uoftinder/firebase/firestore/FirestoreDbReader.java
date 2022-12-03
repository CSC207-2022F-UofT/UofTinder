package com.group80.uoftinder.firebase.firestore;

import android.util.Log;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.firestore.Query;
import com.google.firebase.firestore.FirebaseFirestore;

public class FirestoreDbReader {
    public static Query getContactsAsQuery(){
        FirebaseFirestore firebaseFirestore = FirebaseFirestore.getInstance();
        String uid = FirebaseAuth.getInstance().getCurrentUser().getUid();

        Query query = firebaseFirestore.collection("Users")
                .whereEqualTo("uid", uid);

        query.get().addOnCompleteListener(task -> {
            if (task.isSuccessful()) {
                Log.d("FIRESTORE", "Retrieved query for contacts");
            }
        });
        return query;
    }
}
