package com.ameen.e_store.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.AsyncListDiffer
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.ameen.e_store.data.model.BrandModel
import com.ameen.e_store.databinding.ItemBrandBinding
import com.bumptech.glide.Glide

class BrandsAdapter() : RecyclerView.Adapter<BrandsAdapter.ViewHolder>() {

    var _binding: ItemBrandBinding? = null

    inner class ViewHolder(val binding: ItemBrandBinding) : RecyclerView.ViewHolder(binding.root) {}

    private val differCallBack = object : DiffUtil.ItemCallback<BrandModel>() {
        override fun areItemsTheSame(oldItem: BrandModel, newItem: BrandModel): Boolean {
            return oldItem.branTitle == newItem.branTitle
        }

        override fun areContentsTheSame(oldItem: BrandModel, newItem: BrandModel): Boolean {
            return oldItem == newItem
        }

    }

    val differ = AsyncListDiffer(this, differCallBack)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        _binding = ItemBrandBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return ViewHolder(_binding!!)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val item = differ.currentList[position]

        holder.binding.apply {
            brandTitle.text = item.branTitle
            brandProducts.text = item.brandProductsNumber.toString() + " Products"
            Glide.with(_binding!!.root)
                .load(item.brandImage)
                .centerInside()
                .into(brandImageView)
        }
    }

    override fun getItemCount(): Int {
        return differ.currentList.size
    }
}