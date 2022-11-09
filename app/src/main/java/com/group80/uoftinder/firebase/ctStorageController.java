package com.group80.uoftinder.firebase;

import android.graphics.Bitmap;

import com.google.firebase.auth.FirebaseAuth;

public class ctStorageController {
    public String uploadProfileImage(StorageDbUploadable listener, Bitmap bitmap) {
        ucProfileImageUploader profileImageUploader = new ucProfileImageUploader();
        profileImageUploader.addListener(listener);
        String uid = FirebaseAuth.getInstance().getCurrentUser().getUid();
        return profileImageUploader.upload(bitmap, uid + "/img/" + "_profile_img.jpg");
    }

    public String uploadImage(StorageDbUploadable listener, Bitmap bitmap, String fileName) {
        ucImageUploader imageUploader = new ucImageUploader();
        imageUploader.addListener(listener);
        String uid = FirebaseAuth.getInstance().getCurrentUser().getUid();
        return imageUploader.upload(bitmap, uid + "/img/" + fileName);
    }
}
