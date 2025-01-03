package com.example.AndroidPhotos42;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
import android.graphics.BitmapFactory;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Toast;

import com.example.AndroidPhotos42.model.Photo;
import com.example.AndroidPhotos42.model.handleFiles;

import java.util.List;

public class SlideshowActivity extends AppCompatActivity {
    List<Photo> photos;
    String album;
    Button remove;
    Button move;
    ImageView image;
    public final Context context = this;
    int i;

    private void refreshPhoto() {
        image.setImageBitmap(BitmapFactory.decodeFile(photos.get(i).getLocation()));
    }






    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_slideshow);

        move = findViewById(R.id.moveToAlbum);
        image = findViewById(R.id.imageView);
        photos = AlbumActivity.photos;
        i = 0;
        refreshPhoto();

        Bundle bundle = getIntent().getExtras();
        if (bundle != null) album = (bundle.getString("album"));


        move.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v){
                AlertDialog.Builder mBuilder = new AlertDialog.Builder(SlideshowActivity.this);
                final View mView = getLayoutInflater().inflate(R.layout.move_to_album, null);
                final EditText albumNameUser = (EditText) mView.findViewById(R.id.editAlbumName);
                Button mAddAlbum = (Button) mView.findViewById(R.id.AddAlbum);

                mBuilder.setView(mView);
                final AlertDialog dialog = mBuilder.create();
                dialog.show();

                mAddAlbum.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v){

                        if(!albumNameUser.getText().toString().isEmpty() && MainActivity.albumsList.containsKey(albumNameUser.getText().toString())){

                            String albumFileNameNew = String.format(handleFiles.ALBUM_PATH_FORMAT, albumNameUser.getText().toString());
                            List<Photo> photosInNewAlbum = handleFiles.readSerializedObjectFromFile(context, albumFileNameNew);
                            photosInNewAlbum.add(AlbumActivity.photos.get(i));
                            handleFiles.writeSerializedObjectToFile(context,photosInNewAlbum, albumFileNameNew);
                            String albumFileNameOld = String.format(handleFiles.ALBUM_PATH_FORMAT, AlbumActivity.albumName);
                            List<Photo> photosInOldAlbum = handleFiles.readSerializedObjectFromFile(context, albumFileNameOld);

                            int z = 0;
                            int index = 0;
                            for(Photo p : photosInOldAlbum){

                                if(p.getLocation().equals(AlbumActivity.photos.get(i).getLocation())) {
                                    index = z;
                                    break;
                                }

                                z++;
                            }

                            photosInOldAlbum.remove(index);
                            handleFiles.writeSerializedObjectToFile(context, photosInOldAlbum, albumFileNameOld);
                            Intent intent = new Intent(SlideshowActivity.this, MainActivity.class);
                            startActivity(intent);

                        } else {

                            Toast.makeText(SlideshowActivity.this, "Error", Toast.LENGTH_SHORT).show();
                        }
                    }
                });
            }
        });

    }

    // public void prevPhoto(View view) {

    //     if (--i < 0) i = photos.size() - 1;
    //     refreshPhoto();
    // }

    // public void nextPhoto(View view) {

    //     if (++i >= photos.size()) i = 0;
    //     refreshPhoto();
    // }

    // public void changeToEditTagsActivity(View view) {

    //     Intent intent = new Intent(SlideshowActivity.this, EditTagsActivity.class);
    //     intent.putExtra("index",i);
    //     intent.putExtra("album",album);

    //     startActivity(intent);


    // }






    // @Override
    // protected void onCreate(Bundle savedInstanceState) {
    //     super.onCreate(savedInstanceState);
    //     setContentView(R.layout.activity_slideshow);

    //     initializeViews();
    //     retrieveIntentData();
    //     displayCurrentPhoto();

    //     move.setOnClickListener(v -> showMoveToAlbumDialog());
    // }

    // private void initializeViews() {
    //     move = findViewById(R.id.moveToAlbum);
    //     image = findViewById(R.id.imageView);
    // }

    // private void retrieveIntentData() {
    //     Bundle bundle = getIntent().getExtras();
    //     if (bundle != null) {
    //         album = bundle.getString("album");
    //         photos = AlbumActivity.photos;  // Assuming this is populated
    //         i = 0;
    //     }
    // }

    private void displayCurrentPhoto() {
        if (!photos.isEmpty()) {
            image.setImageBitmap(BitmapFactory.decodeFile(photos.get(i).getLocation()));
        }
    }

    // private void showMoveToAlbumDialog() {
    //     AlertDialog.Builder builder = new AlertDialog.Builder(this);
    //     View dialogView = getLayoutInflater().inflate(R.layout.move_to_album, null);
    //     final EditText newAlbumNameEditText = dialogView.findViewById(R.id.editAlbumName);
    //     Button confirmButton = dialogView.findViewById(R.id.AddAlbum);

    //     builder.setView(dialogView);
    //     AlertDialog dialog = builder.create();
    //     dialog.show();

    //     confirmButton.setOnClickListener(v -> movePhotoToNewAlbum(newAlbumNameEditText.getText().toString(), dialog));
    // }

    // private void movePhotoToNewAlbum(String newAlbumName, AlertDialog dialog) {
    //     if (!newAlbumName.isEmpty() && MainActivity.albumsList.containsKey(newAlbumName)) {
    //         Photo photo = photos.get(i);
    //         handleFiles.movePhotoToAlbum(this, album, newAlbumName, photo);
    //         dialog.dismiss();
    //         startActivity(new Intent(this, MainActivity.class));
    //     } else {
    //         Toast.makeText(this, "Invalid album name", Toast.LENGTH_SHORT).show();
    //     }
    // }

    public void prevPhoto(View view) {
        if (--i < 0) i = photos.size() - 1;
        displayCurrentPhoto();
    }

    public void nextPhoto(View view) {
        if (++i >= photos.size()) i = 0;
        displayCurrentPhoto();
    }

    public void changeToEditTagsActivity(View view) {
        Intent intent = new Intent(this, EditTagsActivity.class);
        intent.putExtra("index", i);
        intent.putExtra("album", album);
        startActivity(intent);
    }






}


