package com.group80.uoftinder.firebase.realtime;

import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.auth.FirebaseAuth;

import java.util.List;

public class ucClassWriter<T> extends ucWriter<T> {
    @Override
    public void write(List<String> structure, T data) {
        this.databaseReference.child("users").child(FirebaseAuth.getInstance().getCurrentUser().getUid()).setValue(data).addOnSuccessListener(this::notifySuccess);
    }
}
