package com.group80.uoftinder.create_account_use_case;

import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.android.material.chip.Chip;
import com.google.android.material.chip.ChipGroup;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.group80.uoftinder.R;
import com.group80.uoftinder.entities.Constants;
import com.group80.uoftinder.entities.User;
//import com.group80.uoftinder.feed.RecommendationView;
import com.group80.uoftinder.feed.RecommendationView;
import com.group80.uoftinder.firebase.realtime.UserRealtimeDbFacade;
import com.group80.uoftinder.login_use_case.LoginActivity;

import java.util.ArrayList;
import java.util.Collections;
import java.util.LinkedList;
import java.util.List;

/**
 * Code for all the views that the user will go through to create a user
 */
public class CreateAccountView extends AppCompatActivity implements CreateAccountViewModel{

    private EditText createAccountEmail;
    private EditText createAccountPassword1;
    private EditText createAccountPassword2;

    private CreateAccountPresenter presenter = new CreateAccountPresenterFormatter(RecommendationView.class,
            CreateAccountView.this);
    private CreateAccountInput interactor = new CreateAccountInteractor(presenter);
    private CreateAccountController controller = new CreateAccountController(interactor);

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.createaccountview);

       FirebaseAuth.getInstance();

        createAccountEmail = findViewById(R.id.accountEmail);
        createAccountPassword1 = findViewById(R.id.password1);
        createAccountPassword2 = findViewById(R.id.password2);

        Button buttonShowLoginView = findViewById(R.id.loginButton);
        buttonShowLoginView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                showLoginView(v);
            }
        });

        Button enter = findViewById(R.id.accountEnter);
        // creating account and uploading to the FirebaseAuth
        enter.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                controller.createAccount(createAccountEmail, createAccountPassword1, createAccountPassword2);

            }
        });
    }
    @Override
    public void showMessage(String message){
        Toast.makeText(CreateAccountView.this, message, Toast.LENGTH_SHORT).show();
    }

    @Override
    public void showEmailMessage(String error){
        createAccountEmail.setError(error);
        createAccountEmail.requestFocus();
    }

    @Override
    public void showPassword1Message(String error){
        createAccountPassword1.setError(error);
        createAccountPassword1.requestFocus();
    }

    @Override
    public void showPassword2Message(String error){
        createAccountPassword2.setError(error);
        createAccountPassword2.requestFocus();
    }

    @Override
    public void basicInfoUI(User currentUser){
        setContentView(R.layout.basicinfoview);

        Button enter = findViewById(R.id.cont);
        EditText userName = findViewById(R.id.name);
        EditText userAge = findViewById(R.id.age);

        ChipGroup identityGroup = findViewById(R.id.identityGroup);
        ChipGroup typeGroup = findViewById(R.id.typeGroup);

        enter.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                controller.setBasicInfo(currentUser, userName, userAge, identityGroup, typeGroup);
            }
        });
    }

    @Override
    public void academicQuestionnaireUI(User currentUser){
        setContentView(R.layout.academic_questionnaire);
        Button enter = findViewById(R.id.academic_finish);

        ChipGroup yearGroup = findViewById(R.id.yeargroup);
        ChipGroup majorGroup = findViewById(R.id.majorgroup);
        ChipGroup campusGroup = findViewById(R.id.campusgroup);

        enter.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                controller.setAcademicInfo(currentUser, yearGroup, majorGroup, campusGroup);
            }
        });
    }

    @Override
    public void friendshipQuestionnaireUI(User currentUser){
        setContentView(R.layout.friendship_questionnaire);
        Button enter = findViewById(R.id.friend_finish);

        ChipGroup yearGroup = findViewById(R.id.yeargroup);
        ChipGroup majorGroup = findViewById(R.id.majorgroup);
        ChipGroup campusGroup = findViewById(R.id.campusgroup);
        ChipGroup interestsGroup = findViewById(R.id.interestsgroup);
        ChipGroup colourGroup = findViewById(R.id.colourgroup);

        enter.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                controller.setFriendshipInfo(currentUser, yearGroup, majorGroup,
                        campusGroup, interestsGroup, colourGroup);
            }
        });
    }

    @Override
    public void romanticQuestionnaireUI(User currentUser){
        setContentView(R.layout.romantic_questionnaire);
        Button enter = findViewById(R.id.romantic_finish);

        ChipGroup sexualityGroup = findViewById(R.id.sexualitygroup);
        ChipGroup majorGroup = findViewById(R.id.majorgroup);
        ChipGroup campusGroup = findViewById(R.id.campusgroup);
        ChipGroup interestsGroup = findViewById(R.id.interestsgroup);
        ChipGroup distanceGroup = findViewById(R.id.distancegroup);
        ChipGroup relationshipGroup = findViewById(R.id.relationshipgroup);

        enter.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                controller.setRomanticInfo(currentUser, sexualityGroup, majorGroup,
                        campusGroup, interestsGroup, distanceGroup, relationshipGroup);
            }
        });
    }

    @Override
    public void updateUI(User currentUser){
        Intent intent = new Intent(CreateAccountView.this, RecommendationView.class);
        intent.putExtra(Constants.CURRENT_USER_STRING, currentUser);
        startActivity(intent);
        finish();
    }

    /**
     * Returns view back to loginView
     * @param view      the current view (createAccountView)
     */
    private void showLoginView(View view) {
        Intent intent  = new Intent(CreateAccountView.this, LoginActivity.class);
        startActivity(intent);
        finish();
    }
}