package com.group80.uoftinder.feed;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import androidx.appcompat.app.AppCompatActivity;
import com.group80.uoftinder.R;
import com.group80.uoftinder.entities.User;

public class NoNewRecommendation extends AppCompatActivity
{
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        // set the view to no new recommendation
        setContentView(R.layout.no_new_recommendation);

        User currentUser = (User) getIntent().getSerializableExtra("currentUser");

        Button filterButton = findViewById(R.id.filterButton);
        filterButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(NoNewRecommendation.this, AcademicFilterActivity.class);
                intent.putExtra("currentUser", currentUser);
                startActivity(intent);
            }
        });
    }
}
