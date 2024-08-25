package com.example.myapplication2;

import android.app.ProgressDialog;
import android.content.ClipData;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Spinner;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.cardview.widget.CardView;

import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.StorageReference;
import com.google.firebase.storage.UploadTask;

import java.util.HashMap;

public class AddData extends AppCompatActivity {

    EditText username,email,address,age,phone;
    Spinner spinnerCountryCode;
    EditText showdate;
    ImageView openDatePickerButton,sendimage1;
    CardView submit;
    FirebaseAuth auth;
    ProgressDialog dialog;
    DatabaseReference databaseReference;
    Spinner dropdown;
    String radioButton;
    private int GALLARY_PICK_REQUEST=100;
    Uri imageuri=null;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_data);
        username=findViewById(R.id.username);
        email=findViewById(R.id.email);
        address=findViewById(R.id.address);
        age=findViewById(R.id.age);
        submit=findViewById(R.id.submit);
        phone=findViewById(R.id.phone);
        spinnerCountryCode=findViewById(R.id.spinnerCountryCode);
        sendimage1=findViewById(R.id.sendimage1);
        Spinner spinnerCountryCode = findViewById(R.id.spinnerCountryCode);
        ArrayAdapter<CharSequence> adapter = ArrayAdapter.createFromResource(this, R.array.country_codes, android.R.layout.simple_spinner_item);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinnerCountryCode.setAdapter(adapter);
        spinnerCountryCode.setSelection(0);

        auth = FirebaseAuth.getInstance();
        FirebaseUser currentUser = auth.getCurrentUser();
        if (currentUser != null)
        {
            String uid = currentUser.getUid();
            DatabaseReference db= FirebaseDatabase.getInstance().getReference("userinformation").child(uid);
            db.addListenerForSingleValueEvent(new ValueEventListener() {
                @Override
                public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                    if (dataSnapshot.exists()) {
                        String username1 = dataSnapshot.child("username").getValue(String.class);
                        String email1 = dataSnapshot.child("email").getValue(String.class);

                        if (username != null && email!=null)
                        {
                            // Update TextView elements
                            username.setText(username1);
                            email.setText(email1);
                        }
                    }
                }

                @Override
                public void onCancelled(@NonNull DatabaseError databaseError) {
                    Toast.makeText(AddData.this,"Error " ,Toast.LENGTH_SHORT).show();
                }
            });
        }

        dialog=new ProgressDialog(this);
        auth=FirebaseAuth.getInstance();
        databaseReference= FirebaseDatabase.getInstance().getReference("information");

        sendimage1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                opengallary();
            }
        });

        submit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v)
            {
                checkvalidation();
            }
        });
    }
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if(requestCode==GALLARY_PICK_REQUEST && resultCode== RESULT_OK )
        {
            if(data!=null)
            {
                if (data.getData() != null)
                {
                    imageuri=data.getData();
                    sendimage1.setImageURI(imageuri);
                }
                else if (data.getClipData() != null)
                {
                    ClipData clipData = data.getClipData();
                    for (int i = 0; i < clipData.getItemCount(); i++)
                    {
                        imageuri = clipData.getItemAt(i).getUri();
                    }
                    sendimage1.setImageURI(imageuri);
                }
            }
       }
    }



    private void checkvalidation()
    {
        String emailPattern = "[a-zA-Z0-9._-]+@[a-z]+\\.+[a-z]+";
        String Username =username.getText().toString();
        String Email =email.getText().toString();
        String Address =address.getText().toString();
        String Age =age.getText().toString();
        String Phonee =phone.getText().toString();

       String spinnerCountryCode1=spinnerCountryCode.getSelectedItem().toString();
       String Phone=spinnerCountryCode1 +" "+Phonee;


        if (Username.isEmpty())
        {
            username.setError("required");
        }
        else if (Email.isEmpty())
        {
            email.setError("required");
        }
        else if (!Email.matches(emailPattern))
        {
            email.setError("invalid email address");
        }
        else if (Address.isEmpty())
        {
            address.setError("required");
        }
        else if (Age.isEmpty())
        {
            age.setError("required");
        }
        else if (Phonee.length()!=10)
        {
            phone.setError("Mobile number should be 10 digit");
        }
        else
        {
            adddata(Username,Email,Address,Age,Phone,imageuri);
        }
    }
    private void adddata(String username, String email, String address, String age, String phone, Uri imageuri)
    {
        if (imageuri == null) {
            // Handle the case where the image URI is null, for example, show an error message
            Toast.makeText(this, "Image not selected", Toast.LENGTH_SHORT).show();
            return;
        }
        dialog.setTitle("please wait");
        dialog.setMessage("data adding in a process");
        dialog.setCancelable(false);
        dialog.show();
//        databaseReference= FirebaseDatabase.getInstance().getReference("information");
        StorageReference storageReference= FirebaseStorage.getInstance().getReference().child("imahefolder");
        String uid=auth.getCurrentUser().getUid();
        StorageReference imagerefernce=storageReference.child(uid);
        imagerefernce.putFile(imageuri).addOnSuccessListener(new OnSuccessListener<UploadTask.TaskSnapshot>()
        {
            @Override
            public void onSuccess(UploadTask.TaskSnapshot taskSnapshot) {
                dialog.dismiss();
                imagerefernce.getDownloadUrl().addOnSuccessListener(new OnSuccessListener<Uri>()
                {
                    @Override
                    public void onSuccess(Uri uri)
                    {
                        String uid=auth.getCurrentUser().getUid();
                        DatabaseReference db=FirebaseDatabase.getInstance().getReference("information");
                        HashMap<String,Object> hashMap=new HashMap<>();
                        hashMap.put("username",username);
                        hashMap.put("email",email);
                        hashMap.put("address",address);
                        hashMap.put("age",age);
                        hashMap.put("phone",phone);
                        hashMap.put("url",uri.toString());
                        db.child(uid).setValue(hashMap);
                        Toast.makeText(AddData.this,"data and image added",Toast.LENGTH_SHORT).show();
//                        startActivity(new Intent(AddData.this,Logindashboard.class));
                        startActivity(new Intent(AddData.this,    SideNavigationDrawerActivity.class));


                    }
                });
            }
        });
    }
    private void opengallary()
    {
        Intent intent=new Intent(Intent.ACTION_GET_CONTENT);
        intent.setType("image/*");
        intent.putExtra(Intent.EXTRA_ALLOW_MULTIPLE,true);
        startActivityForResult(intent,GALLARY_PICK_REQUEST);
    }
}
