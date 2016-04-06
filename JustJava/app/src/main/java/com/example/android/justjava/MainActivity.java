package com.example.android.justjava;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.TextView;

/**
 * This app displays an order form to order coffee.
 */
public class MainActivity extends AppCompatActivity {

    private int quantity = 0;
    private int priceOfCup = 5;
    private String personName="";
    private int wippedCreamPrice =1;
    private int chocolatePrice = 2;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        displayQuantity(quantity);
        displayMessage(createOrderSummary(quantity));

    }



    /**
     * This method is called when the order button is clicked.
     */
    public void submitOrder(View view) {
        String priceMessage;
        priceMessage= createOrderSummary(quantity);
        displayMessage(priceMessage+"\nThank you!");
    }

    /**
     * Resets the current order and prices.
     *
     * @param view
     */

    public void clearOrder(View view){
        quantity=0;
        personName="";
        ((CheckBox) findViewById(R.id.add_whipped_cream)).setChecked(false);
        ((CheckBox) findViewById(R.id.add_chocolate)).setChecked(false);
        displayQuantity(quantity);
        displayMessage(createOrderSummary(quantity));

    }

    /**
     * This method displays the given quantity value on the screen.
     */
    private void displayQuantity(int number) {
        TextView quantityTextView = (TextView) findViewById(R.id.quantity_text_view);
        quantityTextView.setText("" + number);
    }

    /**
     * This method increments the value of quantity.
     */
    public void increment(View view) {
        quantity++;
        displayQuantity(quantity);
    }

    /**
     * This method decrements the value of quantity of cups of coffee.
     */
    public void decrement(View view) {
        if (quantity > 0)
            quantity--;

        displayQuantity(quantity);
    }

    /**
     * This method displays the given text on the screen.
     */
    private void displayMessage(String message) {
        TextView orderSummaryTextView = (TextView) findViewById(R.id.order_summary_text_view);
        orderSummaryTextView.setText(message);
    }

    /**
     * Calculates the price of the order.
     *
     * @param quantity is the number of cups of coffee ordered
     */
    private int calculatePrice(int quantity, int priceOfOne, boolean cream, boolean chocolate) {

       if((cream||chocolate)){
           if(cream&&chocolate) {
               return quantity * (priceOfOne+ chocolatePrice+wippedCreamPrice);
           }else if(chocolate){
               return quantity * (priceOfOne+ chocolatePrice);
           }else if(cream){
               return quantity * (priceOfOne+ wippedCreamPrice);
           }else{
               return 0;
           }
       }else{
           return quantity * priceOfOne;
       }

    }

    /**
     * Creates a summary order message.
     *
     * @param numOfCoffees quantity of ordered cups
     * @return summary order message
     */

    private String createOrderSummary(int numOfCoffees){
        CheckBox wippedCream =(CheckBox)findViewById(R.id.add_whipped_cream);
        CheckBox chocolate = (CheckBox)findViewById(R.id.add_chocolate);
        nameInput();
        return "Name: "+ personName +
                "\nQuantity: "+numOfCoffees+
                "\nTotal: $"+calculatePrice(quantity, priceOfCup, wippedCream.isChecked(), chocolate.isChecked());
    }


    private void nameInput(){
        personName=((EditText)findViewById(R.id.name_input)).getText().toString();
    }

    /*public void setTextNull(View v){
        EditText ed = (EditText)findViewById(R.id.name_input);
        ed.setText("");
        ed.setCursorVisible(true);
    }*/


}