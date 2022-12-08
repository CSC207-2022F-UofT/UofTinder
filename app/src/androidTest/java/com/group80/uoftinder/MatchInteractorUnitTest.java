package com.group80.uoftinder;

import static org.junit.Assert.assertEquals;

import com.google.android.gms.tasks.Task;
import com.google.android.gms.tasks.Tasks;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.group80.uoftinder.chat.chat.Message;
import com.group80.uoftinder.entities_layer.User;
import com.group80.uoftinder.firebase.realtime.UserRealtimeDbFacade;
import com.group80.uoftinder.use_case_layer.feed.MatchInteractor;

import org.junit.Test;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.concurrent.TimeUnit;

/**
 * A MatchInteractorUnitTest class that tests the functionality of the MatchInteractor class
 */
public class MatchInteractorUnitTest {
    /**
     * Test to see if the match lists for two users are both updated in the local User classes
     */
    @Test
    public void checkMatchListsUpdatedLocal() {
        User user1 = new User("user1");
        user1.setName("Alice");
        User user2 = new User("user2");
        user2.setName("Benjamin");
        user1.setUserType("Romantic");
        user2.setUserType("Romantic");
        user1.getLiked().add(user2.getUid());
        user2.getLiked().add(user1.getUid());
        MatchInteractor.checkForMatchAndCreate(user1, user2);
        assert user1.getMatches().contains(user2.getUid());
        assert user2.getMatches().contains(user1.getUid());
    }

    /**
     * Test to see if the match lists for two users are both updated in the database upon match
     */
    @Test
    public void checkMatchListsUpdatedRemote() {
        User user1 = new User("user1");
        user1.setName("Alice");
        User user2 = new User("user2");
        user2.setName("Benjamin");
        user1.setUserType("Romantic");
        user2.setUserType("Romantic");
        user1.getLiked().add(user2.getUid());
        user2.getLiked().add(user1.getUid());
        MatchInteractor.checkForMatchAndCreate(user1, user2);
        UserRealtimeDbFacade.getUser(
                "Romantic", "user1",
                u1 -> {
                    UserRealtimeDbFacade.getUser(
                            "Romantic", "user2",
                            u2 -> {
                                assert u1.getMatches().contains(u2.getUid());
                                assert u2.getMatches().contains(u1.getUid());
                            }
                    );
                }
        );
    }

    /**
     * Test to see if the local currentUser viewed list is updated when the currentUser
     * does not 'like' the displayedUser
     */
    @Test
    public void currUserSkipsDisplayedUserLocal() {
        // set the currentUser and displayedUser
        User currentUser = new User("currUser");
        currentUser.setName("Alice");
        User displayedUser = new User("displayedUser");
        displayedUser.setName("Benjamin");
        currentUser.setUserType("Academic");
        displayedUser.setUserType("Academic");

        // add displayedUser to viewedList but not likedList
        MatchInteractor.addToList(currentUser, displayedUser, false);

        // expected results: displayedUser in currentUser's visited list but not liked list.
        List<String> expectedLikedList = new ArrayList<>();
        List<String> expectedVisitedList = new ArrayList<>(Collections.singletonList(
                displayedUser.getUid()));

        // actual results:
        List<String> actualLikedList = currentUser.getLiked();
        List<String> actualVisitedList = currentUser.getViewed();

        // assert statements
        assertEquals(expectedLikedList, actualLikedList);
        assertEquals(expectedVisitedList, actualVisitedList);
    }


    /**
     * Test to see if the local currentUser viewed and liked lists are updated when the currentUser
     * 'likes' the displayedUser
     */
    @Test
    public void currUserLikesDisplayedUserLocal() {
        // set the currentUser and displayedUser
        User currentUser = new User("currUser");
        currentUser.setName("Alice");
        User displayedUser = new User("displayedUser");
        displayedUser.setName("Benjamin");
        currentUser.setUserType("Academic");
        displayedUser.setUserType("Academic");

        // add displayedUser to viewedList and likedList
        MatchInteractor.addToList(currentUser, displayedUser, true);

        // expected results:  displayedUser in currentUser's liked and visited list
        List<String> expectedLikedList = new ArrayList<>(Collections.singletonList(
                displayedUser.getUid()));
        List<String> expectedVisitedList = new ArrayList<>(Collections.singletonList(
                displayedUser.getUid()));

        // actual results:
        List<String> actualLikedList = currentUser.getLiked();
        List<String> actualVisitedList = currentUser.getViewed();

        // assert statements
        assertEquals(expectedLikedList, actualLikedList);
        assertEquals(expectedVisitedList, actualVisitedList);
    }

