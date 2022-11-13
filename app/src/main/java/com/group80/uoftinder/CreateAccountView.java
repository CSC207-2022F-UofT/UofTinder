package com.group80.uoftinder;

import android.widget.EditText;

import androidx.appcompat.app.AppCompatActivity;

import java.util.HashMap;

public class CreateAccountView extends AppCompatActivity {
    //very rough class right now, just using this to store questions and store answers into User
    HashMap<String, String> basicInfo = new HashMap<String, String>();
    private final String email = "";
    private String password1 = "", password2 = "";
    private boolean accountCreated = false;


    public boolean checkPasswords(String password1, String password2) {
        return password1.compareTo(password2) == 0;
    }

    public boolean checkEmail(String email) {
        //firebase stuff
        //if email hasn't already been used in firebase
        return true;
        //if email is already in use
        //return false;

    }

    public boolean getCreated() {
//        if (checkEmail(password1,password2) && checkPasswords(email))
//            accountCreated = true;
        return accountCreated;
    }
}
