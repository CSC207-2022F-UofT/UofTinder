package com.group80.uoftinder;

import android.os.Bundle;
import android.widget.EditText;

import androidx.appcompat.app.AppCompatActivity;

import com.group80.uoftinder.entities.User;

public class testing extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.test_file);

        EditText enterEmail = findViewById(R.id.enterEmail);
        EditText enterPassword = findViewById(R.id.enterPassword);
        EditText enterPassword2 = findViewById(R.id.enterPassword2);



            User user = new User(enterEmail.getText().toString(), enterPassword.getText().toString());
    }
}