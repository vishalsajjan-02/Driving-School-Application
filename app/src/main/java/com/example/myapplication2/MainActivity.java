package com.example.myapplication2;


import android.app.AlertDialog;
import android.app.ProgressDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import com.airbnb.lottie.LottieAnimationView;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
public class MainActivity extends AppCompatActivity
{
    EditText editpass,editemail1;
    Button btn;
    TextView textlsignup;
    ProgressDialog dialog;
    FirebaseAuth auth;
    LottieAnimationView animationView;

    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        editpass = findViewById(R.id.enterpassword);
        editemail1 = findViewById(R.id.editemaillogin);
        btn = findViewById(R.id.loginbtn);
        textlsignup = findViewById(R.id.textlinear1);

        animationView = findViewById(R.id.animation_view);
        animationView.playAnimation();

        auth = FirebaseAuth.getInstance();
        FirebaseUser currentUser = auth.getCurrentUser();

        dialog=new ProgressDialog(this);
//        auth =FirebaseAuth.getInstance();

        btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                checkvalidation();
            }
        });
        textlsignup.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(MainActivity.this,Signupp.class));
            }
        });
    }
    private void checkvalidation()
    {
        String email = editemail1.getText().toString();
        String password = editpass.getText().toString();
        String emailPattern = "[a-zA-Z0-9._-]+@[a-z]+\\.+[a-z]+";
        if(email.isEmpty())
        {
            editemail1.setError("please enter email");
        }
        else if (password.isEmpty())
        {
            editpass.setError("enter your password");
        }
        else if (!email.matches(emailPattern))
        {
            editemail1.setError("invalid email address");
        }
        else if (password.length()<6)
        {
            editpass.setError("password length smaller than 6");
        }
        else
        {
            authenticationuser(email,password);
        }
    }
    private void authenticationuser(String email, String password)
    {
        dialog.setMessage("please wait");
        dialog.setCancelable(true);
        dialog.show();
        auth.signInWithEmailAndPassword(email, password).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
            @Override
            public void onComplete(@NonNull Task<AuthResult> task) {
                if (task.isSuccessful())
                {
                    dialog.dismiss();
                    Toast.makeText(MainActivity.this,"user logged in ",Toast.LENGTH_SHORT).show();
                    checkUserData(auth.getCurrentUser().getUid());
                }
            }
        }).addOnFailureListener(new OnFailureListener() {
            @Override
            public void onFailure(@NonNull Exception e) {
                dialog.dismiss();
                Toast.makeText(MainActivity.this,"Error "+e.getMessage() ,Toast.LENGTH_SHORT).show();
            }
        });
    }
    private void checkUserData(String uid)
    {
        DatabaseReference databaseReference1= FirebaseDatabase.getInstance().getReference("information").child(uid);
        databaseReference1.addListenerForSingleValueEvent(new ValueEventListener()
        {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                if(snapshot.exists())
                {
                    startActivity(new Intent(MainActivity.this, SideNavigationDrawerActivity.class));
                }
                else
                {
                    startActivity(new Intent(MainActivity.this, AddData.class));
                }
            }
            @Override
            public void onCancelled(@NonNull DatabaseError error) {
                startActivity(new Intent(MainActivity.this,AddData.class));
            }

        });
    }
    public void onBackPressed()
    {
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
//        builder.setTitle(R.string.app_name);
        builder.setIcon(R.mipmap.ic_launcher_round);
        builder.setMessage("Do you want to exit").setPositiveButton("Yes", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which)
            {
                finishAffinity();
            }
        }).setNegativeButton("No", new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialog, int which) {
                            dialog.cancel();
                        }
                    });
                    builder.create();
                    builder.show();
                }

    }





