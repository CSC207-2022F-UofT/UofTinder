package com.group80.uoftinder;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import com.group80.uoftinder.feed.AcademicFilterActivity;

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

        Button resetFilterButton = findViewById(R.id.resetFilterButton);
        resetFilterButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

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