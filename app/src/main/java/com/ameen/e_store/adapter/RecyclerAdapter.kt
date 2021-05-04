package com.ameen.e_store.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.ameen.e_store.databinding.ItemCategoryBinding
import com.bumptech.glide.Glide

class RecyclerAdapter(private val imageSet: List<Int>, private val nameSet: Array<String>) :
    RecyclerView.Adapter<RecyclerAdapter.ViewHolder<Any?>>() {

    private var _binding: ItemCategoryBinding? = null

    inner class ViewHolder<T>(val binding: ItemCategoryBinding) :
        RecyclerView.ViewHolder(binding.root)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder<Any?> {
        _binding =
            ItemCategoryBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return ViewHolder(_binding!!)
    }

    override fun onBindViewHolder(holder: ViewHolder<Any?>, position: Int) {
        holder.binding.apply {
            itemText.text = nameSet[position]
            Glide.with(_binding!!.root)
                .load(imageSet[position])
                .centerInside()
                .into(itemImage)
        }
    }

    override fun getItemCount(): Int {
        return nameSet.size
    }
}