package com.group80.uoftinder;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.AppCompatEditText;

import com.google.android.material.chip.Chip;
import com.google.android.material.chip.ChipGroup;
import com.group80.uoftinder.create_account_use_case.CreateAccountPresenter;
import com.group80.uoftinder.entities.User;

import org.w3c.dom.Text;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;

public class CreateAccountView extends AppCompatActivity {
    private User currentUser = new User();
    private ArrayList<HashSet<Integer>> answers = new ArrayList<>();
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
//            currentUser.setEmail(email);
//            currentUser.setPassword(password1);
            createBasicInfoView();
        }
        else {
            String text = proceed.account_error(email, password1, password2);
            TextView error = findViewById(R.id.errormessage);
            error.setText(text);
            System.out.println(error.getText().toString());
        }
    }

    private void createBasicInfoView() {
        setContentView(R.layout.basicinfoview);

        Button enter = findViewById(R.id.cont);
        enter.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                String userName = ((EditText) findViewById(R.id.name)).getText().toString().trim();
                int userAge = Integer.parseInt(((EditText) findViewById(R.id.age)).getText().toString());

                ChipGroup identity_group = findViewById(R.id.identityGroup);
                int identity_count = identity_group.getChildCount();
                String identity = "";

                for(int i = 0; i<identity_count; i++) {
                    Chip chip  = (Chip) identity_group.getChildAt(i);
                    if(chip.isChecked()) {
                        identity = chip.getText().toString();
                    }
                }
                ChipGroup type_group = findViewById(R.id.typeGroup);
                int type_count = type_group.getChildCount();
                String type = "";
                for(int i = 0; i<type_count; i++) {
                    Chip chip  = (Chip) identity_group.getChildAt(i);
                    if(chip.isChecked()) {
                        type = chip.getText().toString();
                    }
                }

                boolean move_on = control.newAccount(userName, userAge, identity, type);
                if(move_on) {
                    currentUser.setName(userName);
                    currentUser.setAge(userAge);
                    currentUser.setGender(identity);
                    currentUser.setUserType(type);

                    createQuestionnaireView("Academic");
                }
                else {
                    //display text message error
                }

            }
        });

    }

    private void createQuestionnaireView(String type) {
        if(type.compareTo("Academic")==0) {
            createAcademicQuestionnaire();
        }
//        else if(type.compareTo("Friendship")==0) {
////           friendship questionnaire view
//        }
//        else if(type.compareTo("Romantic")==0) {
////            academic questionnaire view
//        }
    }

    private void createAcademicQuestionnaire() {
        setContentView(R.layout.academic_questionnaire);

        Button enter = findViewById(R.id.finish);
        enter.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                ChipGroup year_group = findViewById(R.id.yeargroup);
                int year_count = year_group.getChildCount();
                int year = 0;
                for(int i = 0; i<year_count; i++) {
                    Chip chip  = (Chip) year_group.getChildAt(i);
                    if(chip.isChecked()) {
                        year = i;
                    }
                }

                ChipGroup major_group = findViewById(R.id.majorgroup);
                int major_count = major_group.getChildCount();
                HashSet<Integer> majors = new HashSet<>();
                for(int i = 0; i<major_count; i++) {
                    Chip chip  = (Chip) major_group.getChildAt(i);
                    if(chip.isChecked()) {
                        majors.add(i);
                    }
                }

                ChipGroup campus_group = findViewById(R.id.campusgroup);
                int campus_count = major_group.getChildCount();
                int campus = 0;
                for(int i = 0; i<campus_count; i++) {
                    Chip chip  = (Chip) campus_group.getChildAt(i);
                    if(chip.isChecked()) {
                        campus = i;
                    }
                }

                boolean move_on = control.finalAccount(year, majors, campus);

                if (move_on) {
                    answers.add(new HashSet<>(year));
                    answers.add(majors);
                    answers.add(new HashSet<>(campus));
                    currentUser.setAnswers((answers));
                    //store User into database
                    //go onto recommendation feed
                }
                else {
                    //display text message error
                }
            }
        });
    }

    private void showLoginView(View view) {
        Intent intent  = new Intent(CreateAccountView.this, appTestWelcomeScreens.class);
        startActivity(intent);
        finish();
    }
}
