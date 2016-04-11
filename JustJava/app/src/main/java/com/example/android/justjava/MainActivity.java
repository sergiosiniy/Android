package com.example.android.justjava;

import android.content.Intent;
import android.net.MailTo;
import android.net.Uri;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

/**
 * This app displays an order form to order coffee.
 */
public class MainActivity extends AppCompatActivity {

    private int quantity = 1;
    private int priceOfCup = 5;
    private String personName = "";
    private int wippedCreamPrice = 1;
    private int chocolatePrice = 2;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        displayQuantity(quantity);

    }


    /**
     * This method is called when the order button is clicked.
     */
    public void submitOrder(View view) {
        String priceMessage;
        priceMessage = createOrderSummary(quantity);


        Intent sendMail = new Intent(Intent.ACTION_SENDTO)
                //.setType("text/plain")
                .setData(Uri.parse("mailto:sergio.siniy@gmail.com"))
                .putExtra(Intent.EXTRA_SUBJECT, "Order from " + personName)
                .putExtra(Intent.EXTRA_TEXT, priceMessage);


        if (sendMail.resolveActivity(getPackageManager()) != null) {
            startActivity(sendMail);
        } else {
            Toast.makeText(this, R.string.toast_no_app, Toast.LENGTH_SHORT).show();
        }

        quantity = 1;
        personName = "";
        ((EditText) findViewById(R.id.name_input)).setText("");
        ((CheckBox) findViewById(R.id.add_whipped_cream)).setChecked(false);
        ((CheckBox) findViewById(R.id.add_chocolate)).setChecked(false);
        displayQuantity(quantity);

    }

    /**
     * Resets the current order and prices.
     *
     * @param view
     */

    /*public void clearOrder(View view){
        quantity=1;
        personName="";
        ((EditText) findViewById(R.id.name_input)).setText("");
        ((CheckBox) findViewById(R.id.add_whipped_cream)).setChecked(false);
        ((CheckBox) findViewById(R.id.add_chocolate)).setChecked(false);
        displayQuantity(quantity);
        displayMessage(createOrderSummary(0));

    }*/

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
        if (quantity < 100) {
            quantity++;
            displayQuantity(quantity);
        } else {
            Toast.makeText(this, R.string.toast_100_coffee, Toast.LENGTH_SHORT).show();
        }

    }

    /**
     * This method decrements the value of quantity of cups of coffee.
     */
    public void decrement(View view) {
        if (quantity > 1) {
            quantity--;
            displayQuantity(quantity);
        } else {
            Toast.makeText(this, R.string.toast_1_coffee, Toast.LENGTH_SHORT).show();
        }


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
     * @param cream     if whippedCream is added contains true
     * @param chocolate if chocolate is added contains true
     */
    private int calculatePrice(int quantity, boolean cream, boolean chocolate) {

        if (cream && chocolate) {
            return quantity * (priceOfCup + chocolatePrice + wippedCreamPrice);
        } else if (chocolate) {
            return quantity * (priceOfCup + chocolatePrice);
        } else if (cream) {
            return quantity * (priceOfCup + wippedCreamPrice);
        } else {
            return quantity * priceOfCup;
        }

    }

    /**
     * Creates a summary order message.
     *
     * @param numOfCoffees quantity of ordered cups
     * @return summary order message
     */

    private String createOrderSummary(int numOfCoffees) {
        CheckBox wippedCream = (CheckBox) findViewById(R.id.add_whipped_cream);
        CheckBox chocolate = (CheckBox) findViewById(R.id.add_chocolate);
        nameInput();
        return getResources().getString(R.string.form_order_name) + personName +
                /*"\n"+*/getResources().getString(R.string.form_order_quantity) + numOfCoffees +
                /*"\n"+*/getResources().getString(R.string.form_order_price) + calculatePrice(numOfCoffees, wippedCream.isChecked(), chocolate.isChecked());
    }


    private void nameInput() {
        personName = ((EditText) findViewById(R.id.name_input)).getText().toString();
    }


}