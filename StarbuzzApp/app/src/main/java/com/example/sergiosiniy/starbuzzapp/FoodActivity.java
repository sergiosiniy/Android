package com.example.sergiosiniy.starbuzzapp;

import android.content.ContentValues;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteException;
import android.database.sqlite.SQLiteOpenHelper;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.CheckBox;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

public class FoodActivity extends AppCompatActivity {
    public static final String EXTRA_FOODNO = "foodNo";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_food);

        int foodNo = getIntent().getIntExtra(EXTRA_FOODNO, 0);
        new CreateFoodActivityClass().execute(foodNo);
    }

    public void onFavoriteClicked(View view) {
        int foodNo = getIntent().getIntExtra(EXTRA_FOODNO, 0);
        new UpdateFoodFavoritesClass().execute(foodNo);

    }

    private class CreateFoodActivityClass extends AsyncTask<Integer, Void, Boolean> {

        Cursor selectedItemCursor;
        SQLiteDatabase db;

        @Override
        protected Boolean doInBackground(Integer... foods) {
            SQLiteOpenHelper starbuzzDbHelper = new StarbuzzDatabaseHelper(FoodActivity.this);
            try {
                db = starbuzzDbHelper.getReadableDatabase();
                selectedItemCursor = db.query("FOOD",
                        new String[]{"NAME", "DESCRIPTION", "IMAGE_RESOURCE_ID", "FAVORITE"},
                        "_id = ?",
                        new String[]{Integer.toString(foods[0])},
                        null, null, null);
                return true;
            } catch (SQLiteException e) {
                return false;
            }
        }

        protected void onPostExecute(Boolean result) {
            if (!result) {
                Toast.makeText(FoodActivity.this, "No DB connection!", Toast.LENGTH_SHORT).show();
            } else {
                if (selectedItemCursor.moveToFirst()) {
                    String foodName = selectedItemCursor.getString(0);
                    String foodDescr = selectedItemCursor.getString(1);
                    int imgRes = selectedItemCursor.getInt(2);
                    boolean isFavorite = (selectedItemCursor.getInt(3) == 1);
                    //Populate the drink image
                    ImageView photo = (ImageView) findViewById(R.id.food_photo);
                    photo.setImageResource(imgRes);
                    photo.setContentDescription(foodName);

                    //Populate the drink name
                    TextView name = (TextView) findViewById(R.id.food_name);
                    name.setText(foodName);

                    //Populate the drink description
                    TextView description = (TextView) findViewById(R.id.food_description);
                    description.setText(foodDescr);

                    //Populate checkbox
                    CheckBox favorite = (CheckBox) findViewById(R.id.favorite_food_checkbox);
                    favorite.setChecked(isFavorite);
                }

            }
            //release resources
            selectedItemCursor.close();
            db.close();
        }
    }

    private class UpdateFoodFavoritesClass extends AsyncTask<Integer, Void, Boolean> {

        ContentValues foodValues;

        @Override
        protected void onPreExecute(){
            CheckBox favorite = (CheckBox) findViewById(R.id.favorite_food_checkbox);
            foodValues = new ContentValues();
            foodValues.put("FAVORITE", favorite.isChecked());
        }

        @Override
        protected Boolean doInBackground(Integer... foods) {

            SQLiteOpenHelper starbuzzDbHelper = new StarbuzzDatabaseHelper(FoodActivity.this);

            try{
                SQLiteDatabase db = starbuzzDbHelper.getWritableDatabase();
                db.update("FOOD",
                        foodValues,
                        "_id = ?",
                        new String[]{Integer.toString(foods[0])});
                db.close();
                return true;
            }catch (SQLiteException e){
                return false;
            }
        }
    }


    }

