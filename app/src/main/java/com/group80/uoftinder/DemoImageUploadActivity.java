package com.group80.uoftinder;

import android.app.Activity;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.provider.MediaStore;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.Toast;

import androidx.activity.result.ActivityResultLauncher;
import androidx.activity.result.contract.ActivityResultContracts;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.content.res.ResourcesCompat;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.storage.UploadTask;
import com.group80.uoftinder.firebase.storage.StorageDbUploadable;
import com.group80.uoftinder.firebase.storage.ctStorageController;

import java.io.IOException;

public class DemoImageUploadActivity extends AppCompatActivity implements StorageDbUploadable {
    private ImageView imageView;
    private Uri localImagePath;
    private String url;

    private ActivityResultLauncher<Intent> launcher;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_demo_image_upload);

        /******************************************************************************************/
        FirebaseAuth mAuth = FirebaseAuth.getInstance();
        mAuth.signInWithEmailAndPassword("csc207.group80.uoftinder@gmail.com", "CSC207Group80!")
                .addOnCompleteListener(this, new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {
                        if (task.isSuccessful()) {
                            // Sign in success, update UI with the signed-in user's information
                            FirebaseUser user = mAuth.getCurrentUser();
                        } else {
                            // If sign in fails, display a message to the user.
                            Toast.makeText(getApplicationContext(), "Authentication failed.", Toast.LENGTH_SHORT).show();
                        }
                    }
                });
        /******************************************************************************************/
        launcher = registerForActivityResult(
                new ActivityResultContracts.StartActivityForResult(),
                result -> {
                    if (result.getResultCode() == Activity.RESULT_OK) {
                        Intent intent = result.getData();
                        assert intent != null;
                        localImagePath = intent.getData();
                        imageView.setImageURI(localImagePath);
                    }
                }
        );
        /******************************************************************************************/

        imageView = findViewById(R.id.imageView);
//        imageView.setImageDrawable(getResources().getDrawable(R.drawable.ic_launcher_background));
        imageView.setImageDrawable(ResourcesCompat.getDrawable(getResources(), R.drawable.ic_launcher_background, null));
        imageView.setOnClickListener(view -> {
            Intent intent = new Intent(Intent.ACTION_PICK, MediaStore.Images.Media.INTERNAL_CONTENT_URI);
            launcher.launch(intent);
        });

        Button button = findViewById(R.id.button);
        button.setOnClickListener(view -> {
            String downloadUrl = null;
            ctStorageController controller = new ctStorageController();

            try {
                downloadUrl = controller.uploadProfileImage(this, MediaStore.Images.Media.getBitmap(getContentResolver(), localImagePath));
            } catch (IOException e) {
                e.printStackTrace();
            }
        });
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        if (resultCode == RESULT_OK) {
            localImagePath = data.getData();
            imageView.setImageURI(localImagePath);
        }
        super.onActivityResult(requestCode, resultCode, data);
    }

    @Override
    public void onStorageUploadSuccess(UploadTask.TaskSnapshot taskSnapshot) {
        Toast.makeText(getApplicationContext(), "Image Uploaded!", Toast.LENGTH_SHORT).show();
    }

    @Override
    public void onStorageUploadFailure(@NonNull Exception exception) {
        Toast.makeText(getApplicationContext(), "Image NOT Uploaded!", Toast.LENGTH_SHORT).show();
    }
}