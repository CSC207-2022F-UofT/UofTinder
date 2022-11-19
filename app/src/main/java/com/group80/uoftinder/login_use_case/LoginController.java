package com.group80.uoftinder.login_use_case;

import android.widget.EditText;

import androidx.appcompat.app.AppCompatActivity;

import com.google.firebase.auth.FirebaseAuth;

public class LoginController extends AppCompatActivity {
    final LoginInput loginInput;

    public LoginController(LoginInput loginGateway) {
        this.loginInput = loginGateway;
    }

    public void loginUser(FirebaseAuth mAuth, EditText loginEmail, EditText loginPassword) {
        loginInput.loginUser(mAuth, loginEmail, loginPassword);
    };

}