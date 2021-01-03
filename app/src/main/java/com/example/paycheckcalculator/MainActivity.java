package com.example.paycheckcalculator;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {

    ArrayList<Integer> Shifts = new ArrayList<Integer>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        if(getIntent().hasExtra("com.example.paycheckcalculator.SHIFTS")) Shifts = getIntent().getExtras().getIntegerArrayList("com.example.paycheckcalculator.SHIFTS");

        final TextView TimeTotalTV = (TextView) findViewById(R.id.TimeTotalTV);
        TimeTotalTV.setText(Shifts.size() == 0 ? "Welcome to PayCheckCalculator" : String.format("Total Time: %02d:%02d\n", sum(Shifts) / 60, sum(Shifts) % 60));

        Button AddShiftB = (Button) findViewById(R.id.AddShiftB);
        AddShiftB.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View view) {

                finish();
                Intent AddShiftIntent = new Intent(getApplicationContext(), AddShiftActivity.class);
                AddShiftIntent.putExtra("com.example.paycheckcalculator.SHIFTS", Shifts);
                startActivity(AddShiftIntent);
            }
        });

        Button ResetB = (Button) findViewById(R.id.ResetB);
        ResetB.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View view) {

                Shifts =  new ArrayList<Integer>();
                TimeTotalTV.setText("Welcome to PayCheckCalculator");
            }
        });

        Button WageAndTaxB = (Button) findViewById(R.id.WageAndTaxB);
        WageAndTaxB.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View view) {

                finish();
                Intent WageAndTaxIntent = new Intent(getApplicationContext(), WageAndTaxActivity.class);
                WageAndTaxIntent.putExtra("com.example.paycheckcalculator.SHIFTS", Shifts);
                startActivity(WageAndTaxIntent);
            }
        });

        Button ExitB = (Button) findViewById(R.id.ExitB);
        ExitB.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View view) {

                finish();
            }
        });

        Button RemovePreviousB = (Button) findViewById(R.id.RemovePreviousB);
        RemovePreviousB.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View view) {

                Shifts.remove(Shifts.size() - 1);
                TimeTotalTV.setText(Shifts.size() == 0 ? "Welcome to PayCheckCalculator" : String.format("Total Time: %02d:%02d\n", sum(Shifts) / 60, sum(Shifts) % 60));
            }
        });
    }

    @Override
    public void onBackPressed() {

        if(Shifts.size() == 0) {

            finish();
        }
    }

    public int sum(ArrayList<Integer> Shifts) {

        int Total = 0;
        for(int z = 0; z < Shifts.size(); z++) Total += Shifts.get(z);
        return Total;
    }
}