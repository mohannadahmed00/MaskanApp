package com.example.mymaskan.view;

import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import com.example.mymaskan.model.ItemData;

import java.util.ArrayList;

public class ItemViewModel extends ViewModel {
    public MutableLiveData<ArrayList<ItemData>> listMutableLiveData =new MutableLiveData<>();



    public void sendItems(String type){
        if (type.equals("p")) {
            listMutableLiveData.setValue(getItems());
        }else if (type.equals("d")){
            listMutableLiveData.setValue(getDates());
        }else if (type.equals("t")){
            listMutableLiveData.setValue(getType());
        }else {
            listMutableLiveData.setValue(getNum());
        }
    }

    private ArrayList<ItemData> getItems(){
        ArrayList<ItemData> list =new ArrayList<>();
        list.add(new ItemData("1000",false));
        list.add(new ItemData("1100",false));
        list.add(new ItemData("1200",false));
        list.add(new ItemData("1300",false));
        list.add(new ItemData("1400",false));
        list.add(new ItemData("1500",false));
        list.add(new ItemData("1600",false));
        list.add(new ItemData("1700",false));

        return list;
    }

    private ArrayList<ItemData> getDates(){
        ArrayList<ItemData> list =new ArrayList<>();
        list.add(new ItemData("TUE 20/10 20:30",false));
        list.add(new ItemData("FRI 22/02 12:15",false));
        list.add(new ItemData("SAT 25/01 08:30",false));
        list.add(new ItemData("WED 15/09 21:15",false));
        list.add(new ItemData("MON 01/11 16:00",false));
        list.add(new ItemData("SUN 10/10 12:30",false));
        list.add(new ItemData("TUE 20/10 20:30",false));
        list.add(new ItemData("FRI 22/02 12:15",false));
        list.add(new ItemData("SAT 25/01 08:30",false));
        list.add(new ItemData("WED 15/09 21:15",false));
        list.add(new ItemData("MON 01/11 16:00",false));
        list.add(new ItemData("SUN 10/10 12:30",false));

        return list;
    }
    private ArrayList<ItemData> getNum(){
        ArrayList<ItemData> list =new ArrayList<>();
        list.add(new ItemData("1",false));
        list.add(new ItemData("2",false));
        list.add(new ItemData("3",false));
        list.add(new ItemData("4",false));
        list.add(new ItemData("5",false));
        list.add(new ItemData("6",false));
        return list;
    }
    private ArrayList<ItemData> getType(){
        ArrayList<ItemData> list =new ArrayList<>();
        list.add(new ItemData("buy",false));
        list.add(new ItemData("rent",false));
        return list;
    }
}
