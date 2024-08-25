package com.example.myapplication2;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Locale;

public class Logindashboard extends AppCompatActivity {

    TextView viewall,Domen,date;
    RelativeLayout teacher1,invoice1,leaveall,contactdetails,payments,profile;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_logindashboard);
        viewall = findViewById(R.id.view);
        teacher1 = findViewById(R.id.teacher);
        invoice1=findViewById(R.id.invoice);
        leaveall=findViewById(R.id.leave);
        contactdetails=findViewById(R.id.contact);
        payments=findViewById(R.id.payment);
        profile=findViewById(R.id.profile);
        Domen=findViewById(R.id.Domen);
        date=findViewById(R.id.date);

        FirebaseAuth auth = FirebaseAuth.getInstance();
        FirebaseUser currentUser = auth.getCurrentUser();


        if (currentUser != null)
        {
            String uid = currentUser.getUid();
            DatabaseReference db= FirebaseDatabase.getInstance().getReference("information").child(uid);
            db.addListenerForSingleValueEvent(new ValueEventListener() {
                @Override
                public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                    if (dataSnapshot.exists()) {
                        String username = dataSnapshot.child("username").getValue(String.class);

                        if (username != null ) {
                            // Update TextView elements
                            Domen.setText(username);
                        }
                    }
                }

                @Override
                public void onCancelled(@NonNull DatabaseError databaseError) {
                    Toast.makeText(Logindashboard.this,"Error " ,Toast.LENGTH_SHORT).show();
                }
            });

            Calendar calendar=Calendar.getInstance();
            SimpleDateFormat dateFormat=new SimpleDateFormat("dd.MM.yyyy", Locale.getDefault());
            String currentDate=dateFormat.format(calendar.getTime());
            date.setText(currentDate);
        }


        profile.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(Logindashboard.this,profile.class));
            }
        });

        viewall.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(Logindashboard.this,viewall.class));
            }
        });

        teacher1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(Logindashboard.this, Teacher.class));
            }
        });
        invoice1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(Logindashboard.this,invoice.class));
            }
        });
        leaveall.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(Logindashboard.this, Leave.class));
            }
        });
        contactdetails.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(Logindashboard.this, contact.class));
            }
        });
        payments.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(Logindashboard.this, payment.class));
            }
        });



    }
}