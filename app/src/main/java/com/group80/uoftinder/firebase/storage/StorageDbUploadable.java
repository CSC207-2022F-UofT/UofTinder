package com.group80.uoftinder.firebase.storage;

import androidx.annotation.NonNull;

import com.google.firebase.storage.UploadTask;

/**
 * Defines the behaviour after a upload task for the cloud storage database
 */
public interface StorageDbUploadable {
    /**
     * Handles successful uploads
     *
     * @param taskSnapshot taskSnapshot.getMetadata() contains file metadata such as size, content-type, etc.
     */
    void onStorageUploadSuccess(UploadTask.TaskSnapshot taskSnapshot);

    /**
     * Handles unsuccessful uploads
     *
     * @param exception the exception that caused the upload to fail
     */
    void onStorageUploadFailure(@NonNull Exception exception);
}
