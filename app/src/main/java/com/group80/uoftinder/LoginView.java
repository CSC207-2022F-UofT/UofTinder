package com.group80.uoftinder;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;

public class LoginView extends AppCompatActivity {
    String email, password;
    Button enterLogin;
    FirebaseAuth fAuth;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.loginview);

        email = ((EditText) findViewById(R.id.email)).getText().toString().trim();
        password = ((EditText) findViewById(R.id.password)).getText().toString().trim();

        fAuth = FirebaseAuth.getInstance();

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

                fAuth.signInWithEmailAndPassword(email, password).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {
                        if (task.isSuccessful()) {
                            Toast.makeText(LoginView.this, "Success!", Toast.LENGTH_SHORT).show();
                            startActivity(new Intent(getApplicationContext(), appTestWelcomeScreens.class));
                        }
                    }
                });
            }
        });
    }
}
