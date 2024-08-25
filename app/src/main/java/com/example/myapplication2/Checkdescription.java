package com.example.myapplication2;

import android.app.NotificationChannel;
import android.app.NotificationManager;
import android.content.Intent;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.NotificationCompat;
import androidx.core.app.NotificationManagerCompat;

import com.denzcoskun.imageslider.ImageSlider;
import com.denzcoskun.imageslider.constants.ScaleTypes;
import com.denzcoskun.imageslider.models.SlideModel;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;

public class Checkdescription extends AppCompatActivity
{
    TextView text1,text2,text3,text4,text5;
    String username,email,address,age,phone,imageurl;
    ImageView image;
    Button btnphonecall,btnphonewhatsapp,btnnotificationpro;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_checkdescription);
        text1=findViewById(R.id.text1);
        text2=findViewById(R.id.text2);
        text3=findViewById(R.id.text3);
        text4=findViewById(R.id.text4);
        text5=findViewById(R.id.text5);
        btnphonecall=findViewById(R.id.btnphonecall);
        btnnotificationpro=findViewById(R.id.btnnotification1);
        btnphonewhatsapp=findViewById(R.id.btnphonewhatsapp);
        image=findViewById(R.id.image);
//        ArrayList<SlideModel> imagelist=new ArrayList<>();
        ArrayList<SlideModel> imagelist=new ArrayList<>();


        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O)
        {
            NotificationChannel channel=new NotificationChannel("my", "my", NotificationManager.IMPORTANCE_DEFAULT);
            NotificationManager manager=getSystemService(NotificationManager.class);
            manager.createNotificationChannel(channel);
        }


        Intent intent=getIntent();
        if(intent != null)
        {
            username=intent.getStringExtra("username");
            email=intent.getStringExtra("email");
            address=intent.getStringExtra("address");
            age=intent.getStringExtra("age");
            phone=intent.getStringExtra("phone");
            imageurl=intent.getStringExtra("image");

            text1.setText("username :"+" "+username);
            text2.setText("email :"+" "+email);
            text3.setText("address :"+" "+address);
            text4.setText("age :"+" "+age);
            text5.setText("phone :"+" "+phone);
            Picasso.get().load(imageurl).into(image);
//            imagelist.add(new SlideModel(imageurl," ", ImageView.ScaleType.CENTER_CROP));

            imagelist.add(new SlideModel(imageurl," ", ScaleTypes.CENTER_CROP));
            imagelist.add(new SlideModel(imageurl," ", ScaleTypes.CENTER_CROP));
            imagelist.add(new SlideModel(imageurl," Hello ", ScaleTypes.CENTER_CROP));
            ImageSlider imageSlider=findViewById(R.id.image_slider);
            imageSlider.setImageList(imagelist);

            btnphonecall.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Intent intent1=new Intent(Intent.ACTION_DIAL);
                    intent1.setData(Uri.parse("tel:"+phone));
                    startActivity(intent1);
                }
            });
            btnphonewhatsapp.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    String url="https://wa.me/"+phone;
                    Intent intent1=new Intent(Intent.ACTION_VIEW,Uri.parse(url));
                    startActivity(intent1);
                }
            });
            btnnotificationpro.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    NotificationCompat.Builder builder=new NotificationCompat.Builder(Checkdescription.this,"my");
                    builder.setContentTitle("Driving class");
                    builder.setContentText("how are you");
                    builder.setSmallIcon(R.drawable.ic_launcher_foreground );
                    builder.setAutoCancel(true);

                    NotificationManagerCompat managerCompat= NotificationManagerCompat.from(Checkdescription.this);
                    managerCompat.notify(1,builder.build());
                }
            });
        }

    }
}