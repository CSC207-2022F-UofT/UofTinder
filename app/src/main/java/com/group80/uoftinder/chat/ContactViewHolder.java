package com.group80.uoftinder.chat;

import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.group80.uoftinder.R;

public class ContactViewHolder extends RecyclerView.ViewHolder {
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

    public ImageView getContactPic() {
        return this.contactPic;
    }
}
