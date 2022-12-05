package com.group80.uoftinder.profile_upload;

import static android.content.Intent.FLAG_ACTIVITY_NEW_TASK;

import android.content.Context;
import android.content.Intent;
import android.content.res.Resources;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.net.Uri;
import android.util.Log;
import android.widget.Toast;

import com.group80.uoftinder.Constants;
import com.group80.uoftinder.firebase.storage.ImageStorageDbController;

import java.io.Serializable;

public class ProfileUploadPresenter {
    private final ProfileUploadView view;

    /**
     * Constructor, initializes the presenter
     *
     * @param view the view this presenter is in charge of
     */
    public ProfileUploadPresenter(ProfileUploadView view) {
        this.view = view;
    }

    /**
     * Sets a {@link Bitmap} as the profile picture for display
     *
     * @param bm the bitmap to show
     */
    public void setProfileImage(Bitmap bm) {
        view.showProfileImage(bm);
    }

    /**
     * Sets a linked image as the profile picture for display from an {@link Uri}
     *
     * @param uri the link to the image to show
     */
    public void setProfileImage(Uri uri) {
        view.showProfileImage(uri);
    }

    /**
     * Uploads a {@link Bitmap} as the profile picture for current user
     *
     * @param bm the bitmap to be uploaded
     */
    public void uploadProfileImage(Bitmap bm) {
        view.showProfileImage(bm);
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
     * Proceeds to the next view, while passing on the current {@link com.group80.uoftinder.entities.User}
     * as a {@link Serializable}
     *
     * @param context  a {@link Context} of the application package implementing this class
     * @param cls      the component class that is to be used for the intent.
     * @param currUser the information of the currently logged in User
     */
    public void proceedToNextView(Context context, Class<?> cls, Serializable currUser) {
        Intent intent = new Intent(context, cls).setFlags(FLAG_ACTIVITY_NEW_TASK);
        intent.putExtra(Constants.CURRENT_USER_STRING, currUser);
        Log.d("DEBUGGING", "proceedToNextView: " + (currUser == null ? "No User passed in" : "User passed in"));
        context.startActivity(intent);
    }

    /**
     * Informs that no image is set as the profile image
     *
     * @param context the context to use
     * @param msg     the tex to show. Can be formatted text.
     */
    public void showEmptyImageMessage(Context context, CharSequence msg) {
        Toast.makeText(context, msg, Toast.LENGTH_SHORT).show();
    }
}
