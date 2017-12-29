package com.example.android.justjava;

/**
 * IMPORTANT: Add your package below. Package name can be found in the project's AndroidManifest.xml file.
 * This is the package name our example uses:
 *
 * package com.example.android.justjava;
 *
 */


import android.content.Intent;
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
    int quantity=0;
    boolean value,value1;
    String name;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }

    /**
     * increment method
     */
    public void increment(View view){
        if(quantity==100) {
            display(quantity);
            Toast t = Toast.makeText(this, "You cannot have more than 100 coffees", Toast.LENGTH_SHORT);
            t.show();
        }
        quantity+=1;
        display(quantity);
    }

    public void decrement(View view){
        if(quantity==0) {
            display(quantity);
            Toast t = Toast.makeText(this, "You cannot have less than 1 coffee", Toast.LENGTH_SHORT);
            t.show();
        }
        else {
            quantity -= 1;
            display(quantity);
        }
    }

    public String getName(){
        EditText text = (EditText) findViewById(R.id.Name);
        name = text.getText().toString();
        return name;

    }

    /**
     * This method is called when the order button is clicked.
     */
    public void submitOrder(View view) {
        int price=calculatePrice();
        String priceMessage=createOrderSummary(price);
       // displayMessage(priceMessage);
        composeEmail("Bill Regarding Coffee Order",priceMessage);
    }

    public void WboxChecked(View view) {
        CheckBox check = (CheckBox) findViewById(R.id.whipped_checked);
        if (check.isChecked()) {
            value = true;
            //Context context = getApplicationContext();
            Toast t = Toast.makeText(this, "Whipped Cream Added", Toast.LENGTH_SHORT);
            t.show();
        } else
            value = false;
    }

    public void CboxChecked(View view){
        CheckBox check1 = (CheckBox) findViewById(R.id.chocolate_checked);
        if(check1.isChecked()){
            value1= true;
//        Context context = getApplicationContext();
        Toast t= Toast.makeText(this, "Chocolate Added", Toast.LENGTH_SHORT);
        t.show();
                    }
            else
                value1=false;

    }

    private int calculatePrice(){
        int price = 5;
        if(value)
            price+=1;
        if(value1)
            price+=2;
        int p= quantity * price;
        return p;
    }

    private String createOrderSummary( int price){

        String priceMessage=getString(R.string.order_name,name)+ "\n"+getString(R.string.whipped,value)+"\n"+getString(R.string.choco,value1)+"\n"+getString(R.string.qty,quantity)+"\n"+getString(R.string.tot,price);
        priceMessage= priceMessage+ "\n" + getString(R.string.thank_you);
        return priceMessage;
    }

    public void composeEmail(String subject, String body) {
        Intent intent = new Intent(Intent.ACTION_SENDTO);
        intent.setData(Uri.parse("mailto:")); // only email apps should handle this
        //intent.putExtra(Intent.EXTRA_EMAIL, addresses);
        intent.putExtra(Intent.EXTRA_SUBJECT, subject);
        intent.putExtra(Intent.EXTRA_TEXT,body);
        if (intent.resolveActivity(getPackageManager()) != null) {
            startActivity(intent);
        }
    }

    /**
     * This method displays the given quantity value on the screen.
     */
    private void display(int number) {
        TextView quantityTextView = (TextView) findViewById(R.id.quantity_text_view);
        quantityTextView.setText("" + number);
    }

    /**
     * This method displays the given text on the screen.
     */
    private void displayMessage(String message) {
        TextView orderSummaryTextView = (TextView) findViewById(R.id.order_summary_text_view);
        orderSummaryTextView.setText(message);
    }
}