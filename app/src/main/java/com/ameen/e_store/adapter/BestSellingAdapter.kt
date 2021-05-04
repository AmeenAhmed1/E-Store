package com.ameen.e_store.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.ameen.e_store.databinding.ItemExploreBinding
import com.bumptech.glide.Glide

class BestSellingAdapter(
    private val imageSet: List<Int>,
    private val titleSet: Array<String>,
    private val desSet: Array<String>,
    private val priceSet: Array<String>
) :
    RecyclerView.Adapter<BestSellingAdapter.ViewHolder>() {

    private var _binding: ItemExploreBinding? = null

    inner class ViewHolder(val binding: ItemExploreBinding) :
        RecyclerView.ViewHolder(binding.root)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        _binding = ItemExploreBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return ViewHolder(_binding!!)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.binding.apply {
            exploreTitleTextView.text = titleSet[position]
            exploreDescTextView.text = desSet[position]
            explorePriceTextView.text = priceSet[position]
            Glide.with(_binding!!.root)
                .load(imageSet[position])
                .centerInside()
                .into(exploreImageView)
        }
    }

    override fun getItemCount(): Int {
        return imageSet.size
    }
}