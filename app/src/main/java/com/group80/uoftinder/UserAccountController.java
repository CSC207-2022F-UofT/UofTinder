package com.group80.uoftinder;

public class UserAccountController {

    public boolean newAccount(String email, String password1, String password2) {
        CreateAccountInteractor interactor = new CreateAccountInteractor();

        boolean checked_passwords = interactor.checkPasswords(password1, password2);
        boolean new_email = interactor.checkEmail(email);

        return checked_passwords && new_email;
    }

}
