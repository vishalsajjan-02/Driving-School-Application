
package com.example.myapplication2;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.myapplication2.Adapter.InformationAdapter;
import com.example.myapplication2.model.InformationModel;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;

public class datafetchactivity extends AppCompatActivity {

    RecyclerView recyclerdata;
    DatabaseReference databaseReference;
    ArrayList<InformationModel> list;
    InformationAdapter adapter;
    FirebaseAuth auth;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_datafetchactivity);

        recyclerdata = findViewById(R.id.recyclerdata);
        auth = FirebaseAuth.getInstance();

        databaseReference= FirebaseDatabase.getInstance().getReference("information");
        recyclerdata.setHasFixedSize(true);
        recyclerdata.setLayoutManager(new LinearLayoutManager(this));

        list = new ArrayList<>();
        adapter = new InformationAdapter(this,list);

        databaseReference.addValueEventListener(new ValueEventListener() {
            @Override

            public void onDataChange(@NonNull DataSnapshot snapshot)
            {
                for (DataSnapshot dataSnapshot : snapshot.getChildren()) {
                    InformationModel informationModel = dataSnapshot.getValue(InformationModel.class);
                    list.add(informationModel);
                }
                adapter = new InformationAdapter( datafetchactivity.this,list);
                recyclerdata.setAdapter(adapter);
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {
                // Handle onCancelled here
            }
        });
    }
}
