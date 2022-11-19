package com.group80.uoftinder;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.NumberPicker;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class AcademicFilterActivity extends AppCompatActivity {

    private NumberPicker minAgePicker;
    private NumberPicker maxAgePicker;
    private CheckBox[] programOfStudyBoxes;
    private CheckBox[] yearOfStudyBoxes;
    private CheckBox[] campusBoxes;
    private final int MIN_AGE = 13;
    private final int MAX_AGE = 100;

    /**
     * Initialize Android Number Picker objects with minimum and maximum ages.
     * Populates picker instance variables with Android Number Picker objects.
     */
    private void initializePickers() {
        minAgePicker = findViewById(R.id.minAgePicker);
        maxAgePicker = findViewById(R.id.maxAgePicker);
        minAgePicker.setMaxValue(MAX_AGE);
        minAgePicker.setMinValue(MIN_AGE);
        maxAgePicker.setMaxValue(MAX_AGE);
        maxAgePicker.setMinValue(MIN_AGE);
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

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_academic_filter);

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
                RecommendationInteractor recommendationInteractor = new RecommendationInteractor();
                int minAge = minAgePicker.getValue();
                int maxAge = maxAgePicker.getValue();
                List<Set<Integer>> filters = new ArrayList<>();
                filters.add(populateCheckboxValues(yearOfStudyBoxes));
                filters.add(populateCheckboxValues(programOfStudyBoxes));
                filters.add(populateCheckboxValues(campusBoxes));
                recommendationInteractor.filterCompatibilityList(filters, minAge, maxAge);
                // TODO: change to RecommendationFeed class later
                startActivity(
                        new Intent(AcademicFilterActivity.this, HelloWorld.class)
                );
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
                maxAgePicker.setValue(MAX_AGE);
                minAgePicker.setValue(MIN_AGE);
                for(CheckBox checkBox: programOfStudyBoxes)
                    uncheckBox(checkBox);
                for(CheckBox checkBox: yearOfStudyBoxes)
                    uncheckBox(checkBox);
                for(CheckBox checkBox: campusBoxes)
                    uncheckBox(checkBox);
            }
        });
    }
}