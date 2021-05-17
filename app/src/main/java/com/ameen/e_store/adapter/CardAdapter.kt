package com.ameen.e_store.adapter

import android.util.Log
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.AsyncListDiffer
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.ameen.e_store.data.model.CardModel
import com.ameen.e_store.databinding.ItemCreditCardBinding

class CardAdapter() : RecyclerView.Adapter<CardAdapter.ViewHolder>() {

    private val TAG = "CardAdapter"

    private var _binding: ItemCreditCardBinding? = null

    inner class ViewHolder(val binding: ItemCreditCardBinding) :
        RecyclerView.ViewHolder(binding.root)

    val differCallBack = object : DiffUtil.ItemCallback<CardModel>() {
        override fun areItemsTheSame(oldItem: CardModel, newItem: CardModel): Boolean {
            return oldItem.cardNumber == newItem.cardNumber
        }

        override fun areContentsTheSame(oldItem: CardModel, newItem: CardModel): Boolean {
            return oldItem == newItem
        }

    }

    val diff = AsyncListDiffer(this, differCallBack)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        _binding = ItemCreditCardBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return ViewHolder(_binding!!)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {

        val card = diff.currentList[position]

        holder.binding.apply {
            cardNumber.text =
                card.cardNumber.toString().chunked(4).joinToString(separator = "     ")
            cardHolderName.text = card.cardHolderName
            cardExpiryDate.text = card.cardExpiryDate
        }
    }

    override fun getItemCount(): Int = diff.currentList.size

}