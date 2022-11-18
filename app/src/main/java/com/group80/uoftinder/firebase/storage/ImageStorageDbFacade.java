package com.group80.uoftinder.firebase.storage;

import android.graphics.Bitmap;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.storage.StorageReference;

/**
 * A facade wrapping up uploads / downloads regrading the storage database for images
 */
public class ImageStorageDbFacade {
    public static final int MAX_IMAGE_SIZE = 5120;

    /**
     * Uploads the profile image to the cloud storage
     *
     * @param listener the listener to the upload event
     * @param bitmap   the bitmap image to be uploaded
     * @return the download url to the file
     */
    public static String uploadProfileImage(StorageDbUploadable listener, Bitmap bitmap) {
        ucCompressedImageUploader profileImageUploader = new ucCompressedImageUploader(25);
        profileImageUploader.addListener(listener);
        String uid = FirebaseAuth.getInstance().getCurrentUser().getUid();
        return profileImageUploader.upload(bitmap, uid + "/img/" + "_profile_img.jpg");
    }

    /**
     * Uploads a bitmap image to the cloud storage
     *
     * @param listener the listener to the upload event
     * @param bitmap   the bitmap image to be uploaded
     * @param fileName the name of the image file (not the full path, only the file name)
     * @return the download url to the file
     */
    public static String uploadImage(StorageDbUploadable listener, Bitmap bitmap, String fileName) {
        ucImageUploader imageUploader = new ucImageUploader();
        imageUploader.addListener(listener);
        String uid = FirebaseAuth.getInstance().getCurrentUser().getUid();
        return imageUploader.upload(bitmap, uid + "/img/" + fileName);
    }

    /**
     * Downloads a bitmap image from the cloud storage using an url
     *
     * @param listener    the listener to the download event
     * @param downloadUrl the url to download the image from
     * @return the downloaded bitmap image
     */
    public static void downloadImage(StorageDbDownloadable<byte[]> listener, String downloadUrl) {
        ucImageDownloader imageDownloader = new ucImageDownloader();
        imageDownloader.addListener(listener);
        imageDownloader.download(downloadUrl);
    }

    /**
     * Downloads a bitmap image from the cloud storage using a path
     *
     * @param path     the path of the file on the cloud storage (including the file name itself)
     * @param listener the listener to the download event
     */
    public static void downloadImage(String[] path, StorageDbDownloadable<byte[]> listener) {
        ucImageDownloader imageDownloader = new ucImageDownloader();
        imageDownloader.addListener(listener);
        imageDownloader.download(path);
    }
}
