package ua.kiev.home.sergiosiniy.cookyourself;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import java.util.ArrayList;

public class ReadyForCook extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_ready_for_cook);

    }

    public void borschRecipe(View view){

    }

    private void openRecipe(){
        Intent recipe = new Intent(this,Recipe.class);
        startActivity(recipe);
    }



}
