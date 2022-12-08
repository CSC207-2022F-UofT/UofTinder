package com.group80.uoftinder.interface_adapter_layer.chat;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * A model for Firebase Firestore, storing basic contact info
 */
public class ContactModel {
    /**
     * The name of the contact
     */
    private String name;

    /**
     * The UID of the contact
     */
    private String uid;

    /**
     * Matches the contact has made, as a list of UID's
     */
    private List<String> matches;

    /**
     * The default constructor initializing the fields in the model
     */
    public ContactModel() {
        this.uid = String.valueOf(new Date().getTime());
        this.name = "User_" + this.uid;
        this.matches = new ArrayList<>();
    }

    /**
     * Constructor, creates a new Contact Model
     *
     * @param name    the name of the contact
     * @param uid     the UID of the contact
     * @param matches UID's of the matches this contact has made
     */
    public ContactModel(String name, String uid, List<String> matches) {
        this.name = name;
        this.uid = uid;
        this.matches = matches;
    }

    /**
     * Returns the name of the contact
     *
     * @return the name of the contact
     */
    public String getName() {
        return name;
    }

    /**
     * Sets the name of the contact
     *
     * @param name the name of the contact
     */
    public void setName(String name) {
        this.name = name;
    }

    /**
     * Returns the UID of the contact
     *
     * @return the UID of the contact
     */
    public String getUid() {
        return uid;
    }

    /**
     * Sets the UID of the contact
     *
     * @param uid the UID of the contact
     */
    public void setUid(String uid) {
        this.uid = uid;
    }

    /**
     * Returns the match list of the contact
     *
     * @return UID's of the matches this contact has made
     */
    public List<String> getMatches() {
        return matches;
    }

    /**
     * Sets the matches of the contact
     *
     * @param matches UID's of the matches this contact has made
     */
    public void setMatches(List<String> matches) {
        this.matches = matches;
    }
}
