package com.ameen.e_store.ui.fragment

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.lifecycle.Observer
import androidx.navigation.fragment.findNavController
import com.ameen.e_store.R
import com.ameen.e_store.data.model.UserModel
import com.ameen.e_store.databinding.FragmentAccountBinding
import com.ameen.e_store.ui.BaseFragment
import com.ameen.e_store.ui.activity.MainActivity
import com.ameen.e_store.viewmodel.ProductViewModel

class AccountFragment : BaseFragment<FragmentAccountBinding>() {

    private val TAG = "AccountFragment"

    //User args.
    //private val args: AccountFragmentArgs by navArgs()
    private var user: UserModel? = null

    //ViewModel
    lateinit var viewModel: ProductViewModel

    override val bindingInflater: (LayoutInflater, ViewGroup?, Boolean) -> FragmentAccountBinding
        get() = FragmentAccountBinding::inflate

    override fun setupOnViewCreated() {
        initViewModel()
        initViews()
    }

    private fun initViews() {
        Log.i(TAG, "onViewCreated: $user")

        binding?.apply {
            accountImageProfile.setImageResource(user!!.userImage)
            accountProfileName.text = user!!.userName
            accountProfileEmail.text = user!!.userEmail
            cardSection.setOnClickListener {
                val bundle = Bundle()
                bundle.putSerializable("userCard", user)
                findNavController().navigate(
                    R.id.action_accountFragment_to_cardsFragment,
                    bundle
                )
            }

            addressSection.setOnClickListener {
                val bundle = Bundle()
                bundle.putSerializable("userAddress", user)
                findNavController().navigate(
                    R.id.action_accountFragment_to_addressFragment,
                    bundle
                )
            }
        }
    }

    private fun initViewModel() {

        viewModel = (activity as MainActivity).viewModel

        user = viewModel.userData.value
        Log.i(TAG, "initViewModel: ")
        viewModel.userData.observe(viewLifecycleOwner, Observer {
            Log.i(TAG, "getUserData: $it")
            user = it
        })
    }
}