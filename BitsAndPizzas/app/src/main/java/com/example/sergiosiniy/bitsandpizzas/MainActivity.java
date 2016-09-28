package com.example.sergiosiniy.bitsandpizzas;

import android.content.Intent;
import android.support.v4.app.ShareCompat;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;

public class MainActivity extends AppCompatActivity {


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case R.id.action_create_order:
                Intent newOrder = new Intent (MainActivity.this, OrderActivity.class);
                startActivity(newOrder);
                return true;
            case R.id.action_settings:
//Code to run when the settings item is clicked
                return true;
            case R.id.action_share:
                ShareCompat.IntentBuilder
                        .from(this) // getActivity() or activity field if within Fragment
                        .setText("Test piece of text")
                        .setType("text/plain") // most general text sharing MIME type
                        .setChooserTitle(getResources().getString(R.string.action_share))
                        .startChooser();
                return true;
            default:
                return super.onOptionsItemSelected(item);
        }
    }
}
