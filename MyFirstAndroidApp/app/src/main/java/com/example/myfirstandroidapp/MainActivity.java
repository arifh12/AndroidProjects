package com.example.myfirstandroidapp;

import android.view.View;
import android.widget.EditText;
import android.widget.TextView;
import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }

    public void onRegisterBtnClick(View view) {
        TextView firstNameTxt = findViewById(R.id.firstNameTxtView);
        EditText firstNameField = findViewById(R.id.firstNameField);
        firstNameTxt.setText(firstNameTxt.getText() + firstNameField.getText().toString());

        TextView lastNameTxt = findViewById(R.id.lastNameTxtView);
        EditText lastNameField = findViewById(R.id.lastNameField);
        lastNameTxt.setText(lastNameTxt.getText() + lastNameField.getText().toString());

        TextView emailTxt = findViewById(R.id.emailTxtView);
        EditText emailField = findViewById(R.id.emailField);
        emailTxt.setText(emailTxt.getText() + emailField.getText().toString());
    }
}