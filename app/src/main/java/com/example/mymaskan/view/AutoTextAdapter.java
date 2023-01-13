package com.example.mymaskan.view;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Filter;
import android.widget.Filterable;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import com.example.mymaskan.R;

import java.util.ArrayList;

public class AutoTextAdapter extends ArrayAdapter<String> {
    ArrayList<String> govs;
    ArrayList<String> filtered;
    private ListFilter listFilter = new ListFilter();

    public AutoTextAdapter(@NonNull Context context, ArrayList<String> gov) {
        super(context, 0,gov);
        govs=gov;
        filtered=new ArrayList<>(govs);
    }

    @NonNull
    @Override
    public Filter getFilter() {
        return listFilter;
    }

    @Nullable
    @Override
    public String getItem(int position) {
        return govs.get(position);
    }

    @Override
    public int getCount() {
        return govs.size();
    }

    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
        if (convertView == null) {
            convertView = LayoutInflater.from(getContext()).inflate(R.layout.date, parent, false);
        }
        TextView textView=convertView.findViewById(R.id.tv_date_date);
        textView.setText(getItem(position));
        return convertView;
    }



    private class ListFilter extends Filter {
        Object lock;
        @Override
        protected FilterResults performFiltering(CharSequence constraint) {
            if (filtered == null) {
                synchronized (lock) {
                    filtered = new ArrayList<String>(govs);
                }
            }
            FilterResults filterResults=new FilterResults();
            ArrayList<String> filteredGovs=new ArrayList<>();
            for (String s:govs) {
                if (constraint.toString().toLowerCase().equals(s.toLowerCase())){
                    filteredGovs.add(s);
                }
            }
            filterResults.values=filteredGovs;
            filterResults.count=filteredGovs.size();
            return filterResults;
        }

        @Override
        protected void publishResults(CharSequence constraint, FilterResults results) {
            govs=(ArrayList<String>)results.values;
            notifyDataSetChanged();
        }
    }
}
