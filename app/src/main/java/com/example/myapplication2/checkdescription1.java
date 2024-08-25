package com.example.myapplication2;

import android.app.NotificationChannel;
import android.app.NotificationManager;
import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import com.denzcoskun.imageslider.models.SlideModel;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;

public class checkdescription1 extends AppCompatActivity {
    TextView text11, text22, text33, text44, text55;
    String Username, carcompany, carmodel, experience, carnumber, url;
    ImageView image;
    LinearLayout bookicon;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_checkdescription1);
        text11 = findViewById(R.id.text11);
        text22 = findViewById(R.id.text22);
        text33 = findViewById(R.id.text33);
        text44 = findViewById(R.id.text44);
        text55 = findViewById(R.id.text55);
        image = findViewById(R.id.image);
        bookicon = findViewById(R.id.bookicon); // Initialize the button

        ArrayList<SlideModel> imagelist = new ArrayList<>();

        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
            NotificationChannel channel = new NotificationChannel("my notification", "my notification", NotificationManager.IMPORTANCE_DEFAULT);
            NotificationManager manager = getSystemService(NotificationManager.class);
            manager.createNotificationChannel(channel);
        }

        Intent intent2 = getIntent();
        if (intent2 != null) {
            Username = intent2.getStringExtra("username");
            carcompany = intent2.getStringExtra("company");
            carmodel = intent2.getStringExtra("model");
            experience = intent2.getStringExtra("experience");
            carnumber = intent2.getStringExtra("carnumber");
            url = intent2.getStringExtra("image");

            text11.setText("Username :" + " " + Username);
            text22.setText("carcompany :" + " " + carcompany);
            text33.setText("carmodel :" + " " + carmodel);
            text44.setText("experience :" + " " + experience);
            text55.setText("carnumber :" + " " + carnumber);
            Picasso.get().load(url).into(image);
        }
        bookicon.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
              startActivity(new Intent(checkdescription1.this,bookingsection.class));
            }
        });

    }

    public void onCarButtonClick(View view) {
        startActivity(new Intent(checkdescription1.this, bookingsection.class));
    }
}
