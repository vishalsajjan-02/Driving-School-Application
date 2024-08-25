package com.example.myapplication2;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.myapplication2.Adapter.DriverAdapter;
import com.example.myapplication2.model.DriverModel;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;

public class Teacher extends AppCompatActivity {

    ImageView back1;
    RecyclerView recyclerdata;
    DatabaseReference databaseReference;
    ArrayList<DriverModel>driverModelArrayList;

    DriverAdapter adapter;
 /*   @SuppressLint("MissingInflatedId")*/
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_teacher);

        back1=findViewById(R.id.backpage1);
        recyclerdata=findViewById(R.id.recyclerdata);
        databaseReference= FirebaseDatabase.getInstance().getReference("CarInfo");
        recyclerdata.setHasFixedSize(true);
        recyclerdata.setLayoutManager(new GridLayoutManager(this,2));
        driverModelArrayList=new ArrayList<>();
        adapter=new DriverAdapter(this,driverModelArrayList);
        databaseReference.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot)
            {
                   driverModelArrayList.clear();
                for (DataSnapshot dataSnapshot : snapshot.getChildren())
                {
                    DriverModel driverModel=dataSnapshot.getValue(DriverModel.class);
                    driverModelArrayList.add(driverModel);
                }
                adapter=new DriverAdapter(Teacher.this,driverModelArrayList);
                recyclerdata.setAdapter(adapter);
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });

        back1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(Teacher.this, Logindashboard.class));
            }
        });
    }
}