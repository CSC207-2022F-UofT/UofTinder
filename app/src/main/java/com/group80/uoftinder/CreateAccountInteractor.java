package com.group80.uoftinder;

public class CreateAccountInteractor {

    public boolean checkPasswords(String password1, String password2) {
        return password1.compareTo(password2) == 0;
    }

    public boolean checkEmail(String email) {
//        firebase stuff
//        if email hasn't already been used in firebase
        return true;
//        if email is already in use
//        return false;

    }
}
