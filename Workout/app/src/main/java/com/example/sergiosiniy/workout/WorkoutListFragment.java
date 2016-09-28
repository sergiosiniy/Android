package com.example.sergiosiniy.workout;


import android.annotation.TargetApi;
import android.app.Activity;
import android.app.ListFragment;
import android.content.Context;
import android.os.Build;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.TextView;

import static android.content.ContentValues.TAG;

/**
 * A simple {@link Fragment} subclass.
 */
public class WorkoutListFragment extends ListFragment {

    interface WorkoutListListener {
        void itemClicked(long id);
    };
    private WorkoutListListener listener;


    public WorkoutListFragment() {
        // Required empty public constructor
    }


    @TargetApi(23)
    @Override public void onAttach(Context context) {
        //This method avoid to call super.onAttach(context) if I'm not using api 23 or more
        //if (Build.VERSION.SDK_INT >= 23) {
        super.onAttach(context);
        onAttachToContext(context);
        //}
    }

    /*
     * Deprecated on API 23
     * Use onAttachToContext instead
     */
    @SuppressWarnings("deprecation")
    @Override public void onAttach(Activity activity) {
        super.onAttach(activity);
        if (Build.VERSION.SDK_INT < 23) {
            onAttachToContext(activity);
        }
    }

    /*
     * This method will be called from one of the two previous method
     */
    protected void onAttachToContext(Context context) {
        this.listener = (WorkoutListListener) context;
    }



    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        ArrayAdapter<Workout> adapter = new ArrayAdapter<Workout>(
                inflater.getContext(), android.R.layout.simple_list_item_1,
                Workout.workouts);
        setListAdapter(adapter);
        return super.onCreateView(inflater, container, savedInstanceState);
    }

    @Override
    public void onListItemClick(ListView l, View v, int position, long id) {
        Log.i(TAG, "List item was clicked");
        if(listener!=null) {
            listener.itemClicked(id);
        }else{
            Log.e(TAG, "listener is empty");
        }

    }


}
