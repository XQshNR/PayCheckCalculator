package com.example.paycheckcalculator;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import java.lang.reflect.Array;
import java.util.ArrayList;

public class CalculationActivity extends AppCompatActivity {

    ArrayList<Integer> Shifts = new ArrayList<Integer>();
    double Wage, Tax;

    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_calculation);

        Shifts = getIntent().getExtras().getIntegerArrayList("com.example.paycheckcalculator.SHIFTS");
        Wage = Double.parseDouble(getIntent().getExtras().getString("com.example.paycheckcalculator.WAGE"));
        Tax = Double.parseDouble(getIntent().getExtras().getString("com.example.paycheckcalculator.TAX"));

        TextView CalculationTV = (TextView) findViewById(R.id.CalculationTV);
        CalculationTV.setText(String.format("Predicted Earnings:\n$%.2f\n\n\n Average Daily Earnings:\n$%.2f",
                sum(Shifts) / 60 * Wage * (100 - Tax) * 0.01, sum(Shifts) / 60 * Wage * (100 - Tax) * 0.01 / Shifts.size()));

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

        Button ExitB = (Button) findViewById(R.id.ExitB);
        ExitB.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View view) {

                finish();
            }
        });
    }

    @Override
    public void onBackPressed() {

        finish();
        Intent WageAndTaxIntent = new Intent(getApplicationContext(), WageAndTaxActivity.class);
        WageAndTaxIntent.putExtra("com.example.paycheckcalculator.SHIFTS", Shifts);
        startActivity(WageAndTaxIntent);
    }

    public int sum(ArrayList<Integer> Shifts) {

        int Total = 0;
        for(int z = 0; z < Shifts.size(); z++) Total += Shifts.get(z);
        return Total;
    }
}