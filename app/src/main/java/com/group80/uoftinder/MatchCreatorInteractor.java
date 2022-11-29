package com.group80.uoftinder;

import com.group80.uoftinder.entities.User;
import com.group80.uoftinder.firebase.realtime.UserRealtimeDbFacade;

import java.util.List;

/**
 * The MatchCreatorInteractor class is used to create a match between the current user and the user
 * they have liked, if that user has also liked them
 */
public class MatchCreatorInteractor {
//    private final User currentUser;
//    private final User user2;

    /**
     * This method is called when the currentUser has clicked like while displaying the
     * profile of user2, indicating that we need to check for a match.
     *
     * If user2 has already liked currentUser, then we add the two users to each other's
     * match list, reupload the users to the database, and send an introductory message on
     * behalf of currentUser
     *
     * Preconditions
     *   - currentUser.getLiked().contains(user2.getUid());
     * @param currentUser is the user currently using the app who clicked like
     * @param user2 is the user whose liked list we need to check and possibly create a match with
     * @return a boolean indicating whether a match has been created
     */
    // called when currentUser likes user2
    public static boolean checkForMatchAndCreate(User currentUser, User user2) {
        if (user2.getLiked().contains(currentUser.getUid())) { // if user2 has liked currentUser
            List<String> user1MatchList = currentUser.getMatches();
            List<String> user2MatchList = user2.getMatches();
            user1MatchList.add(user2.getUid()); // add both users to each other's match lists
            user2MatchList.add(currentUser.getUid());
            UserRealtimeDbFacade.uploadUser(user2); // reupload users to the database
            UserRealtimeDbFacade.uploadUser(currentUser);

            sendIntroMessage(); // send a message from currentUser to user2
            return true;
        }
        return false;
    }
}
