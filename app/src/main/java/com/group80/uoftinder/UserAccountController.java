package com.group80.uoftinder;

import com.group80.uoftinder.create_account_use_case.CreateAccountInteractor;
import java.util.HashSet;

public class UserAccountController {

    public boolean newAccount(String email, String password1, String password2) {
        CreateAccountInteractor interactor = new CreateAccountInteractor();

        boolean checked_passwords = interactor.checkPasswords(password1, password2);
        boolean new_email = interactor.checkEmail(email);

        return checked_passwords && new_email;
    }
    public boolean newAccount(String name, String age, String identity, String type) {
        boolean checkName = name.compareTo("")!=0;
        boolean checkAge = age.compareTo("")!=0;
        boolean checkIdentity = identity.compareTo("")!=0;
        boolean checkType = type.compareTo("")!=0;
        return checkName && checkAge && checkIdentity && checkType;
    }
    public boolean finalAccount(int year, HashSet<Integer> majors, int campus) {
        boolean checkYear = year != -1;
        System.out.println(year);
        boolean checkMajors = !(majors.isEmpty());
        System.out.println(campus);
        boolean checkCampus = campus != -1;
        return checkYear && checkMajors && checkCampus;
    }
}
