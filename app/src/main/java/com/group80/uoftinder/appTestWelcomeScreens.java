package com.group80.uoftinder;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import androidx.appcompat.app.AppCompatActivity;

public class appTestWelcomeScreens extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.loginview);
    }

    public void showCreateAccountView(View view) {
        Intent intent = new Intent(appTestWelcomeScreens.this, CreateAccountView.class);
        startActivity(intent);
        finish();
    }

}
