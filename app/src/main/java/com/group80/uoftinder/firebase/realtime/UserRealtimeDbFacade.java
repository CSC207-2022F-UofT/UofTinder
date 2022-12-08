package com.group80.uoftinder.firebase.realtime;

import com.group80.uoftinder.entities_layer.User;

import java.util.List;

/**
 * A Facade for methods regrading User in realtime database. All methods are constants, and no
 * instances of this Facade should be created.
 */
public class UserRealtimeDbFacade {
    private static final String DEFAULT_USER_TYPE = "Academic";

    /**
     * Upload a user to the realtime database, based on the user type and user id. The user is
     * stored under "users" / user.userType / user.uid
     *
     * @param user the user to be stored in the database
     */
    public static void uploadUser(User user) {
        ucClassWriter<User> userWriter = new ucClassWriter<>();
        userWriter.write(new String[]{"users", user.getUserType(), user.getUid()}, user);
    }

    /**
     * Upload a user to the realtime database, based on the user type and user id. The user is
     * stored under "users" / user.userType / user.uid
     *
     * @param user     the user to be stored in the database
     * @param listener an interface handles behaviour of successful and failed user uploads
     */
    public static void uploadUser(User user, RealtimeDbWriteListener listener) {
        ucClassWriter<User> classWriter = new ucClassWriter<>();
        classWriter.addListener(listener);
        classWriter.write(new String[]{"users", user.getUserType(), user.getUid()}, user);
    }

    /**
     * Returns a list of Users of the given userType.
     *
     * @param callBack an interface handles the value retrieved
     */
    public static void getAllUsers(RealtimeDbCallback<List<User>> callBack) {
        ucUserReader.getAllUsers(DEFAULT_USER_TYPE, callBack);
    }

    /**
     * Returns a list of Users of the given userType.
     *
     * @param userType the type of user
     * @param callBack an interface handles the value retrieved
     */
    public static void getAllUsers(String userType, RealtimeDbCallback<List<User>> callBack) {
        ucUserReader.getAllUsers(userType, callBack);
    }

    /**
     * Search for a User entry in the realtime database with the given user ID.
     *
     * @param uid      the uid entry searched in the realtime database
     * @param callback UserCallback to handle the value retrieved since onComplete has type void
     */
    public static void getUser(String uid, RealtimeDbCallback<User> callback) {
        ucUserReader.getUser(DEFAULT_USER_TYPE, uid, callback);
    }

    /**
     * Search for a User entry in the realtime database with the given user ID.
     *
     * @param userType the type of the user
     * @param uid      the uid entry searched in the realtime database
     * @param callback UserCallback to handle the value retrieved since onComplete has type void
     */
    public static void getUser(String userType, String uid, RealtimeDbCallback<User> callback) {
        ucUserReader.getUser(userType, uid, callback);
    }
}
