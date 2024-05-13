package com.maya2002yagan.homework3.viewmodel

import android.util.Log
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.maya2002yagan.homework3.model.Product
import com.maya2002yagan.homework3.service.ProductAPIService
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class MainViewModel : ViewModel() {
    private val api = ProductAPIService()
    val productData = MutableLiveData<List<Product>>()
    val productLoad = MutableLiveData<Boolean>()
    val productError = MutableLiveData<Boolean>()

    fun getDataFromAPI(){
        productLoad.value = true
        api.getData().enqueue(object : Callback<List<Product>>{
            override fun onResponse(call : Call<List<Product>>, response : Response<List<Product>>){
                productData.value = response.body()
                productError.value = false
                productLoad.value = false
            }

            override fun onFailure(call: Call<List<Product>>, t: Throwable) {
                productLoad.value = false
                productError.value = true
                Log.e("RetrofitError", t.message.toString())
            }
        })
    }
}