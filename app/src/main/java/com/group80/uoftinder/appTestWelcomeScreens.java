package com.group80.uoftinder;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import androidx.appcompat.app.AppCompatActivity;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

public class appTestWelcomeScreens extends AppCompatActivity {

    FirebaseAuth mAuth;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.loginview);
        mAuth = FirebaseAuth.getInstance(); // initialize the Firebase Auth
    }

    @Override
    protected void onStart(){
        super.onStart();
        FirebaseUser user = mAuth.getCurrentUser();
        if (user == null) {
            startActivity(new Intent(appTestWelcomeScreens.this, LoginView.class));
        }
    }

    public void showCreateAccountView(View view) {
        Intent intent = new Intent(appTestWelcomeScreens.this, CreateAccountView.class);
        startActivity(intent);
        finish();
    }
}
