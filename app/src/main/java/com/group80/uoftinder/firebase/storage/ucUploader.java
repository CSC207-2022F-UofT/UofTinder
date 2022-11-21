package com.group80.uoftinder.firebase.storage;

import androidx.annotation.NonNull;

import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.StorageReference;
import com.google.firebase.storage.UploadTask;

import java.util.ArrayList;
import java.util.List;


/**
 * An uploader to upload data to the firebase cloud storage
 *
 * @param <T> the type of data to be uploaded
 */
public abstract class ucUploader<T> {
    protected final StorageReference storageReference;
    protected List<StorageDbUploadable> listeners;

    /**
     * Creates an uploader for firebase
     */
    public ucUploader() {
        this.storageReference = FirebaseStorage.getInstance().getReference();
        this.listeners = new ArrayList<>();
    }

    /**
     * Uploads data to the firebase storage
     *
     * @param data      the data to be uploaded
     * @param cloudPath the path of the file on the cloud storage
     * @return
     */
    public abstract String upload(@NonNull T data, @NonNull String cloudPath);

    /**
     * Adds a listener to the current uploader
     *
     * @param listener the listener to be added
     */
    public void addListener(StorageDbUploadable listener) {
        this.listeners.add(listener);
    }

    /**
     * Notifies the listeners for a successful upload
     *
     * @param taskSnapshot
     */
    protected void notifySuccess(UploadTask.TaskSnapshot taskSnapshot) {
        for (StorageDbUploadable listener : this.listeners)
            listener.onStorageUploadSuccess(taskSnapshot);
    }

    /**
     * Notifies the listeners for a upload failure
     *
     * @param e the exception representing the failure
     */
    protected void notifyFailure(@NonNull Exception e) {
        for (StorageDbUploadable listener : this.listeners)
            listener.onStorageUploadFailure(e);
    }
}
