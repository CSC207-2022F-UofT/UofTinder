package com.group80.uoftinder.login_use_case;

import com.google.firebase.auth.FirebaseUser;

public interface LoginViewModel {
    void updateUI(FirebaseUser firebaseUser);
    void showMessageToast(String message);
    void showEmailMessage(String error);
    void showPasswordMessage(String error);
}
