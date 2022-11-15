package com.group80.uoftinder;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.NumberPicker;

public class FilterActivity extends AppCompatActivity {

    private NumberPicker minAgePicker;
    private NumberPicker maxAgePicker;
    private NumberPicker programOfStudyPicker;
    private NumberPicker yearOfStudyPicker;

    private final String[] PROGRAM_OF_STUDY = new String[] {
            "Computer Science","Mathematical and Physical Sciences", "Life Sciences",
            "Social Sciences", "Rotman", "Other"
    };
    private final String[] YEAR_OF_STUDY = new String[] {
            "First", "Second", "Third", "Fourth", "Graduate", "Co-op"
    };

    private void initializePickers() {
        minAgePicker = findViewById(R.id.minAgePicker);
        maxAgePicker = findViewById(R.id.maxAgePicker);
        minAgePicker.setMaxValue(100);
        minAgePicker.setMinValue(18);
        maxAgePicker.setMaxValue(100);
        maxAgePicker.setMinValue(18);

        programOfStudyPicker = findViewById(R.id.programOfStudyPicker);
        programOfStudyPicker.setMinValue(0);
        programOfStudyPicker.setMaxValue(PROGRAM_OF_STUDY.length - 1);
        programOfStudyPicker.setDisplayedValues(PROGRAM_OF_STUDY);

        yearOfStudyPicker = findViewById(R.id.yearOfStudyPicker);
        yearOfStudyPicker.setMinValue(0);
        yearOfStudyPicker.setMaxValue(YEAR_OF_STUDY.length - 1);
        yearOfStudyPicker.setDisplayedValues(YEAR_OF_STUDY);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_filter);

        initializePickers();
        Button filterButton = findViewById(R.id.filterButton);
        Button resetButton = findViewById(R.id.resetButton);

        filterButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                // change to FeedActivity class later
                startActivity(new Intent(FilterActivity.this, HelloWorld.class));
                RecommendationInteractor recommendationInteractor = new RecommendationInteractor();
                int minAge = minAgePicker.getValue();
                int maxAge = maxAgePicker.getValue();
                recommendationInteractor.FilterCompatibilityList(minAge, maxAge);
            }
        });

        resetButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                maxAgePicker.setValue(18);
                minAgePicker.setValue(18);
//                yearOfStudyPicker.setValue();

            }
        });
    }
}