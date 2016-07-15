package com.czettner.justjava;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.CheckBox;
import android.widget.TextView;

import java.text.NumberFormat;

public class MainActivity extends AppCompatActivity {

    int quantity = 2;
    int price = 5;
    boolean whippedCream = false;
    boolean chocolate = false;
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

    /**
     * Increment quantity by 1
     * @param view
     */
    public void increment(View view) {
        quantity++;
        display(quantity);
        displayPrice(price * quantity);
    }

    /**
     * Decrement quantity by 1
     * @param view
     */
    public void decrement(View view) {
        quantity--;
        display(quantity);
        displayPrice(price * quantity);
    }

    /**
     * Clicked on Whipped Cream checkbox
     * @param view
     */
    public void whippedClick(View view) {
        CheckBox checkbox = (CheckBox) view;
        whippedCream = checkbox.isChecked();
    }

    /**
     * Clicked on Chocolate checkbox
     * @param view
     */
    public void chocolateClick(View view) {
        CheckBox checkbox = (CheckBox) view;
        chocolate = checkbox.isChecked();
    }

    /**
     * Create order summary with price
     * @param price
     * @return Order summary
     */
    private String createOrderSummary(int price) {
        String summary = "Name: Kaptain Kunal" + NL;
        summary += "Add whipped cream? " + (whippedCream?"Yes":"No") + NL;
        summary += "Add chocolate? " + (chocolate?"Yes":"No") + NL;
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
        TextView orderSummaryTextView = (TextView) findViewById(R.id.order_summary_text_view);
        orderSummaryTextView.setText(NumberFormat.getCurrencyInstance().format(number));
    }

    /**
     * This method displays the given text on the screen.
     */
    private void displayMessage(String message) {
        TextView orderSummaryTextView = (TextView) findViewById(R.id.order_summary_text_view);
        orderSummaryTextView.setText(message);
    }
}
