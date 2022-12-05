package com.group80.uoftinder;

import android.app.Activity;
import android.content.Intent;
import android.graphics.Bitmap;
import android.net.Uri;
import android.os.Bundle;
import android.provider.MediaStore;
import android.widget.Button;
import android.widget.ImageView;

import androidx.activity.result.ActivityResultLauncher;
import androidx.activity.result.contract.ActivityResultContracts;
import androidx.appcompat.app.AppCompatActivity;
import androidx.cardview.widget.CardView;
import androidx.core.content.res.ResourcesCompat;

import com.group80.uoftinder.feed.RecommendationView;
import com.group80.uoftinder.profile_upload.ProfileUploadPresenter;
import com.group80.uoftinder.profile_upload.ProfileUploadView;

import java.io.IOException;
import java.util.concurrent.atomic.AtomicReference;

/**
 * An activity allowing the user to upload a profile picture, or skip to use the default.
 */
public class ProfilePicUploadActivity extends AppCompatActivity implements ProfileUploadView {
    private ImageView profileImage;

    /**
     * Called when the activity is first created. This is where you should do all of your normal
     * static set up: create views, bind data to lists, etc. This method also provides you with a
     * Bundle containing the activity's previously frozen state, if there was one.
     * <p>
     * Always followed by `onStart()`.
     *
     * @param savedInstanceState If the activity is being re-initialized after previously being
     *                           shut down then this Bundle contains the data it most recently
     *                           supplied in `onSaveInstanceState(Bundle)`.
     *                           <p>
     *                           Note: Otherwise it is null.
     */
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_profile_upload);

        ProfileUploadPresenter presenter = new ProfileUploadPresenter(this);

        // Initialize UI elements
        CardView cardView = findViewById(R.id.profilePicUploadActivityProfileImgCardView);
        Button uploadButton = findViewById(R.id.profileImgUploadActivityUploadButton);
        Button skipButton = findViewById(R.id.profileImgUploadActivitySkipButton);
        profileImage = findViewById(R.id.profileImgUploadActivityProfileImg);

        // Result Launcher for picking image from album
        AtomicReference<Uri> imagePath = new AtomicReference<>();
        ActivityResultLauncher<Intent> launcher = registerForActivityResult(
                new ActivityResultContracts.StartActivityForResult(),
                result -> {
                    if (result.getResultCode() == Activity.RESULT_OK) { // operation succeeded
                        Intent intent = result.getData();

                        Uri imageUri = intent.getData(); // get a uri to the locally stored image
                        if (imageUri != null) {
                            imagePath.set(imageUri);
                            // profileImageView.setImageURI(imagePath.get());
                            presenter.setProfileImage(imageUri);
                        }
                    }
                });

        // Default profile image
        profileImage.setImageDrawable(ResourcesCompat.getDrawable(getResources(), R.drawable.ic_baseline_account_circle_24, null));

        // When clicked, launch the window to let the user select a profile picture. The launcher
        // will save the result (selected image)
        profileImage.setOnClickListener(view -> {
            Intent intent = new Intent(Intent.ACTION_PICK, MediaStore.Images.Media.INTERNAL_CONTENT_URI);
            launcher.launch(intent);
        });

        // Button for uploading the image. `NullPointerException` implies no image set on ImageView.
        uploadButton.setOnClickListener(v -> {
            try {
                presenter.uploadProfileImage(MediaStore.Images.Media.getBitmap(getContentResolver(), imagePath.get()));
                presenter.proceedToNextView(ProfilePicUploadActivity.this, RecommendationView.class, getIntent().getSerializableExtra(Constants.CURRENT_USER_STRING));
            } catch (IOException e) {
                e.printStackTrace();
            } catch (NullPointerException e) { // no file set
                presenter.showEmptyImageMessage(getApplicationContext(), "Profile Image Empty!");
            }
        });

        // Button for skip. No image will be uploaded
        skipButton.setOnClickListener(view -> {
            presenter.proceedToNextView(ProfilePicUploadActivity.this, RecommendationView.class, getIntent().getSerializableExtra(Constants.CURRENT_USER_STRING));
        });
    }

    /**
     * Sets a {@link Bitmap} as the profile picture for display
     *
     * @param bm the bitmap to show
     */
    @Override
    public void showProfileImage(Bitmap bm) {
        profileImage.setImageBitmap(bm);
    }

    /**
     * Sets a linked image as the profile picture for display from an {@link Uri}
     *
     * @param uri the link to the image to show
     */
    @Override
    public void showProfileImage(Uri uri) {
        profileImage.setImageURI(uri);
    }
}
