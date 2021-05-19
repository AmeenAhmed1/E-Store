package com.ameen.e_store.adapter

import android.util.Log
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.AsyncListDiffer
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.ameen.e_store.data.model.ProductModel
import com.ameen.e_store.databinding.ItemCartBinding
import com.ameen.e_store.viewmodel.ProductViewModel
import com.bumptech.glide.Glide

class CartAdapter(val viewModel: ProductViewModel) :
    RecyclerView.Adapter<CartAdapter.ViewHolder>() {

    private val TAG = "CartAdapter"

    inner class ViewHolder(val binding: ItemCartBinding) :
        RecyclerView.ViewHolder(binding.root)

    private var _binding: ItemCartBinding? = null

    private val differCallBack = object : DiffUtil.ItemCallback<ProductModel>() {
        override fun areItemsTheSame(oldItem: ProductModel, newItem: ProductModel): Boolean {
            return oldItem.productId == newItem.productId
        }

        override fun areContentsTheSame(oldItem: ProductModel, newItem: ProductModel): Boolean {
            return oldItem == newItem
        }
    }

    val diff = AsyncListDiffer(this, differCallBack)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        _binding = ItemCartBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return ViewHolder(_binding!!)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        var itemCount = diff.currentList[position].productCountInCart
        val currentItem = diff.currentList[position]

        holder.binding.apply {
            itemCartTitle.text = currentItem.productTitle
            itemCartPrice.text = "$" + (currentItem.productPrice * itemCount).toString()
            itemCountText.text = itemCount.toString()
            Glide.with(_binding!!.root)
                .load(currentItem.productImage)
                .centerCrop()
                .into(itemCartImage)

            increaseButton.setOnClickListener {
                itemCountText.text = (++itemCount).toString()
                itemCartPrice.text = "$" + (currentItem.productPrice * itemCount).toString()
                diff.currentList[position].productCountInCart = itemCount

                viewModel.cartData.postValue(diff.currentList)

                Log.i(
                    TAG,
                    "onBindViewHolder: UpdateProductCount ${diff.currentList[position].productCountInCart}"
                )
            }

            decreaseButton.setOnClickListener {
                if (itemCount > 1) {
                    itemCountText.text = (--itemCount).toString()
                    itemCartPrice.text = "$" + (currentItem.productPrice * itemCount).toString()
                    diff.currentList[position].productCountInCart = itemCount

                    viewModel.cartData.postValue(diff.currentList)

                    Log.i(
                        TAG,
                        "onBindViewHolder: UpdateProductCount ${diff.currentList[position].productCountInCart}"
                    )
                }
            }
        }
    }

    override fun getItemCount(): Int = diff.currentList.size
}