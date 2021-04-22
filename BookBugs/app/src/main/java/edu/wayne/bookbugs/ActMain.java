//====================================================================
//
// Application: Book Bugs
// Activity:    ActMain
// Course:      CSC 4330
// Homework:    01
// Author:      Arif Hasan
// Date:        02/06/2021
// Description:
//   This application allows the user to order a book, and then
// shows the order details in a toast message.
//
//====================================================================
package edu.wayne.bookbugs;

// imports automatically generated by intelliJ
import android.content.DialogInterface;
import android.view.View;
import android.widget.*;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;

public class ActMain extends AppCompatActivity {

    //----------------------------------------------------------------
    // Constants and variables
    //----------------------------------------------------------------
    final String[] books= {
            "The Alchemist, $12.99",
            "To Kill a Mockingbird, $10.50",
            "Harry Potter and the Goblet of Fire, $9.15",
            "Animal Farm, $8.95",
            "Captain Underpants, $7.59",
            "Of Mice and Men, $6.99",
            "The Great Gatsby, $5.99",
            "Green Eggs and Ham, $3.40"};
    EditText nameBox, emailBox, titleBox, costBox, salesTaxBox, totalBox;
    RadioGroup paymentTypes;
    RadioButton checkedPaymentType;

    //----------------------------------------------------------------
    // onCreate
    //----------------------------------------------------------------
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        // Connect layout to this file
        super.onCreate(savedInstanceState);
        setContentView(R.layout.laymain);

        // Initialize variables
        nameBox = findViewById(R.id.nameTextBox);
        emailBox = findViewById(R.id.emailTextBox);
        titleBox = findViewById(R.id.titleTxtBox);
        costBox = findViewById(R.id.costTextBox);
        salesTaxBox = findViewById(R.id.salesTaxTextBox);
        totalBox = findViewById(R.id.totalTextBox);
    }

    //----------------------------------------------------------------
    // Method for showing book list alert dialog
    //----------------------------------------------------------------
    public void showBookListDialog(View v) {
        AlertDialog.Builder builder = new AlertDialog.Builder(v.getContext());
        builder.setTitle("Choose a book: ").setItems(books, new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                String selectedBook[] = books[which].split(", \\$");
                showBookDetails(selectedBook[0], Double.parseDouble(selectedBook[1]));
            }
        });
        builder.show();
    }

    //----------------------------------------------------------------
    // Method to set book details based on selection
    //----------------------------------------------------------------
    public void showBookDetails(String title, double price) {
        double salesTax = price * 0.06;

        titleBox.setText(title);
        costBox.setText(String.format("$%.2f", price));
        salesTaxBox.setText(String.format("$%.2f", salesTax));
        totalBox.setText(String.format("$%.2f", price + salesTax));
    }

    //----------------------------------------------------------------
    // Get and show toast message when Submit is clicked
    //----------------------------------------------------------------
    public void showSubmitToastMsg(View v) {
        String toastMsg = getToastMsg(v);
        Toast.makeText(getApplicationContext(), toastMsg, Toast.LENGTH_LONG).show();
    }

    private String getToastMsg(View v) {
        String title = titleBox.getText().toString();
        String name = nameBox.getText().toString();
        String email = emailBox.getText().toString();
        String cost = costBox.getText().toString();
        String salesTax = salesTaxBox.getText().toString();
        String totalCost = totalBox.getText().toString();
        String paymentType = "";

        // Find checked RadioButton from RadioGroup and getText()
        paymentTypes = findViewById(R.id.paymentRadioGroup);
        checkedPaymentType = findViewById(paymentTypes.getCheckedRadioButtonId());
        if(checkedPaymentType != null)
            paymentType = checkedPaymentType.getText().toString();

        String toastMsg = "Title: " + title +
                "\nName: " + name +
                "\nEmail: " + email +
                "\nCost: " + cost +
                "\nSales Tax: " + salesTax +
                "\nTotal Cost: " + totalCost +
                "\nPayment Type: " + paymentType;

        return toastMsg;
    }

    //----------------------------------------------------------------
    // Clear inputs on Reset button click
    //----------------------------------------------------------------
    public void onResetBtnClick(View v) {
        AlertDialog.Builder builder = new AlertDialog.Builder(v.getContext());
        builder.setTitle("Reset");
        builder.setMessage("Are you sure you want to reset? All inputs will be erased.");
        builder.setPositiveButton("Yes", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                nameBox.setText("");
                emailBox.setText("");
                titleBox.setText("");
                costBox.setText("");
                salesTaxBox.setText("");
                totalBox.setText("");
                paymentTypes.clearCheck();
            }
        });
        builder.setNegativeButton("No", null);
        builder.show();
    }
}