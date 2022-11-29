package com.group80.uoftinder;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import androidx.appcompat.app.AppCompatActivity;

import com.group80.uoftinder.entities.User;
import com.group80.uoftinder.feed.AcademicFilterActivity;
import com.group80.uoftinder.feed.RecommendationView;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

public class HelloWorld extends AppCompatActivity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.hello_world);

        // For testing the filter functionality
        Button testButton = findViewById(R.id.testButton);
        testButton.setOnClickListener(new View.OnClickListener() {
              @Override
              public void onClick(View view) {
                  startActivity(new Intent(HelloWorld.this, AcademicFilterActivity.class));
              }
        });

        // For testing the recommendationView
        Button recommendationButton = findViewById(R.id.recommendationViewButton);
        recommendationButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                User currTestUser = new User("test");
                List<List<Integer>> userAnswers1 = new ArrayList<>();
                userAnswers1.add(new ArrayList<>(Collections.singletonList(4))); // single
                userAnswers1.add(new ArrayList<>(Collections.singletonList(1))); // single
                userAnswers1.add(new ArrayList<>(Arrays.asList(0, 1))); // multi
                currTestUser.setAnswers(userAnswers1);

//                RecommendationView recView = new RecommendationView(currTestUser);
//                RecommendationPresenter presenter = new RecommendationPresenter(currTestUser, recView);
                Intent intent = new Intent(HelloWorld.this, RecommendationView.class);
                intent.putExtra("currentUser", currTestUser);
                startActivity(intent);
            }
        });


        Button resetFilterButton = findViewById(R.id.resetFilterButton);
        resetFilterButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                // TODO: update to the actual current user object
                User curUser = new User("curUser");
//                RecommendationView recommendationView = new RecommendationView(curUser);
//                RecommendationPresenter recPresenter = new RecommendationPresenter(curUser, recommendationView);
//                recPresenter.revertFilters();
            }
        });

        Button button = findViewById(R.id.helloWorldEnterChatButton);
        button.setOnClickListener(view -> {
            Intent intent = new Intent(HelloWorld.this, ChatActivity.class);
            // TODO: remove such dependency
            intent.putExtra("name", "Bot");
            intent.putExtra("contactUid", "FJuPu9PeQ8TpTPZmDXOVluUCp7c2");
            startActivity(intent);
        });
    }
}