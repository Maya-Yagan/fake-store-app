package com.maya2002yagan.homework3.view

import android.os.Bundle
import android.view.View
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import com.maya2002yagan.homework3.R
import com.maya2002yagan.homework3.adapter.ProductAdapter
import com.maya2002yagan.homework3.databinding.ActivityMainBinding
import com.maya2002yagan.homework3.viewmodel.MainViewModel

class MainActivity : AppCompatActivity() {
    private lateinit var viewModel : MainViewModel
    private lateinit var binding: ActivityMainBinding
    private var adapter = ProductAdapter(mutableListOf())

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_main)
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }
        viewModel = ViewModelProvider(this)[MainViewModel::class.java]
        binding = DataBindingUtil.setContentView(this, R.layout.activity_main)
        binding.rvProductRecyclerView.adapter = adapter
        binding.rvProductRecyclerView.layoutManager = LinearLayoutManager(this)
        viewModel.getDataFromAPI()
        setObservers()
    }

    private fun setObservers(){
        viewModel.productData.observe(this){ list ->
            adapter.updateList(list)
        }

        viewModel.productLoad.observe(this){ loading ->
            if(loading)
                binding.pbLoading.visibility = View.VISIBLE
            else
                binding.pbLoading.visibility = View.GONE
        }

        viewModel.productError.observe(this){ error ->
            if(error)
                binding.tvError.visibility = View.VISIBLE
            else
                binding.tvError.visibility = View.GONE
        }
    }
}