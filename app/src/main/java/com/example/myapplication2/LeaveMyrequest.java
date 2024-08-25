package com.example.myapplication2;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

public class LeaveMyrequest extends AppCompatActivity {

    ImageView backleaverequest1;
    TextView myrequestLeaving1;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_leave_myrequest);
        backleaverequest1=findViewById(R.id.backleaverequest);
        myrequestLeaving1=findViewById(R.id.myrequestLeaving);


        backleaverequest1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(LeaveMyrequest.this, Leave.class));
            }
        });
        myrequestLeaving1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(LeaveMyrequest.this, Leave.class));
            }
        });


    }
}