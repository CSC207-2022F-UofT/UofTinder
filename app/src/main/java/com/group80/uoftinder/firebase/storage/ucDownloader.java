package com.group80.uoftinder.firebase.storage;

import androidx.annotation.NonNull;

import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.StorageReference;

import java.util.ArrayList;
import java.util.List;

/**
 * A downloader to download data from the firebase cloud storage
 *
 * @param <T> the type of the processed downloaded data, to be returned
 * @param <S> the type of the raw downloaded data
 */
public abstract class ucDownloader<T, S> {
    protected final StorageReference storageReference;
    protected List<StorageDbDownloadable<S>> listeners;

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
     * @return the data
     */
    public abstract T download(String downloadUri);

    public void addListener(StorageDbDownloadable<S> listener) {
        this.listeners.add(listener);
    }

    /**
     * Notifies the listeners for a successful download
     *
     * @param data the data downloaded
     */
    protected void notifySuccess(S data) {
        for (StorageDbDownloadable<S> listener : this.listeners)
            listener.onStorageDownloadSuccess(data);
    }

    protected void notifyFailure(@NonNull Exception e) {
        for (StorageDbDownloadable<S> listener : this.listeners)
            listener.onStorageDownloadFailure(e);
    }
}
