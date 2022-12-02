package com.group80.uoftinder.login_use_case;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import androidx.appcompat.app.AppCompatActivity;

import com.group80.uoftinder.R;

public class WelcomeView extends AppCompatActivity {

    Button continueButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.welcome_screen);

        continueButton = findViewById(R.id.continueButton);
        continueButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                continueToLoginActivity(v);
            }
        });

    }

    /**
     * Change to the login view
     */
    private void continueToLoginActivity(View view) {
        Intent intent = new Intent(WelcomeView.this, LoginActivity.class);
        startActivity(intent);
        finish();
    }
}
