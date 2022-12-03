package com.group80.uoftinder.firebase.firestore;

import android.util.Log;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.Query;
import com.group80.uoftinder.chat.ContactModel;

import java.util.ArrayList;
import java.util.Arrays;

public class FirebaseFirestoreWriter {


//    public static Query getQuery(){
//        FirebaseFirestore firebaseFirestore = FirebaseFirestore.getInstance();
//        String uid = FirebaseAuth.getInstance().getCurrentUser().getUid();
//
//        Query query = firebaseFirestore.collection("Users")
//                .whereEqualTo("uid", uid);
//
//        query.get().addOnCompleteListener(task -> {
//            if (task.isSuccessful())
//        })
//        return query;
//    }
//

    public void writeToFirestore(ContactModel contact) {
        FirebaseFirestore db = FirebaseFirestore.getInstance();
//        User user = new User();
        db.collection("Users").document(contact.getUid()).set(contact).addOnSuccessListener(unused -> Log.d("FIRESTORE", "Write success"));
        ArrayList<String> contacts = new ArrayList<>(Arrays.asList("testtttt", "testtttttt2"));
        db.collection("Users").document(contact.getUid()).update("contacts", contacts);
    }
}
