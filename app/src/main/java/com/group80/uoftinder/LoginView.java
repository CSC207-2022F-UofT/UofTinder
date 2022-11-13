package com.group80.uoftinder;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import androidx.appcompat.app.AppCompatActivity;

public class LoginView extends AppCompatActivity {
    String email, password;
    Button enterLogin;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.loginview);

        email = ((EditText) findViewById(R.id.email)).getText().toString().trim();
        password = ((EditText) findViewById(R.id.password)).getText().toString().trim();

        enterLogin = findViewById(R.id.EnterLogin);

        enterLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (email.isEmpty()){
                    // say email is required idk
                    return;
                }

                if (password.isEmpty()) {
                    // password is required
                    return;
                }

                // password requirements


            }
        });
    }
}
