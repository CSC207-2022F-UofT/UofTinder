package com.group80.uoftinder.firebase.realtime;

public interface RealtimeDbUploadable {
    void onWriteSuccess(Void unused);

    void onWriteFailure(Void unused);
}
