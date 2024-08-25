package com.example.myapplication2;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

public class contact extends AppCompatActivity {

    TextView contactcall1;
    ImageView backcontact1;
    EditText email,subject,message1;
    Button send;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_contact);
        contactcall1=findViewById(R.id.contactcall);
        backcontact1=findViewById(R.id.backcontact);
        email=findViewById(R.id.email);
        subject=findViewById(R.id.subject);
        message1=findViewById(R.id.message1);
        send=findViewById(R.id.send);


        contactcall1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(contact.this, callus.class));
            }
        });
        backcontact1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(contact.this, Logindashboard.class));
            }
        });
        send.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                sendemail();
            }
        });


    }

    private void sendemail()
    {
        String mail=email.getText().toString();
        String Sub=subject.getText().toString();
        String msg=message1.getText().toString();

        Intent intent=new Intent(Intent.ACTION_SEND);
        intent.putExtra(Intent.EXTRA_EMAIL,new String[]{mail});
        intent.putExtra(Intent.EXTRA_SUBJECT,Sub);
        intent.putExtra(Intent.EXTRA_TEXT,msg);
        intent.setType("message/rfc822");
        startActivity(Intent.createChooser(intent,"vishalsajjan02@gmail.com"));
    }
}