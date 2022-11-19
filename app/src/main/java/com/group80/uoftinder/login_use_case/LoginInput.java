package com.group80.uoftinder.login_use_case;

import android.widget.EditText;

import com.google.firebase.auth.FirebaseAuth;

public interface LoginInput {
    void loginUser(FirebaseAuth mAuth, EditText loginEmail, EditText loginPassword);

}
