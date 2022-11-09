package com.group80.uoftinder.firebase;

import androidx.annotation.NonNull;

public interface StorageDbDownloadable<T> {
    void onStorageDownloadSuccess(T data);

    void onStorageDownloadFailure(@NonNull Exception exception);
}
