package com.group80.uoftinder.view_layer.create_account;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.google.android.material.chip.ChipGroup;
import com.google.firebase.auth.FirebaseAuth;
import com.group80.uoftinder.Constants;
import com.group80.uoftinder.interface_adapter_layer.create_account.CreateAccountController;
import com.group80.uoftinder.use_case_layer.create_account.CreateAccountInput;
import com.group80.uoftinder.use_case_layer.create_account.CreateAccountPresenterInterface;
import com.group80.uoftinder.interface_adapter_layer.create_account.CreateAccountViewInterface;
import com.group80.uoftinder.interface_adapter_layer.create_account.CreateAccountPresenter;
import com.group80.uoftinder.use_case_layer.create_account.CreateAccountInteractor;
import com.group80.uoftinder.view_layer.profile_pic_upload.ProfilePicUploadActivity;
import com.group80.uoftinder.R;
import com.group80.uoftinder.entities_layer.User;
import com.group80.uoftinder.view_layer.login.LoginActivity;

// Frameworks & Drivers Layer
/**
 * Code for all the views that the user will go through to create a user
 */
public class CreateAccountActivity extends AppCompatActivity implements CreateAccountViewInterface {

    private final CreateAccountPresenterInterface presenter = new CreateAccountPresenter(CreateAccountActivity.this);
    private final CreateAccountInput interactor = new CreateAccountInteractor(presenter);
    private final CreateAccountController controller = new CreateAccountController(interactor);
    private EditText createAccountEmail;
    private EditText createAccountPassword1;
    private EditText createAccountPassword2;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_create_account);

        FirebaseAuth.getInstance();

        createAccountEmail = findViewById(R.id.createActViewEmailEdtTxt);
        createAccountPassword1 = findViewById(R.id.createActViewPasswordEdtTxt);
        createAccountPassword2 = findViewById(R.id.createActViewReEnterPasswordEdtTxt);

        Button buttonShowLoginView = findViewById(R.id.createAccountViewRetLoginButton);
        buttonShowLoginView.setOnClickListener(v -> showLoginView());

        Button enter = findViewById(R.id.createActViewCreateActBtn);
        // creating account and uploading to the FirebaseAuth
        enter.setOnClickListener(v -> controller.createAccount(createAccountEmail, createAccountPassword1, createAccountPassword2));
    }

    /**
     * Pops up a message notifying the user of an event
     *
     * @param message message displayed
     */
    @Override
    public void showMessage(String message) {
        Toast.makeText(CreateAccountActivity.this, message, Toast.LENGTH_SHORT).show();
    }

    /**
     * Sets error message and requests focus to email input
     *
     * @param error message displayed
     */
    @Override
    public void showEmailMessage(String error) {
        createAccountEmail.setError(error);
        createAccountEmail.requestFocus();
    }

    /**
     * Sets error message and requests focus to password1 input
     *
     * @param error message displayed
     */
    @Override
    public void showPassword1Message(String error) {
        createAccountPassword1.setError(error);
        createAccountPassword1.requestFocus();
    }

    /**
     * Sets error message and requests focus to password2 input
     *
     * @param error message displayed
     */
    @Override
    public void showPassword2Message(String error) {
        createAccountPassword2.setError(error);
        createAccountPassword2.requestFocus();
    }

    /**
     * Creates the basicinfoview.xml and gets inputs which is passed to the controller
     *
     * @param currentUser the current user trying to register
     */
    @Override
    public void basicInfoUI(User currentUser) {
        setContentView(R.layout.basic_info_view);

        Button enter = findViewById(R.id.cont);
        EditText userName = findViewById(R.id.name);
        EditText userAge = findViewById(R.id.age);

        ChipGroup identityGroup = findViewById(R.id.identityGroup);
        ChipGroup typeGroup = findViewById(R.id.typeGroup);

        enter.setOnClickListener(v -> controller.setBasicInfo(currentUser, userName, userAge, identityGroup, typeGroup));
    }

    /**
     * Creates the academic_questionnaire.xml and gets inputs which is passed to the controller
     *
     * @param currentUser the current user trying to register
     */
    @Override
    public void academicQuestionnaireUI(User currentUser) {
        setContentView(R.layout.questionnaire_academic);
        Button enter = findViewById(R.id.academic_finish);

        ChipGroup yearGroup = findViewById(R.id.yeargroup);
        ChipGroup majorGroup = findViewById(R.id.majorgroup);
        ChipGroup campusGroup = findViewById(R.id.campusgroup);

        enter.setOnClickListener(v -> controller.setAcademicInfo(currentUser, yearGroup, majorGroup, campusGroup));
    }

    /**
     * Creates the questionnaire_friendship.xml and gets inputs which is passed to the controller
     *
     * @param currentUser the current user trying to register
     */
    @Override
    public void friendshipQuestionnaireUI(User currentUser) {
        setContentView(R.layout.questionnaire_friendship);
        Button enter = findViewById(R.id.friend_finish);

        ChipGroup yearGroup = findViewById(R.id.yeargroup);
        ChipGroup majorGroup = findViewById(R.id.majorgroup);
        ChipGroup campusGroup = findViewById(R.id.campusgroup);
        ChipGroup interestsGroup = findViewById(R.id.interestsgroup);
        ChipGroup colourGroup = findViewById(R.id.colourgroup);

        enter.setOnClickListener(v -> controller.setFriendshipInfo(currentUser, yearGroup, majorGroup,
                campusGroup, interestsGroup, colourGroup));
    }

    /**
     * Creates the questionnaire_romantic.xml and gets inputs which is passed to the controller
     *
     * @param currentUser the current user trying to register
     */
    @Override
    public void romanticQuestionnaireUI(User currentUser) {
        setContentView(R.layout.questionnaire_romantic);
        Button enter = findViewById(R.id.romantic_finish);

        ChipGroup sexualityGroup = findViewById(R.id.sexualitygroup);
        ChipGroup majorGroup = findViewById(R.id.majorgroup);
        ChipGroup campusGroup = findViewById(R.id.campusgroup);
        ChipGroup interestsGroup = findViewById(R.id.interestsgroup);
        ChipGroup distanceGroup = findViewById(R.id.distancegroup);
        ChipGroup relationshipGroup = findViewById(R.id.relationshipgroup);

        enter.setOnClickListener(v -> controller.setRomanticInfo(currentUser, sexualityGroup, majorGroup,
                campusGroup, interestsGroup, distanceGroup, relationshipGroup));
    }

    /**
     * Updates UI to the user's recommendation view after finishing setting up their profile
     * and answering the questionnaire
     *
     * @param currentUser the current user
     */
    @Override
    public void uploadProfilePicture(User currentUser) {
        Intent intent = new Intent(CreateAccountActivity.this, ProfilePicUploadActivity.class);
        intent.putExtra(Constants.CURRENT_USER_STRING, currentUser);
        startActivity(intent);
        finish();
    }

    /**
     * Returns view back to loginView
     */
    private void showLoginView() {
        Intent intent = new Intent(CreateAccountActivity.this, LoginActivity.class);
        startActivity(intent);
        finish();
    }
}