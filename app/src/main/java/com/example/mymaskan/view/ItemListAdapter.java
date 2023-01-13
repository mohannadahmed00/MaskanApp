package com.example.mymaskan.view;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.mymaskan.R;
import com.example.mymaskan.model.ItemData;

import java.util.ArrayList;

import static androidx.core.content.ContextCompat.startActivity;

public class ItemListAdapter extends RecyclerView.Adapter<ItemListAdapter.MyItemViewHolder>  {
    ArrayList<ItemData> itemDataList;


    @NonNull
    @Override
    public MyItemViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return new MyItemViewHolder(LayoutInflater.from(parent.getContext()).inflate(R.layout.item, parent, false));
    }

    @Override
    public void onBindViewHolder(@NonNull MyItemViewHolder holder, int position) {
        holder.itemPrice.setText(itemDataList.get(position).price);
    }

    @Override
    public int getItemCount() {
        return itemDataList.size();
    }

    public void setList(ArrayList<ItemData> list){
        this.itemDataList=list;
        notifyDataSetChanged();
    }

    public static class MyItemViewHolder extends RecyclerView.ViewHolder {
        TextView itemPrice;
        RelativeLayout item;
        Context context;
        public MyItemViewHolder(@NonNull View itemView) {
            super(itemView);
            itemPrice = itemView.findViewById(R.id.tv_price_item);
            item = itemView.findViewById(R.id.rl_item);
            context =itemView.getContext();


            itemView.setOnClickListener(e->{
                context.startActivity(new Intent(context, DetailsActivity.class));
            });
        }
    }
}
