package com.group80.uoftinder;

import java.util.List;

public class UserAccountController {
    // TODO: implement clean architecture, shove methods into here from CreateAccountView
    /*
     * Checks if information entered in createaccountview.xml was entered correctly. Checks if the
     * passwords the user entered matched and if the email entered is not already taken by another
     * account in the database.
     * @param email     a string representing the email the user inputted
     * @param password1 a string representing the first password the user inputted
     * @param password2 a string representing the second password the user inputted
     * @return          returns a boolean representing whether the email is a new email and if the
     *                  passwords match
     */


//    public boolean newAccount(String email, String password1, String password2) {
//        CreateAccountInteractor interactor = new CreateAccountInteractor();
//
//        boolean checked_passwords = interactor.checkPasswords(password1, password2);
//        boolean new_email = interactor.checkEmail(email);
//
//        return checked_passwords && new_email;
//    }


    /**
     * Checks if the information entered in the basicinfoview.xml was entered correctly. Checks if
     * all the fields are non-empty.
     * @param name     a string representing the name the user inputted
     * @param age      a string representing the age the user inputted
     * @param identity a string representing the identity the user chose
     * @param type     a string representing the account type the user chose (only academic for now)
     * @return         returns a boolean representing whether all arguments are non-empty
     */
    public boolean newAccount(String name, String age, String identity, String type) {
        boolean checkName = name.compareTo("")!=0;
        boolean checkAge = age.compareTo("")!=0;
        boolean checkIdentity = identity.compareTo("")!=0;
        boolean checkType = type.compareTo("")!=0;
        return checkName && checkAge && checkIdentity && checkType;
    }
    /**
     *  Checks if the information entered in academic_questionnaire.xml was entered correctly.
     * Checks if all fields are non-empty or if an answer was chosen correctly (i.e. the int given
     * is within the index of possible selected answers.
     * @param year     an int representing the index of the year selected (out of all possible
     *                 years) that the user chose
     * @param majors   List<Integers> representing the index of the majors selected (out of all
     *                 possible majors) that the user chose. List because this question is a
     *                 multi-select question
     * @param campus   an int representing the index of the campus selected (out of all possible
     *                 campuses) that the user chose
     * @return         returns a boolean representing whether all arguments are not 0 or not
     *                 selected
     */
    public boolean finalAccountAcademic(int year, List<Integer> majors, int campus) {
        boolean checkYear = year != -1;
        boolean checkMajors = !(majors.isEmpty());
        boolean checkCampus = campus != -1;
        return checkYear && checkMajors && checkCampus;
    }

    public boolean finalAccountFriendship(int year, List<Integer> majors, int campus, List<Integer>
                                          interests, List<Integer> colours) {
        boolean checkYear = year != -1;
        boolean checkMajors = !(majors.isEmpty());
        boolean checkCampus = campus != -1;
        boolean checkInterests = !(interests.isEmpty());
        boolean checkColours = !(colours.isEmpty());
        return checkYear && checkMajors && checkCampus && checkInterests && checkColours;
    }
}