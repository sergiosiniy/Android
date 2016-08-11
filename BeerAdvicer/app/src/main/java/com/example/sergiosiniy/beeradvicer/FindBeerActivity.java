package com.example.sergiosiniy.beeradvicer;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Spinner;
import android.widget.TextView;

public class FindBeerActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_find_beer);
    }

    public void onClickFindBeer(View view){
        BeerExpert beerExpert=new BeerExpert();
        StringBuffer sb = new StringBuffer();
        Spinner color = (Spinner) findViewById(R.id.color);

        for(String brand:beerExpert.getBrands(String.valueOf(color.getSelectedItem()))){
            sb.append(brand+"\n");
        }

        TextView beerType = (TextView) findViewById(R.id.brands);
        beerType.setText(sb);
    }

}
