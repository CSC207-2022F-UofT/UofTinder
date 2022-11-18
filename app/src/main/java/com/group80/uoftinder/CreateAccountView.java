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
    private final UserAccountController control = new UserAccountController();
    private final CreateAccountPresenter proceed = new CreateAccountPresenter();

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
        String email = ((EditText) findViewById(R.id.email)).getText().toString().trim();
        String password1 = ((EditText)findViewById(R.id.password1)).getText().toString().trim();
        String password2 = ((EditText)findViewById(R.id.password2)).getText().toString().trim();
        boolean move_on = control.newAccount(email, password1, password2);
        if (move_on) {
            createBasicInfoView();
        }
        else {
            String text = proceed.account_error(email, password1, password2);
            //use text to set error message in UI
        }
    }

    private void createBasicInfoView() {
        setContentView(R.layout.basicinfoview);
        String userName = ((EditText) findViewById(R.id.name)).getText().toString().trim();
        String userAge = ((EditText) findViewById(R.id.age)).getText().toString();
//        String password2 = ((EditText)findViewById(R.id.password2)).getText().toString().trim();
//        get all attributes and pass type to createQuestionnaireView
//        String type = get type from boxes and createQuestionnaireView(type)

    }

    private void createQuestionnaireView(String type) {
        if(type.compareTo("Romantic")==0) {
//            romantic questionnaire view
        }
        else if(type.compareTo("Friendship")==0) {
//           friendship questionnaire view
        }
        else if(type.compareTo("Academic")==0) {
//            academic questionnaire view
        }
    }

    private void showLoginView(View view) {
        Intent intent  = new Intent(CreateAccountView.this, appTestWelcomeScreens.class);
        startActivity(intent);
        finish();
    }
}
