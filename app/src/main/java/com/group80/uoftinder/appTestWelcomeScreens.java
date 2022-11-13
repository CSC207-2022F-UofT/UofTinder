package com.group80.uoftinder;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import androidx.activity.result.ActivityResultLauncher;
import androidx.activity.result.contract.ActivityResultContracts;
import androidx.appcompat.app.AppCompatActivity;

public class appTestWelcomeScreens extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.loginview);


        }

    public void showCreateAccountView(View view) {
        setContentView(R.layout.createaccountview);
    }

    public void showLoginView(View view) {
        setContentView(R.layout.loginview);
    }
}
