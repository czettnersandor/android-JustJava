package com.czettner.justjava;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.TextView;

import java.text.NumberFormat;

public class MainActivity extends AppCompatActivity {

    int quantity = 2;
    int price = 5;
    boolean whippedCream = false;
    boolean chocolate = false;
    public static final String NL = "\n";
    public static final int WHIPPED_PRICE = 1;
    public static final int CHOCOLATE_PRICE = 2;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }

    /**
     * This method is called when the order button is clicked.
     */
    public void submitOrder(View view) {
        EditText nameEditText = (EditText) findViewById(R.id.edit_name);
        String name = nameEditText.getText().toString();
        String message = createOrderSummary(calculateOrderPrice(price), name);
        displayMessage(message);
    }

    /**
     * Increment quantity by 1
     * @param view
     */
    public void increment(View view) {
        quantity++;
        display(quantity);
        displayPrice(calculateOrderPrice(price));
    }

    /**
     * Decrement quantity by 1
     * @param view
     */
    public void decrement(View view) {
        quantity--;
        display(quantity);
        displayPrice(calculateOrderPrice(price));
    }

    /**
     * Calculate order price
     * @param orderPrice
     * @return
     */
    private int calculateOrderPrice(int orderPrice) {
        orderPrice += (whippedCream?WHIPPED_PRICE:0);
        orderPrice += (chocolate?CHOCOLATE_PRICE:0);
        orderPrice = orderPrice * quantity;
        return orderPrice;
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
     * @param price Deal price
     * @param name  Buyer's name
     * @return Order summary
     */
    private String createOrderSummary(int price, String name) {
        String summary = "Name: " + name + NL;
        summary += "Add whipped cream? " + (whippedCream?"Yes":"No") + NL;
        summary += "Add chocolate? " + (chocolate?"Yes":"No") + NL;
        summary += "Quantity: " + quantity + NL;
        summary += "Total: " + NumberFormat.getCurrencyInstance().format(price) + NL;
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
