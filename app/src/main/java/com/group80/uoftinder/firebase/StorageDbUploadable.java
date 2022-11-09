package com.group80.uoftinder.firebase;

import androidx.annotation.NonNull;

import com.google.firebase.storage.UploadTask;

public interface StorageDbUploadable {
    /**
     * Handles successful uploads
     *
     * @param taskSnapshot taskSnapshot.getMetadata() contains file metadata such as size,
     *                     content-type, etc.
     */
    void onStorageUploadSuccess(UploadTask.TaskSnapshot taskSnapshot);

    /**
     * Handles unsuccessful uploads
     *
     * @param exception the exception that caused the upload to fail
     */
    void onStorageUploadFailure(@NonNull Exception exception);
}
