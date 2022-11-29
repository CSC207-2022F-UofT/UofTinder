package com.group80.uoftinder;

import com.group80.uoftinder.entities.User;
import com.group80.uoftinder.feed.RecViewInterface;
import com.group80.uoftinder.firebase.realtime.UserRealtimeDbFacade;

import java.util.List;

/**
 *
 */
public class MatchCreatorInteractor {
//    private final User currentUser;
//    private final User user2;

    /**
     *
     * @param currentUser
     * @param user2
     * @return
     */
    // called when currentUser likes user2
    public static boolean checkForMatchAndCreate(User currentUser, User user2) {
        if (user2.getLiked().contains(currentUser.getUid())) {
            List<String> user1MatchList = currentUser.getMatches();
            List<String> user2MatchList = user2.getMatches();
            user1MatchList.add(user2.getUid());
            user2MatchList.add(currentUser.getUid());
            UserRealtimeDbFacade.uploadUser(user2);
            UserRealtimeDbFacade.uploadUser(currentUser);

            sendIntroMessage(); // send a message from currentUser to user2
            return true;
        }
        return false;
    }
}
