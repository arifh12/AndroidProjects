//====================================================================
//
// Application: Bike Bistro
// Activity:    ActMeal
// Course:      CSC 4330
// Homework:    02
// Author:      Arif Hasan
// Date:        03/02/2021
// Description:
//   This activity allows the user to select an entree, drink, and
// dessert and passes them into ActDelivery through SharedPreferences.
//
//====================================================================

package edu.wayne.bikebistro;

// Imports automatically generated by intelliJ
import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.Spinner;
import androidx.annotation.NonNull;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import android.view.Menu;

import java.util.ArrayList;

public class ActMeal extends AppCompatActivity {

    //----------------------------------------------------------------
    // Constants and variables
    //----------------------------------------------------------------
    final String[][] menuItems = {
            {"Entree", "Turkey Sandwich", "11.49"},
            {"Entree", "Hamburger", "10.99"},
            {"Entree", "Pizza", "15.75"},
            {"Entree", "Tacos", "9.98"},
            {"Entree", "Szechuan Chicken", "12.50"},

            {"Drink", "Pepsi", "1.99"},
            {"Drink", "Coffee", "1.27"},
            {"Drink", "Iced Tea", "1.50"},
            {"Drink", "Milkshake", "2.94"},
            {"Drink", "Water", "1.00"},

            {"Dessert", "Ice Cream Sundae", "4.79"},
            {"Dessert", "Cheesecake", "6.98"},
            {"Dessert", "Apple Pie", "5.86"},
            {"Dessert", "Cookies", "3.42"},
            {"Dessert", "Churros", "2.99"}
    };
    EditText subtotalEdtTxt, edtTxtEntreePrice, edtTxtDrinkPrice, edtTxtDessertPrice = null;
    Spinner spEntree, spDrink, spDessert = null;
    double entreePrice, drinkPrice, dessertPrice, subtotal = 0.00d;
    final String MY_PREFS = "MY_PREFS";
    ArrayList<String> alEntreeNames, alDrinkNames, alDessertNames, alEntreePrices, alDrinkPrices, alDessertPrices;

