package com.group80.uoftinder.chat;

import android.graphics.drawable.Drawable;

import androidx.annotation.NonNull;

import com.group80.uoftinder.firebase.ImageViewImagePresenter;

public class ContactPresenter {
    private final ContactsView view;

    public ContactPresenter(ContactsView view) {
        this.view = view;
    }

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

    public void enterChatActivity(ContactModel contactModel) {
        view.enterChatView(contactModel);
    }

    public void enterRecommendationActivity() {
        view.enterRecommendationView();
    }
}
