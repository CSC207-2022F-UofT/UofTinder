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

/**
 * An RecyclerView.Adapter that provide a binding from Message to views that is displayed in the
 * recycler view for the messages
 */
public class MessageAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {
    /**
     * the flag for item sent
     */
    private final int ITEM_SENT = 0;
    /**
     * the flag for item received
     */
    private final int ITEM_RECEIVED = 1;

    /**
     * Global information about the application environment.
     */
    private final Context context;
    /**
     * List of messages to be displayed
     */
    private final List<Message> messageList;

    /**
     * Constructor, creates the message adapter
     *
     * @param context     the application context
     * @param messageList the list of messages
     */
    public MessageAdapter(Context context, List<Message> messageList) {
        this.context = context;
        this.messageList = messageList;
    }

    /**
     * Called when a new RecyclerView.ViewHolder to represent an item is needed.
     *
     * @param parent   the ViewGroup into which the new View will be added after it is bound to an
     *                 adapter position.
     * @param viewType the view type of the new View.
     * @return A new ViewHolder that holds a View of the given view type.
     */
    @NonNull
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        if (viewType == ITEM_SENT) { // sent by this
            View view = LayoutInflater.from(context).inflate(R.layout.uoftinder_sent_message_layout, parent, false);
            return new SentMessageViewHolder(view);
        } else { // sent by other
            View view = LayoutInflater.from(context).inflate(R.layout.uoftinder_received_nessage_layout, parent, false);
            return new ReceivedMessageViewHolder(view);
        }
    }


    /**
     * Called by RecyclerView to display the data at the specified position, and update the contents
     * of the RecyclerView.ViewHolder.itemView to reflect the item at the given position.
     *
     * @param holder   the ViewHolder which should be updated to represent the contents of the item
     *                 at the given position in the data set.
     * @param position the position of the item within the adapter's data set.
     */
    @Override
    public void onBindViewHolder(@NonNull RecyclerView.ViewHolder holder, int position) {
        Message message = this.messageList.get(position);

        if (holder.getClass() == SentMessageViewHolder.class) { // sent by this
            SentMessageViewHolder viewHolder = (SentMessageViewHolder) holder;
            viewHolder.message_text.setText(message.getMessage());
            viewHolder.message_time.setText(message.getCurrentTime());
        } else { // sent by other
            ReceivedMessageViewHolder viewHolder = (ReceivedMessageViewHolder) holder;
            viewHolder.message_text.setText(message.getMessage());
            viewHolder.message_time.setText(message.getCurrentTime());
        }
    }

    /**
     * Return the view type of the item at position for the purposes of view recycling.
     *
     * @param position position to query
     * @return integer value identifying the type of the view needed to represent the item at
     * position. Type codes need not be contiguous.
     */
    @Override
    public int getItemViewType(int position) {
        Message message = this.messageList.get(position);
        if (FirebaseAuth.getInstance().getCurrentUser().getUid().equals(message.getSenderId()))
            return ITEM_SENT;
        return ITEM_RECEIVED;
    }

    /**
     * Returns the total number of items in the data set held by the adapter.
     *
     * @return the total number of items in this adapter.
     */
    @Override
    public int getItemCount() {
        return messageList.size();
    }

    /**
     * A RecyclerView.ViewHolder for holding messages received
     */
    static class ReceivedMessageViewHolder extends RecyclerView.ViewHolder {
        protected TextView message_text;
        protected TextView message_time;

        public ReceivedMessageViewHolder(@NonNull View itemView) {
            super(itemView);
            this.message_text = itemView.findViewById(R.id.uoftinder_other_message_text);
            this.message_time = itemView.findViewById(R.id.uoftinder_other_message_time);
        }
    }

    /**
     * A RecyclerView.ViewHolder for holding messages sent
     */
    class SentMessageViewHolder extends RecyclerView.ViewHolder {
        protected TextView message_text;
        protected TextView message_time;

        public SentMessageViewHolder(@NonNull View itemView) {
            super(itemView);
            this.message_text = itemView.findViewById(R.id.uoftinder_self_message_text);
            this.message_time = itemView.findViewById(R.id.uoftinder_self_message_time);
        }
    }
}
