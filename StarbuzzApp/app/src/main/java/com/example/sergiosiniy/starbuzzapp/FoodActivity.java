package com.example.sergiosiniy.starbuzzapp;

import android.content.ContentValues;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteException;
import android.database.sqlite.SQLiteOpenHelper;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.CheckBox;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import static android.support.v7.appcompat.R.styleable.View;

public class FoodActivity extends AppCompatActivity {
    public static final String EXTRA_FOODNO = "foodNo";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_food);

        try {
            int foodNo = getIntent().getIntExtra(EXTRA_FOODNO, 0);
            SQLiteOpenHelper starbuzzDbHelper = new StarbuzzDatabaseHelper(this);
            SQLiteDatabase db = starbuzzDbHelper.getReadableDatabase();
            Cursor selectedItemCursor = db.query("FOOD",
                    new String[]{"NAME, DESCRIPTION, IMAGE_RESOURCE_ID", "FAVORITE"},
                    "_id = ?",
                    new String[]{Integer.toString(foodNo)},
                    null, null, null);

            if (selectedItemCursor.moveToFirst()) {
                boolean isFavorite = (selectedItemCursor.getInt(3) == 1);
                //Populate the drink image
                ImageView photo = (ImageView) findViewById(R.id.food_photo);
                photo.setImageResource(selectedItemCursor.getInt(2));
                photo.setContentDescription(selectedItemCursor.getString(0));

                //Populate the drink name
                TextView name = (TextView) findViewById(R.id.food_name);
                name.setText(selectedItemCursor.getString(0));

                //Populate the drink description
                TextView description = (TextView) findViewById(R.id.food_description);
                description.setText(selectedItemCursor.getString(1));

                //Populate checkbox
                CheckBox favorite = (CheckBox) findViewById(R.id.favorite_food);
                favorite.setChecked(isFavorite);
            }
            //release resources
            selectedItemCursor.close();
            db.close();
        } catch (SQLiteException e) {
            Toast.makeText(this, "No DB connection", Toast.LENGTH_SHORT).show();
        }
    }

    public void onFavoriteClicked(View view) {
        int foodNo = getIntent().getIntExtra(EXTRA_FOODNO, 0);
        CheckBox favorite = (CheckBox) findViewById(R.id.favorite_food);

        ContentValues foodValues = new ContentValues();
        foodValues.put("FAVORITE",favorite.isChecked());

        SQLiteOpenHelper starbuzzDbHelper = new StarbuzzDatabaseHelper(FoodActivity.this);
        try{
            SQLiteDatabase db = starbuzzDbHelper.getWritableDatabase();
            db.update("FOOD",
                    foodValues,
                    "_id = ?",
                    new String[]{Integer.toString(foodNo)});
            db.close();
        }catch (SQLiteException e){
            Toast.makeText(this, "No DB connection!",Toast.LENGTH_SHORT).show();
        }

    }

}

