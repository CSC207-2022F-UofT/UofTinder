package com.group80.uoftinder.chat.contacts_list;

import com.group80.uoftinder.chat.chat.ChatActivity;
import com.group80.uoftinder.view_layer.feed.RecommendationView;

/**
 * A view for the list of contacts
 */
public interface ContactsView {
    /**
     * Enters the {@link ChatActivity} with the given contact
     *
     * @param contactModel a model storing the information of a contact
     */
    void enterChatView(ContactModel contactModel);

    /**
     * Enters the {@link RecommendationView}
     */
    void enterRecommendationView();
}
