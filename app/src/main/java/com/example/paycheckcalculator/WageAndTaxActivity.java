package com.example.paycheckcalculator;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.text.Editable;
import android.text.Selection;
import android.text.TextWatcher;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import java.net.Inet4Address;
import java.util.ArrayList;
import java.util.Scanner;

public class WageAndTaxActivity extends AppCompatActivity {

    ArrayList<Integer> Shifts = new ArrayList<Integer>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_wage_and_tax);

        Shifts = getIntent().getExtras().getIntegerArrayList("com.example.paycheckcalculator.SHIFTS");

        TextView tv = (TextView) findViewById(R.id.WageAndTaxTV);

        Button CalculateB = (Button) findViewById(R.id.CalculateB);
        CalculateB.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View view) {

                String WageStr = ((EditText) findViewById(R.id.WageETND)).getText().toString();
                String TaxStr = ((EditText) findViewById(R.id.TaxETND)).getText().toString();

                if(WageStr.matches("")) ((EditText) findViewById(R.id.WageETND)).setError("Incorrect Format");
                else if(TaxStr.matches("")) ((EditText) findViewById(R.id.TaxETND)).setError("Incorrect Format");
                else {
                    
                    finish();
                    Intent CalculationIntent = new Intent(getApplicationContext(), CalculationActivity.class);
                    CalculationIntent.putExtra("com.example.paycheckcalculator.SHIFTS", Shifts);
                    CalculationIntent.putExtra("com.example.paycheckcalculator.WAGE", WageStr);
                    CalculationIntent.putExtra("com.example.paycheckcalculator.TAX", TaxStr);
                    startActivity(CalculationIntent);
                }
            }
        });

        Button MainMenuB = (Button) findViewById(R.id.MainMenuB);
        MainMenuB.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View view) {

                finish();
                Intent MainIntent = new Intent(getApplicationContext(), MainActivity.class);
                MainIntent.putExtra("com.example.paycheckcalculator.SHIFTS", Shifts);
                startActivity(MainIntent);
            }
        });
    }

    @Override
    public void onBackPressed() {

        finish();
        Intent MainIntent = new Intent(getApplicationContext(), MainActivity.class);
        MainIntent.putExtra("com.example.paycheckcalculator.SHIFTS", Shifts);
        startActivity(MainIntent);
    }
}