package com.example.sergiosiniy.starbuzzapp;

import android.content.ContentValues;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteException;
import android.database.sqlite.SQLiteOpenHelper;
import android.os.AsyncTask;
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
            Cursor selectedItemCursor = db.query("DRINK",
                    new String[]{"NAME", "DESCRIPTION", "IMAGE_RESOURCE_ID", "FAVORITE"},
                    "_id = ?",
                    new String[]{Integer.toString(drinkNo)},
                    null, null, null);
            //Move to the first record in the Cursor
            if (selectedItemCursor.moveToFirst()) {
                //Get the drink details from the cursor
                String nameText = selectedItemCursor.getString(0);
                String descriptionText = selectedItemCursor.getString(1);
                int photoId = selectedItemCursor.getInt(2);
                boolean isFavorite = (selectedItemCursor.getInt(3)==1);

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
                CheckBox favorite = (CheckBox) findViewById(R.id.favorite_drink_checkbox);
                favorite.setChecked(isFavorite);
            }
            //release resources
            selectedItemCursor.close();
            db.close();
        } catch (SQLiteException e) {
            Toast toast = Toast.makeText(this, "Database unavailable", Toast.LENGTH_SHORT);
            toast.show();
        }
    }
    public void onFavoriteClicked(View view){
        int drinkNo = getIntent().getIntExtra(EXTRA_DRINKNO,0);
        new UpdateDrinkClass().execute(drinkNo);
    }

    /**
     * AsyncTask for sync favorites lists in background.
     *
     */
    private class UpdateDrinkClass extends AsyncTask<Integer, Void, Boolean>{

        ContentValues drinkValues;
        @Override
        protected void onPreExecute(){
            CheckBox favorite = (CheckBox) findViewById(R.id.favorite_drink_checkbox);
            drinkValues=new ContentValues();
            drinkValues.put("FAVORITE", favorite.isChecked());
        }

        @Override
        protected Boolean doInBackground(Integer... drinks) {
            int drinkNo = drinks[0];
            SQLiteOpenHelper starbuzzDbHelper = new StarbuzzDatabaseHelper(DrinkActivity.this);
            try{
                SQLiteDatabase db = starbuzzDbHelper.getWritableDatabase();
                db.update("DRINK",
                        drinkValues,
                        "_id = ?",
                        new String[]{Integer.toString(drinkNo)});
                db.close();
                return true;
            }catch(SQLiteException e){
                return false;
            }

        }

        @Override
        protected void onPostExecute(Boolean result){
            if(!result){
                Toast.makeText(DrinkActivity.this, "No DB connection!", Toast.LENGTH_SHORT).show();
            }
        }
    }
}
