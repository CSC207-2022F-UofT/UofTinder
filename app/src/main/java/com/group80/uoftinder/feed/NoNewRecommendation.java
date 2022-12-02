package com.group80.uoftinder.feed;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import androidx.appcompat.app.AppCompatActivity;
import com.group80.uoftinder.R;
import com.group80.uoftinder.entities.Constants;
import com.group80.uoftinder.entities.User;

import java.io.Serializable;
import java.util.List;
import java.util.Set;

public class NoNewRecommendation extends AppCompatActivity
{
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        // set the view to no new recommendation
        setContentView(R.layout.no_new_recommendation);

        User currentUser = (User) getIntent().getSerializableExtra(Constants.CURRENT_USER_STRING);

        List<Set<Integer>> filters = (List<Set<Integer>>) getIntent().getSerializableExtra(Constants.FILTERS_STRING);
        int minAge = getIntent().getIntExtra(Constants.MIN_AGE_STRING, Constants.MIN_AGE);
        int maxAge = getIntent().getIntExtra(Constants.MAX_AGE_STRING, Constants.MAX_AGE);

        Button filterButton = findViewById(R.id.filterButton);
        filterButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(NoNewRecommendation.this, AcademicFilterActivity.class);
                intent.putExtra(Constants.CURRENT_USER_STRING, currentUser);
                intent.putExtra(Constants.FILTERS_STRING, (Serializable) filters);
                intent.putExtra(Constants.MIN_AGE_STRING, minAge);
                intent.putExtra(Constants.MAX_AGE_STRING, maxAge);
                startActivity(intent);
            }
        });
    }
}
