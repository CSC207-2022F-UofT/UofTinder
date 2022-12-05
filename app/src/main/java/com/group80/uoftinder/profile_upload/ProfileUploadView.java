package com.group80.uoftinder.profile_upload;

import android.graphics.Bitmap;
import android.net.Uri;

import java.io.Serializable;

public interface ProfileUploadView {
    /**
     * Sets the {@link Bitmap} as the profile picture for display
     *
     * @param bm the bitmap to show
     */
    void showProfileImage(Bitmap bm);

    /**
     * Sets a linked image as the profile picture for display from an {@link Uri}
     *
     * @param uri the link to the image to show
     */
    void showProfileImage(Uri uri);

    void showNextView(Serializable data);

    void showImageMessage(CharSequence msg);
}
