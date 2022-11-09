package com.group80.uoftinder.firebase;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;

import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.StorageReference;

public class ucImageDownloader extends ucDownloader<Bitmap, byte[]> {
    @Override
    public Bitmap download(String downloadUri) {
        StorageReference imageReference = FirebaseStorage.getInstance().getReferenceFromUrl(downloadUri);
        byte[] imageData = imageReference.getBytes(5120 * 5120).addOnSuccessListener(this::notifySuccess).addOnFailureListener(this::notifyFailure).getResult();
        return BitmapFactory.decodeByteArray(imageData, 0, imageData.length);
    }
}
