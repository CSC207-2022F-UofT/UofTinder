package com.group80.uoftinder.use_case_layer.firebase.storage;

import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.StorageReference;
import com.group80.uoftinder.use_case_layer.firebase.realtime.Downloader;

/**
 * A downloader to download bitmap images from the firebase cloud storage
 */
public class ImageDownloader extends Downloader<byte[]> {
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

    /**
     * Downloads an byte array from the firebase storage, then convert it to an bitmap image
     *
     * @param path the path of the image in the storage database
     */
    public void download(String[] path) {
        StorageReference imageReference = storageReference;
        for (String subDir : path)
            imageReference = imageReference.child(subDir);
        imageReference.getBytes(5120 * 5120).addOnSuccessListener(this::notifySuccess).addOnFailureListener(this::notifyFailure);
    }
}
