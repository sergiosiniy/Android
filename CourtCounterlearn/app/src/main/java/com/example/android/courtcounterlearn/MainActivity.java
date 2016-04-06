package com.example.android.courtcounterlearn;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.Menu;
import android.view.View;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {

    private int scoreTeamA = 0;
    private int scoreTeamB = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        displayForTeamA(scoreTeamA);

    }

    /**
     * Displays the given score for Team A.
     */
    public void displayForTeamA(int score) {
        TextView scoreView = (TextView) findViewById(R.id.team_a_score);
        scoreView.setText(String.valueOf(score));
    }

    public void displayForTeamB(int score) {
        TextView scoreView = (TextView) findViewById(R.id.team_b_score);
        scoreView.setText(String.valueOf(score));
    }

    public void setScoreThreeA(View view) {
        scoreTeamA += 3;
        displayForTeamA(scoreTeamA);
    }

    public void setScoreTwoA(View view) {
        scoreTeamA += 2;
        displayForTeamA(scoreTeamA);
    }

    public void setScoreOneA(View view) {
        scoreTeamA += 1;
        displayForTeamA(scoreTeamA);
    }

    public void setScoreThreeB(View view) {
        scoreTeamB += 3;
        displayForTeamB(scoreTeamB);
    }

    public void setScoreTwoB(View view) {
        scoreTeamB += 2;
        displayForTeamB(scoreTeamB);
    }

    public void setScoreOneB(View view) {
        scoreTeamB += 1;
        displayForTeamB(scoreTeamB);
    }

    public void resetScores(View v) {
        scoreTeamA = scoreTeamB = 0;
        displayForTeamA(scoreTeamA);
        displayForTeamB(scoreTeamB);
    }

}