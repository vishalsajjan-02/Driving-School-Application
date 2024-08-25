package com.example.myapplication2;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;
import androidx.cardview.widget.CardView;

public class Safety extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_safety);

        // Get a reference to the CardView
        CardView cardView1 = findViewById(R.id.cardView1);
        CardView cardView2 = findViewById(R.id.cardView2);
        CardView cardView3 = findViewById(R.id.cardView3);

        // Get a reference to the TextView for Covid safety
        TextView covidTextView1= findViewById(R.id.covid1);
        TextView covidTextView2 = findViewById(R.id.covid2);
        TextView covidTextView3 = findViewById(R.id.covid3);

        // Set an OnClickListener for the Covid TextView
        covidTextView1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Toggle the visibility of the CardView
                if (cardView1.getVisibility() == View.VISIBLE) {
                    cardView1.setVisibility(View.GONE);
                } else {
                    cardView1.setVisibility(View.VISIBLE);
                }
            }
        });

        covidTextView2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Toggle the visibility of the CardView
                if (cardView2.getVisibility() == View.VISIBLE) {
                    cardView2.setVisibility(View.GONE);
                } else {
                    cardView2.setVisibility(View.VISIBLE);
                }
            }
        });

        covidTextView3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Toggle the visibility of the CardView
                if (cardView3.getVisibility() == View.VISIBLE) {
                    cardView3.setVisibility(View.GONE);
                } else {
                    cardView3.setVisibility(View.VISIBLE);
                }
            }
        });
    }
}
