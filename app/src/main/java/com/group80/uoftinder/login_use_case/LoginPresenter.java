package com.group80.uoftinder.login_use_case;

import com.google.firebase.auth.FirebaseUser;

public interface LoginPresenter {

    void prepareSuccessView(FirebaseUser user);

    void prepareFailureView(String error);
}
