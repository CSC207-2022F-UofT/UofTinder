package com.group80.uoftinder.interface_adapter_layer.firebase;

import android.graphics.Bitmap;

import com.google.firebase.auth.FirebaseAuth;
import com.group80.uoftinder.use_case_layer.firebase.storage.StorageDbDownloadable;
import com.group80.uoftinder.use_case_layer.firebase.storage.StorageDbUploadable;
import com.group80.uoftinder.use_case_layer.firebase.storage.CompressedImageUploader;
import com.group80.uoftinder.use_case_layer.firebase.storage.ImageDownloader;
import com.group80.uoftinder.use_case_layer.firebase.storage.ImageUploader;

/**
 * A facade wrapping up uploads / downloads regrading the storage database for images
 */
public class ImageStorageDbController {
    public static final int MAX_IMAGE_SIZE = 5120;

    /**
     * Uploads the profile image to the cloud storage
     *
     * @param listener the listener to the upload event
     * @param bitmap   the bitmap image to be uploaded
     * @return the download url to the file
     */
    public static String uploadProfileImage(Bitmap bitmap, StorageDbUploadable listener) {
        CompressedImageUploader profileImageUploader = new CompressedImageUploader(25);
        profileImageUploader.addListener(listener);
        String uid = FirebaseAuth.getInstance().getCurrentUser().getUid();
        return profileImageUploader.upload(bitmap, uid + "/img/" + "_profile_img.jpg");
    }

    /**
     * Uploads the profile image to the cloud storage
     *
     * @param bitmap the bitmap image to be uploaded
     * @return the download url to the file
     */
    public static String uploadProfileImage(Bitmap bitmap) {
        CompressedImageUploader profileImageUploader = new CompressedImageUploader(25);
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
    public static String uploadImage(Bitmap bitmap, String fileName, StorageDbUploadable listener) {
        ImageUploader imageUploader = new ImageUploader();
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
    public static void downloadImage(String downloadUrl, StorageDbDownloadable<byte[]> listener) {
        ImageDownloader imageDownloader = new ImageDownloader();
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
        ImageDownloader imageDownloader = new ImageDownloader();
        imageDownloader.addListener(listener);
        imageDownloader.download(path);
    }
}
