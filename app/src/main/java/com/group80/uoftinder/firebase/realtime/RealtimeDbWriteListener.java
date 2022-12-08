package com.group80.uoftinder.firebase.realtime;

/**
 * An interface handles successful / failed uploads to the realtime database
 */
public interface RealtimeDbWriteListener {
    /**
     * Handles the behaviour for a successful upload
     *
     * @param unused a placeholder
     */
    void onWriteSuccess(Void unused);

    /**
     * Handles the behaviour for a failed upload
     *
     * @param e the Exception that caused the upload to fail
     */
    void onWriteFailure(Exception e);
}
