package com.group80.uoftinder;

import androidx.annotation.NonNull;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.group80.uoftinder.chat.MessageFactory;
import com.group80.uoftinder.entities.User;
import com.group80.uoftinder.firebase.realtime.UserRealtimeDbFacade;

import com.group80.uoftinder.firebase.realtime.*;

import java.util.List;

/**
 * The MatchInteractor class is used to create a match between the current user and the user
 * they have liked, if that user has also liked them
 */
public class MatchInteractor {
    /**
     * This method is called when the currentUser has clicked like while displaying the
     * profile of user2, indicating that we need to check for a match.
     *
     * If user2 has already liked currentUser, then we add the two users to each other's
     * match list, re-upload the users to the database, and send an introductory message on
     * behalf of currentUser
     *
     * Preconditions
     *   - currentUser.getLiked().contains(user2.getUid());
     * @param currentUser is the user currently using the app who clicked like
     * @param user2 is the user whose liked list we need to check and possibly create a match with
     * @return a boolean indicating whether a match has been created
     */
    public static boolean checkForMatchAndCreate(User currentUser, User user2) {
        if (user2.getLiked().contains(currentUser.getUid())) { // if user2 has liked currentUser
            List<String> user1MatchList = currentUser.getMatches();
            List<String> user2MatchList = user2.getMatches();
            user1MatchList.add(user2.getUid()); // add both users to each other's match lists
            user2MatchList.add(currentUser.getUid());

            UserRealtimeDbFacade.uploadUser(user2); // re-upload users to the database
            UserRealtimeDbFacade.uploadUser(currentUser);

            sendIntroMessage(currentUser.getUid(),
                    user2.getUid(), "Hey! We matched with each other!"); // send a message from currentUser to user2
            return true;
        }
        return false;
    }

    private static void sendIntroMessage(String selfUid, String contactUid, String message) {
        String chatRoom = selfUid.compareTo(contactUid) < 0 ? selfUid + contactUid : contactUid + selfUid;

        ucChatMessageWriter chatMessageWriter = new ucChatMessageWriter(new RealtimeDbValueObserver() {
            @Override
            public void onRealtimeDbDataChange(@NonNull DataSnapshot snapshot) {
            }

            @Override
            public void onRealtimeDbCancelled(@NonNull DatabaseError error) {
            }
        }, chatRoom);

        chatMessageWriter.write(MessageFactory.createMessage(message, selfUid));
    }

    /**
     * Adds displayedUser to currentUser's viewed list.
     * If liked is true, also adds displayedUser to currentUser's liked list.
     * @param displayedUser is the user that is currently being displayed to currentUser.
     * @param currentUser is the user that is currently logged in.
     * @param liked is True when currentUser 'likes' displayedUser, False otherwise.
     * @param viewedList is a list of Users that currentUser has currently viewed
     * @param likedList is a list of Users that currentUser has currently liked
     */
    public static void addToList(User displayedUser, User currentUser, boolean liked,
                                 List<String> viewedList, List<String> likedList) {
        viewedList.add(displayedUser.getUid());
        currentUser.setViewed(viewedList);
        // push currentUser.getViewed() to firebase viewed list
        if (liked) {
            likedList.add(displayedUser.getUid());
            currentUser.setLiked(likedList);
            // push currentUser.getLiked() to firebase liked list
        }
        // TODO: write a function that writes to currentUser's liked and visited list in firebase
        // if liked is true, push to liked list and viewed list
        // if liked is false, push to viewed list
        // sending display user id to be added to firebase
        // updateListStatus(displayedUser.getUid(), liked);
    }
}
