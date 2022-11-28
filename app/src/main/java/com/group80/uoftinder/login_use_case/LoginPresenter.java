package com.group80.uoftinder.login_use_case;

import android.widget.EditText;

import com.google.firebase.auth.FirebaseUser;

public interface LoginPresenter {

    void prepareSuccessView(FirebaseUser user);

    void prepareFailureViewLogin(String error);

    void prepareFailureView(EditText text, String error);
}
