package com.group80.uoftinder;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;

import androidx.appcompat.app.AppCompatActivity;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.group80.uoftinder.login_use_case.LoginController;
import com.group80.uoftinder.login_use_case.LoginInput;
import com.group80.uoftinder.login_use_case.LoginInteractor;
import com.group80.uoftinder.login_use_case.LoginPresenter;
import com.group80.uoftinder.login_use_case.LoginPresenterFormatter;
import com.group80.uoftinder.login_use_case.LoginViewInterface;


public class Login extends AppCompatActivity implements LoginViewInterface {

    EditText loginEmail;
    EditText loginPassword;
    Button enterLogin;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.loginview);

        FirebaseAuth mAuth = FirebaseAuth.getInstance(); // initialize the Firebase Auth

        // UserAccountController
        loginEmail = findViewById(R.id.loginEmail);
        loginPassword = findViewById(R.id.loginPassword);

        LoginPresenter loginPresenter = new LoginPresenterFormatter(HelloWorld.class, Login.this);
        LoginInput loginInteractor = new LoginInteractor(loginPresenter);
        LoginController loginController = new LoginController(loginInteractor);


        enterLogin = findViewById(R.id.EnterLogin);
        enterLogin.setOnClickListener(view -> loginController.loginUser(loginEmail, loginPassword));
    }

    /**
     * Update the UI to the logged in user's recommendation view.
     * @param firebaseUser current FirebaseUser
     */
    @Override
    public void updateUI(FirebaseUser firebaseUser) {
        startActivity(new Intent(Login.this, HelloWorld.class));
    }
//
//    public void showCreateAccountView(View view) {
//        // CreateAccountView will be fine when merged with create account branch
//        Intent intent = new Intent(Login.this, CreateAccountView.class);
//        startActivity(intent);
//        finish();
//    }

    // LoginInteractor
//    public void loginUser(FirebaseAuth mAuth, EditText loginEmail, EditText loginPassword) {
//
//        String email = loginEmail.getText().toString().trim();
//        String password = loginPassword.getText().toString().trim();
//
//        /* To log-in, use the following
//         * mAuth.signInWithEmailAndPassword("demo@email.com", "password");
//         * To get user uid, after you have signed in, do the following
//         * mAuth.getCurrentUser().getUid();
//         */
//        if (TextUtils.isEmpty(email)) { // no email input, user == null
//            loginEmail.setError("Email is required!");
//            loginEmail.requestFocus();
//        }
//        else if (TextUtils.isEmpty(password)){ // no password input, user == null
//            loginPassword.setError("Password is required!");
//            loginPassword.requestFocus();
//        }
//        else { // user == null, signing in with email and password
//            mAuth.signInWithEmailAndPassword(email, password)
//                    .addOnCompleteListener(this, new OnCompleteListener<AuthResult>() {
//                        @Override
//                        public void onComplete(@NonNull Task<AuthResult> task) {
//                            // LoginInteractor checks
//
//                            // Login Presenter?
//                            if (task.isSuccessful()) {
//                                Toast.makeText(Login.this, "Login Successful!", Toast.LENGTH_SHORT).show();
//                                startActivity(new Intent(Login.this, HelloWorld.class));
//
//                            } else {
//                                // If sign in fails, display a message to the user.
//                                Toast.makeText(Login.this,
//                                        "Login Failed :(",
//                                        Toast.LENGTH_SHORT).show();
//                            }
//                        }
//                    });
//        }
//    }
}