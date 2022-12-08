package com.group80.uoftinder.interface_adapter_layer.chat;

import com.group80.uoftinder.view_layer.chat.ChatActivity;
import com.group80.uoftinder.view_layer.feed.RecommendationView;

/**
 * A view for the list of contacts
 */
public interface ContactsViewInterface {
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
