package com.group80.uoftinder.feed;

import androidx.appcompat.app.AppCompatActivity;
import androidx.coordinatorlayout.widget.CoordinatorLayout;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.NumberPicker;

import com.google.android.material.snackbar.Snackbar;
import com.group80.uoftinder.R;
import com.group80.uoftinder.Constants;
import com.group80.uoftinder.entities.User;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class AcademicFilterActivity extends AppCompatActivity {
    private CoordinatorLayout coordinatorLayout;
    private NumberPicker minAgePicker;
    private NumberPicker maxAgePicker;
    private CheckBox[] programOfStudyBoxes;
    private CheckBox[] yearOfStudyBoxes;
    private CheckBox[] campusBoxes;
    private final String AGE_PICKER_ERROR = "Maximum Age must be greater than or equal to Minimum Age";
    private List<Set<Integer>> filters;
    private int minAge;
    private int maxAge;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_academic_filter);
        coordinatorLayout = findViewById(R.id.coordinatorLayout);

        User currentUser = (User) getIntent().getSerializableExtra(Constants.CURRENT_USER_STRING);
        filters = (List<Set<Integer>>) getIntent().getSerializableExtra(Constants.FILTERS_STRING);
        minAge = getIntent().getIntExtra(Constants.MIN_AGE_STRING, Constants.MIN_AGE);
        maxAge = getIntent().getIntExtra(Constants.MAX_AGE_STRING, Constants.MAX_AGE);

        initializePickers();
        initializeCheckBoxes();
        Button filterButton = findViewById(R.id.filterButton);
        Button resetButton = findViewById(R.id.resetButton);

        filterButton.setOnClickListener(new View.OnClickListener() {
            /**
             * Returns to main page from filter view UI after updating the list
             * of compatible users for the current user via RecommendationInteractor.
             * @param view  Current view
             */
            @Override
            public void onClick(View view) {
                minAge = minAgePicker.getValue();
                maxAge = maxAgePicker.getValue();
                filters = new ArrayList<>();
                filters.add(populateCheckboxValues(yearOfStudyBoxes));
                filters.add(populateCheckboxValues(programOfStudyBoxes));
                filters.add(populateCheckboxValues(campusBoxes));

                // validation to ensure chosen max age is greater than or equal to chosen min age
                if(maxAge < minAge)
                    Snackbar.make(coordinatorLayout, AGE_PICKER_ERROR,Snackbar.LENGTH_LONG).show();

                else {
                    // Go back to the main Recommendation View
                    Intent intent = new Intent(
                            AcademicFilterActivity.this, RecommendationView.class
                    );
                    intent.putExtra(Constants.SHOULD_FILTER_STRING, true);
                    intent.putExtra(Constants.FILTERS_STRING, (Serializable) filters);
                    intent.putExtra(Constants.MIN_AGE_STRING, minAge);
                    intent.putExtra(Constants.MAX_AGE_STRING, maxAge);
                    intent.putExtra(Constants.CURRENT_USER_STRING, currentUser);
                    startActivity(intent);
                }
            }
        });

        resetButton.setOnClickListener(new View.OnClickListener() {
            /**
             * Resets Android Number Picker to default age values defined by constants
             * and uncheck all Android Checkbox objects when the resetButton gets clicked.
             * @param view  Current view
             */
            @Override
            public void onClick(View view) {
                minAgePicker.setValue(Constants.MIN_AGE);
                maxAgePicker.setValue(Constants.MAX_AGE);
                uncheckAllBoxes();
                Intent intent = new Intent(
                        AcademicFilterActivity.this, RecommendationView.class
                );
                intent.putExtra(Constants.SHOULD_FILTER_STRING, false);
                intent.putExtra(Constants.CURRENT_USER_STRING, currentUser);
                startActivity(intent);
            }
        });
    }

    /**
     * Initialize Android Number Picker objects with minimum and maximum ages.
     * Populates picker instance variables with Android Number Picker objects.
     */
    private void initializePickers() {
        minAgePicker = findViewById(R.id.minAgePicker);
        maxAgePicker = findViewById(R.id.maxAgePicker);
        minAgePicker.setMaxValue(Constants.MAX_AGE);
        minAgePicker.setMinValue(Constants.MIN_AGE);
        maxAgePicker.setMaxValue(Constants.MAX_AGE);
        maxAgePicker.setMinValue(Constants.MIN_AGE);
        minAgePicker.setValue(minAge);
        maxAgePicker.setValue(maxAge);
    }

    /**
     * Populate checkboxes instance variables with Android CheckBox objects.
     */
    private void initializeCheckBoxes() {
        CheckBox csBox = findViewById(R.id.computerScienceBox);
        CheckBox mpBox = findViewById(R.id.mathematicalPhysicalSciencesBox);
        CheckBox lifeSciBox = findViewById(R.id.lifeSciencesBox);
        CheckBox socialSciBox = findViewById(R.id.socialSciencesBox);
        CheckBox rotmanBox = findViewById(R.id.rotmanBox);
        CheckBox otherStudyBox = findViewById(R.id.otherStudyBox);
        programOfStudyBoxes = new CheckBox[] {
                csBox, mpBox, lifeSciBox, socialSciBox, rotmanBox, otherStudyBox
        };

        CheckBox y1Box = findViewById(R.id.firstYearBox);
        CheckBox y2Box = findViewById(R.id.secondYearBox);
        CheckBox y3Box = findViewById(R.id.thirdYearBox);
        CheckBox y4Box = findViewById(R.id.fourthYearBox);
        CheckBox graduateBox = findViewById(R.id.graduateBox);
        yearOfStudyBoxes = new CheckBox[]{y1Box, y2Box, y3Box, y4Box, graduateBox};

        CheckBox stGeorgeBox = findViewById(R.id.stGeorgeBox);
        CheckBox mississaugaBox = findViewById(R.id.mississaugaBox);
        CheckBox scarboroughBox = findViewById(R.id.scarboroughBox);
        campusBoxes = new CheckBox[]{stGeorgeBox, mississaugaBox, scarboroughBox};

        if(filters.size() > 0) {
            // existing filters are present
            setCheckboxValues(yearOfStudyBoxes, filters.get(0));
            setCheckboxValues(programOfStudyBoxes, filters.get(1));
            setCheckboxValues(campusBoxes, filters.get(2));
        }
    }

    /**
     * Populates checkboxes based on the values present in the provided set.
     * @param checkboxes    Android Checkbox objects to toggle
     * @param values        Data about which boxes should be toggled
     */
    private void setCheckboxValues(CheckBox[] checkboxes, Set<Integer> values) {
        for(int selected: values) {
            checkboxes[selected].toggle();
        }
    }

    /**
     * Uncheck a box by toggling if the box is currently checked.
     * @param checkBox  Android Checkbox object to uncheck
     */
    private void uncheckBox(CheckBox checkBox) {
        if(checkBox.isChecked())
            checkBox.toggle();
    }

    /**
     * Reset all checkboxes of the Activity to the default unchecked mode.
     */
    private void uncheckAllBoxes() {
        for(CheckBox checkBox: programOfStudyBoxes)
            uncheckBox(checkBox);
        for(CheckBox checkBox: yearOfStudyBoxes)
            uncheckBox(checkBox);
        for(CheckBox checkBox: campusBoxes)
            uncheckBox(checkBox);
    }

    /**
     * Return a set of integers representing user input to filter options
     * represented by checkboxes.
     * Integers included represent the index of the checked checkboxes.
     * For example, {1, 2} represents the second and third checkboxes in
     * checkboxes param were checked.
     * @param checkboxes    A list of Android Checkbox objects
     * @return              A set of integers containing the indices of
     *                      the boxes in checkboxes that were checked.
     */
    private Set<Integer> populateCheckboxValues(CheckBox[] checkboxes) {
        Set<Integer> checkboxValues = new HashSet<>();
        for(int i = 0; i < checkboxes.length; i++) {
            CheckBox checkBox = checkboxes[i];
            if(checkBox.isChecked())
                checkboxValues.add(i);
        }
        return checkboxValues;
    }
}