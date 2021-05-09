package com.ameen.e_store.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.AsyncListDiffer
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.ameen.e_store.data.model.CategoriesModel
import com.ameen.e_store.databinding.ItemCategoryBinding
import com.bumptech.glide.Glide

class CategoriesAdapter() : RecyclerView.Adapter<CategoriesAdapter.ViewHolder>() {

    private var _binding: ItemCategoryBinding? = null

    inner class ViewHolder(val binding: ItemCategoryBinding) : RecyclerView.ViewHolder(binding.root)

    private val differCallBack = object : DiffUtil.ItemCallback<CategoriesModel>() {
        override fun areContentsTheSame(
            oldItem: CategoriesModel,
            newItem: CategoriesModel
        ): Boolean {
            return oldItem == newItem
        }

        override fun areItemsTheSame(oldItem: CategoriesModel, newItem: CategoriesModel): Boolean {
            return oldItem.categoryTitle == newItem.categoryTitle
        }
    }

    val differ = AsyncListDiffer(this, differCallBack)


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        _binding =
            ItemCategoryBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return ViewHolder(_binding!!)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {

        val items = differ.currentList[position]

        holder.binding.apply {
            itemText.text = items.categoryTitle
            Glide.with(_binding!!.root)
                .load(items.categoryIcon)
                .centerInside()
                .into(itemImage)
        }
    }

    override fun getItemCount(): Int {
        return differ.currentList.size
    }
}