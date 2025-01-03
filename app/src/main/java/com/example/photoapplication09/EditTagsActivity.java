package com.example.AndroidPhotos42;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.Toast;

import com.example.AndroidPhotos42.model.Photo;
import com.example.AndroidPhotos42.model.handleFiles;

import java.util.ArrayList;
import java.util.List;

public class EditTagsActivity extends AppCompatActivity {
    List<Photo> photos;
    int photoIndex;
    String album;
    public final Context context = this;

    ListView persons;
    ListView locations;
    Button addPersonButton;
    Button addLocationButton;
    Button removePersonButton;
    Button removeLocationButton;
    // @Override

    // protected void onCreate(Bundle savedInstanceState) {
    //     super.onCreate(savedInstanceState);
    //     setContentView(R.layout.activity_edit_tags);

    //     persons =(ListView)findViewById(R.id.persons);
    //     locations =(ListView)findViewById(R.id.locations);
    //     addPersonButton = (Button)findViewById(R.id.addPersonButton);
    //     addLocationButton = (Button)findViewById(R.id.addLocationButton);
    //     removePersonButton = (Button)findViewById(R.id.removePersonButton);
    //     removeLocationButton = (Button)findViewById(R.id.removeLocationButton);


    //     photos = AlbumActivity.photos;
    //     Bundle bundle = getIntent().getExtras();
    //     if (bundle != null) {

    //         i = bundle.getInt("index");
    //         album = bundle.getString("album");
    //         refreshAlbums();
    //     }

    //     addPersonButton.setOnClickListener(new View.OnClickListener(){
    //         @Override
    //         public void onClick(View v){
    //             AlertDialog.Builder mBuilder = new AlertDialog.Builder(EditTagsActivity.this);
    //             final View mView = getLayoutInflater().inflate(R.layout.dialogs, null);
    //             final EditText albumNameUser = (EditText) mView.findViewById(R.id.editAlbumName);
    //             Button mAddAlbum = (Button) mView.findViewById(R.id.AddAlbum);

    //             mBuilder.setView(mView);
    //             final AlertDialog dialog = mBuilder.create();
    //             dialog.show();

    //             mAddAlbum.setOnClickListener(new View.OnClickListener() {
    //                 @Override
    //                 public void onClick(View v){
    //                     if(!albumNameUser.getText().toString().isEmpty() && !AlbumActivity.photos.get(i).getTags().get("person").contains(albumNameUser.getText().toString())){
    //                         AlbumActivity.photos.get(i).addTag("person", albumNameUser.getText().toString());
    //                         dialog.dismiss();

    //                     } else {
    //                         Toast.makeText(EditTagsActivity.this, "Error Creating", Toast.LENGTH_SHORT).show();
    //                     }
    //                     refreshAlbums();
    //                 }
    //             });
    //             refreshAlbums();
    //         }
    //     });

    //     addLocationButton.setOnClickListener(new View.OnClickListener(){
    //         @Override
    //         public void onClick(View v){
    //             AlertDialog.Builder mBuilder = new AlertDialog.Builder(EditTagsActivity.this);
    //             final View mView = getLayoutInflater().inflate(R.layout.dialogs, null);
    //             final EditText albumNameUser = (EditText) mView.findViewById(R.id.editAlbumName);
    //             Button mAddAlbum = (Button) mView.findViewById(R.id.AddAlbum);

    //             mBuilder.setView(mView);
    //             final AlertDialog dialog = mBuilder.create();
    //             dialog.show();

    //             mAddAlbum.setOnClickListener(new View.OnClickListener() {
    //                 @Override
    //                 public void onClick(View v){
    //                     if(!albumNameUser.getText().toString().isEmpty() && !AlbumActivity.photos.get(i).getTags().get("location").contains(albumNameUser.getText().toString())){
    //                         AlbumActivity.photos.get(i).addTag("location", albumNameUser.getText().toString());
    //                         dialog.dismiss();

