package com.example.sergiosiniy.tooglebottons2;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.Toast;
import android.widget.ToggleButton;

public class MainActivity extends AppCompatActivity {

    private ToggleButton toggleButton1, toggleButton2;
    private Button btnDisplay;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        addListenerOnButton();
        addListenerOnToggleButton1();
        addListenerOnToggleButton2();
    }

    public void addListenerOnButton() {

        toggleButton1 = (ToggleButton) findViewById(R.id.toggleButton1);
        toggleButton2 = (ToggleButton) findViewById(R.id.toggleButton2);
        btnDisplay = (Button) findViewById(R.id.btnDisplay);

        btnDisplay.setOnClickListener(new OnClickListener() {

            @Override
            public void onClick(View v) {

                StringBuffer result = new StringBuffer();
                result.append("toggleButton1 : ").append(toggleButton1.getText());
                result.append("\ntoggleButton2 : ").append(toggleButton2.getText());

                Toast.makeText(MainActivity.this, result.toString(),
                        Toast.LENGTH_SHORT).show();

            }

        });

    }

    public void addListenerOnToggleButton1(){
        toggleButton1 = (ToggleButton) findViewById(R.id.toggleButton1);

        toggleButton1.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View v) {
                StringBuffer toggleAction = new StringBuffer();
                toggleAction.append(" ").append(toggleButton1.getText());


            Toast.makeText(MainActivity.this, toggleAction.toString(),
                        Toast.LENGTH_SHORT).show();
            }

        });
    }

    public void addListenerOnToggleButton2(){
        toggleButton2 = (ToggleButton) findViewById(R.id.toggleButton2);

        toggleButton2.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View v) {
                StringBuffer toggleAction = new StringBuffer();
                toggleAction.append(" ").append(toggleButton2.getText());


                Toast.makeText(MainActivity.this, toggleAction.toString(),
                        Toast.LENGTH_SHORT).show();
            }

        });
    }
}
