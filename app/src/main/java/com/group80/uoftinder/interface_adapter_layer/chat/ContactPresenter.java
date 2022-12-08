package com.group80.uoftinder.interface_adapter_layer.chat;

import com.firebase.ui.firestore.FirestoreRecyclerOptions;
import com.google.firebase.auth.FirebaseAuth;
import com.group80.uoftinder.use_case_layer.firebase.firestore.FirestoreDbReader;

/**
 * A presenter for presenting the contacts list
 */
public class ContactPresenter {
    private final ContactsViewInterface view;

    /**
     * Constructor, creates the presenter
     *
     * @param view the view this presenter is responsible for
     */
    public ContactPresenter(ContactsViewInterface view) {
        this.view = view;
    }

    /**
     * Sets the options to configure a {@link com.firebase.ui.firestore.FirestoreRecyclerAdapter}.
     *
     * @return options to configure a FirestoreRecyclerAdapter.
     */
    public FirestoreRecyclerOptions<ContactModel> setRecyclerAdapterOption() {
        return new FirestoreRecyclerOptions
                .Builder<ContactModel>()
                .setQuery(FirestoreDbReader.getContactsAsQuery(FirebaseAuth.getInstance().getUid()), ContactModel.class)
                .build();
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
