package com.maya2002yagan.homework3.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.maya2002yagan.homework3.databinding.ListItemBinding
import com.maya2002yagan.homework3.model.Product
import com.maya2002yagan.homework3.util.downloadURL

class ProductAdapter(private val list: MutableList<Product>) : RecyclerView.Adapter<ProductAdapter.ProductViewHolder>() {
   inner class ProductViewHolder(private val binding : ListItemBinding) : RecyclerView.ViewHolder(binding.root) {
        fun bind(product: Product){
            binding.tvProductTitle.text = product.title
            binding.tvProductPrice.text = "$" + product.price
            binding.ivProductImage.downloadURL(product.image)
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ProductViewHolder {
        val binding = ListItemBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return ProductViewHolder(binding)
    }

    override fun getItemCount(): Int = list.size

    override fun onBindViewHolder(holder : ProductViewHolder, position : Int) {
        holder.bind(list[position])
    }

    fun updateList(newList : List<Product>){
        list.clear()
        list.addAll(newList)
        notifyDataSetChanged()
    }
}