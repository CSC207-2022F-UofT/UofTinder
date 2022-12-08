package com.group80.uoftinder.interface_adapter_layer.firebase;

import android.graphics.BitmapFactory;
import android.graphics.drawable.Drawable;

import androidx.annotation.NonNull;

import com.group80.uoftinder.use_case_layer.firebase.storage.StorageDbDownloadable;

public class ProfileImagePresenter {
    private ProfileImageViewInterface profileImageViewInterface;

    public ProfileImagePresenter(ProfileImageViewInterface profileImageViewInterface) {
        this.profileImageViewInterface = profileImageViewInterface;
    }

    public void downloadBitmapToImageView(String[] path, Drawable defaultDrawable) {
        ImageStorageDbController.downloadImage(path, new StorageDbDownloadable<byte[]>() {
            @Override
            public void onStorageDownloadSuccess(byte[] data) {
                profileImageViewInterface.setProfileImage(BitmapFactory.decodeByteArray(data, 0, data.length));
            }

            @Override
            public void onStorageDownloadFailure(@NonNull Exception exception) {
                // Set to a default profile image
                profileImageViewInterface.setProfileImage(defaultDrawable);
            }
        });
    }
}
