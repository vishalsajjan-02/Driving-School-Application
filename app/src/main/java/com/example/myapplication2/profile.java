package com.example.myapplication2;


import android.app.ProgressDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.provider.MediaStore;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import com.bumptech.glide.Glide;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.StorageReference;

public class profile extends AppCompatActivity {

    TextView usernameall,emailall,addressall,ageall,phoneall;
    ImageView profileImage;
    FirebaseAuth auth;
    DatabaseReference db;
    private  int GALLARY_PICK_REQUEST=100;

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_profile);

        usernameall=findViewById(R.id.usernameall);
        emailall=findViewById(R.id.emailall);
        addressall=findViewById(R.id.addressall);
        ageall=findViewById(R.id.ageall);
        phoneall=findViewById(R.id.phoneall);


         auth = FirebaseAuth.getInstance();
        FirebaseUser currentUser = auth.getCurrentUser();
        profileImage = findViewById(R.id.profileImage);
        if (currentUser != null)
        {
            String uid = currentUser.getUid();
             db=FirebaseDatabase.getInstance().getReference("information").child(uid);
            db.addListenerForSingleValueEvent(new ValueEventListener() {
                @Override
                public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                    if (dataSnapshot.exists()) {
                        String username = dataSnapshot.child("username").getValue(String.class);
                        String email = dataSnapshot.child("email").getValue(String.class);
                        String address = dataSnapshot.child("address").getValue(String.class);
                        String age = dataSnapshot.child("age").getValue(String.class);
                        String phone = dataSnapshot.child("phone").getValue(String.class);
                        String imageUrl = dataSnapshot.child("url").getValue(String.class);

                        // Update TextView elements
                        if (username != null && email != null && address !=null && age!=null && phone!=null) {
                            // Update TextView elements
                            usernameall.setText("Username: " + username);
                            emailall.setText("Email: " + email);
                            addressall.setText("address: " + address);
                            ageall.setText("age: " + age);
                            phoneall.setText("phone: " + phone);

                            if (imageUrl != null) {
                                // Use Glide to load and display the image
                                Glide.with(profile.this)
                                        .load(imageUrl)
                                        .into(profileImage);
                            } else {
                                Toast.makeText(profile.this, "Image URL is null", Toast.LENGTH_SHORT).show();
                            }
                        } else {
                            Toast.makeText(profile.this, "Username or Email is null", Toast.LENGTH_SHORT).show();
                        }
                    } else {
                        Toast.makeText(profile.this, "DataSnapshot does not exist", Toast.LENGTH_SHORT).show();
                    }
                }


                @Override
                public void onCancelled(@NonNull DatabaseError databaseError) {
                    Toast.makeText(profile.this,"Error " ,Toast.LENGTH_SHORT).show();
                }
            });
        }
    }
    public void editimage(View view)
    {
        Intent intent = new Intent(Intent.ACTION_PICK, MediaStore.Images.Media.EXTERNAL_CONTENT_URI);
        startActivityForResult(intent,GALLARY_PICK_REQUEST);
    }
    public void editInfo(View view) {
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setTitle("Edit Information");

        View dialogView = getLayoutInflater().inflate(R.layout.edit_info, null);
        builder.setView(dialogView);

        EditText editTextName = dialogView.findViewById(R.id.editTextName);
        EditText editTextEmail = dialogView.findViewById(R.id.editTextEmail);
        EditText editTextAddress = dialogView.findViewById(R.id.editTextAddress);
        EditText editTextAge = dialogView.findViewById(R.id.editTextAge);
        EditText editTextPhone = dialogView.findViewById(R.id.editTextPhone);

        editTextName.setText(usernameall.getText().toString());
        editTextEmail.setText(emailall.getText().toString());
        editTextAddress.setText(addressall.getText().toString());
        editTextAge.setText(ageall.getText().toString());
        editTextPhone.setText(phoneall.getText().toString());

        builder.setPositiveButton("save", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                String newName = editTextName.getText().toString();
                String newEmail = editTextEmail.getText().toString();
                String newAddress = editTextAddress.getText().toString();
                String newAge = editTextAge.getText().toString();
                String newPhone = editTextPhone.getText().toString();

                usernameall.setText(newName);
                emailall.setText(newEmail);
                addressall.setText(newAddress);
                ageall.setText(newAge);
                phoneall.setText(newPhone);

                db.child("username").setValue(removePrefix("Username: ", newName));
                db.child("email").setValue(removePrefix("Email: ", newEmail));
                db.child("address").setValue(removePrefix("address: ", newAddress));
                db.child("age").setValue(removePrefix("age: ", newAge));
                db.child("phone").setValue(removePrefix("phone: ", newPhone));
                Toast.makeText(profile.this, "Information updated", Toast.LENGTH_SHORT).show();

            }
            private String removePrefix(String prefix, String value) {
                if (value.startsWith(prefix)) {
                    return value.substring(prefix.length());
                }
                return value;
            }
        });
        builder.setNegativeButton("Cancel", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                dialog.cancel();
            }
        });
        builder.show();
    }
        protected void onActivityResult(int requestCode, int resultCode, Intent data) {

        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == GALLARY_PICK_REQUEST && resultCode == RESULT_OK && data != null && data.getData() != null)
        {
            Uri imageUri = data.getData();

            StorageReference storageReference= FirebaseStorage.getInstance().getReference().child("imahefolder");
            StorageReference imageRef=storageReference.child(auth.getCurrentUser().getUid()+" .jpg");
            ProgressDialog progressDialog = new ProgressDialog(this);
            progressDialog.setMessage("Uploading Image...");
            progressDialog.show();

            imageRef.putFile(imageUri).addOnSuccessListener(taskSnapshot -> {
                imageRef.getDownloadUrl().addOnSuccessListener(uri -> {
                    String imageUrl=uri.toString();
                    db.child("url").setValue(imageUrl);
                    Glide.with(this)
                            .load(imageUrl)

                            .into(profileImage);
                    progressDialog.dismiss();
                    Toast.makeText(profile.this, "Profile image updated", Toast.LENGTH_SHORT).show();
                });
            }).addOnFailureListener(e -> {
                Toast.makeText(profile.this, "Failed to upload image: " + e.getMessage(), Toast.LENGTH_SHORT).show();
            });
        }
        }
}
