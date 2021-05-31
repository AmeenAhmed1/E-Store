package com.ameen.e_store.ui.fragment

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.navigation.fragment.navArgs
import androidx.recyclerview.widget.LinearLayoutManager
import com.ameen.e_store.adapter.AddressAdapter
import com.ameen.e_store.data.model.UserModel
import com.ameen.e_store.databinding.FragmentAddressBinding
import com.ameen.e_store.ui.BaseFragment

class AddressFragment : BaseFragment<FragmentAddressBinding>() {

    //Sent args
    private val args: AddressFragmentArgs by navArgs()
    private lateinit var selectedUserAddress: UserModel

    //Adapter
    private lateinit var addressAdapter: AddressAdapter

    override val bindingInflater: (LayoutInflater, ViewGroup?, Boolean) -> FragmentAddressBinding
        get() = FragmentAddressBinding::inflate

    override fun setupOnViewCreated() {
        selectedUserAddress = args.userAddress

        addressAdapter = AddressAdapter()
        //addressAdapter.differ.submitList(selectedUserAddress.userAddressModel)

        binding!!.addressRecycler.apply {
            adapter = addressAdapter
            layoutManager = LinearLayoutManager(activity, LinearLayoutManager.VERTICAL, false)
        }
    }
}