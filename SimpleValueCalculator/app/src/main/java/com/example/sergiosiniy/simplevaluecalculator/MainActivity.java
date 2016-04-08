package com.example.sergiosiniy.simplevaluecalculator;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

public class MainActivity extends AppCompatActivity {

    private Map<String, Integer> itemPrices = new HashMap<>();
    private Map<String, Integer> itemWeights = new HashMap<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }

    /**
     * Calculates items.
     *
     * @return list of strings with item(s) which meets the conditions
     */
    private ArrayList<String> calculateItems(){
        ArrayList<String> list = new ArrayList<>();

        return list;
    }

    /**
     * Gets the values from all EditViews and puts it to the lists.
     *
     */
    private void collectTheEntries(){

    }

    /**
     * Displays list of items which meets the conditions.
     *
     * @param listOfItems
     */
    private void displayItems(ArrayList<String> listOfItems){
        TextView results = (TextView) findViewById(R.id.result_text_view);
        StringBuilder sb = new StringBuilder();

        for(String item:listOfItems){
            sb.append(item+"\n");
        }

        results.setText(sb.toString());
    }

    /**
     * Saves selected items.
     *
     * @param listOfItems contains the items
     */
    public void saveItems(ArrayList<String> listOfItems, View view){

    }

    public void submitItems(View view){

        displayItems(calculateItems());
    }

    /**
     * Clears all inputs and results.
     *
     */
    public void clear(View view){

    }






}
