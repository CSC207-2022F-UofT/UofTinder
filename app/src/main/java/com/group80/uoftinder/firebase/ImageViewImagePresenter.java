package com.group80.uoftinder.firebase;

import android.graphics.BitmapFactory;
import android.graphics.drawable.Drawable;
import android.widget.ImageView;

import androidx.annotation.NonNull;

import com.group80.uoftinder.firebase.storage.ImageStorageDbController;
import com.group80.uoftinder.firebase.storage.StorageDbDownloadable;

public class ImageViewImagePresenter {
    public static void downloadBitmapToImageView(String[] path, ImageView imageView, Drawable defaultDrawable) {
        ImageStorageDbController.downloadImage(path, new StorageDbDownloadable<byte[]>() {
            @Override
            public void onStorageDownloadSuccess(byte[] data) {
                imageView.setImageBitmap(BitmapFactory.decodeByteArray(data, 0, data.length));
            }

            @Override
            public void onStorageDownloadFailure(@NonNull Exception exception) {
                // Set to a default profile image
                imageView.setImageDrawable(defaultDrawable);
            }
        });
    }
}
