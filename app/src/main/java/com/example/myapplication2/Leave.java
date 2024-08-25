package com.example.myapplication2;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

public class Leave extends AppCompatActivity {
    ImageView backleave1;
    TextView myrequest1;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_leave);

        backleave1=findViewById(R.id.backleave);
        myrequest1=findViewById(R.id.myrequest);


        backleave1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(Leave.this, Logindashboard.class));
            }
        });
        myrequest1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(Leave.this, LeaveMyrequest.class));
            }
        });
    }
}