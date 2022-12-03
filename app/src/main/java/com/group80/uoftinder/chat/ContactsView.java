package com.group80.uoftinder.chat;

/**
 * A view for the list of contacts
 */
public interface ContactsView {
    /**
     * Enters the `ChatActivity` with the given contact
     *
     * @param contactModel a model storing the information of a contact
     */
    void enterChatView(ContactModel contactModel);

    /**
     * Enters the recommendation view
     */
    void enterRecommendationView();
}
