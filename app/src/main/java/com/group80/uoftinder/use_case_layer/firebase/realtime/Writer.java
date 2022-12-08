package com.group80.uoftinder.use_case_layer.firebase.realtime;

import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.util.ArrayList;
import java.util.List;

public abstract class Writer<T> {
    /**
     * A reference to the root of the database
     */
    protected final DatabaseReference databaseReference;
    /**
     * A list of listeners, actions triggered on successful / failed uploads
     */
    protected List<RealtimeDbWriteListener> listeners;

    /**
     * Constructor, creates a realtime database writer
     */
    public Writer() {
        this.databaseReference = FirebaseDatabase.getInstance().getReference();
        this.listeners = new ArrayList<>();
    }

    /**
     * Writes the data to the given structural location in the realtime database
     *
     * @param structure the file structure of the upload
     * @param data      the data to be uploaded
     */
    public abstract void write(String[] structure, T data);

    /**
     * Adds a listener to the listener list
     *
     * @param listener the listener to be added
     */
    public void addListener(RealtimeDbWriteListener listener) {
        this.listeners.add(listener);
    }

    /**
     * Notifies the listeners that a successful upload has been made
     *
     * @param unused a placeholder
     */
    protected void notifySuccess(Void unused) {
        for (RealtimeDbWriteListener listener : this.listeners)
            listener.onWriteSuccess(unused);
    }

    /**
     * Notifies the listeners that a failed upload has been made
     *
     * @param e the Exception that caused the upload to fail
     */
    protected void notifyFailure(Exception e) {
        for (RealtimeDbWriteListener listener : this.listeners)
            listener.onWriteFailure(e);
    }
}
