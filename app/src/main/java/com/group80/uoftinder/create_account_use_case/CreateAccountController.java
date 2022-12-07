package com.group80.uoftinder.create_account_use_case;

import android.widget.EditText;

import com.google.android.material.chip.Chip;
import com.google.android.material.chip.ChipGroup;
import com.group80.uoftinder.entities.User;

import java.util.LinkedList;
import java.util.List;
// Interface adapter layer
/**
 * Responsible for responding to user interaction when they input email, passwords, and
 * answer questionnaires
 */
public class CreateAccountController {
    final CreateAccountInput createAccountInput;

    public CreateAccountController(CreateAccountInput createAccountInput) {
        this.createAccountInput = createAccountInput;
    }

    public void createAccount(EditText createAccountEmail, EditText createAccountPassword1,
                              EditText createAccountPassword2) {
        String email = createAccountEmail.getText().toString().trim();
        String password1 = createAccountPassword1.getText().toString().trim();
        String password2 = createAccountPassword2.getText().toString().trim();

        createAccountInput.createAccount(email, password1, password2);
    }

    /**
     * Groups the inputs of the user when setting up their basic information into more accessible
     * datatype, passes information to the interactor
     *
     * @param currentUser   the current user trying to register
     * @param userNameT     name input of the user
     * @param userAgeT      age input of the user
     * @param identityGroup identity the user selects
     * @param typeGroup     type of account the user selects to create
     */
    public void setBasicInfo(User currentUser, EditText userNameT, EditText userAgeT,
                             ChipGroup identityGroup, ChipGroup typeGroup) {

        String userName = userNameT.getText().toString().trim();
        String userAge = userAgeT.getText().toString();

        int identity_count = identityGroup.getChildCount();
        String identity = "";

        //loops through all chips(answers) of identity answers and finds which chip was
        //selected
        for (int i = 0; i < identity_count; i++) {
            Chip chip = (Chip) identityGroup.getChildAt(i);
            if (chip.isChecked()) {
                identity = chip.getText().toString();
            }
        }
        //loops through all chips(answers) of type answers and finds which chip was
        //selected
        int type_count = typeGroup.getChildCount();
        String type = "";
        for (int i = 0; i < type_count; i++) {
            Chip chip = (Chip) typeGroup.getChildAt(i);
            if (chip.isChecked()) {
                type = chip.getText().toString();
            }
        }

        createAccountInput.setBasicInfo(userName, userAge, identity, type, currentUser);
    }

    /**
     * Groups the inputs of the user when setting up their academic information into more accessible
     * datatype, passes information to the interactor
     *
     * @param currentUser the current user trying to register
     * @param yearGroup   academic year the user selects
     * @param majorGroup  program majors the user selects
     * @param campusGroup campus the user selects
     */
    public void setAcademicInfo(User currentUser, ChipGroup yearGroup, ChipGroup majorGroup,
                                ChipGroup campusGroup) {
        int yearCount = yearGroup.getChildCount();
        int year = -1; //sets year to -1 (user did not select a chip)
        //loops through all chips(answers) of year answers and finds which chip was
        //selected
        for (int i = 0; i < yearCount; i++) {
            Chip chip = (Chip) yearGroup.getChildAt(i);
            if (chip.isChecked()) {
                year = i;
            }
        }
        //loops through all chips(answers) of majors answers and finds the chips that were
        //selected
        int majorCount = majorGroup.getChildCount();
        List<Integer> majors = new LinkedList<>();
        for (int i = 0; i < majorCount; i++) {
            Chip chip = (Chip) majorGroup.getChildAt(i);
            if (chip.isChecked()) {
                majors.add(i);
            }
        }

        //loops through all chips(answers) of campus answers and finds which chip was
        //selected
        int campusCount = campusGroup.getChildCount();
        int campus = -1;
        for (int i = 0; i < campusCount; i++) {
            Chip chip = (Chip) campusGroup.getChildAt(i);
            if (chip.isChecked()) {
                campus = i;
            }
        }
        createAccountInput.setAcademicInfo(currentUser, year, majors, campus);
    }

