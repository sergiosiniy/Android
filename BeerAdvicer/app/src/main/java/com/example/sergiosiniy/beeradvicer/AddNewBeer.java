package com.example.sergiosiniy.beeradvicer;

import android.content.Intent;
import android.net.Uri;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;

public class AddNewBeer extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_new_beer);
    }

    public void sendMessage(View view){
        EditText beerType = (EditText)findViewById(R.id.beer_type_edit_text);
        EditText beerBrand = (EditText)findViewById(R.id.brand_edit_text);

        Uri adminMail = Uri.parse("mailto:sergio.siniy@gmail.com");

        Intent sendMsg = new Intent (Intent.ACTION_SENDTO)
        .setData(adminMail)
        .putExtra("subject", "Beer advice from user")
        .putExtra("body", beerType.getText().toString()+"-"+beerBrand.getText().toString());


        startActivity(sendMsg);
    }

}
