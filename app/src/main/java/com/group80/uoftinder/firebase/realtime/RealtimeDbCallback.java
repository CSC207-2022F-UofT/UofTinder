package com.group80.uoftinder.firebase.realtime;

/**
 * An interface handles the data read from the realtime database
 *
 * @param <T> the type of the data
 */
public interface RealtimeDbCallback<T> {
    /**
     * The behaviour on the data
     *
     * @param data the data read from the database
     */
    void onData(T data);
}
