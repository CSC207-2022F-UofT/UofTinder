package com.group80.uoftinder;

import com.group80.uoftinder.create_account_use_case.CreateAccountInteractor;
import com.group80.uoftinder.login_use_case.LoginInteractor;

public class UserAccountController {

    public boolean newAccount(String email, String password1, String password2) {
        CreateAccountInteractor interactor = new CreateAccountInteractor();

        boolean checked_passwords = interactor.checkPasswords(password1, password2);
        boolean new_email = interactor.checkEmail(email);

        return checked_passwords && new_email;
    }

    public boolean loginAccount(String email, String password) {
        LoginInteractor interactor = new LoginInteractor();
        // check if user is in the database
    }

}
