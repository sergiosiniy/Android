package com.example.sergiosiniy.workout;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

public class MainActivity extends AppCompatActivity implements WorkoutListFragment.WorkoutListListener{

    WorkoutDetailFragment detailFragment;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }

    @Override
    public void itemClicked(long id) {
       detailFragment = (WorkoutDetailFragment) getFragmentManager().findFragmentById(R.id.detail_frag);
        detailFragment.setWorkoutID(id);
    }

}
