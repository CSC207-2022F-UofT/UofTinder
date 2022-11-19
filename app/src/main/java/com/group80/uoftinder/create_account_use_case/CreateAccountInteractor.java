package com.group80.uoftinder.create_account_use_case;

public class CreateAccountInteractor {
    private static int[] answerSchema;
    private static boolean[] isMultiSelect;

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

    //purely for testing purposes
    public static void setAnswerSchema(int[] aS) {
        answerSchema = aS;
    }

    //purely for testing purposes ANSWERSCHEMA AND GETISMULTISELECT SHOULD NOT BE CHANGED OTHERWISE
    public static void setIsMultiSelect(boolean[] iMS) {
        isMultiSelect = iMS;
    }

    public static int[] getAnswerSchema() {
        return answerSchema;
    }

    public static boolean[] getIsMultiSelect() {
        return isMultiSelect;
    }
}
