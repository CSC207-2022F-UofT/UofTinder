package com.group80.uoftinder.login_use_case;

import com.google.firebase.auth.FirebaseUser;

public interface LoginViewInterface {
    void updateUI(FirebaseUser firebaseUser);
}
