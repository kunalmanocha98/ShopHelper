package com.example.kunal.shophelper.Profile.Fragments.Transactionhistory.Models

import com.example.kunal.shophelper.Transaction.Model.UserDetails

data class UserResult(
        var userdetails:UserDetails ?=null,
        var monthlist:List<MonthResults> ?= null,
        var total_balance:String ?=null
)