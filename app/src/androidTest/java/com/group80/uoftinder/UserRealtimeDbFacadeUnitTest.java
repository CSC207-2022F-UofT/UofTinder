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
import com.group80.uoftinder.entities_layer.User;
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

        Thread thread = new Thread(() -> {
            try {
                Thread.sleep(500);
            } catch (InterruptedException e) {
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

    /**
     * Test if the db can get all users of a given user type
     */
    @Test
    public void getAllUsers_isCorrect() {
        StringBuilder namesBuilder = new StringBuilder();
        // refer to Java lambda expression for more details on the following syntax
        UserRealtimeDbFacade.getAllUsers("test", userList -> {
            userList.forEach(user -> namesBuilder.append(user.getUid()).append("_"));
            assertEquals("lisinan2_vgvg_", namesBuilder.toString());
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
