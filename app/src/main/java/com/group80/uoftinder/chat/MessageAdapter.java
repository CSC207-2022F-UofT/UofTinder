package com.group80.uoftinder.chat;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.google.firebase.auth.FirebaseAuth;
import com.group80.uoftinder.R;

import java.util.List;

public class MessageAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {
    private final int ITEM_SENT = 0;
    private final int ITEM_RECEIVED = 1;

    private final Context context;
    private final List<Message> messageList;

    public MessageAdapter(Context context, List<Message> messageList) {
        this.context = context;
        this.messageList = messageList;
    }

    @NonNull
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        if (viewType == ITEM_SENT) {
            View view = LayoutInflater.from(context).inflate(R.layout.uoftinder_this_layout, parent, false);
            return new ThisMessageViewHolder(view);
        } else {
            View view = LayoutInflater.from(context).inflate(R.layout.uoftinder_other_layout, parent, false);
            return new OtherMessageViewHolder(view);
        }
    }

    @Override
    public void onBindViewHolder(@NonNull RecyclerView.ViewHolder holder, int position) {
        Message message = this.messageList.get(position);

        if (holder.getClass() == ThisMessageViewHolder.class) { // send by user
            ThisMessageViewHolder viewHolder = (ThisMessageViewHolder) holder;
            viewHolder.message_text.setText(message.getMessage());
            viewHolder.message_time.setText(message.getCurrentTime());
        } else { // sent by contact
            OtherMessageViewHolder viewHolder = (OtherMessageViewHolder) holder;
            viewHolder.message_text.setText(message.getMessage());
            viewHolder.message_time.setText(message.getCurrentTime());
        }
    }

    @Override
    public int getItemViewType(int position) {
        Message message = this.messageList.get(position);
        if (FirebaseAuth.getInstance().getCurrentUser().getUid().equals(message.getSenderId()))
            return ITEM_SENT;
        return ITEM_RECEIVED;
    }

    @Override
    public int getItemCount() {
        return messageList.size();
    }

    class ThisMessageViewHolder extends RecyclerView.ViewHolder {
        protected TextView message_text;
        protected TextView message_time;

        public ThisMessageViewHolder(@NonNull View itemView) {
            super(itemView);
            this.message_text = itemView.findViewById(R.id.uoftinder_self_message_text);
            this.message_time = itemView.findViewById(R.id.uoftinder_self_message_time);
        }
    }

    class OtherMessageViewHolder extends RecyclerView.ViewHolder {
        protected TextView message_text;
        protected TextView message_time;

        public OtherMessageViewHolder(@NonNull View itemView) {
            super(itemView);
            this.message_text = itemView.findViewById(R.id.uoftinder_other_message_text);
            this.message_time = itemView.findViewById(R.id.uoftinder_other_message_time);
        }
    }
}
