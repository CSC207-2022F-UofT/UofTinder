package com.group80.uoftinder.login_use_case;

import android.widget.EditText;

import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseUser;

public interface LoginPresenter {

    void prepareSuccessView(Task<AuthResult> task, FirebaseUser user);

    void prepareFailureViewLogin(String error);

    void prepareFailureView(EditText text, String error);
}
