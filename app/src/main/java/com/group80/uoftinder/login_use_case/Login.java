package com.group80.uoftinder.login_use_case;

import android.os.Bundle;
import android.view.View;

import androidx.appcompat.app.AppCompatActivity;

import com.google.firebase.auth.FirebaseAuth;
import com.group80.uoftinder.R;

public class Login extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.loginview);
    }

    public void logout(View view) {
        FirebaseAuth.getInstance().signOut();

    }
}