package com.group80.uoftinder.chat;

/**
 * A presenter for presenting the contacts list
 */
public class ContactPresenter {
    private final ContactsView view;

    /**
     * Constructor, coreates the presenter
     *
     * @param view the view this presenter is responsible for
     */
    public ContactPresenter(ContactsView view) {
        this.view = view;
    }

    /**
     * Enters the `ChatActivity` with the given contact
     *
     * @param contactModel a model storing the information of a contact
     */
    public void enterChatActivity(ContactModel contactModel) {
        view.enterChatView(contactModel);
    }

    /**
     * Enters the recommendation view
     */
    public void enterRecommendationActivity() {
        view.enterRecommendationView();
    }
}
