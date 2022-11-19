package com.group80.uoftinder.create_account_use_case;

import java.util.LinkedHashMap;

public class CreateAccountPresenter {
    public String account_error(String email, String password1, String password2) {
        CreateAccountInteractor interactor = new CreateAccountInteractor();
        if(email.compareTo("")==0 || password1.compareTo("")==0)
            return "Please enter your information";
        if(!(interactor.checkPasswords(password1, password2)))
            return "Your passwords do not match!";
        if(!(interactor.checkEmail(email)))
            return "This email already has an account";
        return "Sorry something is wrong, please try again later :(";

    }
}
