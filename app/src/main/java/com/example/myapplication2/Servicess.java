package com.example.myapplication2;

import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;
import androidx.cardview.widget.CardView;

public class Servicess extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_servicess);

        CardView cardView1 = findViewById(R.id.cardView1);
        CardView cardView2 = findViewById(R.id.cardView2);
        CardView cardView3 = findViewById(R.id.cardView3);
        CardView cardView4 = findViewById(R.id.cardView4);
        CardView cardView5 = findViewById(R.id.cardView5);
        CardView cardView6 = findViewById(R.id.cardView6);

        // Get a reference to the TextView for Covid safety
        TextView covidTextView1= findViewById(R.id.a1);
        TextView covidTextView2= findViewById(R.id.a2);
        TextView covidTextView3= findViewById(R.id.a3);
        TextView covidTextView4= findViewById(R.id.a4);
        TextView covidTextView5= findViewById(R.id.a5);
        TextView covidTextView6= findViewById(R.id.a6);

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

        covidTextView4.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Toggle the visibility of the CardView
                if (cardView4.getVisibility() == View.VISIBLE) {
                    cardView4.setVisibility(View.GONE);
                } else {
                    cardView4.setVisibility(View.VISIBLE);
                }
            }
        });

        covidTextView5.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Toggle the visibility of the CardView
                if (cardView5.getVisibility() == View.VISIBLE) {
                    cardView5.setVisibility(View.GONE);
                } else {
                    cardView5.setVisibility(View.VISIBLE);
                }
            }
        });

        covidTextView6.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Toggle the visibility of the CardView
                if (cardView6.getVisibility() == View.VISIBLE) {
                    cardView6.setVisibility(View.GONE);
                } else {
                    cardView6.setVisibility(View.VISIBLE);
                }
            }
        });


    }
}