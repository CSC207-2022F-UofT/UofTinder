package com.group80.uoftinder.chat.contacts_list;

import com.group80.uoftinder.chat.chat.ChatActivity;

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
     * Enters the {@link com.group80.uoftinder.feed.RecommendationView}
     */
    void enterRecommendationView();
}
