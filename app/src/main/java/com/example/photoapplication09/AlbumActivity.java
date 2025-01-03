package com.example.AndroidPhotos42;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;

import android.Manifest;
import android.app.AlertDialog;
import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.database.Cursor;
import android.graphics.BitmapFactory;
import android.graphics.Color;
import android.net.Uri;
import android.os.Bundle;
import android.provider.MediaStore;
import android.view.View;
import android.widget.ImageView;
import android.widget.TableLayout;
import android.widget.TableRow;
import android.widget.TextView;
import android.widget.Toast; // Import Toast

import com.example.AndroidPhotos42.model.Photo;
import com.example.AndroidPhotos42.model.handleFiles;

import java.io.File;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

public class AlbumActivity extends AppCompatActivity {
    TextView albumNameUser;

    public static String albumName;
    public final Context context = this;
    Uri currrentImageURI;

    static List<Photo> photos;
    View selected;
    // private Uri currentImageURI;



    // private void updateList() {
    //     photos = handleFiles.readSerializedObjectFromFile(this, String.format(handleFiles.ALBUM_PATH_FORMAT, albumName));
    //     TableLayout entryList = findViewById(R.id.entryList);
    //     entryList.invalidate();
    //     entryList.removeAllViews();

    //     int i = 0;
    //     for (Photo p : photos) {
    //         TableRow row = new TableRow(this);
    //         row.setClickable(true);
    //         row.setId(i++);
    //         row.setOnClickListener(new View.OnClickListener() {
    //             private void resetBackgroundColors(int idToSkip, TableLayout table) {
    //                 int tableSize = table.getChildCount();
    //                 for (int i = 0; i < tableSize; i++) {
    //                     if (i != idToSkip) table.getChildAt(i).setBackgroundColor(Color.TRANSPARENT);
    //                 }
    //             }

    //             @Override
    //             public void onClick(View view) {
    //                 view.setBackgroundColor(Color.LTGRAY);
    //                 resetBackgroundColors(view.getId(), (TableLayout) view.getParent());
    //                 selected = view;
    //             }
    //         });
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
    // @Override
    // protected void onCreate(Bundle savedInstanceState) {
    //     super.onCreate(savedInstanceState);
    //     setContentView(R.layout.activity_album);
    //     albumNameUser = (TextView) findViewById(R.id.AlbumName);
    //     Bundle bundle = getIntent().getExtras();
    //     if (bundle != null) {
    //         albumNameUser.setText(bundle.getString("AlbumName"));
    //         albumName = albumNameUser.getText().toString();
    //     }
    //     updateList();
    // }

    // public void addPhotoToApp(View view) {
    //     try {
    //         if (ActivityCompat.checkSelfPermission(AlbumActivity.this, Manifest.permission.READ_EXTERNAL_STORAGE) != PackageManager.PERMISSION_GRANTED) {
    //             ActivityCompat.requestPermissions(AlbumActivity.this, new String[]{Manifest.permission.READ_EXTERNAL_STORAGE, Manifest.permission.WRITE_EXTERNAL_STORAGE}, 0);
    //         } else {
    //             AlertDialog.Builder builder = new AlertDialog.Builder(this);
    //             builder.setTitle("Add Photo");
    //             Intent intent = new Intent(Intent.ACTION_PICK,
    //                     android.provider.MediaStore.Images.Media.EXTERNAL_CONTENT_URI);
    //             startActivityForResult(intent, 0);


    //             TableLayout entryList = findViewById(R.id.entryList);
    //             updateList();
    //         }
    //     } catch (Exception e) {
    //         e.printStackTrace();
    //     }
    // }

    public void removePhotoFromApp(View view) {
        if (selected != null && selected.getId() < photos.size()) {
            String albumFileName = String.format(handleFiles.ALBUM_PATH_FORMAT, albumName);
            photos.remove(selected.getId());
            handleFiles.writeSerializedObjectToFile(this, photos, albumFileName);
            updateList();
        } else {
            Toast.makeText(this, "No photo selected or invalid selection", Toast.LENGTH_SHORT).show();
        }
    }