    //----------------------------------------------------------------
    // onCreate
    //----------------------------------------------------------------
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.laymeal);

        // Creating toolbar
        Toolbar tbrMeal = findViewById(R.id.tbrMeal);
        setSupportActionBar(tbrMeal);

        // Initializing variables
        spEntree = findViewById(R.id.spinnerEntree);
        spDrink = findViewById(R.id.spinnerDrink);
        spDessert = findViewById(R.id.spinnerDessert);

        edtTxtEntreePrice = findViewById(R.id.editTextEntree);
        edtTxtDrinkPrice = findViewById(R.id.editTextDrink);
        edtTxtDessertPrice = findViewById(R.id.editTextDessert);
        subtotalEdtTxt = findViewById(R.id.editTextSubtotal);

        // Initializing spinners
        initializeEntreeSpinner();
        initializeDrinkSpinner();
        initializeDessertSpinner();
    }


    //----------------------------------------------------------------
    // Adding menu options to toolbar (not used)
    //----------------------------------------------------------------
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.main_menu, menu);
        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        // TODO: Implement menu options; not required
        return super.onOptionsItemSelected(item);
    }

    //----------------------------------------------------------------
    // Populating entree spinner and showing price for selected item
    //----------------------------------------------------------------
    private void initializeEntreeSpinner() {
        // Extracting and storing entree names and prices
        alEntreeNames = extractColumn("Entree", 1);
        alEntreePrices = extractColumn("Entree", 2);
        spEntree.setAdapter(getAdapter(alEntreeNames));

        // Display price of selected entree item
        spEntree.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
                entreePrice = (i > 0) ? Double.parseDouble(alEntreePrices.get(i)) : Double.parseDouble("0.00");
                edtTxtEntreePrice.setText(String.format("$%.2f", entreePrice));

                updateSubtotal();
            }

            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {
                edtTxtEntreePrice.setText("0.00");
            }
        });
    }

    //----------------------------------------------------------------
    // Populating drink spinner and showing price for selected item
    //----------------------------------------------------------------
    private void initializeDrinkSpinner() {
        // Extracting and storing drink names and prices
        alDrinkNames = extractColumn("Drink", 1);
        alDrinkPrices = extractColumn("Drink", 2);
        spDrink.setAdapter(getAdapter(alDrinkNames));

        // Display price of selected drink item
        spDrink.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
                drinkPrice = (i > 0) ? Double.parseDouble(alDrinkPrices.get(i)) : Double.parseDouble("0.00");
                edtTxtDrinkPrice.setText(String.format("$%.2f", drinkPrice));

                updateSubtotal();
            }

            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {
                edtTxtDrinkPrice.setText("0.00");
            }
        });
    }

    //----------------------------------------------------------------
    // Populating dessert spinner and showing price for selected item
    //----------------------------------------------------------------
    private void initializeDessertSpinner() {
        // Extracting and storing dessert names and prices
        alDessertNames = extractColumn("Dessert", 1);
        alDessertPrices = extractColumn("Dessert", 2);
        spDessert.setAdapter(getAdapter(alDessertNames));

        // Display price of selected dessert item
        spDessert.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
                dessertPrice = (i > 0) ? Double.parseDouble(alDessertPrices.get(i)) : Double.parseDouble("0.00");
                edtTxtDessertPrice.setText(String.format("$%.2f", dessertPrice));

                updateSubtotal();
            }

            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {
                edtTxtDessertPrice.setText("0.00");
            }
        });
    }

    //----------------------------------------------------------------
    // Updating subtotal text box based on selections
    //----------------------------------------------------------------
    private void updateSubtotal() {
        subtotal = entreePrice + drinkPrice + dessertPrice;
        subtotalEdtTxt.setText(String.format("$%.2f", subtotal));
    }

    //----------------------------------------------------------------
    // Returns an adapter for spinners
    //----------------------------------------------------------------
    private ArrayAdapter<String> getAdapter(ArrayList<String> arr) {
        ArrayAdapter<String> adapter = new ArrayAdapter<String>(
                this, R.layout.support_simple_spinner_dropdown_item, arr);
        adapter.setDropDownViewResource(R.layout.support_simple_spinner_dropdown_item);

        return adapter;
    }

    //----------------------------------------------------------------
    // Method for extracting names and prices of a given category
    //----------------------------------------------------------------
    private ArrayList<String> extractColumn(String category, int column) {
        ArrayList<String> arr = new ArrayList<>();
        arr.add("");

        for(String[] item : menuItems) {
            if (item[0] == category)
                arr.add(item[column]);
        }

        return arr;
    }

    //----------------------------------------------------------------
    // Displaying a dialog box and loading preferences
    //----------------------------------------------------------------
    public void onClickRecall(View view) {
        AlertDialog.Builder dialog = new AlertDialog.Builder(this)
                .setTitle("Confirm Recall")
                .setMessage("Are you sure you to retrieve the last order?")
                .setNegativeButton("No", null)
                .setPositiveButton("Yes", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                        loadPreferences();
                    }
                });
        dialog.show();
    }

    //----------------------------------------------------------------
    // Resetting activity back to original state
    //----------------------------------------------------------------
    public void onClickReset(View view) {
        AlertDialog.Builder dialog = new AlertDialog.Builder(this)
                .setTitle("Confirm Reset")
                .setMessage("Are you sure you want to reset?")
                .setPositiveButton("Yes", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {
                spEntree.setSelection(0);
                spDrink.setSelection(0);
                spDessert.setSelection(0);

                edtTxtEntreePrice.setText("$0.00");
                edtTxtDrinkPrice.setText("$0.00");
                edtTxtDessertPrice.setText("$0.00");
            }
        })
                .setNegativeButton("No", null);

        dialog.show();
    }

    //----------------------------------------------------------------
    // Saving user inputs and opens the Delivery activity
    //----------------------------------------------------------------
    public void onClickDelivery(View view) {
        savePreferences();
        Intent intent = new Intent(this, ActDelivery.class);

        // Passing order information using Intent.putString();
        intent.putExtra("items", new String[]{(String) spEntree.getSelectedItem(),
                (String) spDrink.getSelectedItem(), (String) spDessert.getSelectedItem()});
        intent.putExtra("subtotal", subtotal);

        startActivity(intent);
    }

    //----------------------------------------------------------------
    // Storing user inputs into SharedPreferences
    //----------------------------------------------------------------
    private void savePreferences() {
        SharedPreferences sp = getSharedPreferences(MY_PREFS, MODE_PRIVATE);
        SharedPreferences.Editor editor = sp.edit();

        editor.putInt("entreeIndex", spEntree.getSelectedItemPosition());
        editor.putInt("drinkIndex", spDrink.getSelectedItemPosition());
        editor.putInt("dessertIndex", spDessert.getSelectedItemPosition());

        editor.apply();
    }

    //----------------------------------------------------------------
    // Retrieving user saved inputs from previous order
    //----------------------------------------------------------------
    private void loadPreferences() {
        SharedPreferences sp = getSharedPreferences(MY_PREFS, MODE_PRIVATE);

        int entreeIndex = sp.getInt("entreeIndex", 0);
        int drinkIndex = sp.getInt("drinkIndex", 0);
        int dessertIndex = sp.getInt("dessertIndex", 0);

        // Setting inputs from SharedPreferences
        spEntree.setSelection(entreeIndex);
        spDrink.setSelection(drinkIndex);
        spDessert.setSelection(dessertIndex);

        edtTxtEntreePrice.setText(alEntreePrices.get(entreeIndex));
        edtTxtDrinkPrice.setText(alDrinkPrices.get(drinkIndex));
        edtTxtDessertPrice.setText(alDessertPrices.get(dessertIndex));
    }
}