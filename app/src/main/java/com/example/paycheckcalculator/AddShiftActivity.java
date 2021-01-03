package com.example.paycheckcalculator;

import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.TimePicker;

import org.w3c.dom.Text;

import java.util.ArrayList;
import java.util.Scanner;

public class AddShiftActivity extends AppCompatActivity {

    ArrayList<Integer> Shifts = new ArrayList<Integer>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_shift);

        Shifts = getIntent().getExtras().getIntegerArrayList("com.example.paycheckcalculator.SHIFTS");

        TextView DayCounterTV = (TextView) findViewById(R.id.DayCounterTV);
        DayCounterTV.setText("Day " + (Shifts.size() + 1));

        Button ConfirmB = (Button) findViewById(R.id.ConfirmB);
        ConfirmB.setOnClickListener(new View.OnClickListener() {

            @RequiresApi(api = Build.VERSION_CODES.M)
            @Override
            public void onClick(View view) {

                int ShiftEntry = ((TimePicker) findViewById(R.id.EndTP)).getHour() * 60 + ((TimePicker) findViewById(R.id.EndTP)).getMinute()
                        - ((TimePicker) findViewById(R.id.StartTP)).getHour() * 60 - ((TimePicker) findViewById(R.id.StartTP)).getMinute();

                CheckBox TenBreakCB = (CheckBox) findViewById(R.id.TenMinuteBreakCB);
                if(TenBreakCB.isChecked()) ShiftEntry -= 10;

                CheckBox FifteenBreakCB = (CheckBox) findViewById(R.id.FifteenMinuteBreakCB);
                if(FifteenBreakCB.isChecked()) ShiftEntry -= 15;

                CheckBox TwentyBreakCB = (CheckBox) findViewById(R.id.TwentyMinuteBreakCB);
                if(TwentyBreakCB.isChecked()) ShiftEntry -= 20;

                CheckBox ThirtyBreakCB = (CheckBox) findViewById(R.id.ThirtyMinuteBreakCB);
                if(ThirtyBreakCB.isChecked()) ShiftEntry -= 30;

                Shifts.add(ShiftEntry);

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