package com.example.myapplication2.ui.home;

import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;

import com.example.myapplication2.Leave;
import com.example.myapplication2.Teacher;
import com.example.myapplication2.contact;
import com.example.myapplication2.databinding.FragmentHomeBinding;
import com.example.myapplication2.invoice;
import com.example.myapplication2.payment;
import com.example.myapplication2.profile;
import com.example.myapplication2.quizzz.quizz;
import com.example.myapplication2.viewall;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Locale;


public class HomeFragment extends Fragment {
private FirebaseAuth auth;
private DatabaseReference databaseReference;
    private FragmentHomeBinding binding;

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {

// Add this line after initializing the RatingBar in onCreateView method
        //binding.rbRatingBar.setRating(2.5f);

        binding = FragmentHomeBinding.inflate(inflater, container, false);
        View root = binding.getRoot();

        auth = FirebaseAuth.getInstance();
        FirebaseUser currentUser = auth.getCurrentUser();

        if (currentUser != null)
        {
            String uid = currentUser.getUid();
            DatabaseReference db= FirebaseDatabase.getInstance().getReference("information").child(uid);
            db.addListenerForSingleValueEvent(new ValueEventListener() {
                @Override
                public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                    if (dataSnapshot.exists()) {
                        String username = dataSnapshot.child("username").getValue(String.class);

                        if (username != null ) {
                            // Update TextView elements
                            binding.Domen.setText(username);
                        }
                    }
                }

                @Override
                public void onCancelled(@NonNull DatabaseError databaseError) {
                    Toast.makeText(requireContext(),"Error " ,Toast.LENGTH_SHORT).show();
                }
            });

            Calendar calendar=Calendar.getInstance();
            SimpleDateFormat dateFormat=new SimpleDateFormat("dd.MM.yyyy", Locale.getDefault());
            String currentDate=dateFormat.format(calendar.getTime());
            binding.date.setText(currentDate);
        }

        binding.profile.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(requireContext(), profile.class));
            }
        });

        binding.view.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(requireContext(), viewall.class));
            }
        });

        binding.teacher1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(requireContext(), Teacher.class));
            }
        });
       binding.invoice1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(requireContext(), invoice.class));
            }
        });
        binding.leave.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(requireContext(), Leave.class));
            }
        });
        binding.contact.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(requireContext(), contact.class));
            }
        });
        binding.payment.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(requireContext(), payment.class));
            }
        });
        binding.rechrage.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(requireContext(), quizz.class));
            }
        });



//        final TextView textView = binding.textHome;
//        homeViewModel.getText().observe(getViewLifecycleOwner(), textView::setText);
        return root;
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        binding = null;
    }
}