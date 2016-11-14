package com.example.sergiosiniy.starbuzzapp;

import android.app.ListActivity;
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

    SQLiteDatabase db;
    Cursor cursor;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        try{
            SQLiteOpenHelper starbuzzDbHelper = new StarbuzzDatabaseHelper(this);
            db=starbuzzDbHelper.getReadableDatabase();
            cursor=db.query("FOOD",
                    new String[]{"_id","NAME"},
                    null,null,null,null,null);

            CursorAdapter listAdapter = new SimpleCursorAdapter(this,
                    android.R.layout.simple_list_item_1,
                    cursor,
                    new String[]{"NAME"},
                    new int[]{android.R.id.text1},
                    0);
            getListView().setAdapter(listAdapter);
        }catch (SQLiteException e){
            Toast.makeText(this,"No DB connection!",Toast.LENGTH_SHORT).show();
        }
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
}
