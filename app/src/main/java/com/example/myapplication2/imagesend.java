package com.example.myapplication2;

import android.app.ProgressDialog;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Toast;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.cardview.widget.CardView;

import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.StorageReference;
import com.google.firebase.storage.UploadTask;

import java.util.HashMap;

public class imagesend extends AppCompatActivity {
    EditText user , useremail , address , age , phone ;

    CardView card;

    ImageView img;

    DatabaseReference databaseReference;

    FirebaseAuth auth;

    ProgressDialog progressDialog;

    private int GALLERY_PICK_REQUEST = 100;

    Uri imageuri = null;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_image_send);
        user = findViewById(R.id.user);
        useremail = findViewById(R.id.useremail);
        address = findViewById(R.id.address);
        age = findViewById(R.id.age);
        phone = findViewById(R.id.phone);
        img = findViewById(R.id.img);
        card = findViewById(R.id.card);
        progressDialog = new ProgressDialog(this);
        auth = FirebaseAuth.getInstance();
        databaseReference = FirebaseDatabase.getInstance().getReference("Informationwithimages");
        img.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                OpenGallery();
            }
        });
        card.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                CheckValidation();
            }
        });    }

    private void CheckValidation() {
        String UserName = user.getText().toString();
        String Email = useremail.getText().toString();
        String Address = address.getText().toString();
        String Age = age.getText().toString();
        String Phone = phone.getText().toString();

        if (UserName.isEmpty()){
            user.setText("Required");
        }
        else if (Email.isEmpty()){
            useremail.setText("Required");
        }
        else if (Address.isEmpty()){
            address.setText("Required");
        }else if (Age.isEmpty()){
            age.setText("Required");
        }else if (Phone.isEmpty()){
            phone.setText("Required");
        }else {
            if (imageuri != null) {
                AddDataToFirebase(UserName, Email, Address, Age, Phone, imageuri);
            } else {
                // Handle the case when imageuri is null
                Toast.makeText(imagesend.this, "Please select an image", Toast.LENGTH_SHORT).show();
            }
        }
    }
    private void AddDataToFirebase(String userName, String email, String address, String age, String phone, Uri imageuri) {
        progressDialog.setMessage("Please Wait....");
        progressDialog.setCancelable(false);
        progressDialog.show();

        StorageReference storageReference = FirebaseStorage.getInstance().getReference().child("ImageFolder");
//        StorageReference imagereference = storageReference.child("uid");
        // Replace "uid" with the actual UID of the user
        StorageReference imagereference = storageReference.child(auth.getCurrentUser().getUid());

        imagereference.putFile(imageuri).addOnSuccessListener(new OnSuccessListener<UploadTask.TaskSnapshot>() {
            @Override
            public void onSuccess(UploadTask.TaskSnapshot taskSnapshot) {
                progressDialog.dismiss();
                imagereference.getDownloadUrl().addOnSuccessListener(new OnSuccessListener<Uri>() {
                    @Override
                    public void onSuccess(Uri uri) {
                        String uid = auth.getCurrentUser().getUid();
//                        DatabaseReference db = FirebaseDatabase.getInstance().getReference("Informationwithimages");
                        HashMap<String , Object> hashmap = new HashMap<>();
                        hashmap.put("user" , userName);
                        hashmap.put("useremail" , email);
                        hashmap.put("address" , address);
                        hashmap.put("age" , age);
                        hashmap.put("phone" , phone);
//                        hashmap.put("url" , uri.toString());
                        hashmap.put("url", uri.toString());

//                        db.child(uid).push().setValue(hashmap);
                        DatabaseReference db = FirebaseDatabase.getInstance().getReference("Informationwithimages").child(auth.getCurrentUser().getUid());
                        db.push().setValue(hashmap);
                        Toast.makeText(imagesend.this, "Data And Image Added", Toast.LENGTH_SHORT).show();
                    }
                });
            }
        });
    }

    private void OpenGallery() {
        Intent intent = new Intent(Intent.ACTION_GET_CONTENT);
        intent.setType("image/*");
        intent.putExtra(Intent.EXTRA_ALLOW_MULTIPLE, true);
        startActivityForResult(intent , GALLERY_PICK_REQUEST);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == GALLERY_PICK_REQUEST && resultCode == RESULT_OK) {
            imageuri = data.getData();
            img.setImageURI(imageuri);
}
}
}



