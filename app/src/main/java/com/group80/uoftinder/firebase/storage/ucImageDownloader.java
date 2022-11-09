package com.group80.uoftinder.firebase.storage;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;

import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.StorageReference;

/**
 * A downloader to download bitmap images from the firebase cloud storage
 */
public class ucImageDownloader extends ucDownloader<Bitmap, byte[]> {
    /**
     * Downloads an byte array from the firebase storage, then convert it to an bitmap image
     *
     * @param downloadUri the url to download the image
     * @return the bitmap image
     */
    @Override
    public Bitmap download(String downloadUri) {
        StorageReference imageReference = FirebaseStorage.getInstance().getReferenceFromUrl(downloadUri);
        byte[] imageData = imageReference.getBytes(5120 * 5120).addOnSuccessListener(this::notifySuccess).addOnFailureListener(this::notifyFailure).getResult();
        return BitmapFactory.decodeByteArray(imageData, 0, imageData.length);
    }
}
