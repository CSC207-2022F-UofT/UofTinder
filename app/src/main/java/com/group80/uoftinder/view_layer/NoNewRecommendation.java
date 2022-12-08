package com.group80.uoftinder.view_layer;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import androidx.appcompat.app.AppCompatActivity;

import com.google.firebase.auth.FirebaseAuth;
import com.group80.uoftinder.chat.contacts_list.ContactsActivity;
import com.group80.uoftinder.R;
import com.group80.uoftinder.Constants;
import com.group80.uoftinder.entities_layer.User;
import com.group80.uoftinder.login_use_case.LoginActivity;

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


        Button logoutButton = findViewById(R.id.logoutButton);
        logoutButton.setOnClickListener(new View.OnClickListener() {
            /**
             * Logs out the current logged in user and return to the main Login page.
             * @param view  Current view
             */
            @Override
            public void onClick(View view) {
                FirebaseAuth.getInstance().signOut();
                startActivity(new Intent(NoNewRecommendation.this, LoginActivity.class));
            }
        });

        Button chatButton = findViewById(R.id.chatButton);
        chatButton.setOnClickListener(new View.OnClickListener()  {
            /**
             * Enters the chat page for the current user to chat with their matched users.
             * @param view  Current view
             */
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(NoNewRecommendation.this, ContactsActivity.class);
                intent.putExtra(Constants.CURRENT_USER_STRING, currentUser);
                startActivity(intent);
            }
        });

        Button filterButton = findViewById(R.id.filterButton);
        filterButton.setOnClickListener(new View.OnClickListener() {
            /**
             * Enters the filter page for current user to choose filtering criteria.
             * @param view  Current view
             */
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
