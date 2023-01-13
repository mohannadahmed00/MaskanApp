package com.example.mymaskan.view;

import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import com.example.mymaskan.model.ItemData;

public class ItemVM extends ViewModel {
    MutableLiveData<String> mutableLiveData=new MutableLiveData<>();

    void getItemInfo(){
        String type=getItem().type;
        mutableLiveData.setValue(type);
    }

    ItemData getItem(){
        return new ItemData("dsfsdfs","sdfsffsf","1","shady","484655");
    }
}
