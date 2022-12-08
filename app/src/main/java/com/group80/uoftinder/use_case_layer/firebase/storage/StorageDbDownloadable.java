package com.group80.uoftinder.use_case_layer.firebase.storage;

import androidx.annotation.NonNull;

/**
 * Defines the behaviour after a download task for the cloud storage database
 *
 * @param <T> the type of raw data downloaded from the cloud storage
 */
public interface StorageDbDownloadable<T> {
    /**
     * Handles successful downloads
     *
     * @param data the raw data downloaded from cloud
     */
    void onStorageDownloadSuccess(T data);

    /**
     * Handles unsuccessful uploads
     *
     * @param exception the exception that caused the upload to fail
     */
    void onStorageDownloadFailure(@NonNull Exception exception);
}
