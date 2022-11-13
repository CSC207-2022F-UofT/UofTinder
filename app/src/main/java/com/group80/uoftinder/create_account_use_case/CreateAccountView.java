package com.group80.uoftinder.create_account_use_case;

import android.view.View;
import android.widget.Button;

import androidx.appcompat.app.AppCompatActivity;

import com.group80.uoftinder.R;
import com.group80.uoftinder.UserAccountController;
import com.group80.uoftinder.create_account_use_case.CreateAccountPresenter;

import java.util.HashMap;

public class CreateAccountView extends AppCompatActivity {
    HashMap<String, String> basicInfo = new HashMap<String, String>();
    private String email = "";
    private String password1 = "", password2 = "";

    public void createAccountView() {
//        setContentView(R.layout.createaccountview);

        UserAccountController control = new UserAccountController();
        CreateAccountPresenter proceed = new CreateAccountPresenter();

        Button enter =findViewById(R.id.accountEnter);
        enter.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                email = findViewById(R.id.email).toString();
                password1 = findViewById(R.id.password1).toString();
                password2 = findViewById(R.id.password2).toString();
                control.newAccount(email, password1, password2);

            }
        });

    }
}
