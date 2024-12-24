package com.example.musicapp;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;
import android.widget.Spinner;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;



public class dnmh3_enterphoneActivity extends AppCompatActivity {
    private Button btnSendCode;
    private TextView textPhone;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.dnmh3_enterphone_view);


        btnSendCode = findViewById(R.id.btnSendCode);
        btnSendCode.setOnClickListener(v -> {
            Intent launchActivity2 = new Intent(dnmh3_enterphoneActivity.this, dnmh4_checkphoneActivity.class);
            textPhone = findViewById(R.id.editTextPhone);
            Spinner spinnerCountry = findViewById(R.id.spinnerCountry);
            String selectedCountry = spinnerCountry.getSelectedItem().toString();

            String phone = textPhone.getText().toString();
            launchActivity2.putExtra("phoneFromSignIn", selectedCountry + phone);
            startActivity(launchActivity2);


        });


    }



}
