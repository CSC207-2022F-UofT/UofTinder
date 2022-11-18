package com.group80.uoftinder.firebase.realtime;

import androidx.annotation.NonNull;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;

public interface RealtimeDbValueObserver {
    void onRealtimeDbDataChange(@NonNull DataSnapshot snapshot);

    void onRealtimeDbCancelled(@NonNull DatabaseError error);
}

