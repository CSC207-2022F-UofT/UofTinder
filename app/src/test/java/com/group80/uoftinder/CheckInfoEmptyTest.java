package com.group80.uoftinder;

import com.group80.uoftinder.create_account_use_case.CreateAccountInteractor;
import com.group80.uoftinder.create_account_use_case.CreateAccountPresenter;
import com.group80.uoftinder.create_account_use_case.CreateAccountPresenterFormatter;
import com.group80.uoftinder.create_account_use_case.CreateAccountView;
import com.group80.uoftinder.create_account_use_case.CreateAccountViewModel;
import com.group80.uoftinder.feed.RecommendationView;

/**
 * Unit tests for testing that ensures the current user trying to create a new account enters all
 * information on the questionnaires.
 */
public class CheckInfoEmptyTest {

//    Class<RecommendationView> testRecView;
//    CreateAccountViewModel viewModel;
    CreateAccountPresenter testPresenter;
//    = new CreateAccountPresenterFormatter(testRecView, viewModel);
    CreateAccountInteractor testInteractor = new CreateAccountInteractor(testPresenter);


}
