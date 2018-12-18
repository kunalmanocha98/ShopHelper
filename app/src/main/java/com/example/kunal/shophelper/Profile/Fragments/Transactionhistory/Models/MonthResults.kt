package com.example.kunal.shophelper.Profile.Fragments.Transactionhistory.Models

data class MonthResults(
        var monthname:String ?=null,
        var daylist:List<DayResults> ? =null
)