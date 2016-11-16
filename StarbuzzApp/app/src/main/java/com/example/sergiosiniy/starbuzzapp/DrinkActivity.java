package com.example.sergiosiniy.starbuzzapp;

import android.content.ContentValues;
import android.content.SearchRecentSuggestionsProvider;
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

        int drinkNo = getIntent().getIntExtra(EXTRA_DRINKNO, 0);
        new CreateDrinkActivityClass().execute(drinkNo);
    }

    public void onFavoriteClicked(View view) {
        int drinkNo = getIntent().getIntExtra(EXTRA_DRINKNO, 0);
        new UpdateDrinkFavoritesClass().execute(drinkNo);
    }

    /**
     * AsyncTask for sync favorites lists in background.
     */
    private class UpdateDrinkFavoritesClass extends AsyncTask<Integer, Void, Boolean> {

        ContentValues drinkValues;

        @Override
        protected void onPreExecute() {
            CheckBox favorite = (CheckBox) findViewById(R.id.favorite_drink_checkbox);
            drinkValues = new ContentValues();
            drinkValues.put("FAVORITE", favorite.isChecked());
        }

        @Override
        protected Boolean doInBackground(Integer... drinks) {
            int drinkNo = drinks[0];
            SQLiteOpenHelper starbuzzDbHelper = new StarbuzzDatabaseHelper(DrinkActivity.this);
            try {
                SQLiteDatabase db = starbuzzDbHelper.getWritableDatabase();
                db.update("DRINK",
                        drinkValues,
                        "_id = ?",
                        new String[]{Integer.toString(drinkNo)});
                db.close();
                return true;
            } catch (SQLiteException e) {
                return false;
            }

        }

        @Override
        protected void onPostExecute(Boolean result) {
            if (!result) {
                Toast.makeText(DrinkActivity.this, "No DB connection!", Toast.LENGTH_SHORT).show();
            }
        }
    }

    private class CreateDrinkActivityClass extends AsyncTask<Integer, Void, Boolean> {

        Cursor selectedItemCursor;
        SQLiteDatabase db;

        @Override
        protected Boolean doInBackground(Integer... drinks) {
            SQLiteOpenHelper starbuzzDbHelpder = new StarbuzzDatabaseHelper(DrinkActivity.this);

            try{
                db = starbuzzDbHelpder.getReadableDatabase();
                selectedItemCursor = db.query("DRINK",
                        new String[]{"NAME","DESCRIPTION","IMAGE_RESOURCE_ID", "FAVORITE"},
                        "_id = ?",
                        new String[]{Integer.toString(drinks[0])},
                        null,null,null);
                db.close();
                return true;
            }catch (SQLiteException e){
                return false;
            }

        }

        protected void onPostExecute(Boolean result){
            if(!result){
                Toast.makeText(DrinkActivity.this, "No DB connection!",Toast.LENGTH_SHORT).show();
            }else{
                String nameText = selectedItemCursor.getString(0);
                String descriptionText = selectedItemCursor.getString(1);
                int photoId = selectedItemCursor.getInt(2);
                boolean isFavorite = (selectedItemCursor.getInt(3) == 1);

                if(selectedItemCursor.moveToFirst()){
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
                    selectedItemCursor.close();
                }
            }
        }
    }

}
