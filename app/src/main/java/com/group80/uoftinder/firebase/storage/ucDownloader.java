package com.group80.uoftinder.firebase.storage;

import androidx.annotation.NonNull;

import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.StorageReference;

import java.util.ArrayList;
import java.util.List;

/**
 * A downloader to download data from the firebase cloud storage
 *
 * @param <T> the type of the downloaded data
 */
public abstract class ucDownloader<T> {
    protected final StorageReference storageReference;
    protected List<StorageDbDownloadable<T>> listeners;

    /**
     * Creates a downloader for firebase
     */
    public ucDownloader() {
        this.storageReference = FirebaseStorage.getInstance().getReference();
        this.listeners = new ArrayList<>();
    }

    /**
     * Downloads data from the firebase storage.
     *
     * @param downloadUri the url to download the data
     */
    public abstract void download(String downloadUri);

    public void addListener(StorageDbDownloadable<T> listener) {
        this.listeners.add(listener);
    }

    /**
     * Notifies the listeners for a successful download
     *
     * @param data the data downloaded
     */
    protected void notifySuccess(T data) {
        for (StorageDbDownloadable<T> listener : this.listeners)
            listener.onStorageDownloadSuccess(data);
    }

    /**
     * Notifies the listeners for a fail download
     *
     * @param e the error that caused the download to fail
     */
    protected void notifyFailure(@NonNull Exception e) {
        for (StorageDbDownloadable<T> listener : this.listeners)
            listener.onStorageDownloadFailure(e);
    }
}
