package com.czettner.justjava;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import java.text.NumberFormat;

public class MainActivity extends AppCompatActivity {

    int quantity = 2;
    int price = 5;
    public static final String NL = "\n";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }

    /**
     * This method is called when the order button is clicked.
     */
    public void submitOrder(View view) {
        String message = createOrderSummary(price);
        displayMessage(message);
    }

    public void increment(View view) {
        quantity++;
        display(quantity);
        displayPrice(price * quantity);
    }

    public void decrement(View view) {
        quantity--;
        display(quantity);
        displayPrice(price * quantity);
    }

    private String createOrderSummary(int price) {
        String summary = "Name: Kaptain Kunal" + NL;
        summary += "Quantity: " + quantity + NL;
        summary += "Total: " + NumberFormat.getCurrencyInstance().format(price * quantity) + NL;
        summary += "Thank You!";
        return summary;
    }

    /**
     * This method displays the given quantity value on the screen.
     */
    private void display(int number) {
        TextView quantityTextView = (TextView) findViewById(R.id.quantity_text);
        quantityTextView.setText("" + number);
    }

    /**
     * This method displays the given price on the screen.
     */
    private void displayPrice(int number) {
        TextView priceTextView = (TextView) findViewById(R.id.price_text);
        priceTextView.setText(NumberFormat.getCurrencyInstance().format(number));
    }

    /**
     * This method displays the given text on the screen.
     */
    private void displayMessage(String message) {
        TextView priceTextView = (TextView) findViewById(R.id.price_text);
        priceTextView.setText(message);
    }
}
