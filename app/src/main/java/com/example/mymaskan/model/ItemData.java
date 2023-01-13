package com.example.mymaskan.model;

public class ItemData {
    public String id,address,type,owner,price;
    public boolean select;

    public ItemData(String id, String address, String type, String owner, String price) {
        this.id = id;
        this.address = address;
        this.type = type;
        this.owner = owner;
        this.price = price;
    }

    public ItemData(String price ,boolean select) {
        this.price = price;
        this.select=select;
    }

    public void setSelect(boolean select) {
        this.select = select;
    }
}
