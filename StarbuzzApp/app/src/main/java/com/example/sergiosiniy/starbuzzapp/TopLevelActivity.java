package com.example.sergiosiniy.starbuzzapp;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;

public class TopLevelActivity extends AppCompatActivity {

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

    }





}
