package com.group80.uoftinder.create_account_use_case;

public class CreateAccountInteractor {

    public boolean checkPasswords(String password1, String password2) {
        return password1.compareTo(password2) == 0;
    }

    public boolean checkEmail(String email) {
        if (email.compareTo("")==0) {
            return false;
        }
//        firebase stuff
//        if email hasn't already been used in firebase
        return true;
//        if email is already in use
//        return false;

    }

    public static int[] getAnswerSchema() {
        return new int[]{5, 6, 3};
    }

    public static boolean[] getIsMultiSelect() {
        return new boolean[]{false, true, false};
    }
}
