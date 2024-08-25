package com.example.myapplication2;

import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.util.HashMap;

public class Signupp extends AppCompatActivity {


    TextView textsignin1;
    ImageView backregistration1;
    Button registerbtn;
    EditText editusername,editemail,editpassword,editcnfpassword;
    ProgressDialog dialog;
    FirebaseAuth auth;
    DatabaseReference databaseReference;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_signupp);
        textsignin1 = findViewById(R.id.textsignin1);
        backregistration1=findViewById(R.id.backregistration);
        registerbtn=findViewById(R.id.registerbtn);
        editusername=findViewById(R.id.editusername);
        editemail=findViewById(R.id.editemail);
        editpassword=findViewById(R.id.editpassword);
        editcnfpassword=findViewById(R.id.editcnfpassword);

        dialog=new ProgressDialog(this);
        auth=FirebaseAuth.getInstance();
        databaseReference= FirebaseDatabase.getInstance().getReference("userinformation");

        registerbtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v)
            {
                checkvalidation();
            }
        });
        textsignin1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(Signupp.this, MainActivity.class));
            }
        });
        backregistration1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(Signupp.this,MainActivity.class));
            }
        });
//        registerbtn.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                startActivity(new Intent(Signupp.this, Logindashboard.class));
//            }
//        });
    }

    private void checkvalidation() {
        String Username=editusername.getText().toString();
        String Email=editemail.getText().toString();
        String Password=editpassword.getText().toString();
        String Cnfpass=editcnfpassword.getText().toString();
        String emailPattern = "[a-zA-Z0-9._-]+@[a-z]+\\.+[a-z]+";

        if(Username.isEmpty())
        {
            editusername.setError("Required");
        }
        else if(Email.isEmpty())
        {
            editemail.setError("Required");
        }
        else if (!Email.matches(emailPattern))
        {
            editemail.setError("invalid email address");
        }
        else  if(Password.length()<6)
        {
            editpassword.setError("length small than 6");
        }
        else  if(Cnfpass.length()<6)
        {
            editcnfpassword.setError("length small than 6");
        }
        else if (!Password.equals(Cnfpass))
        {
            Toast.makeText(this,"password and cinfirm password does not matched",Toast.LENGTH_SHORT).show();
        }
        else
        {
            AuthenticateUser(Username,Email,Password);
        }
    }
    private void AuthenticateUser(String username, String email, String password)
    {
        dialog.setMessage("please wait.....");
        dialog.setCancelable(false);
        dialog.show();

        auth.createUserWithEmailAndPassword(email,password).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
            @Override
            public void onComplete(@NonNull Task<AuthResult> task) {
                if(task.isSuccessful())
                {
                    dialog.dismiss();
                    String uid=auth.getCurrentUser().getUid();
                    HashMap<String,Object>hashMap=new HashMap<>();
                    hashMap.put("username",username);
                    hashMap.put("email",email);
                    hashMap.put("password",password);
                    databaseReference.child(uid).setValue(hashMap);
                    Toast.makeText(Signupp.this, "user created", Toast.LENGTH_SHORT).show();
                    Intent intent=new Intent(Signupp.this,AddData.class);
                    intent.putExtra("email",email);
                    intent.putExtra("username",username);
                    startActivity(intent);
                }
            }
        }).addOnFailureListener(new OnFailureListener() {
            @Override
            public void onFailure(@NonNull Exception e) {
                dialog.dismiss();
                Toast.makeText(Signupp.this, "Error"+e.getMessage(), Toast.LENGTH_SHORT).show();
            }
        });
    }
    public  void openActivity(View v)
    {
    }
}