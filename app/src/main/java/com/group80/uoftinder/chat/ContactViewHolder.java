package com.group80.uoftinder.chat;

import android.graphics.Bitmap;
import android.graphics.drawable.Drawable;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.group80.uoftinder.R;
import com.group80.uoftinder.firebase.ProfileImageViewInterface;

public class ContactViewHolder extends RecyclerView.ViewHolder implements ProfileImageViewInterface {
    private final TextView contactName;
    private final ImageView contactPic;

    public ContactViewHolder(@NonNull View itemView) {
        super(itemView);
        contactName = itemView.findViewById(R.id.contactListContactName);
        contactPic = itemView.findViewById(R.id.contactListContactPic);
    }

    public void setContactName(String contactName) {
        this.contactName.setText(contactName);
    }

    @Override
    public void setProfileImage(Bitmap bm) {
        this.contactPic.setImageBitmap(bm);
    }

    @Override
    public void setProfileImage(Drawable drawable) {
        this.contactPic.setImageDrawable(drawable);
    }
}
