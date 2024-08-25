package com.example.myapplication2.Adapter;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.myapplication2.Checkdescription;
import com.example.myapplication2.R;
import com.example.myapplication2.model.InformationModel;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;


public class InformationAdapter extends RecyclerView.Adapter<InformationAdapter.MyViewHolder>
{
    ArrayList<InformationModel> arrayList;
    Context context;
    public InformationAdapter( Context context,ArrayList<InformationModel> arrayList) {
        this.arrayList = arrayList;
        this.context = context;
    }


    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return new MyViewHolder(LayoutInflater.from(context).inflate(R.layout.item_information,parent,false));
    }

    @Override
    public void onBindViewHolder(@NonNull MyViewHolder holder, int position) {
        InformationModel informationModel = arrayList.get(position);
        holder.username.setText(informationModel.getUsername());
        holder.email.setText(informationModel.getEmail());
        holder.address.setText(informationModel.getAddress());
        holder.age.setText(informationModel.getAge());
        holder.phone.setText(informationModel.getPhone());
        Picasso.get().load(informationModel.getUrl()).into(holder.image);
        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent=new Intent(context, Checkdescription.class);
                intent.putExtra("username",informationModel.getUsername());
                intent.putExtra("email",informationModel.getEmail());
                intent.putExtra("address",informationModel.getAddress());
                intent.putExtra("age",informationModel.getAge());
                intent.putExtra("phone",informationModel.getPhone());
                intent.putExtra("image",informationModel.getUrl());


                context.startActivity(intent);
            }
        });
    }


    @Override
    public int getItemCount() {
        return arrayList.size();
    }

    public class MyViewHolder extends RecyclerView.ViewHolder
    {

        TextView username,email,address,age,phone;
        ImageView image;
        public MyViewHolder(@NonNull View itemView) {
            super(itemView);
            username=itemView.findViewById(R.id.username);
            email=itemView.findViewById(R.id.email);
            address=itemView.findViewById(R.id.address);
            age=itemView.findViewById(R.id.age);
            phone=itemView.findViewById(R.id.phone);
            image=itemView.findViewById(R.id.image);
        }
    }
}
