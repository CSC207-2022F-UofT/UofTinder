package com.group80.uoftinder.create_account_use_case;

public class CreateAccountInteractor {
    private static int[] answerSchema; //should be [5, 6, 3] for now (academic answers)
    private static boolean[] isMultiSelect; //should be [false, true, false] for now (academic answers)

    /*
     * Checks if passwords entered match
     * @param password1        first password entered
     * @param password2        second password entered
     * @return                 returns a boolean representing if passwords are exactly the same
     */
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

    /*
     * Returns the number of answers to each question
     * @return       returns an array of booleans representing the number of answers for each question in
     *               the correct order of questions
     */
    public static int[] getAnswerSchema() {
        return answerSchema;
    }

    /*
     * Returns whether each question is mulitselect or not
     * @return       returns an array of booleans representing whether each question is multiselect in
     *               the correct order of questions
     */
    public static boolean[] getIsMultiSelect() {
        return isMultiSelect;
    }
}
