package com.example.myapplication2;

import android.os.Bundle;
import android.view.View;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.cardview.widget.CardView;

public class bookingsection extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_bookingsection);

        CardView cardSubmit = findViewById(R.id.cardSubmit);

        cardSubmit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                showConfirmationDialog();
            }
        });
    }


    private void showConfirmationDialog() {
        AlertDialog.Builder builder = new AlertDialog.Builder(bookingsection.this);
        builder.setTitle("Confirm Booking");
        builder.setMessage("Are you sure you want to book this session?");

        builder.setPositiveButton("Yes", (dialog, which) -> {
            // Handle positive button click
            dialog.dismiss();
            // Add your booking logic here
            showSessionBookedDialog();
        });

        builder.setNegativeButton("No", (dialog, which) -> {
            // Handle negative button click
            dialog.dismiss();
        });

        AlertDialog dialog = builder.create();
        dialog.show();
    }
    private void showSessionBookedDialog() {
        AlertDialog.Builder builder = new AlertDialog.Builder(bookingsection.this);
        builder.setTitle("Booking Confirmed");
        builder.setMessage("Your session is booked.");
        builder.setPositiveButton("OK", (dialog, which) -> dialog.dismiss());
        AlertDialog dialog = builder.create();
        dialog.show();
    }
}