package com.example.sergiosiniy.starbuzzapp;

import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteException;
import android.database.sqlite.SQLiteOpenHelper;
import android.support.v4.widget.CursorAdapter;
import android.support.v4.widget.SimpleCursorAdapter;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.Toast;

public class TopLevelActivity extends AppCompatActivity {

    private SQLiteDatabase db;
    private Cursor favoriteDrinksCursor;
    private Cursor favoriteFoodsCursor;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_top_level);
        AdapterView.OnItemClickListener listViewListener = new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> listView, View itemView, int position, long id) {

                switch(position){
                    case 0:
                        Intent drinkStart = new Intent(TopLevelActivity.this, DrinkCategoryActivity.class);
                        startActivity(drinkStart);
                        break;
                    case 1:
                        Intent foodStart = new Intent(TopLevelActivity.this, FoodCategoryActivity.class);
                        startActivity(foodStart);
                        break;
                }

            }
        };
        ListView listView = (ListView) findViewById(R.id.list_options);
        listView.setOnItemClickListener(listViewListener);

        //Populate the list_favorites ListView from a cursor
        ListView listFavoriteDrinks = (ListView)findViewById(R.id.list_favorite_drinks);
        ListView listFavoriteFoods = (ListView)findViewById(R.id.list_favorite_foods);


        try{
            SQLiteOpenHelper starbuzzDatabaseHelper = new StarbuzzDatabaseHelper(this);
            db = starbuzzDatabaseHelper.getReadableDatabase();

            //Set adapter for favorite drinks list
            favoriteDrinksCursor = db.query("DRINK",
                    new String[] { "_id", "NAME"},
                    "FAVORITE = 1",
                    null, null, null, null);
            CursorAdapter favoriteDrinksAdapter =
                    new SimpleCursorAdapter(TopLevelActivity.this,
                            android.R.layout.simple_list_item_1,
                            favoriteDrinksCursor,
                            new String[]{"NAME"},
                            new int[]{android.R.id.text1}, 0);
            listFavoriteDrinks.setAdapter(favoriteDrinksAdapter);

            //Set adapter for favorite foods list
            favoriteFoodsCursor = db.query("FOOD",
                    new String[] { "_id", "NAME"},
                    "FAVORITE = 1",
                    null, null, null, null);
            CursorAdapter favoriteFoodsAdapter =
                    new SimpleCursorAdapter(TopLevelActivity.this,
                            android.R.layout.simple_list_item_1,
                            favoriteFoodsCursor,
                            new String[]{"NAME"},
                            new int[]{android.R.id.text1}, 0);
            listFavoriteFoods.setAdapter(favoriteFoodsAdapter);

        } catch(SQLiteException e) {
            Toast toast = Toast.makeText(this, "Database unavailable", Toast.LENGTH_SHORT);
            toast.show();
        }
        //Navigate to DrinkActivity if a drink is clicked
        listFavoriteDrinks.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> listView, View v, int position, long id)
            {
                Intent intent = new Intent(TopLevelActivity.this, DrinkActivity.class);
                intent.putExtra(DrinkActivity.EXTRA_DRINKNO, (int)id);
                startActivity(intent);
            }
        });
        //Navigate to FoodActivity if a food is clicked
        listFavoriteFoods.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> listView, View v, int position, long id)
            {
                Intent intent = new Intent(TopLevelActivity.this, FoodActivity.class);
                intent.putExtra(FoodActivity.EXTRA_FOODNO, (int)id);
                startActivity(intent);
            }
        });
    }

    /**
     * We use onCreate() method to initialize our favorite lists when activity was created
     * and started.
     * But when activity is paused or stopped it doesn't refreshes the favorite lists after resuming
     * because it is not destroyed and onCreate() method is not called.
     * So we need do that in this method for updating our favorite lists using DB changes.
     */
    public void onRestart() {
        super.onRestart();
        try{
            StarbuzzDatabaseHelper starbuzzDatabaseHelper = new StarbuzzDatabaseHelper(this);
            db = starbuzzDatabaseHelper.getReadableDatabase();

            //refresh Drinks favorite list
            Cursor drinkCursor = db.query("DRINK",
                    new String[] { "_id", "NAME"},
                    "FAVORITE = 1",
                    null, null, null, null);
            ListView listFavoriteDrinks = (ListView)findViewById(R.id.list_favorite_drinks);
            CursorAdapter adapterDrinks = (CursorAdapter) listFavoriteDrinks.getAdapter();
            adapterDrinks.changeCursor(drinkCursor);
            favoriteDrinksCursor = drinkCursor;

            //refresh Foods favorite list
            Cursor foodCursor = db.query("FOOD",
                    new String[] { "_id", "NAME"},
                    "FAVORITE = 1",
                    null, null, null, null);
            ListView listFavoriteFoods = (ListView)findViewById(R.id.list_favorite_foods);
            CursorAdapter adapterFoods = (CursorAdapter) listFavoriteFoods.getAdapter();
            adapterFoods.changeCursor(foodCursor);
            favoriteFoodsCursor = foodCursor;

        } catch(SQLiteException e) {
            Toast toast = Toast.makeText(this, "Database unavailable", Toast.LENGTH_SHORT);
            toast.show();
        }
    }

    //Close the cursor and database in the onDestroy() method
    @Override
    public void onDestroy(){
        super.onDestroy();
        favoriteDrinksCursor.close();
        favoriteFoodsCursor.close();
        db.close();
    }
}








