package com.example.mymaskan.view;

import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import com.example.mymaskan.model.UserData;

public class UserVM extends ViewModel {
    MutableLiveData<String> mutableLiveData =new MutableLiveData<>();
    void getUserData(){
        String name=getUser().name;
        mutableLiveData.setValue(name);

    }

    UserData getUser(){
        return new UserData("lkjhgfds","dsadcom","ahmed","15","aasbuhcba","4645564");
    }
}
