package com.ameen.e_store.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.AsyncListDiffer
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.ameen.e_store.data.model.CategoriesModel
import com.ameen.e_store.data.model.ProductModel
import com.ameen.e_store.databinding.ItemExploreBinding
import com.bumptech.glide.Glide

class BestSellingAdapter() : RecyclerView.Adapter<BestSellingAdapter.ViewHolder>() {

    private var _binding: ItemExploreBinding? = null

    inner class ViewHolder(val binding: ItemExploreBinding) : RecyclerView.ViewHolder(binding.root)

    val differCallBack = object : DiffUtil.ItemCallback<ProductModel>() {
        override fun areItemsTheSame(oldItem: ProductModel, newItem: ProductModel): Boolean {
            return oldItem.productTitle == newItem.productTitle
        }

        override fun areContentsTheSame(oldItem: ProductModel, newItem: ProductModel): Boolean {
            return oldItem == newItem
        }

    }

    val differ = AsyncListDiffer(this, differCallBack)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        _binding = ItemExploreBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return ViewHolder(_binding!!)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val items = differ.currentList[position]

        if (items.productStateNew)
            holder.binding.exploreItemNewTag.visibility = View.VISIBLE
        else
            holder.binding.exploreItemNewTag.visibility = View.GONE

        holder.binding.apply {
            exploreTitleTextView.text = items.productTitle
            exploreDescTextView.text = items.productDescription
            explorePriceTextView.text = "$" + items.productPrice.toString()
            Glide.with(_binding!!.root)
                .load(items.productImage)
                .into(exploreImageView)
        }
    }

    override fun getItemCount(): Int {
        return differ.currentList.size
    }
}