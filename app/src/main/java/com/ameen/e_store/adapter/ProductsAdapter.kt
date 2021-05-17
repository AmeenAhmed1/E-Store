package com.ameen.e_store.adapter

import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.AdapterView
import androidx.recyclerview.widget.AsyncListDiffer
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.ameen.e_store.data.model.ProductModel
import com.ameen.e_store.databinding.ItemExploreBinding
import com.bumptech.glide.Glide

class ProductsAdapter() : RecyclerView.Adapter<ProductsAdapter.ViewHolder>() {

    private val TAG = "ProductsAdapter"

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

        //Log.i(TAG, "onBindViewHolder: ${items.productStateNew}")

        if (items.productStateNew) {
            Log.i(TAG, "onBindViewHolder: True")
            holder.binding.productState.exploreItemNewTag.visibility = View.VISIBLE
        } else {
            Log.i(TAG, "onBindViewHolder: False")
            holder.binding.productState.exploreItemNewTag.visibility = View.GONE
        }
        holder.binding.apply {
            exploreTitleTextView.text = items.productTitle
            exploreDescTextView.text = items.productDescription
            explorePriceTextView.text = "$" + items.productPrice.toString()
            Glide.with(_binding!!.root)
                .load(items.productImage)
                .into(exploreImageView)
        }

        holder.itemView.setOnClickListener { onItemClickListener?.let { it(items) } }
    }

    override fun getItemCount(): Int {
        //Return the first two products and the other in the
        //See more page.
        return differ.currentList.size
    }


    private var onItemClickListener: ((ProductModel) -> Unit)? = null
    fun onItemClicked(listner: (ProductModel) -> Unit) {
        onItemClickListener = listner
    }
}