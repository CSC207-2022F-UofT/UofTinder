package com.group80.uoftinder;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import androidx.appcompat.app.AppCompatActivity;

import java.util.HashMap;

public class CreateAccountView extends AppCompatActivity {
    HashMap<String, String> basicInfo = new HashMap<String, String>();
    private String email = "";
    private String password1 = "", password2 = "";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.createaccountview);

        Button buttonShowLoginView = findViewById(R.id.loginButton);
        buttonShowLoginView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                showLoginView(v);
            }
        });

        Button enter = findViewById(R.id.accountEnter);
        enter.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                createAccountView();

            }
        });
    }

    public void createAccountView() {

        UserAccountController control = new UserAccountController();
        CreateAccountPresenter proceed = new CreateAccountPresenter();

        String email = findViewById(R.id.email).toString();
        String password1 = findViewById(R.id.password1).toString();
        String password2 = findViewById(R.id.password2).toString();
        control.newAccount(email, password1, password2);
    }

    private void showLoginView(View view) {
        Intent intent  = new Intent(CreateAccountView.this, appTestWelcomeScreens.class);
        startActivity(intent);
        finish();
    }
}
