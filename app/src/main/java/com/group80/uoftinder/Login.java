package com.group80.uoftinder;

import static android.content.ContentValues.TAG;

import android.os.Bundle;
import android.text.TextUtils;
import android.util.Log;
import android.widget.EditText;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

public class Login extends AppCompatActivity {
    private FirebaseAuth mAuth;

    EditText loginEmail;
    EditText loginPassword;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.loginview);

        mAuth = FirebaseAuth.getInstance(); // initialize the Firebase Auth
        loginEmail = findViewById(R.id.loginEmail);
        loginPassword = findViewById(R.id.password);

    }

    @Override
    public void onStart() {
        super.onStart();

        String email = loginEmail.getText().toString().trim();
        String password = loginPassword.getText().toString().trim();
        // Check if user is signed in (non-null) and update UI accordingly.

        FirebaseUser currentUser = mAuth.getCurrentUser(); // this is not null ONLY if you have logged in
        /* To log-in, use the following
         * mAuth.signInWithEmailAndPassword("demo@email.com", "password");
         * To get user uid, after you have signed in, do the following
         * mAuth.getCurrentUser().getUid();
         */
        if(currentUser != null){ // this means the user is logged in
            currentUser.reload();
        }
        else if (TextUtils.isEmpty(email)) {
            loginEmail.setError("Email is required!");
            loginEmail.requestFocus();
        }
        else if (TextUtils.isEmpty(password)){
            loginPassword.setError("Password is required!");
            loginPassword.requestFocus();
        }
        else {
            mAuth.signInWithEmailAndPassword(email, password)
                    .addOnCompleteListener(this, new OnCompleteListener<AuthResult>() {
                        @Override
                        public void onComplete(@NonNull Task<AuthResult> task) {
                            if (task.isSuccessful()) {
                                // Sign in success, update UI with the signed-in user's information
                                Log.d(TAG, "signInWithEmail:success");
                                FirebaseUser user = mAuth.getCurrentUser();
                                // updateUI(user);
                                user.getUid();
                                // getUserById()

                            } else {
                                // If sign in fails, display a message to the user.
                                Log.w(TAG, "signInWithEmail:failure", task.getException());
                                Toast.makeText(Login.this, "Authentication failed.",
                                        Toast.LENGTH_SHORT).show();
                            }
                        }
                    });
            }


        }

    }