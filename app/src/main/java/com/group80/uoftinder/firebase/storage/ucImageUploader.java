package com.group80.uoftinder.firebase.storage;

import android.graphics.Bitmap;

import androidx.annotation.NonNull;

import com.google.firebase.storage.StorageReference;
import com.google.firebase.storage.UploadTask;

import java.io.ByteArrayOutputStream;

/**
 * A uploader to upload bitmap images to the firebase cloud storage
 */
public class ucImageUploader extends ucUploader<Bitmap> {
    /**
     * Upload a byte array (Bitmap image) to the Firebase Storage Database
     *
     * @param bitmap        The byte array to be uploaded
     * @param cloudFilePath The full path to the file in the Firebase Storage Database, in the format of "path/fileName"
     * @return The download URL to the cloud image, or `null` if the upload failed
     */
    public String upload(@NonNull Bitmap bitmap,
                         @NonNull String cloudFilePath) {
        if (cloudFilePath.equals(""))
            return null;

        StorageReference imageRef = storageReference.child(cloudFilePath);
        byte[] image = this.bitmapToByteArray(bitmap);

        UploadTask uploadTask = imageRef.putBytes(image);
        uploadTask.addOnSuccessListener(this::notifySuccess).addOnFailureListener(this::notifyFailure);

        if (uploadTask.isSuccessful())
            return imageRef.getDownloadUrl().toString();
        return null;
    }

    /**
     * Converts a bitmap image to a byte array in PNG format
     *
     * @param bitmap The bitmap image to be converted
     * @return The converted byte array
     */
    protected byte[] bitmapToByteArray(Bitmap bitmap) {
        ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
        bitmap.compress(Bitmap.CompressFormat.PNG, 100, byteArrayOutputStream);
        return byteArrayOutputStream.toByteArray();
    }
}
