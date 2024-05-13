package com.maya2002yagan.homework3.service

import com.maya2002yagan.homework3.model.Product
import retrofit2.Call
import retrofit2.http.GET

interface ProductAPI {
    @GET("products")
    fun getProducts() : Call<List<Product>>
}