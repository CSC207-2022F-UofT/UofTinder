package com.group80.uoftinder.firebase;


import android.graphics.Bitmap;
import android.graphics.drawable.Drawable;

public interface ProfileImageViewInterface {
    /**
     * Sets the profile image using a {@link Bitmap}
     *
     * @param bm the {@link Bitmap} to be set as profile image
     */
    void setProfileImage(Bitmap bm);

    /**
     * Sets the profile image using a {@link Drawable}
     *
     * @param drawable the {@link Drawable} to be set as profile image
     */
    void setProfileImage(Drawable drawable);
}
