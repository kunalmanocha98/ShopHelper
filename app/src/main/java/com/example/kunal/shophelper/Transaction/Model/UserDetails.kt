package com.example.kunal.shophelper.Transaction.Model

import com.google.gson.annotations.SerializedName

data class UserDetails (
        var user_name:String? =null,
        var user_shopname:String?= null,
        var user_contact:String?=null,
        var user_address:String? =null,
        var user_profile_pic_url:String? =null
)