    //                     } else {
    //                         Toast.makeText(EditTagsActivity.this, "Error Creating", Toast.LENGTH_SHORT).show();
    //                     }
    //                     refreshAlbums();
    //                 }
    //             });
    //             refreshAlbums();

    //         }
    //     });

    //     removeLocationButton.setOnClickListener(new View.OnClickListener(){
    //         @Override
    //         public void onClick(View v){
    //             AlertDialog.Builder mBuilder = new AlertDialog.Builder(EditTagsActivity.this);
    //             final View mView = getLayoutInflater().inflate(R.layout.remove_dialogs, null);
    //             final EditText albumNameUser = (EditText) mView.findViewById(R.id.removeAlbumName);
    //             Button mAddAlbum = (Button) mView.findViewById(R.id.removeAlbum);

    //             mBuilder.setView(mView);
    //             final AlertDialog dialog = mBuilder.create();
    //             dialog.show();

    //             mAddAlbum.setOnClickListener(new View.OnClickListener() {
    //                 @Override
    //                 public void onClick(View v){
    //                     if(!albumNameUser.getText().toString().isEmpty() && AlbumActivity.photos.get(i).getTags().get("location").contains(albumNameUser.getText().toString())){
    //                         AlbumActivity.photos.get(i).deleteTagValue("location", albumNameUser.getText().toString());
    //                         dialog.dismiss();

    //                     } else {
    //                         Toast.makeText(EditTagsActivity.this, "Error Creating", Toast.LENGTH_SHORT).show();
    //                     }
    //                     refreshAlbums();
    //                 }
    //             });
    //             refreshAlbums();

    //         }
    //     });

    //     removePersonButton.setOnClickListener(new View.OnClickListener(){
    //         @Override
    //         public void onClick(View v){
    //             AlertDialog.Builder mBuilder = new AlertDialog.Builder(EditTagsActivity.this);
    //             final View mView = getLayoutInflater().inflate(R.layout.remove_dialogs, null);
    //             final EditText albumNameUser = (EditText) mView.findViewById(R.id.removeAlbumName);
    //             Button mAddAlbum = (Button) mView.findViewById(R.id.removeAlbum);

    //             mBuilder.setView(mView);
    //             final AlertDialog dialog = mBuilder.create();
    //             dialog.show();

    //             mAddAlbum.setOnClickListener(new View.OnClickListener() {
    //                 @Override
    //                 public void onClick(View v){
    //                     if(!albumNameUser.getText().toString().isEmpty() && AlbumActivity.photos.get(i).getTags().get("person").contains(albumNameUser.getText().toString())){
    //                         AlbumActivity.photos.get(i).deleteTagValue("person", albumNameUser.getText().toString());
    //                         dialog.dismiss();

    //                     } else {
    //                         Toast.makeText(EditTagsActivity.this, "Error Creating", Toast.LENGTH_SHORT).show();
    //                     }
    //                     refreshAlbums();
    //                 }
    //             });
    //             refreshAlbums();

    //         }
    //     });






    // }

    // private void refreshAlbums(){
    //     List<String> listOfPersons = new ArrayList<String>(AlbumActivity.photos.get(i).getTags().get("person"));
    //     ArrayAdapter arrayAdapter = new ArrayAdapter(this, android.R.layout.simple_list_item_1,listOfPersons);
    //     persons.setAdapter(arrayAdapter);

    //     List<String> listOfLocations = new ArrayList<String>(AlbumActivity.photos.get(i).getTags().get("location"));
    //     ArrayAdapter arrayAdapter2 = new ArrayAdapter(this, android.R.layout.simple_list_item_1,listOfLocations);
    //     locations.setAdapter(arrayAdapter2);

    //     String albumFileName = String.format(handleFiles.ALBUM_PATH_FORMAT, album);
    //     handleFiles.writeSerializedObjectToFile(context, AlbumActivity.photos, albumFileName);

