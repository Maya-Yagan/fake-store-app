package com.maya2002yagan.homework3.model

import com.google.gson.annotations.SerializedName

data class Product(
    @SerializedName("title")
    val title : String,
    @SerializedName("price")
    val price : String,
    @SerializedName("image")
    val image : String
)
