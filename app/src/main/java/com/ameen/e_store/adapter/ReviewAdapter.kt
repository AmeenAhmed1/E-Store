package com.ameen.e_store.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.AsyncListDiffer
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.ameen.e_store.data.model.ReviewModel
import com.ameen.e_store.databinding.ItemReviewBinding
import com.bumptech.glide.Glide

class ReviewAdapter() : RecyclerView.Adapter<ReviewAdapter.ViewHolder>() {

    private var _binding: ItemReviewBinding? = null

    inner class ViewHolder(val binding: ItemReviewBinding) : RecyclerView.ViewHolder(binding.root)

    val differCallBack = object : DiffUtil.ItemCallback<ReviewModel>() {
        override fun areItemsTheSame(oldItem: ReviewModel, newItem: ReviewModel): Boolean {
            return oldItem.reviewUserId == newItem.reviewUserId
        }

        override fun areContentsTheSame(oldItem: ReviewModel, newItem: ReviewModel): Boolean {
            return oldItem == newItem
        }
    }

    val differ = AsyncListDiffer(this, differCallBack)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        _binding = ItemReviewBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return ViewHolder(_binding!!)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val item = differ.currentList[position]

        holder.binding.apply {
            rateUsername.text = item.reviewUserId.userName
            rateReviewText.text = item.reviewBody
            ratingBar.rating = item.reviewRating
            Glide.with(_binding!!.root)
                .load(item.reviewUserId.userImage)
                .into(rateUserImage)
        }

    }

    override fun getItemCount(): Int = differ.currentList.size


}