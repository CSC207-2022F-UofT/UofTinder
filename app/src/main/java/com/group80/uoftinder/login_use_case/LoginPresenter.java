package com.group80.uoftinder.login_use_case;

import com.google.firebase.auth.FirebaseUser;

public interface LoginPresenter {
    void prepareSuccessView(String success, FirebaseUser firebaseUser);
    void prepareLoginFailureView(String error);
    void prepareEmailFailureView(String error);
    void preparePasswordFailureView(String error);
}
