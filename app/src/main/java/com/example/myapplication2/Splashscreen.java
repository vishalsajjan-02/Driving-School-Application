package com.example.myapplication2;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;

import com.google.firebase.auth.FirebaseAuth;

public class Splashscreen extends AppCompatActivity {

    FirebaseAuth auth;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splashscreen);
        auth= FirebaseAuth.getInstance();

        new Handler().postDelayed(new Runnable()
        {
            public void run()
            {
                    startActivity(new Intent(Splashscreen.this , MainActivity.class));
            }
        },3000);


    }
}