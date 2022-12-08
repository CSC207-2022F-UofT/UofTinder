package com.group80.uoftinder;

import static org.junit.Assert.assertArrayEquals;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotSame;

import androidx.annotation.NonNull;
import androidx.test.ext.junit.runners.AndroidJUnit4;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.group80.uoftinder.entities.User;
import com.group80.uoftinder.firebase.realtime.RealtimeDbWriteListener;
import com.group80.uoftinder.firebase.realtime.UserRealtimeDbFacade;

import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;

import java.util.ArrayList;
import java.util.Arrays;

/**
 * Instrumented Unit tests for UserRealtimeDbFacadeUnitTest, run on Android devices
 */
@RunWith(AndroidJUnit4.class)
public class UserRealtimeDbFacadeUnitTest {
    /**
     * Test if the upload task can be done without error
     * <p>
     * FIXME: the test succeeded even though the Users were not uploaded. However the upload task
     *  can be done outside the texting environment.
     */
    @Test
    public void uploadUser_isCorrect() {
        FirebaseDatabase.getInstance().getReference().child("users").child("test").addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                assertNotSame(snapshot.getChildrenCount(), 0);
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {
                Assert.fail(error.getMessage());
            }
        });

        User lisinan2 = new User("lisinan2");
        lisinan2.setName("Lance Li");
        lisinan2.setUserType("test");
        lisinan2.setViewed(new ArrayList<>(Arrays.asList("a", "b", "c")));

        User vgvg = new User("vgvg");
        vgvg.setName("Vedant Goel");
        vgvg.setUserType("test");
        vgvg.setViewed(new ArrayList<String>());
        vgvg.setMatches(new ArrayList<>(Arrays.asList("d", "e", "f")));

        RealtimeDbWriteListener listener = new RealtimeDbWriteListener() {
            @Override
            public void onWriteSuccess(Void unused) {
            }

            @Override
            public void onWriteFailure(Exception e) {
                Assert.fail(e.getMessage());
            }
        };

        UserRealtimeDbFacade.uploadUser(lisinan2, listener);
        UserRealtimeDbFacade.uploadUser(vgvg, listener);

    }

    /**
     * The realtime database must contain the following entries:
     * - users
     * <p>
     * _ - test
     * <p>
     * _ _ - lisinan2
     * <p>
     * _ _ _ - firstName: "Lance"
     * <p>
     * _ _ _ - lastName:  "Li"
     * <p>
     * _ _ _ - uid:       "lisinan2"
     * <p>
     * _ _ _ - userType:  "test"
     * <p>
     * _ _ - vgvg
     * <p>
     * _ _ _ - firstName: "Vedant"
     * <p>
     * _ _ _ - lastName:  "Goel"
     * <p>
     * _ _ _ - uid:       "vgvg"
     * <p>
     * _ _ _ - userType:  "test"
     */
    @Test
    public void getAllUsers_isCorrect() {
        StringBuilder namesBuilder = new StringBuilder();
        UserRealtimeDbFacade.getAllUsers("Academic", userList -> {
            userList.forEach(user -> namesBuilder.append(user.getUid()).append("_"));
            assertEquals("lisinan2_test1_test2_test3_", namesBuilder.toString());
            // this line is equivalent to the following code chunk -- please refer to Java lambda expressions for more details
            /* userList.forEach(new Consumer<User>() {
             *    @Override
             *    public void accept(User user) {
             *        namesBuilder.append(user.getUserType()).append("_");
             *    }
             *});
             */
        });
    }

    /**
     * Test for the getUser() method.
     * <p>
     * The database should have the same structure as described in getAllUsers_isCorrect()
     */
    @Test
    public void getUser_isCorrect() {
        StringBuilder userInfo = new StringBuilder();
        UserRealtimeDbFacade.getUser(
                "test", "lisinan2",
                user -> {
                    assertEquals(
                            "lisinan2: Lance Li (test)",
                            userInfo.append(user.getUid()).append(": ").append(user.getName()).append(" (").append(user.getUserType()).append(")").toString()
                    );
                    assertArrayEquals(new String[]{"a", "b", "c"}, user.getViewed().toArray());
                }
        );
    }
}
