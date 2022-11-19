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

    private void initializePickers() {
        minAgePicker = findViewById(R.id.minAgePicker);
        maxAgePicker = findViewById(R.id.maxAgePicker);
        minAgePicker.setMaxValue(MAX_AGE);
        minAgePicker.setMinValue(MIN_AGE);
        maxAgePicker.setMaxValue(MAX_AGE);
        maxAgePicker.setMinValue(MIN_AGE);
    }

    private void initializeCheckBoxes() {
        CheckBox csBox = (CheckBox) findViewById(R.id.computerScienceBox);
        CheckBox mpBox = (CheckBox) findViewById(R.id.mathematicalPhysicalSciencesBox);
        CheckBox lifeSciBox = (CheckBox) findViewById(R.id.lifeSciencesBox);
        CheckBox socialSciBox = (CheckBox) findViewById(R.id.socialSciencesBox);
        CheckBox rotmanBox = (CheckBox) findViewById(R.id.rotmanBox);
        CheckBox otherStudyBox = (CheckBox) findViewById(R.id.otherStudyBox);
        programOfStudyBoxes = new CheckBox[]{csBox, mpBox, lifeSciBox, socialSciBox, rotmanBox, otherStudyBox};

        CheckBox y1Box = (CheckBox) findViewById(R.id.firstYearBox);
        CheckBox y2Box = (CheckBox) findViewById(R.id.secondYearBox);
        CheckBox y3Box = (CheckBox) findViewById(R.id.thirdYearBox);
        CheckBox y4Box = (CheckBox) findViewById(R.id.fourthYearBox);
        CheckBox graduateBox = (CheckBox) findViewById(R.id.graduateBox);
        yearOfStudyBoxes = new CheckBox[]{y1Box, y2Box, y3Box, y4Box, graduateBox};

        CheckBox stGeorgeBox = (CheckBox) findViewById(R.id.stGeorgeBox);
        CheckBox mississaugaBox = (CheckBox) findViewById(R.id.mississaugaBox);
        CheckBox scarboroughBox = (CheckBox) findViewById(R.id.scarboroughBox);
        campusBoxes = new CheckBox[]{stGeorgeBox, mississaugaBox, scarboroughBox};
    }

    private void uncheckBox(CheckBox checkBox) {
        if(checkBox.isChecked()){
            checkBox.toggle();
        }
    }

    private Set<Integer> populateCheckboxValues(CheckBox[] checkboxes) {
        Set<Integer> checkboxValues = new HashSet<>();
        for(int i = 0; i < checkboxes.length; i++) {
            CheckBox checkBox = checkboxes[i];
            if (checkBox.isChecked()) {
                checkboxValues.add(i);
            }
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
            @Override
            public void onClick(View view) {
                // TODO: change to FeedActivity class later
                startActivity(new Intent(AcademicFilterActivity.this, HelloWorld.class));
                RecommendationInteractor recommendationInteractor = new RecommendationInteractor();
                int minAge = minAgePicker.getValue();
                int maxAge = maxAgePicker.getValue();
                List<Set<Integer>> filters = new ArrayList<>();
                filters.add(populateCheckboxValues(programOfStudyBoxes));
                filters.add(populateCheckboxValues(yearOfStudyBoxes));
                filters.add(populateCheckboxValues(campusBoxes));
                recommendationInteractor.filterCompatibilityList(filters, minAge, maxAge);
            }
        });

        resetButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                maxAgePicker.setValue(18);
                minAgePicker.setValue(18);
                for(CheckBox checkBox: programOfStudyBoxes)
                    uncheckBox(checkBox);
                for(CheckBox checkBox: yearOfStudyBoxes)
                    uncheckBox(checkBox);
            }
        });
    }
}