    /**
     * Groups the inputs of the user when setting up their friendship information into
     * more accessible datatype, passes information to the interactor
     *
     * @param currentUser    the current user trying to register
     * @param yearGroup      academic year the user selects
     * @param majorGroup     program majors the user selects
     * @param campusGroup    campus the user selects
     * @param interestsGroup interests the user selects
     * @param colourGroup    colours the user selects
     */
    public void setFriendshipInfo(User currentUser, ChipGroup yearGroup, ChipGroup majorGroup,
                                  ChipGroup campusGroup, ChipGroup interestsGroup, ChipGroup colourGroup) {
        int yearCount = yearGroup.getChildCount();
        int year = -1; //sets year to -1 (user did not select a chip)
        //loops through all chips(answers) of year answers and finds which chip was
        //selected
        for (int i = 0; i < yearCount; i++) {
            Chip chip = (Chip) yearGroup.getChildAt(i);
            if (chip.isChecked()) {
                year = i;
            }
        }
        //loops through all chips(answers) of majors answers and finds the chips that were
        //selected
        int majorCount = majorGroup.getChildCount();
        List<Integer> majors = new LinkedList<>();
        for (int i = 0; i < majorCount; i++) {
            Chip chip = (Chip) majorGroup.getChildAt(i);
            if (chip.isChecked()) {
                majors.add(i);
            }
        }

        //loops through all chips(answers) of campus answers and finds which chip was
        //selected
        int campusCount = campusGroup.getChildCount();
        int campus = -1;
        for (int i = 0; i < campusCount; i++) {
            Chip chip = (Chip) campusGroup.getChildAt(i);
            if (chip.isChecked()) {
                campus = i;
            }
        }

        int interestsCount = interestsGroup.getChildCount();
        List<Integer> interests = new LinkedList<>();
        for (int i = 0; i < interestsCount; i++) {
            Chip chip = (Chip) interestsGroup.getChildAt(i);
            if (chip.isChecked()) {
                interests.add(i);
            }
        }

        int colourCount = colourGroup.getChildCount();
        List<Integer> colours = new LinkedList<>();
        for (int i = 0; i < colourCount; i++) {
            Chip chip = (Chip) colourGroup.getChildAt(i);
            if (chip.isChecked()) {
                colours.add(i);
            }
        }
        createAccountInput.setFriendshipInfo(currentUser, year, majors, campus, interests, colours);
    }

    /**
     * Groups the inputs of the user when setting up their romantic information into more accessible
     * datatype, passes information to the interactor
     *
     * @param currentUser       the current user trying to register
     * @param sexualityGroup    sexuality the user selects
     * @param majorGroup        program majors the user selects
     * @param campusGroup       campus the user selects
     * @param interestsGroup    interests the user selects
     * @param distanceGroup     if user is willing to do long distance relationship
     * @param relationshipGroup seriousness of relationship user is looking for
     */
    public void setRomanticInfo(User currentUser, ChipGroup sexualityGroup, ChipGroup majorGroup,
                                ChipGroup campusGroup, ChipGroup interestsGroup,
                                ChipGroup distanceGroup, ChipGroup relationshipGroup) {
        int sexualityCount = sexualityGroup.getChildCount();
        int sexuality = -1;
        for (int i = 0; i < sexualityCount; i++) {
            Chip chip = (Chip) sexualityGroup.getChildAt(i);
            if (chip.isChecked()) {
                sexuality = i;
            }
        }
        //loops through all chips(answers) of majors answers and finds the chips that were
        //selected
        int majorCount = majorGroup.getChildCount();
        List<Integer> majors = new LinkedList<>();
        for (int i = 0; i < majorCount; i++) {
            Chip chip = (Chip) majorGroup.getChildAt(i);
            if (chip.isChecked()) {
                majors.add(i);
            }
        }

        //loops through all chips(answers) of campus answers and finds which chip was
        //selected
        int campusCount = campusGroup.getChildCount();
        int campus = -1;
        for (int i = 0; i < campusCount; i++) {
            Chip chip = (Chip) campusGroup.getChildAt(i);
            if (chip.isChecked()) {
                campus = i;
            }
        }

        int interestsCount = interestsGroup.getChildCount();
        List<Integer> interests = new LinkedList<>();
        for (int i = 0; i < interestsCount; i++) {
            Chip chip = (Chip) interestsGroup.getChildAt(i);
            if (chip.isChecked()) {
                interests.add(i);
            }
        }

        int distanceCount = distanceGroup.getChildCount();
        int distance = -1;
        for (int i = 0; i < distanceCount; i++) {
            Chip chip = (Chip) distanceGroup.getChildAt(i);
            if (chip.isChecked()) {
                distance = i;
            }
        }

        int relationshipCount = relationshipGroup.getChildCount();
        int relationship = -1;
        for (int i = 0; i < relationshipCount; i++) {
            Chip chip = (Chip) relationshipGroup.getChildAt(i);
            if (chip.isChecked()) {
                relationship = i;
            }
        }
        createAccountInput.setRomanticInfo(currentUser, sexuality, majors, campus, interests,
                distance, relationship);
    }
}