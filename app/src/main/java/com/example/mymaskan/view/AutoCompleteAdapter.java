package com.example.mymaskan.view;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Filter;
import android.widget.Filterable;
import android.widget.TextView;

import com.example.mymaskan.R;

import java.util.ArrayList;
import java.util.List;

public class AutoCompleteAdapter extends ArrayAdapter<String> implements Filterable {

    private ArrayList<String> fullList;
    private ArrayList<String> mOriginalValues;
    private ArrayFilter mFilter;

    public AutoCompleteAdapter(Context context, int resource, int textViewResourceId, List<String> objects) {

        super(context, resource, textViewResourceId, objects);
        fullList = (ArrayList<String>) objects;
        mOriginalValues = new ArrayList<String>(fullList);

    }

    @Override
    public int getCount() {
        return fullList.size();
    }

    @Override
    public String getItem(int position) {
        return fullList.get(position);
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        if (convertView == null) {
            convertView = LayoutInflater.from(getContext()).inflate(R.layout.date, parent, false);
        }
        TextView textView = convertView.findViewById(R.id.tv_date_date);
        textView.setText(getItem(position));
        return convertView;
    }

    @Override
    public Filter getFilter() {
        if (mFilter == null) {
            mFilter = new ArrayFilter();
        }
        return mFilter;
    }


    private class ArrayFilter extends Filter {
        //private Object lock;

        @Override
        protected FilterResults performFiltering(CharSequence prefix) {
            FilterResults results = new FilterResults();

            /*if (mOriginalValues == null) {
                synchronized (lock) {
                    mOriginalValues = new ArrayList<String>(fullList);
                }
            }

            if (prefix == null || prefix.length() == 0) {
                synchronized (lock) {
                    ArrayList<String> list = new ArrayList<String>(mOriginalValues);
                    results.values = list;
                    results.count = list.size();
                }
            } else {*/
            final String prefixString = prefix.toString().toLowerCase();

            ArrayList<String> values = mOriginalValues;
            int count = values.size();

            ArrayList<String> newValues = new ArrayList<String>(count);

            for (int i = 0; i < count; i++) {
                String item = values.get(i);
                if (item.toLowerCase().contains(prefixString)) {
                    newValues.add(item);
                }
            }
            /*if (prefixString.length()>=2) {
                for (int i = 0; i < count; i++) {
                    String item = values.get(i);
                    for (int j = 0; j < item.length(); j++) {
                        CharSequence sequence = String.valueOf(prefixString.charAt(j));
                        if (item.toLowerCase().contains(sequence)) {
                            newValues.add(item);
                            break;
                        }
                    }
                }
            }*/


            results.values = newValues;
            results.count = newValues.size();
            //}

            return results;
        }

        @SuppressWarnings("unchecked")
        @Override
        protected void publishResults(CharSequence constraint, FilterResults results) {

            if (results.values != null) {
                fullList = (ArrayList<String>) results.values;
            } else {
                fullList = new ArrayList<String>();
            }
            if (results.count > 0) {
                notifyDataSetChanged();
            } else {
                notifyDataSetInvalidated();
            }
        }
    }


}

