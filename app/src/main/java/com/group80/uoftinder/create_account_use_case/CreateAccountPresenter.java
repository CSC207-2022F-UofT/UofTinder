package com.group80.uoftinder.create_account_use_case;

import com.group80.uoftinder.entities_layer.User;

// Interface adapter layer
/**
 * Updates CreatAccountActivity view.
 */
public class CreateAccountPresenter implements CreateAccountPresenterInterface {
    final CreateAccountViewInterface createAccountViewModel;

    public CreateAccountPresenter(CreateAccountViewInterface createAccountViewModel) {
        this.createAccountViewModel = createAccountViewModel;
    }

    /**
     * Displays message to user saying they registered successfully,
     * switches UI for user to set up their basic information
     *
     * @param success     message displayed
     * @param currentUser current user
     */
    @Override
    public void prepareSuccessView(String success, User currentUser) {
        createAccountViewModel.showMessage(success);
        createAccountViewModel.basicInfoUI(currentUser);
    }

    /**
     * Displays message to user saying they registration was unsuccessful
     *
     * @param failure message displayed
     */
    @Override
    public void prepareCreateAccountFailureView(String failure) {
        createAccountViewModel.showMessage(failure);
    }

    /**
     * Display message when email input is missing when the user is creating their account
     *
     * @param error message displayed
     */
    @Override
    public void prepareEmailMissingView(String error) {
        createAccountViewModel.showEmailMessage(error);
    }

    /**
     * Display message when password1 input is missing
     *
     * @param error message displayed
     */
    @Override
    public void preparePassword1MissingView(String error) {
        createAccountViewModel.showPassword1Message(error);
    }

    /**
     * Display message when password2 input is missing
     *
     * @param error message displayed
     */
    @Override
    public void preparePassword2MissingView(String error) {
        createAccountViewModel.showPassword2Message(error);
    }

    /**
     * Display message when password1 and password2 inputs do not match
     *
     * @param error message displayed
     */
    @Override
    public void preparePasswordMatchError(String error) {
        createAccountViewModel.showPassword1Message(error);
        createAccountViewModel.showPassword2Message(error);
    }

    /**
     * Creates academic questionnaire for the user and changes UI
     *
     * @param currentUser current user
     */
    @Override
    public void createAcademicQuestionnaire(User currentUser) {
        createAccountViewModel.academicQuestionnaireUI(currentUser);
    }

    /**
     * Creates friendship questionnaire for the user and changes UI
     *
     * @param currentUser current user
     */
    @Override
    public void createFriendshipQuestionnaire(User currentUser) {
        createAccountViewModel.friendshipQuestionnaireUI(currentUser);
    }

    /**
     * Creates romantic questionnaire for the user and changes UI
     *
     * @param currentUser current user
     */
    @Override
    public void createRomanticQuestionnaire(User currentUser) {
        createAccountViewModel.romanticQuestionnaireUI(currentUser);
    }

    /**
     * Displays message to user saying they registered successfully,
     * switches UI to user's recommendation view
     *
     * @param currentUser current user
     */
    @Override
    public void prepareProfilePicUploadActivity(User currentUser) {
        createAccountViewModel.uploadProfilePicture(currentUser);
    }
}