    /**
     * Test to see if the viewed list of the current user is updated in the database
     * when the currentUser does not 'like' the displayedUser
     */
    @Test
    public void currUserSkipsDisplayedUserRemote() {
        User currentUser = new User("user1");
        currentUser.setName("Alice");
        User displayedUser = new User("user2");
        displayedUser.setName("Benjamin");
        currentUser.setUserType("Romantic");
        displayedUser.setUserType("Romantic");
        MatchInteractor.addToList(currentUser, displayedUser, false);
        UserRealtimeDbFacade.getUser(
                "Romantic", "user1",
                u1 -> {
                    UserRealtimeDbFacade.getUser(
                            "Romantic", "user2",
                            u2 -> {
                                assert u1.getViewed().contains(u2.getUid());
                            }
                    );
                }
        );
    }

    /**
     * Test to see if the liked and viewed lists of the current user are updated in the database
     * when the currentUser 'likes' the displayedUser
     */
    @Test
    public void currUserLikesDisplayedUserRemote() {
        User currentUser = new User("user1");
        currentUser.setName("Alice");
        User displayedUser = new User("user2");
        displayedUser.setName("Benjamin");
        currentUser.setUserType("Romantic");
        displayedUser.setUserType("Romantic");
        MatchInteractor.addToList(currentUser, displayedUser, true);
        UserRealtimeDbFacade.getUser(
                "Romantic", "user1",
                u1 -> {
                    UserRealtimeDbFacade.getUser(
                            "Romantic", "user2",
                            u2 -> {
                                assert u1.getViewed().contains(u2.getUid());
                                assert u1.getLiked().contains(u2.getUid());
                            }
                    );
                }
        );
    }

    /**
     * Tests to see if the database is updated with the introductory chat message sent from the
     * currentUser to the displayedUser upon a match
     */
    @Test
    public void testDatabaseUpdatedOnMatchMessage() {
        String selfUid = "user1";
        String contactUid = "user2";
        User user1 = new User(selfUid);
        user1.setName("Alice");
        User user2 = new User(contactUid);
        user2.setName("Benjamin");
        user1.setUserType("Romantic");
        user2.setUserType("Romantic");
        String chatRoom = selfUid + contactUid;

        List<Message> originalList = new ArrayList<>();
        DatabaseReference reference = FirebaseDatabase.getInstance().getReference().child("chats").child(chatRoom);
        Task<DataSnapshot> task = reference.get();

        // get list of chat messages before making a match (which sends a new message)
        Thread thread = new Thread(() -> {
            try {
                DataSnapshot dataSnapshot = Tasks.await(task, 2000, TimeUnit.MILLISECONDS);
                for (DataSnapshot child : dataSnapshot.getChildren())
                    originalList.add(child.getValue(Message.class));
            } catch (Exception e) {
                e.printStackTrace();
            }
        });

        thread.start();
        try {
            thread.join();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        // check for a match and create one, which sends a message
        user1.getLiked().add(user2.getUid());
        user2.getLiked().add(user1.getUid());
        MatchInteractor.checkForMatchAndCreate(user1, user2);

        List<Message> newList = new ArrayList<>();
        reference = FirebaseDatabase.getInstance().getReference().child("chats").child(chatRoom);
        Task<DataSnapshot> task2 = reference.get();

        // get list of chat messages after making a match
        thread = new Thread(() -> {
            try {
                DataSnapshot dataSnapshot = Tasks.await(task2, 2000, TimeUnit.MILLISECONDS);
                for (DataSnapshot child : dataSnapshot.getChildren())
                    newList.add(child.getValue(Message.class));
                // remove messages that existed in original message list
                newList.removeAll(originalList);
                // check that the new message equals the message sent upon match
                assert newList.get(0).getMessage().equals("Hey! We matched with each other!");
            } catch (Exception e) {
                e.printStackTrace();
            }
        });

        thread.start();
        try {
            thread.join();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}
