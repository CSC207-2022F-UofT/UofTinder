package com.group80.uoftinder.chat;

import android.graphics.drawable.Drawable;

import androidx.annotation.NonNull;

import com.group80.uoftinder.firebase.ImageViewImagePresenter;

/**
 * A presenter for presenting the contacts list
 */
public class ContactPresenter {
    private final ContactsView view;

    /**
     * Constructor, coreates the presenter
     *
     * @param view the view this presenter is responsible for
     */
    public ContactPresenter(ContactsView view) {
        this.view = view;
    }

    /**
     * Sets the contact information to the view
     *
     * @param holder            the view holder for the view
     * @param contactModel      a model storing the information of the contact
     * @param defaultProfilePic a default profile picture, used if the profile picture of the
     *                          contact failed to be downloaded form the database
     */
    public void setContactInfo(@NonNull ContactViewHolder holder,
                               @NonNull ContactModel contactModel,
                               @NonNull Drawable defaultProfilePic) {
        holder.setContactName(contactModel.getName());

        ImageViewImagePresenter.downloadBitmapToImageView(
                new String[]{contactModel.getUid(), "img", "_profile_img.jpg"},
                holder.getContactPic(),
                defaultProfilePic
        );
    }

    /**
     * Enters the `ChatActivity` with the given contact
     *
     * @param contactModel a model storing the information of a contact
     */
    public void enterChatActivity(ContactModel contactModel) {
        view.enterChatView(contactModel);
    }

    /**
     * Enters the recommendation view
     */
    public void enterRecommendationActivity() {
        view.enterRecommendationView();
    }
}
