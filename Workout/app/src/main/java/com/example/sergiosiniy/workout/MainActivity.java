package com.example.sergiosiniy.workout;

import android.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

public class MainActivity extends AppCompatActivity implements WorkoutListFragment.WorkoutListListener{


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }

    @Override
    public void itemClicked(long id) {
        WorkoutDetailFragment detailsFragment = new WorkoutDetailFragment();
        FragmentTransaction ft = getFragmentManager().beginTransaction();
        detailsFragment.setWorkoutID(id);
        ft.setTransition(FragmentTransaction.TRANSIT_FRAGMENT_FADE);
        ft.replace(R.id.fragment_container, detailsFragment);
        ft.addToBackStack(null);
        ft.commit();
    }

}
