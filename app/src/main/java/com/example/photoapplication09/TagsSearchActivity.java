package com.example.AndroidPhotos42;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import com.example.AndroidPhotos42.model.Photo;
import com.example.AndroidPhotos42.model.handleFiles;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Set;

public class TagsSearchActivity extends AppCompatActivity {

    public final Context context = this;

    EditText location;
    EditText person;
    Button AndSearch;
    Button OrSearch;
    public static List<Photo> finalList = new ArrayList<>();
    private String type = "and";


    // @Override
    // protected void onCreate(Bundle savedInstanceState) {
    //     super.onCreate(savedInstanceState);
    //     setContentView(R.layout.activity_search_tags);

    //     location = findViewById(R.id.editTextLocation);
    //     person = findViewById(R.id.editTextPerson);
    //     AndSearch = findViewById(R.id.andSearch);
    //     OrSearch = findViewById(R.id.orSearch);

    //     AndSearch.setOnClickListener(new View.OnClickListener(){
    //         @Override
    //         public void onClick(View v){
    //             type = "and";
    //             search();
    //         }
    //     });
    //     OrSearch.setOnClickListener(new View.OnClickListener(){
    //         @Override
    //         public void onClick(View v){
    //             type = "or";
    //             search();
    //         }
    //     });
    // }
    // public void search() {

    //     finalList.clear();
    //     if (type.equals("and") || type.equals("or")) {
    //         String locationValue = location.getText().toString().toLowerCase();
    //         String personValue = person.getText().toString().toLowerCase();

    //         HashMap<String, HashSet<Photo>> Albums = MainActivity.albumsList;
    //         Iterator albumIterator = Albums.entrySet().iterator();

    //         while (albumIterator.hasNext()) {
    //             Map.Entry albumElement = (Map.Entry) albumIterator.next();

    //             String key = (String) albumElement.getKey();
    //             String albumPath = String.format(handleFiles.ALBUM_PATH_FORMAT, key);
    //             List<Photo> photos = handleFiles.readSerializedObjectFromFile(context, albumPath);

    //             for (Photo photo : photos) {
    //                 Set<String> tagNames = photo.getTags().keySet();


    //                 Set<String> locationValues = photo.getTags().get("location");
    //                 Set<String> personValues = photo.getTags().get("person");
    //                 boolean foundInLocation = false;
    //                 if (type.equals("and")) {
    //                     for (String location : locationValues) {
    //                         if (location.indexOf(locationValue) == 0) foundInLocation = true;
    //                     }
    //                     for (String person : personValues) {
    //                         if (person.indexOf(personValue) == 0 && foundInLocation) finalList.add(photo);
    //                     }
    //                 } else {
    //                     for (String location : locationValues) {
    //                         if (location.indexOf(locationValue) == 0) foundInLocation = true;
    //                     }
    //                     for (String person : personValues) {
    //                         // Prevents duplicates from showing
    //                         if (person.indexOf(personValue) == 0 || foundInLocation) finalList.add(photo);
    //                     }

    //                 }
    //             }
    //         }
    //     }


    //     Intent intent = new Intent(TagsSearchActivity.this, SearchActivity.class);
    //     startActivity(intent);
    // }




    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_search_tags);

        initializeViews();
        setupSearchButtons();
    }

    private void initializeViews() {
        location = findViewById(R.id.editTextLocation);
        person = findViewById(R.id.editTextPerson);
        AndSearch = findViewById(R.id.andSearch);
        OrSearch = findViewById(R.id.orSearch);
    }

    private void setupSearchButtons() {
        AndSearch.setOnClickListener(v -> {
            type = "and";
            performSearch();
        });

        OrSearch.setOnClickListener(v -> {
            type = "or";
            performSearch();
        });
    }

    private void performSearch() {
        finalList.clear();
        String locationQuery = location.getText().toString().toLowerCase();
        String personQuery = person.getText().toString().toLowerCase();

        for (Map.Entry<String, HashSet<Photo>> albumEntry : MainActivity.albumsList.entrySet()) {
            String albumPath = String.format(handleFiles.ALBUM_PATH_FORMAT, albumEntry.getKey());
            List<Photo> albumPhotos = handleFiles.readSerializedObjectFromFile(this, albumPath);

            for (Photo photo : albumPhotos) {
                boolean isLocationMatch = photo.getTags().get("location").contains(locationQuery);
                boolean isPersonMatch = photo.getTags().get("person").contains(personQuery);

                if ((type.equals("and") && isLocationMatch && isPersonMatch) ||
                    (type.equals("or") && (isLocationMatch || isPersonMatch))) {
                    finalList.add(photo);
                }
            }
        }

        navigateToSearchResults();
    }

    private void navigateToSearchResults() {
        Intent intent = new Intent(this, SearchActivity.class);
        startActivity(intent);
    }
}











