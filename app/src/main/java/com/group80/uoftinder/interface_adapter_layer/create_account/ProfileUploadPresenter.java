package com.group80.uoftinder.interface_adapter_layer.create_account;

import android.content.res.Resources;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.net.Uri;

import com.group80.uoftinder.interface_adapter_layer.firebase.ImageStorageDbController;

import java.io.Serializable;

public class ProfileUploadPresenter {
    private final ProfileUploadView profileUploadView;

    /**
     * Constructor, initializes the presenter
     *
     * @param view the view this presenter is in charge of
     */
    public ProfileUploadPresenter(ProfileUploadView view) {
        this.profileUploadView = view;
    }

    /**
     * Sets a {@link Bitmap} as the profile picture for display
     *
     * @param bm the bitmap to show
     */
    public void setProfileImage(Bitmap bm) {
        profileUploadView.showProfileImage(bm);
    }

    /**
     * Sets a linked image as the profile picture for display from an {@link Uri}
     *
     * @param uri the link to the image to show
     */
    public void setProfileImage(Uri uri) {
        profileUploadView.showProfileImage(uri);
    }

    /**
     * Uploads a {@link Bitmap} as the profile picture for current user
     *
     * @param bm the bitmap to be uploaded
     */
    public void uploadProfileImage(Bitmap bm) {
        profileUploadView.showProfileImage(bm);
        ImageStorageDbController.uploadProfileImage(bm);
    }

    /**
     * Uploads a {@link android.graphics.drawable.Drawable} as the profile picture for current user
     *
     * @param res the resources object containing the image data
     * @param id  the resource id of the image data
     */
    public void uploadProfileImage(Resources res, int id) {
        Bitmap bm = BitmapFactory.decodeResource(res, id);
        uploadProfileImage(bm);
    }

    /**
     * Proceeds to the next view, while passing on the current {@link com.group80.uoftinder.entities_layer.User}
     * as a {@link Serializable}
     *
     * @param currUser the information of the currently logged in User
     */
    public void proceedToNextView(CharSequence msg, Serializable currUser) {
        profileUploadView.showImageMessage(msg);
        profileUploadView.showNextView(currUser);

    }

    /**
     * Informs that no image is set as the profile image
     *
     * @param msg the tex to show. Can be formatted text.
     */
    public void showEmptyImageMessage(CharSequence msg) {
        profileUploadView.showImageMessage(msg);
    }
}
