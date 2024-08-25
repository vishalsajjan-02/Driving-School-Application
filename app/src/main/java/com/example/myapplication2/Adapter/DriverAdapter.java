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

import com.example.myapplication2.R;
import com.example.myapplication2.checkdescription1;
import com.example.myapplication2.model.DriverModel;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;

public class DriverAdapter  extends RecyclerView.Adapter<DriverAdapter.MyViewHolder>
{
    Context context;
    ArrayList<DriverModel> driverModelArrayList;

    public DriverAdapter(Context context, ArrayList<DriverModel> driverModelArrayList) {
        this.context = context;
        this.driverModelArrayList = driverModelArrayList;
    }
    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return new MyViewHolder(LayoutInflater.from(context).inflate(R.layout.driver_information,parent,false));

    }

    @Override
    public void onBindViewHolder(@NonNull MyViewHolder holder, int position)
    {
        DriverModel driverModel=driverModelArrayList.get(position);
        holder.username.setText(driverModel.getUsername());
        holder.company.setText(driverModel.getCarcompany());
        holder.model.setText(driverModel.getCarmodel());
        holder.experience.setText(driverModel.getExperience());
        holder.carnumber.setText(driverModel.getCarnumber());
        Picasso.get().load(driverModel.getUrl()).into(holder.image);

        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent2=new Intent(context, checkdescription1.class);
                intent2.putExtra("username",driverModel.getUsername());
                intent2.putExtra("company",driverModel.getCarcompany());
                intent2.putExtra("model",driverModel.getCarmodel());
                intent2.putExtra("experience",driverModel.getExperience());
                intent2.putExtra("carnumber",driverModel.getCarnumber());
                intent2.putExtra("image",driverModel.getUrl());
                context.startActivity(intent2);
            }
        });

    }

    @Override
    public int getItemCount() {
        return driverModelArrayList.size();
    }
    public class MyViewHolder extends RecyclerView.ViewHolder {
        ImageView image;
        TextView username,company,model,experience,carnumber;

        public MyViewHolder(@NonNull View itemView) {
            super(itemView);
            username=itemView.findViewById(R.id.username1);
            company=itemView.findViewById(R.id.company1);
            model=itemView.findViewById(R.id.model1);
            experience=itemView.findViewById(R.id.experience1);
            carnumber=itemView.findViewById(R.id.carnumber1);
            image=itemView.findViewById(R.id.image1);
        }
    }
}
