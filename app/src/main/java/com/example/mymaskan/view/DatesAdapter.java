package com.example.mymaskan.view;

import android.content.Context;
import android.graphics.Color;
import android.graphics.drawable.Drawable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.core.content.ContextCompat;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.load.engine.Resource;
import com.example.mymaskan.R;
import com.example.mymaskan.model.ItemData;

import java.util.ArrayList;

public class DatesAdapter extends RecyclerView.Adapter<DatesAdapter.DatesViewHolder> {
    public static ArrayList<ItemData> list;
    static Context context;
    ItemData itemData=null;
    int color;

    public DatesAdapter(int color) {
        this.color=color;
    }

    @NonNull
    @Override
    public DatesViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return new DatesViewHolder(LayoutInflater.from(parent.getContext()).inflate(R.layout.date, parent, false));
    }

    @Override
    public void onBindViewHolder(@NonNull DatesViewHolder holder, int position) {
        if (list.get(position).select){
            holder.tvDate.setTextColor(context.getResources().getColor(color));
        }else {
            holder.tvDate.setTextColor(context.getResources().getColor(R.color.colorGray));
        }
        holder.tvDate.setText(list.get(position).price);
        holder.tvDate.setOnClickListener(e->{
            for (ItemData i:list) {
                i.setSelect(false);
            }
            list.get(position).setSelect(true);
            itemData=list.get(position);
            notifyDataSetChanged();
        });
    }

    @Override
    public int getItemCount() {
        return list.size();
    }

    public static class DatesViewHolder extends RecyclerView.ViewHolder {
        TextView tvDate;

        public DatesViewHolder(@NonNull View itemView) {
            super(itemView);
            tvDate = itemView.findViewById(R.id.tv_date_date);
            context=itemView.getContext();
        }
    }

    public void setList(ArrayList<ItemData> list) {
        this.list = list;
        notifyDataSetChanged();
    }

    public ItemData getSelectedItem() {
        return itemData;
    }
}
