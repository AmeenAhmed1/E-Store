package com.ameen.e_store.ui.fragment

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.navigation.fragment.navArgs
import androidx.recyclerview.widget.LinearLayoutManager
import com.ameen.e_store.adapter.CardAdapter
import com.ameen.e_store.data.model.UserModel
import com.ameen.e_store.databinding.FragmentCardsBinding
import com.ameen.e_store.ui.BaseFragment

class CardsFragment : BaseFragment<FragmentCardsBinding>() {

    //Sent args
    private val args: CardsFragmentArgs by navArgs()
    private lateinit var selectedUserCard: UserModel

    //Adapter
    private lateinit var cardAdapter: CardAdapter

    override val bindingInflater: (LayoutInflater, ViewGroup?, Boolean) -> FragmentCardsBinding
        get() = FragmentCardsBinding::inflate

    override fun setupOnViewCreated() {
        selectedUserCard = args.userCard

        cardAdapter = CardAdapter()
        //cardAdapter.diff.submitList(selectedUserCard.userCards)

        binding!!.cardsRecycler.apply {
            adapter = cardAdapter
            layoutManager = LinearLayoutManager(activity, LinearLayoutManager.VERTICAL, false)
        }
    }
}