package com.example.albums_app;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.ArrayList;

public class RetrofitAdapter extends RecyclerView.Adapter<RetrofitAdapter.MyViewHolder> {

    private ArrayList<ModelRecycler>dataModelArrayList;
    private Context mContext;
    public RetrofitAdapter(Context ctx,ArrayList<ModelRecycler>dataModelArrayList){
        this.dataModelArrayList=dataModelArrayList;
        this.mContext = ctx;
    }

    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int i) {
        View v = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.retro_item,parent, false);
        return new MyViewHolder(v);

    }

    @Override
    public void onBindViewHolder(@NonNull MyViewHolder holder, int position) {
        //Picasso.get().load(dataModelArrayList.get(position).getImgURL()).into(holder.iv);
        holder.name.setText(dataModelArrayList.get(position).getId());
        holder.country.setText(dataModelArrayList.get(position).getUserId());
        holder.city.setText(dataModelArrayList.get(position).getTitle());

    }

    @Override
    public int getItemCount() {
        return dataModelArrayList.size();
    }

    class MyViewHolder extends RecyclerView.ViewHolder{
        TextView country, name, city;


        public MyViewHolder(@NonNull View itemView) {
            super(itemView);
            country=(TextView)itemView.findViewById(R.id.country);
            name=(TextView)itemView.findViewById(R.id.name);
            city=(TextView)itemView.findViewById(R.id.city);


        }
    }

}
