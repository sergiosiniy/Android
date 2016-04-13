package ua.kiev.home.sergiosiniy.cookyourself;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Toast;

public class Main extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }

    public void openRecipes(View view){
        createActivityJustCookIt();
    }

    private void createActivityJustCookIt(){
        Intent recipes = new Intent(this,ReadyForCook.class);
        startActivity(recipes);
    }

    public void openFavorites(View view){
        Toast.makeText(this,R.string.not_available,Toast.LENGTH_SHORT).show();
    }

    public void addNewRecipe(View view){
        Toast.makeText(this,R.string.not_available,Toast.LENGTH_SHORT).show();
    }

    public void exit(View view){
        System.exit(1);
    }


}
