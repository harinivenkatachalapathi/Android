package com.example.AndroidPhotos42;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.graphics.BitmapFactory;
import android.os.Bundle;
import android.widget.ImageView;
import android.widget.TableLayout;
import android.widget.TableRow;
import android.widget.TextView;

import com.example.AndroidPhotos42.model.Photo;

import java.util.List;

public class SearchActivity extends AppCompatActivity {

    public final Context context = this;
    static List<Photo> photos;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_search_results);
        updateList();
    }





    // private void updateList() {
    //     photos = TagsSearchActivity.finalList;
    //     TableLayout entryList = findViewById(R.id.searchList);
    //     entryList.invalidate();
    //     entryList.removeAllViews();

    //     int i = 0;
    //     for (Photo p : photos) {
    //         TableRow row = new TableRow(this);
    //         row.setClickable(true);
    //         row.setId(i++);

    //         row.setPadding(0, 5, 0, 5);
    //         TextView photoLocation = new TextView(this);
    //         photoLocation.setText(p.getLocation());

    //         ImageView image = new ImageView(this);
    //         image.setPadding(5, 0, 5, 0);
    //         image.setImageBitmap(BitmapFactory.decodeFile(p.getLocation()));
    //         row.addView(image);
    //         row.addView(photoLocation);
    //         entryList.addView(row);
    //     }
    //     entryList.refreshDrawableState();

    // }

    private void updateList() {
        photos = TagsSearchActivity.finalList;
        TableLayout entryList = findViewById(R.id.searchList);
        entryList.removeAllViews();

        for (int i = 0; i < photos.size(); i++) {
            TableRow row = createPhotoRow(photos.get(i), i);
            entryList.addView(row);
        }
    }

    private TableRow createPhotoRow(Photo photo, int id) {
        TableRow row = new TableRow(this);
        row.setClickable(true);
        row.setId(id);
        row.setPadding(0, 5, 0, 5);

        TextView photoLocation = new TextView(this);
        photoLocation.setText(photo.getLocation());

        ImageView image = new ImageView(this);
        image.setPadding(5, 0, 5, 0);
        image.setImageBitmap(BitmapFactory.decodeFile(photo.getLocation()));

        row.addView(image);
        row.addView(photoLocation);
        return row;
    }

}