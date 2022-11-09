package com.group80.uoftinder.firebase;

import androidx.annotation.NonNull;

import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.StorageReference;
import com.google.firebase.storage.UploadTask;

import java.util.ArrayList;
import java.util.List;

public abstract class ucUploader<T> {
    protected final StorageReference storageReference;
    protected List<StorageDbUploadable> listeners;

    public ucUploader() {
        this.storageReference = FirebaseStorage.getInstance().getReference();
        this.listeners = new ArrayList<>();
    }

    public abstract String upload(@NonNull T data, @NonNull String cloudPath);

    public void addListener(StorageDbUploadable listener) {
        this.listeners.add(listener);
    }

    protected void notifySuccess(UploadTask.TaskSnapshot taskSnapshot) {
        for (StorageDbUploadable listener : this.listeners)
            listener.onStorageUploadSuccess(taskSnapshot);
    }

    protected void notifyFailure(@NonNull Exception e) {
        for (StorageDbUploadable listener : this.listeners)
            listener.onStorageUploadFailure(e);
    }
}
