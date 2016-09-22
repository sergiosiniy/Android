package com.example.sergiosiniy.starbuzzapp;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.ImageView;
import android.widget.TextView;

public class DrinkActivity extends AppCompatActivity {
    public static final String EXTRA_DRINKNO = "drinkNo";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_drink);

        //Get the drink from the intent
        int drinkNo = (Integer) getIntent().getExtras().get(EXTRA_DRINKNO);
        Drink drink = Drink.drinks[drinkNo];

        //Populate the drink image
        ImageView photo = (ImageView) findViewById(R.id.drink_photo);
        photo.setImageResource(drink.getImageResourceId());
        photo.setContentDescription(drink.getName());

        //Populate the drink name
        TextView name = (TextView) findViewById(R.id.drink_name);
        name.setText(drink.getName());

        //Populate the drink description
        TextView description = (TextView) findViewById(R.id.drink_description);
        description.setText(drink.getDescription());
    }
}
