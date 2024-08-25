package com.example.myapplication2;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;
import androidx.cardview.widget.CardView;

public class callus extends AppCompatActivity {

    TextView emailusall;
    ImageView backcallu1;
    CardView phonecall,whatsapp1;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_callus);
        emailusall=findViewById(R.id.emailus10);
        backcallu1=findViewById(R.id.backcallus);
        phonecall=findViewById(R.id.phonecall);
        whatsapp1=findViewById(R.id.whatsapp1);
        String phone="9823839781";

        emailusall.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(callus.this, contact.class));
            }
        });
        backcallu1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(callus.this, contact.class));
            }
        });
        phonecall.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Intent intent1=new Intent(Intent.ACTION_DIAL);
                intent1.setData(Uri.parse("tel:"+phone));
                startActivity(intent1);
            }
        });
        whatsapp1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String url="https://wa.me/"+phone;
                Intent intent1=new Intent(Intent.ACTION_VIEW,Uri.parse(url));
                startActivity(intent1);
            }
        });




    }
}