    // }

    // private void AddPersonView(View view){

    // }





    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_edit_tags);

        initializeViews();
        retrieveIntentData();
        refreshAlbums();
    }

    private void initializeViews() {
        persons = findViewById(R.id.persons);
        locations = findViewById(R.id.locations);
        addPersonButton = findViewById(R.id.addPersonButton);
        addLocationButton = findViewById(R.id.addLocationButton);
        removePersonButton = findViewById(R.id.removePersonButton);
        removeLocationButton = findViewById(R.id.removeLocationButton);

        addPersonButton.setOnClickListener(v -> showTagDialog("person"));
        addLocationButton.setOnClickListener(v -> showTagDialog("location"));
        removePersonButton.setOnClickListener(v -> removeTagDialog("person"));
        removeLocationButton.setOnClickListener(v -> removeTagDialog("location"));
    }

    private void retrieveIntentData() {
        Bundle bundle = getIntent().getExtras();
        if (bundle != null) {
            photoIndex = bundle.getInt("index");
            album = bundle.getString("album");
            photos = AlbumActivity.photos;
        }
    }

    private void showTagDialog(String tagType) {
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        View dialogView = getLayoutInflater().inflate(R.layout.dialogs, null);
        final EditText tagInput = dialogView.findViewById(R.id.editAlbumName);
        Button addTagButton = dialogView.findViewById(R.id.AddAlbum);

        builder.setView(dialogView);
        final AlertDialog dialog = builder.create();
        dialog.show();

        addTagButton.setOnClickListener(v -> {
            String tagValue = tagInput.getText().toString();
            if (!tagValue.isEmpty()) {
                addTagToPhoto(tagType, tagValue);
                dialog.dismiss();
            } else {
                Toast.makeText(this, "Tag cannot be empty", Toast.LENGTH_SHORT).show();
            }
        });
    }

    private void removeTagDialog(String tagType) {
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        View dialogView = getLayoutInflater().inflate(R.layout.remove_dialogs, null);
        final EditText tagInput = dialogView.findViewById(R.id.removeAlbumName);
        Button removeTagButton = dialogView.findViewById(R.id.removeAlbum);

        builder.setView(dialogView);
        final AlertDialog dialog = builder.create();
        dialog.show();

        removeTagButton.setOnClickListener(v -> {
            String tagValue = tagInput.getText().toString();
            if (!tagValue.isEmpty()) {
                removeTagFromPhoto(tagType, tagValue);
                dialog.dismiss();
            } else {
                Toast.makeText(this, "Tag cannot be empty", Toast.LENGTH_SHORT).show();
            }
        });
    }

    private void addTagToPhoto(String tagType, String tagValue) {
        Photo selectedPhoto = photos.get(photoIndex);
        selectedPhoto.addTag(tagType, tagValue);
        refreshAlbums();
    }

    private void removeTagFromPhoto(String tagType, String tagValue) {
        Photo selectedPhoto = photos.get(photoIndex);
        selectedPhoto.deleteTagValue(tagType, tagValue);
        refreshAlbums();
    }

    private void refreshAlbums() {
        ArrayAdapter<String> personAdapter = new ArrayAdapter<>(this, android.R.layout.simple_list_item_1, new ArrayList<>(photos.get(photoIndex).getTags().get("person")));
        persons.setAdapter(personAdapter);

        ArrayAdapter<String> locationAdapter = new ArrayAdapter<>(this, android.R.layout.simple_list_item_1, new ArrayList<>(photos.get(photoIndex).getTags().get("location")));
        locations.setAdapter(locationAdapter);

        savePhotoChanges();
    }

    private void savePhotoChanges() {
        String albumFileName = String.format(handleFiles.ALBUM_PATH_FORMAT, album);
        handleFiles.writeSerializedObjectToFile(this, photos, albumFileName);
    }



}