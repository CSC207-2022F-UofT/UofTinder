package com.group80.uoftinder;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import androidx.appcompat.app.AppCompatActivity;

import com.group80.uoftinder.create_account_use_case.CreateAccountPresenter;

import java.util.HashMap;

public class CreateAccountView extends AppCompatActivity {
    HashMap<String, String> basicInfo = new HashMap<String, String>();

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

        String email = ((EditText) findViewById(R.id.email)).getText().toString().trim();
        String password1 = ((EditText)findViewById(R.id.password1)).getText().toString().trim();
        String password2 = ((EditText)findViewById(R.id.password2)).getText().toString().trim();
        control.newAccount(email, password1, password2);
    }

    private void showLoginView(View view) {
        Intent intent  = new Intent(CreateAccountView.this, appTestWelcomeScreens.class);
        startActivity(intent);
        finish();
    }
}
