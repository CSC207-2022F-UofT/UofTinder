package com.group80.uoftinder.firebase;

import androidx.annotation.NonNull;

import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.StorageReference;

import java.util.ArrayList;
import java.util.List;

public abstract class ucDownloader<T, S> {
    protected final StorageReference storageReference;
    protected List<StorageDbDownloadable<S>> listeners;

    public ucDownloader() {
        this.storageReference = FirebaseStorage.getInstance().getReference();
        this.listeners = new ArrayList<>();
    }

    public abstract T download(String downloadUri);

    public void addListener(StorageDbDownloadable<S> listener) {
        this.listeners.add(listener);
    }

    protected void notifySuccess(S data) {
        for (StorageDbDownloadable<S> listener : this.listeners)
            listener.onStorageDownloadSuccess(data);
    }

    protected void notifyFailure(@NonNull Exception e) {
        for (StorageDbDownloadable<S> listener : this.listeners)
            listener.onStorageDownloadFailure(e);
    }
}
