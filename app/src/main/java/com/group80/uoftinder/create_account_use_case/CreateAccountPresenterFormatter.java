package com.group80.uoftinder.create_account_use_case;

import com.group80.uoftinder.entities.User;
import com.group80.uoftinder.feed.RecommendationView;

public class CreateAccountPresenterFormatter implements CreateAccountPresenter{
    final Class<RecommendationView> recommendationViewClass;
    final CreateAccountViewModel createAccountViewModel;

    public CreateAccountPresenterFormatter(Class<RecommendationView> recommendationViewClass,
                                           CreateAccountViewModel createAccountViewModel) {
        this.recommendationViewClass = recommendationViewClass;
        this.createAccountViewModel = createAccountViewModel;
    }


    @Override
    public void prepareSuccessView(String success, User currentUser) {
        createAccountViewModel.showMessage(success);
        createAccountViewModel.basicInfoUI(currentUser);
    }

    @Override
    public void prepareCreateAccountFailureView(String failure) {
        createAccountViewModel.showMessage(failure);
    }

    @Override
    public void prepareEmailMissingView(String error) {
        createAccountViewModel.showEmailMessage(error);
    }

    @Override
    public void preparePassword1MissingView(String error) {
        createAccountViewModel.showPassword1Message(error);
    }

    @Override
    public void preparePassword2MissingView(String error) {
        createAccountViewModel.showPassword2Message(error);
    }

    @Override
    public void preparePasswordMatchError(String error) {
        createAccountViewModel.showPassword1Message(error);
        createAccountViewModel.showPassword2Message(error);
    }

    @Override
    public void createAcademicQuestionnaire(User currentUser) {
        createAccountViewModel.academicQuestionnaireUI(currentUser);
    }

    @Override
    public void createFriendshipQuestionnaire(User currentUser) {
        createAccountViewModel.friendshipQuestionnaireUI(currentUser);
    }

    @Override
    public void createRomanticQuestionnaire(User currentUser) {
        createAccountViewModel.romanticQuestionnaireUI(currentUser);
    }

    @Override
    public void prepareRecommendationView(User currentUser) {
        createAccountViewModel.showMessage("Registration Successful :D");
        createAccountViewModel.updateUI(currentUser);
    }


}
