package com.example.myapplication2.quizzz;

import android.os.Bundle;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import com.example.myapplication2.R;

public class score extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_score);
        int score = getIntent().getIntExtra("score", 0);

        // Display the score
        TextView scoreTextView = findViewById(R.id.scoreTextView);
        scoreTextView.setText("Your Score: " + score);
    }
}
