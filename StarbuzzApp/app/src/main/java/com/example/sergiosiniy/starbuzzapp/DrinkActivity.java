package com.example.sergiosiniy.starbuzzapp;

import android.content.ContentValues;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteException;
import android.database.sqlite.SQLiteOpenHelper;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.CheckBox;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

public class DrinkActivity extends AppCompatActivity {
    public static final String EXTRA_DRINKNO = "drinkNo";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_drink);

        //Get a drink from the intent
        try {
            int drinkNo = getIntent().getIntExtra(EXTRA_DRINKNO,0);
            //create DBHelper object
            SQLiteOpenHelper starbuzzDatabaseHelper = new StarbuzzDatabaseHelper(this);
            //get reference of the sqlite db
            SQLiteDatabase db = starbuzzDatabaseHelper.getReadableDatabase();
            //create the cursor with db entries
            Cursor cursor = db.query("DRINK",
                    new String[]{"NAME", "DESCRIPTION", "IMAGE_RESOURCE_ID", "FAVORITE"},
                    "_id = ?",
                    new String[]{Integer.toString(drinkNo)},
                    null, null, null);
            //Move to the first record in the Cursor
            if (cursor.moveToFirst()) {
                //Get the drink details from the cursor
                String nameText = cursor.getString(0);
                String descriptionText = cursor.getString(1);
                int photoId = cursor.getInt(2);
                boolean isFavorite = (cursor.getInt(3)==1);

                //Populate the drink image
                ImageView photo = (ImageView) findViewById(R.id.drink_photo);
                photo.setImageResource(photoId);
                photo.setContentDescription(nameText);

                //Populate the drink name
                TextView name = (TextView) findViewById(R.id.drink_name);
                name.setText(nameText);

                //Populate the drink description
                TextView description = (TextView) findViewById(R.id.drink_description);
                description.setText(descriptionText);

                //Populate the favorite value
                CheckBox favorite = (CheckBox) findViewById(R.id.favorite);
                favorite.setChecked(isFavorite);
            }
            //release resources
            cursor.close();
            db.close();
        } catch (SQLiteException e) {
            Toast toast = Toast.makeText(this, "Database unavailable", Toast.LENGTH_SHORT);
            toast.show();
        }
    }
    public void onFavoriteClicked(View view){
        int drinkNo = getIntent().getIntExtra(EXTRA_DRINKNO,0);
        CheckBox favorite = (CheckBox) findViewById(R.id.favorite);

        ContentValues drinkValues = new ContentValues();
        drinkValues.put("FAVORITE",favorite.isChecked());

        SQLiteOpenHelper starbuzzDatabaseHelper =
                new StarbuzzDatabaseHelper(DrinkActivity.this);
        try {
            SQLiteDatabase db = starbuzzDatabaseHelper.getWritableDatabase();
            db.update("DRINK",
                    drinkValues,
                    "_id = ?",
                    new String[]{Integer.toString(drinkNo)});
        }catch (SQLiteException e){
            Toast.makeText(this,"SQLite DB is unavailable!",Toast.LENGTH_SHORT).show();
        }
    }
}
