package com.example.sergiosiniy.starbuzzapp;

import android.app.ListActivity;
import android.os.AsyncTask;
import android.support.v4.widget.CursorAdapter;
import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteException;
import android.database.sqlite.SQLiteOpenHelper;
import android.os.Bundle;
import android.view.View;
import android.widget.ListView;
import android.support.v4.widget.SimpleCursorAdapter;
import android.widget.Toast;

public class FoodCategoryActivity extends ListActivity {

    private SQLiteDatabase db;
    private Cursor cursor;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        new ListViewHandler().execute();
    }

    @Override
    public void onListItemClick(ListView listView,
                                View itemView,
                                int position,
                                long id) {
        Intent intent = new Intent(FoodCategoryActivity.this, FoodActivity.class)
                .putExtra(FoodActivity.EXTRA_FOODNO, (int) id);
        startActivity(intent);
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        cursor.close();
        db.close();
    }

    private class ListViewHandler extends AsyncTask<Void, Void, Boolean> {

        CursorAdapter listAdapter;

        @Override
        protected Boolean doInBackground(Void... s) {
            SQLiteOpenHelper starbuzzDbHelper =
                    new StarbuzzDatabaseHelper(FoodCategoryActivity.this);
            try {

                db = starbuzzDbHelper.getReadableDatabase();
                cursor = db.query("FOOD",
                        new String[]{"_id", "NAME"},
                        null, null, null, null, null);

                listAdapter = new SimpleCursorAdapter(FoodCategoryActivity.this,
                        android.R.layout.simple_list_item_1,
                        cursor,
                        new String[]{"NAME"},
                        new int[]{android.R.id.text1},
                        0);

                return true;
            } catch (SQLiteException e) {
                return false;
            }
        }

        @Override
        protected void onPostExecute(Boolean result) {
            if(!result){
                Toast toast = Toast.makeText(FoodCategoryActivity.this, "Database unavailable",
                        Toast.LENGTH_SHORT);
                toast.show();
            }else{
                ListView listOfDrinks = getListView();
                listOfDrinks.setAdapter(listAdapter);
            }
        }
    }
}
