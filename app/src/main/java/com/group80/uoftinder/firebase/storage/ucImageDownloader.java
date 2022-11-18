package com.group80.uoftinder.firebase.storage;

import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.StorageReference;

/**
 * A downloader to download bitmap images from the firebase cloud storage
 */
public class ucImageDownloader extends ucDownloader<byte[]> {
    /**
     * Downloads an byte array from the firebase storage, then convert it to an bitmap image
     *
     * @param downloadUri the url to download the image
     */
    @Override
    public void download(String downloadUri) {
        StorageReference imageReference = FirebaseStorage.getInstance().getReferenceFromUrl(downloadUri);
        imageReference.getBytes(5120 * 5120).addOnSuccessListener(this::notifySuccess).addOnFailureListener(this::notifyFailure);
    }

    public void download(String[] path) {
        StorageReference imageReference = storageReference;
        for (String subDir : path)
            imageReference = imageReference.child(subDir);
        imageReference.getBytes(5120 * 5120).addOnSuccessListener(this::notifySuccess).addOnFailureListener(this::notifyFailure);
    }
}
