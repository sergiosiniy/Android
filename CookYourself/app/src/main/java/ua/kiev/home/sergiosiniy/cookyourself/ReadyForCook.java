package ua.kiev.home.sergiosiniy.cookyourself;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.Button;
import android.widget.TextView;

import java.util.ArrayList;

public class ReadyForCook extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_ready_for_cook);
        createButtons(getReadyRecipes());
    }

    /**
     * Reads the entries in DB and puts recipe's names to the list.
     *
     * @return list if is not empty or null if it is.
     */
    private ArrayList<String> getReadyRecipes(){
        ArrayList<String> list = new ArrayList<>();
        return list;
    }

    /**
     *
     * @param listOfButtons
     */
    private void createButtons(ArrayList<String> listOfButtons){
        if(!listOfButtons.isEmpty()) {
            for (String name : listOfButtons) {
                new Button(this).setText(name);
            }
        }else{
            new TextView(this).setText(R.string.not_available);
        }
    }

}
