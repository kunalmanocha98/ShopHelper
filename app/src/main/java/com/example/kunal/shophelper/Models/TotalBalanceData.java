package com.example.kunal.shophelper.Models;

public class TotalBalanceData {
    String name;
    String shopnumber;
    String phonenumber;
    String totalbalance;

    public TotalBalanceData(String name, String shopnumber, String phonenumber, String totalbalance) {
        this.name = name;
        this.shopnumber = shopnumber;
        this.phonenumber = phonenumber;
        this.totalbalance = totalbalance;
    }

    public String getName() {
        return name;
    }

    public String getShopnumber() {
        return shopnumber;
    }

    public String getPhonenumber() {
        return phonenumber;
    }

    public String getTotalbalance() {
        return totalbalance;
    }

}
