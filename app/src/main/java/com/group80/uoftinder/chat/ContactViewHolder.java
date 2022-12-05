package com.group80.uoftinder.chat;

import android.content.Context;
import android.graphics.drawable.Drawable;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.core.content.ContextCompat;
import androidx.recyclerview.widget.RecyclerView;

import com.group80.uoftinder.R;
import com.group80.uoftinder.firebase.ImageViewImagePresenter;

public class ContactViewHolder extends RecyclerView.ViewHolder {
    private final TextView contactName;
    private final ImageView contactPic;

    public ContactViewHolder(@NonNull View itemView) {
        super(itemView);
        contactName = itemView.findViewById(R.id.contactListContactName);
        contactPic = itemView.findViewById(R.id.contactListContactPic);
    }

    public void displayContactInfo(Context context, ContactModel contactModel) {
        this.contactName.setText(contactModel.name);

        Drawable defaultProfilePic = ContextCompat.getDrawable(context, R.drawable.ic_launcher_foreground);
        ImageViewImagePresenter.downloadBitmapToImageView(
                new String[]{contactModel.getUid(), "img", "_profile_img.jpg"},
                contactPic,
                defaultProfilePic
        );
    }
}
