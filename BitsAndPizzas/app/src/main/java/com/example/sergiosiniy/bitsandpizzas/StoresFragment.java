package com.example.sergiosiniy.bitsandpizzas;

import android.app.ListFragment;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;

/**
 * Created by Sergiosiniy on 07.10.2016.
 */

public class StoresFragment extends ListFragment {

    public StoresFragment(){
        //empty constructor is needed
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        ArrayAdapter<String> storesList = new ArrayAdapter<>(inflater.getContext(),
                android.R.layout.simple_list_item_1, getResources().getStringArray(R.array.stores));
        setListAdapter(storesList);
        return super.onCreateView(inflater, container, savedInstanceState);
    }
}
