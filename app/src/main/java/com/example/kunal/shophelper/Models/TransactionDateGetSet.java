package com.example.kunal.shophelper.Models;

/**
 * Created by kunal on 27/2/18.
 */

public class TransactionDateGetSet {
    String date;
    String credit;
    String debit;
    String balance;

    public TransactionDateGetSet(String date, String credit, String debit, String balance) {
        this.date=date;
        this.credit=credit;
        this.debit=debit;
        this.balance=balance;
    }

    public String getDate() {
        return date;
    }

    public String getCredit() {
        return credit;
    }

    public String getDebit() {
        return debit;
    }

    public String getBalance() {
        return balance;
    }
}
