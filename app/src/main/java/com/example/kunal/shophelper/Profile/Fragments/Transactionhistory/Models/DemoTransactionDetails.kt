package com.example.kunal.shophelper.Profile.Fragments.Transactionhistory.Models

data class DemoTransactionDetails(
        var monthdata: HashMap<String,HashMap<String,DemoTransactionData>> ?=null,
        var total_balance:String ?=null
)