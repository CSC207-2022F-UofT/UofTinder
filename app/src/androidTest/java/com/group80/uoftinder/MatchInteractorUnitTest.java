package com.group80.uoftinder;

import static org.junit.Assert.assertArrayEquals;
import static org.junit.Assert.assertEquals;

import com.group80.uoftinder.entities.User;
import com.group80.uoftinder.firebase.realtime.UserRealtimeDbFacade;

import org.junit.Test;

public class MatchInteractorUnitTest {
    @Test
    public void checkMatchListsUpdatedLocal() {
        User user1 = new User("Raghav");
        User user2 = new User("Future partner");
        user1.getLiked().add(user2.getUid());
        user2.getLiked().add(user1.getUid());
        MatchInteractor.checkForMatchAndCreate(user1, user2);
        assert user1.getMatches().contains(user2.getUid());
        assert user2.getMatches().contains(user1.getUid());
    }

    @Test
    public void checkMatchListsUpdatedRemote() {
        UserRealtimeDbFacade.getUser(
                "Romantic", "user2",
                user1 -> {
                    UserRealtimeDbFacade.getUser(
                            "Romantic", "user3",
                            user2 -> {
                                user1.getLiked().add(user2.getUid());
                                user2.getLiked().add(user1.getUid());
                                MatchInteractor.checkForMatchAndCreate(user1, user2);
                                assert user1.getMatches().contains(user2.getUid());
                                assert user2.getMatches().contains(user1.getUid());
                            }
                    );
                }
        );
    }
}
