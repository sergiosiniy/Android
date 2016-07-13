package com.example.sergiosiniy.myfirstgame;

import android.app.Activity;
import android.os.Bundle;
import android.view.View;

public class GameActivity extends Activity {
    private int bodyLength=0;
    private int[][] map = new int[50][50];


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_game);
    }


    public void displayBody(View view, int length){

    }


}