    public void changeToSlideshowActivity(View view) {
        Intent intent = new Intent(this, SlideshowActivity.class);
        intent.putExtra("album", albumName);
        int selectedIndex = selected != null ? selected.getId() : 0;
        intent.putExtra("index", selectedIndex);
        startActivity(intent);
    }


    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {

        super.onActivityResult(requestCode, resultCode, data);
        if (resultCode == RESULT_OK) {


            currrentImageURI = data.getData();
            String textInput = getRealPathFromURI(currrentImageURI);
            File file = new File(textInput);
            String albumFileName;
            if (file.isFile()) {
                try {
                    albumFileName = String.format(handleFiles.ALBUM_PATH_FORMAT, albumName);
                    Calendar cal = Calendar.getInstance();
                    cal.set(Calendar.MILLISECOND, 0);

                    Photo photo = new Photo(textInput, new Date(file.lastModified()));
                    List<Photo> photosInAlbum = handleFiles.readSerializedObjectFromFile(context, albumFileName);
                    photosInAlbum.add(photo);
                    handleFiles.writeSerializedObjectToFile(context, photosInAlbum, albumFileName);
                } catch (Exception e) {
                    String msg = "Error writing to file";
                    throw new RuntimeException(msg, e);
                }
            } else {
                handleFiles.displayAlert(context, "Error", "Photo not found");
            }


        }
        TableLayout entryList = findViewById(R.id.entryList);
        updateList();
    }

public String getRealPathFromURI(Uri uri) {
    String result = null;
    Cursor cursor = getContentResolver().query(uri, null, null, null, null);
    if (cursor != null) {
        if (cursor.moveToFirst()) {
            int idx = cursor.getColumnIndex(MediaStore.Images.ImageColumns.DATA);
            result = cursor.getString(idx);
        }
        cursor.close();
    }
    return result;
}





    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_album);

        initializeViews();
        retrieveExtrasAndDisplayAlbumName();
        updateList();
    }

    private void initializeViews() {
        albumNameUser = findViewById(R.id.AlbumName);
    }

    private void retrieveExtrasAndDisplayAlbumName() {
        Bundle bundle = getIntent().getExtras();
        if (bundle != null) {
            albumName = bundle.getString("AlbumName");
            albumNameUser.setText(albumName);
        }
    }

    private void updateList() {
        photos = handleFiles.readSerializedObjectFromFile(this, String.format(handleFiles.ALBUM_PATH_FORMAT, albumName));
        displayPhotosInTable();
    }

    private void displayPhotosInTable() {
        TableLayout entryList = findViewById(R.id.entryList);
        entryList.removeAllViews();

        for (int i = 0; i < photos.size(); i++) {
            Photo photo = photos.get(i);
            TableRow row = createTableRowForPhoto(photo, i);
            entryList.addView(row);
        }
    }

    private TableRow createTableRowForPhoto(Photo photo, int id) {
        TableRow row = new TableRow(this);
        row.setClickable(true);
        row.setId(id);
        row.setOnClickListener(this::onPhotoClick);
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

    private void onPhotoClick(View view) {
        resetBackgroundColors(view.getId());
        view.setBackgroundColor(Color.LTGRAY);
        selected = view;
    }

    private void resetBackgroundColors(int idToSkip) {
        TableLayout table = findViewById(R.id.entryList);
        for (int i = 0; i < table.getChildCount(); i++) {
            if (i != idToSkip) table.getChildAt(i).setBackgroundColor(Color.TRANSPARENT);
        }
    }

    public void addPhotoToApp(View view) {
        if (ActivityCompat.checkSelfPermission(this, Manifest.permission.READ_EXTERNAL_STORAGE) != PackageManager.PERMISSION_GRANTED) {
            ActivityCompat.requestPermissions(this, new String[]{Manifest.permission.READ_EXTERNAL_STORAGE, Manifest.permission.WRITE_EXTERNAL_STORAGE}, 0);
        } else {
            choosePhotoFromGallery();
        }
    }

    private void choosePhotoFromGallery() {
        Intent intent = new Intent(Intent.ACTION_PICK, MediaStore.Images.Media.EXTERNAL_CONTENT_URI);
        startActivityForResult(intent, 0);
    }




    



    
}