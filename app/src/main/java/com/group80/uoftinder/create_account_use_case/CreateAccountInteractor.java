package com.group80.uoftinder.create_account_use_case;

// Use case layer

import android.text.TextUtils;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.group80.uoftinder.entities.User;
import com.group80.uoftinder.firebase.firestore.FirestoreDbWriter;
import com.group80.uoftinder.firebase.realtime.UserRealtimeDbFacade;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class CreateAccountInteractor extends AppCompatActivity implements CreateAccountInput {
    final CreateAccountPresenterInterface createAccountPresenter;

    public CreateAccountInteractor(CreateAccountPresenterInterface createAccountPresenter) {
        this.createAccountPresenter = createAccountPresenter;
    }

    /**
     * Returns the number of answers to each question
     *
     * @return returns an array of integers representing the number of answers for each
     * question in the correct order of questions
     */
    public static int[] getAnswerSchema(String type) {
        if (type.equals("Academic")) {
            return new int[]{5, 6, 3};
        } else if (type.equals("Friendship")) {
            return new int[]{5, 6, 3, 8, 8};
        } else if (type.equals("Romantic")) {
            return new int[]{6, 6, 3, 8, 3, 3};
        }

        return null;
    }

    /**
     * Returns whether each question is mulitselect or not
     *
     * @return returns an array of booleans representing whether each question is multiselect
     * in the correct order of questions
     */
    public static boolean[] getIsMultiSelect(String type) {
        if (type.equals("Academic")) {
            return new boolean[]{false, true, false};
        } else if (type.equals("Friendship")) {
            return new boolean[]{false, true, false, true, true};
        } else if (type.equals("Romantic")) {
            return new boolean[]{false, true, false, true, false, false};
        }

        return null;
    }

    /**
     * Checks if the inputs to email, password1, and password2 are valid,
     * if not, error message pops up
     * If the inputs are valid, check if account can be created and change the UI for user to
     * set up their basic information
     *
     * @param email     email input
     * @param password1 password input
     * @param password2 re-type password input
     */
    public void createAccount(String email, String password1, String password2) {
        if (TextUtils.isEmpty(email)) {
            createAccountPresenter.prepareEmailMissingView("Email is missing!");
        } else if (TextUtils.isEmpty(password1)) {
            createAccountPresenter.preparePassword1MissingView("Password is required!");
        } else if (TextUtils.isEmpty(password2)) {
            createAccountPresenter.preparePassword2MissingView("Password is required!");
        } else if (!password1.equals(password2)) {
            createAccountPresenter.preparePasswordMatchError("Passwords do not match!");
        } else {
            FirebaseAuth.getInstance().createUserWithEmailAndPassword(email, password1)
                    .addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                        @Override
                        public void onComplete(@NonNull Task<AuthResult> task) {
                            if (task.isSuccessful()) {
                                FirebaseUser firebaseUser = FirebaseAuth.getInstance().getCurrentUser();
                                assert firebaseUser != null;
                                User currentUser = new User(firebaseUser.getUid());

                                createAccountPresenter.prepareSuccessView(
                                        "User registered successfully :D",
                                        currentUser);
                            } else {
                                createAccountPresenter.prepareCreateAccountFailureView(
                                        "Registration Failed :(");
                            }
                        }
                    });
        }
    }

    /**
     * Sets up user's basic information and saves to the current user,
     * changes the UI to the questionnaire according to the user's input to type
     *
     * @param userName    name
     * @param userAge     age
     * @param identity    identity
     * @param type        type of account the user wishes to create
     * @param currentUser current user
     */
    public void setBasicInfo(String userName, String userAge, String identity, String type,
                             User currentUser) {
        boolean moveOn = checkBasicInput(userName, userAge, identity, type);
        if (moveOn) {
            //sets information for the currentUser
            currentUser.setName(userName);
            currentUser.setAge(Integer.parseInt(userAge));
            currentUser.setGender(identity);
            currentUser.setUserType(type);

            if (type.compareTo("Academic") == 0) {
                createAccountPresenter.createAcademicQuestionnaire(currentUser);
            } else if (type.compareTo("Friendship") == 0) {
                createAccountPresenter.createFriendshipQuestionnaire(currentUser);
            } else if (type.compareTo("Romantic") == 0) {
                createAccountPresenter.createRomanticQuestionnaire(currentUser);
            }
        } else {
            createAccountPresenter.prepareCreateAccountFailureView(
                    "Please enter your information correctly!");
        }
    }

    /**
     * Sets up user's academic information and saves to the current user,
     * uploads user to the database,
     * changes UI to recommendation view if information was correctly
     *
     * @param currentUser current user
     * @param year        integer of academic year
     * @param majors      list of integers corresponding to program major inputs
     * @param campus      integer corresponding to campus input
     */
    public void setAcademicInfo(User currentUser, int year, List<Integer> majors, int campus) {
        //checks all questions have an answer
        boolean moveOn = checkAcademicInput(year, majors, campus);

        if (moveOn) {
            //adds answers user selected to answers
            List<List<Integer>> answers = new ArrayList<>();

            answers.add(Collections.singletonList(year));
            answers.add(majors);
            answers.add(Collections.singletonList(campus));
            currentUser.setAnswers((answers));
            //store User into realtime database
            UserRealtimeDbFacade.uploadUser(currentUser);
            // stores User into Firebase Firestore
            FirestoreDbWriter.uploadUser(currentUser);

            createAccountPresenter.prepareProfilePicUploadActivity(currentUser);
        } else {
            createAccountPresenter.prepareCreateAccountFailureView(
                    "Please enter your information correctly!");
        }
    }

    /**
     * Sets up user's academic information and saves to the current user,
     * uploads user to the database,
     * changes UI to recommendation view if information was correctly
     *
     * @param currentUser current user
     * @param year        integer of academic year
     * @param majors      list of integers corresponding to program major inputs
     * @param campus      integer corresponding to campus input
     * @param interests   list of integers corresponding to interest inputs
     * @param colours     list of integers corresponding to colour inputs
     */
    @Override
    public void setFriendshipInfo(User currentUser, int year, List<Integer> majors, int campus,
                                  List<Integer> interests, List<Integer> colours) {
        //checks all questions have an answer
        boolean moveOn = checkFriendshipInput(year, majors, campus, interests, colours);
        if (moveOn) {
            List<List<Integer>> answers = new ArrayList<>();

            //adds answers user selected to answers
            answers.add(Collections.singletonList(year));
            answers.add(majors);
            answers.add(Collections.singletonList(campus));
            answers.add(interests);
            answers.add(colours);
            currentUser.setAnswers((answers));
            //store User into realtime database
            UserRealtimeDbFacade.uploadUser(currentUser);
            // stores User into Firebase Firestore
            FirestoreDbWriter.uploadUser(currentUser);

            createAccountPresenter.prepareProfilePicUploadActivity(currentUser);
        } else {
            createAccountPresenter.prepareCreateAccountFailureView(
                    "Please enter your information correctly!");
        }
    }

    /**
     * Sets up user's romantic information and saves to the current user,
     * uploads user to the database,
     * changes UI to recommendation view if information was correctly
     *
     * @param currentUser  current user
     * @param sexuality    integer corresponding to sexuality selected
     * @param majors       list of integers corresponding to program major inputs
     * @param campus       integer corresponding to campus input
     * @param interests    list of integers corresponding to interest inputs
     * @param distance     integer corresponding to distance selected
     * @param relationship integer corresponding to type of relationship selected
     */
    @Override
    public void setRomanticInfo(User currentUser, int sexuality, List<Integer> majors, int campus,
                                List<Integer> interests, int distance, int relationship) {
        //checks all questions have an answer
        boolean moveOn = checkRomanticInput(sexuality, majors, campus, interests,
                distance, relationship);

        if (moveOn) {
            List<List<Integer>> answers = new ArrayList<>();

            //adds answers user selected to answers
            answers.add(Collections.singletonList(sexuality));
            answers.add(majors);
            answers.add(Collections.singletonList(campus));
            answers.add(interests);
            answers.add(Collections.singletonList(distance));
            answers.add(Collections.singletonList(relationship));
            currentUser.setAnswers((answers));
            //store User into realtime database
            UserRealtimeDbFacade.uploadUser(currentUser);
            // stores User into Firebase Firestore
            FirestoreDbWriter.uploadUser(currentUser);

            createAccountPresenter.prepareProfilePicUploadActivity(currentUser);
        } else {
            createAccountPresenter.prepareCreateAccountFailureView("Please enter your information correctly!");
        }

    }

    /**
     * Checks if the information entered in the basicinfoview.xml was entered correctly. Checks if
     * all the fields are non-empty.
     *
     * @param name     a string representing the name the user inputted
     * @param age      a string representing the age the user inputted
     * @param identity a string representing the identity the user chose
     * @param type     a string representing the account type the user chose (only academic for now)
     * @return returns a boolean representing whether all arguments are non-empty
     */
    @Override
    public boolean checkBasicInput(String name, String age, String identity, String type) {
        boolean checkName = name.compareTo("") != 0;
        boolean checkAge = age.compareTo("") != 0;
        boolean checkIdentity = identity.compareTo("") != 0;
        boolean checkType = type.compareTo("") != 0;
        return checkName && checkAge && checkIdentity && checkType;
    }

    /**
     * Checks if the information entered in academic_questionnaire.xml was entered correctly.
     * Checks if all fields are non-empty or if an answer was chosen correctly (i.e. the int given
     * is within the index of possible selected answers.
     *
     * @param year   an int representing the index of the year selected (out of all possible
     *               years) that the user chose
     * @param majors List<Integers> representing the index of the majors selected (out of all
     *               possible majors) that the user chose. List because this question is a
     *               multi-select question
     * @param campus an int representing the index of the campus selected (out of all possible
     *               campuses) that the user chose
     * @return returns a boolean representing whether all arguments are not 0 or not
     * selected
     */
    public boolean checkAcademicInput(int year, List<Integer> majors, int campus) {
        boolean checkYear = year != -1;
        boolean checkMajors = !(majors.isEmpty());
        boolean checkCampus = campus != -1;
        return checkYear && checkMajors && checkCampus;
    }

    /**
     * Checks if the information entered in friendship_questionnaire.xml was entered correctly.
     * Checks if all fields are non-empty or if an answer was chosen correctly (i.e. the int given
     * is within the index of possible selected answers.
     *
     * @param year      an int representing the index of the year selected (out of all possible
     *                  years) that the user chose
     * @param majors    List<Integers> representing the index of the majors selected (out of all
     *                  possible majors) that the user chose. List because this question is a
     *                  multi-select question
     * @param campus    an int representing the index of the campus selected (out of all possible
     *                  campuses) that the user chose
     * @param interests List<Integers> representing the index of the interests selected (out of all
     *                  possible interests) that the user chose. List because this question is a
     *                  multi-select question
     * @param colours   List<Integers> representing the index of the colours selected (out of all
     *                  possible colours) that the user chose. List because this question is a
     *                  multi-select question
     * @return returns a boolean representing whether all arguments are not 0 or not
     * selected
     */
    public boolean checkFriendshipInput(int year, List<Integer> majors, int campus, List<Integer>
            interests, List<Integer> colours) {
        boolean checkYear = year != -1;
        boolean checkMajors = !(majors.isEmpty());
        boolean checkCampus = campus != -1;
        boolean checkInterests = !(interests.isEmpty());
        boolean checkColours = !(colours.isEmpty());
        return checkYear && checkMajors && checkCampus && checkInterests && checkColours;
    }

    /**
     * Checks if the information entered in romantic_questionnaire.xml was entered correctly.
     * Checks if all fields are non-empty or if an answer was chosen correctly (i.e. the int given
     * is within the index of possible selected answers.
     *
     * @param sexuality    an int representing the index of the sexuality selected (out of all possible
     *                     years) that the user chose
     * @param majors       List<Integers> representing the index of the majors selected (out of all
     *                     possible majors) that the user chose. List because this question is a
     *                     multi-select question
     * @param campus       an int representing the index of the campus selected (out of all possible
     *                     campuses) that the user chose
     * @param interests    List<Integers> representing the index of the interests selected (out of all
     *                     possible interests) that the user chose. List because this question is a
     *                     multi-select question
     * @param distance     an int representing the index of whether user is willing to have a long
     *                     distance relationship selected (out of all possible choices) that user chose
     * @param relationship an int representing the index of type of relationship selected
     *                     (out of all possible choices) that user chose
     * @return returns a boolean representing whether all arguments are not 0 or not
     * selected
     */
    public boolean checkRomanticInput(int sexuality, List<Integer> majors, int campus,
                                      List<Integer> interests, int distance, int relationship) {
        boolean checkYear = sexuality != -1;
        boolean checkMajors = !(majors.isEmpty());
        boolean checkCampus = campus != -1;
        boolean checkInterests = !(interests.isEmpty());
        boolean checkDistance = distance != -1;
        boolean checkRelationship = relationship != -1;
        return checkYear && checkMajors && checkCampus && checkInterests && checkDistance &&
                checkRelationship;
    }
}