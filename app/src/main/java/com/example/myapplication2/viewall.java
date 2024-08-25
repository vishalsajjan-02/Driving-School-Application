package com.example.myapplication2;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.CheckBox;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import com.example.myapplication2.ui.gallery.GalleryFragment;

public class viewall extends AppCompatActivity {

    ImageView back1;
    CheckBox checkBox1, checkBox2, checkBox3, checkBox4;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_viewall);

        back1 = findViewById(R.id.backpage);
        checkBox1 = findViewById(R.id.checkbox1);
        checkBox2 = findViewById(R.id.checkbox2);
        checkBox3 = findViewById(R.id.checkbox3);
        checkBox4 = findViewById(R.id.checkbox4);

        back1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(viewall.this, Logindashboard.class));
            }
        });

        checkBox1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (checkBox1.isChecked()) {
                    // Retrieve data for checkbox 1
                    String id = "01";
                    String uniformDriving = ((TextView) findViewById(R.id.uniform1)).getText().toString();
                    String hoursColved = ((TextView) findViewById(R.id.hours1)).getText().toString();
                    String price = ((TextView) findViewById(R.id.price)).getText().toString();
                    Intent intent = new Intent(viewall.this, GalleryFragment.class);
                    intent.putExtra("ID", id);
                    intent.putExtra("UniformDriving", uniformDriving);
                    intent.putExtra("HoursColved", hoursColved);
                    intent.putExtra("Price", price);



                }
            }
        });

        checkBox2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (checkBox2.isChecked()) {
                    // Retrieve data for checkbox 1
                    String id = "02";
                    String uniformDriving = ((TextView) findViewById(R.id.uniform2)).getText().toString();
                    String hoursColved = ((TextView) findViewById(R.id.hours2)).getText().toString();
                    String price = ((TextView) findViewById(R.id.price2)).getText().toString();

                    Intent intent = new Intent(viewall.this, GalleryFragment.class);
                    intent.putExtra("ID", id);
                    intent.putExtra("UniformDriving", uniformDriving);
                    intent.putExtra("HoursColved", hoursColved);
                    intent.putExtra("Price", price);
                   }
            }
        });


        checkBox3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (checkBox3.isChecked()) {
                    // Retrieve data for checkbox 1
                    String id = "03";
                    String uniformDriving = ((TextView) findViewById(R.id.uniform3)).getText().toString();
                    String hoursColved = ((TextView) findViewById(R.id.hours3)).getText().toString();
                    String price = ((TextView) findViewById(R.id.price3)).getText().toString();

                    Intent intent = new Intent(viewall.this, GalleryFragment.class);
                    intent.putExtra("ID", id);
                    intent.putExtra("UniformDriving", uniformDriving);
                    intent.putExtra("HoursColved", hoursColved);
                    intent.putExtra("Price", price);
                  }
            }
        });

        checkBox4.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (checkBox4.isChecked()) {
                    // Retrieve data for checkbox 1
                    String id = "04";
                    String uniformDriving = ((TextView) findViewById(R.id.uniform4)).getText().toString();
                    String hoursColved = ((TextView) findViewById(R.id.hours4)).getText().toString();
                    String price = ((TextView) findViewById(R.id.price4)).getText().toString();

                    Intent intent = new Intent(viewall.this, GalleryFragment.class);
                    intent.putExtra("ID", id);
                    intent.putExtra("UniformDriving", uniformDriving);
                    intent.putExtra("HoursColved", hoursColved);
                    intent.putExtra("Price", price);
                  }
            }
        });

    }
}
