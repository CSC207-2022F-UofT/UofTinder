package com.group80.uoftinder.firebase.realtime;

import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.util.ArrayList;
import java.util.List;

public abstract class ucWriter<T> {
    protected final DatabaseReference databaseReference;
    protected List<RealtimeDbUploadable> listeners;

    public ucWriter() {
        this.databaseReference = FirebaseDatabase.getInstance().getReference();
        this.listeners = new ArrayList<>();
    }

    public abstract void write(List<String> structure, T data);

    public void addListener(RealtimeDbUploadable listener) {
        this.listeners.add(listener);
    }

    protected void notifySuccess(Void unused) {
        for (RealtimeDbUploadable listener : this.listeners)
            listener.onWriteSuccess(unused);
    }

    protected void notifyFailure(Void unused) {
        for (RealtimeDbUploadable listener : this.listeners)
            listener.onWriteFailure(unused);
    }
}
