package com.example.AndroidPhotos42;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

public class AddPhotoActivity extends AppCompatActivity {
    TextView addPhotoLabel;
    EditText photoPathEntry;
    Button addPhotoButton;




    private static final String ADD_PHOTO_LABEL_FORMAT = "Add photo to your %s album";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_photo);

        initializeViews();


    }

    private void initializeViews() {
        addPhotoLabel = findViewById(R.id.addPhotoLabel);
        photoPathEntry = findViewById(R.id.photoPathEntry);
        addPhotoButton = findViewById(R.id.addPhotoButton);

        addPhotoButton.setOnClickListener(this::addPhotoToAlbum);
    }



    public void addPhotoToAlbum(View view) {
        String photoPath = photoPathEntry.getText().toString();
        if (!photoPath.isEmpty()) {
            System.out.println("Adding photo at path: " + photoPath);
            // Add logic here to actually add the photo to the album
        } else {
            System.out.println("Photo path is empty.");
        }
}

}
