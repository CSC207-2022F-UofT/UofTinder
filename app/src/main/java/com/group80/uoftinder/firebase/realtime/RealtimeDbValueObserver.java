package com.group80.uoftinder.firebase.realtime;

import androidx.annotation.NonNull;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;

/**
 * An observer interface, defining the behaviours when the value in the observed realtime database
 * has changed.
 */
public interface RealtimeDbValueObserver {
    /**
     * Triggered when the realtime database has successfully changed.
     *
     * @param snapshot a snapshot of the realtime database after the change
     */
    void onRealtimeDbDataChange(@NonNull DataSnapshot snapshot);

    /**
     * Triggered when the realtime database failed to change due to an error
     *
     * @param error the error that caused the data change to fail
     */
    void onRealtimeDbCancelled(@NonNull DatabaseError error);
}

