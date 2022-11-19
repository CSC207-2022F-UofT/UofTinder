package com.group80.uoftinder;

import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;

public class Login extends AppCompatActivity {
    private FirebaseAuth mAuth;

    EditText loginEmail;
    EditText loginPassword;
    Button enterLogin;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.loginview);

        mAuth = FirebaseAuth.getInstance(); // initialize the Firebase Auth
        loginEmail = findViewById(R.id.loginEmail);
        loginPassword = findViewById(R.id.password);

        enterLogin = findViewById(R.id.EnterLogin);
        enterLogin.setOnClickListener(view -> loginUser());

    }

//    private void showHelloWorldView() {
//        Intent intent = new Intent(Login.this, HelloWorld.class);
//        startActivity(intent);
//        finish();
//    }


    public void loginUser() {
        // UserAccountController
        String email = loginEmail.getText().toString().trim();
        String password = loginPassword.getText().toString().trim();

        /* To log-in, use the following
         * mAuth.signInWithEmailAndPassword("demo@email.com", "password");
         * To get user uid, after you have signed in, do the following
         * mAuth.getCurrentUser().getUid();
         */
        // LoginInteractor
        if (TextUtils.isEmpty(email)) { // no email input, user == null
            loginEmail.setError("Email is required!");
            loginEmail.requestFocus();
            System.out.println("email needed");
        }
        else if (TextUtils.isEmpty(password)){ // no password input, user == null
            loginPassword.setError("Password is required!");
            loginPassword.requestFocus();
            System.out.println("password needed");
        }
        else { // user == null, signing in with email and password
            mAuth.signInWithEmailAndPassword(email, password)
                    .addOnCompleteListener(this, new OnCompleteListener<AuthResult>() {
                        @Override
                        public void onComplete(@NonNull Task<AuthResult> task) {
                            // LoginInteractor checks
                            // boolean success = task.isSuccessful()

                            // Login Presenter
                            if (task.isSuccessful()) {
                                Toast.makeText(Login.this, "Login Successful!", Toast.LENGTH_SHORT).show();
                                startActivity(new Intent(Login.this, HelloWorld.class));

                            } else {
                                // If sign in fails, display a message to the user.
                                Toast.makeText(Login.this,
                                        "Login Failed :(",
                                        Toast.LENGTH_SHORT).show();
                            }
                        }
                    });
        }
    }
}