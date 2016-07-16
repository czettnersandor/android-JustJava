package com.czettner.justjava;

import android.content.Intent;
import android.net.Uri;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import java.text.NumberFormat;

public class MainActivity extends AppCompatActivity {

    int quantity = 2;
    int price = 5;
    boolean whippedCream = false;
    boolean chocolate = false;
    public static final String NL = "\n";
    public static final int MIN_COFFEES = 1;
    public static final int MAX_COFFEES = 100;
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
        Intent intent = new Intent(Intent.ACTION_SENDTO);
        intent.setData(Uri.parse("mailto:")); // only email apps should handle this
        intent.putExtra(Intent.EXTRA_SUBJECT, "Just Java order");
        intent.putExtra(Intent.EXTRA_TEXT, message);
        if (intent.resolveActivity(getPackageManager()) != null) {
            startActivity(intent);
        } else {
            Toast toast = Toast.makeText(getApplicationContext(), "Cannot start email app.", Toast.LENGTH_SHORT);
            toast.show();
        }
    }

    /**
     * Increment quantity by 1
     * @param view
     */
    public void increment(View view) {
        if (quantity >= MAX_COFFEES) {
            Toast toast = Toast.makeText(getApplicationContext(), "You can not have more than " + MAX_COFFEES + " coffees.", Toast.LENGTH_SHORT);
            toast.show();
            return;
        }
        quantity++;
        display(quantity);
        displayPrice(calculateOrderPrice(price));
    }

    /**
     * Decrement quantity by 1
     * @param view
     */
    public void decrement(View view) {
        if (quantity <= MIN_COFFEES) {
            Toast toast = Toast.makeText(getApplicationContext(), "You can not have less than " + MIN_COFFEES + " coffee.", Toast.LENGTH_SHORT);
            toast.show();
            return;
        }
        quantity--;
        display(quantity);
        displayPrice(calculateOrderPrice(price));
    }

    /**
     * Calculate order price
     * @param orderPrice Order price
     * @return Calculated price
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
        displayPrice(calculateOrderPrice(price));
    }

    /**
     * Clicked on Chocolate checkbox
     * @param view
     */
    public void chocolateClick(View view) {
        CheckBox checkbox = (CheckBox) view;
        chocolate = checkbox.isChecked();
        displayPrice(calculateOrderPrice(price));
    }

    /**
     * Create order summary with price
     * @param price Deal price
     * @param name  Buyer's name
     * @return Order summary
     */
    private String createOrderSummary(int price, String name) {
        String summary = getString(R.string.name) + ": " + name + NL;
        summary += getString(R.string.add_whipped) + (whippedCream?"Yes":"No") + NL;
        summary += getString(R.string.add_whipped) + (chocolate?"Yes":"No") + NL;
        summary += getString(R.string.quantity) + ": " + quantity + NL;
        summary += getString(R.string.total) + ": " + NumberFormat.getCurrencyInstance().format(price) + NL;
        summary += getString(R.string.thank_you);
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

}
