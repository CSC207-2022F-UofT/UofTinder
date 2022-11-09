package com.group80.uoftinder.firebase;

import android.net.Uri;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.StorageReference;
import com.google.firebase.storage.UploadTask;

import java.io.File;

public class StorageDbController {
    /**
     * A reference point to the firebase storage
     */
    private final StorageReference storageReference;

    /**
     * Constructor, connects to the online database
     */
    public StorageDbController() {
        FirebaseStorage storage = FirebaseStorage.getInstance();
        storageReference = storage.getReference();
    }

    /**
     * Upload a byte array (Bitmap image) to the Firebase Storage Database
     *
     * @param data              The byte array to be uploaded
     * @param cloudFilePath     The full path to the file in the Firebase Storage Database, in the format of "path/fileName"
     * @param storageDbListener
     * @return
     */
    public String uploadBytes(@NonNull byte[] data,
                              @NonNull String cloudFilePath,
                              @NonNull StorageDbUploadable storageDbListener) {
        if (cloudFilePath.equals(""))
            return null;

        StorageReference fileRef = storageReference.child(cloudFilePath);

        UploadTask uploadTask = fileRef.putBytes(data);
//        uploadTask.addOnSuccessListener(storageDbListener::onSuccess).addOnFailureListener(storageDbListener::onFailure);

        if (uploadTask.isSuccessful())
            return fileRef.getDownloadUrl().toString();
        return null;
    }

    /**
     * Upload a local file to the Firebase Storage Database
     *
     * @param localFilePath     The full path to the file in the local Android system
     * @param cloudFilePath     The full path to the file in the Firebase Storage Database, in the
     *                          format of "path/fileName" (optional, can be `null` or `""`)
     * @param storageDbListener
     * @return The url to the file in the storage if successful, or null if the upload failed
     */
    public String uploadFile(@NonNull String localFilePath,
                             @Nullable String cloudFilePath,
                             @NonNull StorageDbUploadable storageDbListener) {
        // Gets `Uri` to the file
        Uri file = Uri.fromFile(new File(localFilePath));

        // Create a reference to the file in the database
        StorageReference fileRef;
        if (cloudFilePath == null || cloudFilePath.equals(""))
            fileRef = storageReference.child(file.getLastPathSegment());
        else
            fileRef = storageReference.child(cloudFilePath); // we assume the file type is valid

        UploadTask uploadTask = fileRef.putFile(file);
//        uploadTask.addOnSuccessListener(storageDbListener::onSuccess).addOnFailureListener(storageDbListener::onFailure);

        if (uploadTask.isSuccessful())
            return fileRef.getDownloadUrl().toString();
        return null;
    }
}
