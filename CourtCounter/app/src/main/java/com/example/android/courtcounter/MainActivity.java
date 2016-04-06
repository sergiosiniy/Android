package com.example.android.courtcounter;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {

    private int teamAPoints = 0;
    private int teamBPoints = 0;
    private TextView teamAPointsTextView;
    private TextView teamBPointsTextView;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        teamAPointsTextView = (TextView) findViewById(R.id.teamA_count);
        teamBPointsTextView = (TextView) findViewById(R.id.teamB_count);
    }


    public void addPointsTeamAPlusThree(View view) {
        teamAPoints += 3;
        teamAPointsTextView.setText("" + teamAPoints);
    }

    public void addPointsTeamAPlusTwo(View view) {
        teamAPoints += 2;
        teamAPointsTextView.setText("" + teamAPoints);
    }

    public void addPointsTeamAPlusOne(View view) {
        teamAPoints += 1;
        teamAPointsTextView.setText("" + teamAPoints);
    }

    public void addPointsTeamBPlusThree(View view) {
        teamBPoints += 3;
        teamBPointsTextView.setText("" + teamBPoints);
    }

    public void addPointsTeamBPlusTwo(View view) {
        teamBPoints += 2;
        teamBPointsTextView.setText("" + teamBPoints);
    }

    public void addPointsTeamBPlusOne(View view) {
        teamBPoints += 1;
        teamBPointsTextView.setText("" + teamBPoints);
    }

    public void clear(View view) {
        teamAPoints = teamBPoints = 0;
        teamAPointsTextView.setText("" + teamAPoints);
        teamBPointsTextView.setText("" + teamBPoints);
    }


}
