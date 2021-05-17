package com.ameen.e_store.ui.fragment

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.viewModelScope
import androidx.navigation.fragment.findNavController
import androidx.navigation.fragment.navArgs
import com.ameen.e_store.R
import com.ameen.e_store.data.model.UserModel
import com.ameen.e_store.databinding.FragmentAccountBinding
import com.ameen.e_store.repository.ProductRepository
import com.ameen.e_store.viewmodel.ProductViewModel
import com.ameen.e_store.viewmodel.ViewModelProductProvider
import kotlinx.coroutines.launch

class AccountFragment : Fragment(R.layout.fragment_account) {

    private val TAG = "AccountFragment"

    //Binding
    private var _binding: FragmentAccountBinding? = null
    val binding get() = _binding

    //User args.
    //private val args: AccountFragmentArgs by navArgs()
    private var user: UserModel? = null

    //ViewModel
    lateinit var viewModel: ProductViewModel
    lateinit var repository: ProductRepository

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        //init ViewModel
        initViewModel()

        viewModel.getUserData()
        viewModel.userData.observe(viewLifecycleOwner, Observer {
            user = it
        })

        _binding = FragmentAccountBinding.inflate(inflater, container, false)
        return binding!!.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        Log.i(TAG, "onViewCreated: $user")

        binding?.apply {
            accountImageProfile.setImageResource(user!!.userImage)
            accountProfileName.text = user!!.userName
            accountProfileEmail.text = user!!.userEmail
            cardSection.setOnClickListener {
                val bundle = Bundle()
                bundle.putSerializable("userData", user)
                findNavController().navigate(
                    R.id.action_accountFragment_to_cardsFragment,
                    bundle
                )
            }

            addressSection.setOnClickListener {
                val bundle = Bundle()
                bundle.putSerializable("userData", user)
                findNavController().navigate(
                    R.id.action_accountFragment_to_addressFragment,
                    bundle
                )
            }
        }
    }

    private fun initViewModel() {
        repository = ProductRepository()
        viewModel = ViewModelProvider(
            this,
            ViewModelProductProvider(repository)
        ).get(ProductViewModel::class.java)
    }
}