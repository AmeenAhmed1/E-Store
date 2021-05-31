package com.ameen.e_store.ui.fragment

import android.util.Log
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.lifecycle.Observer
import androidx.recyclerview.widget.LinearLayoutManager
import com.ameen.e_store.adapter.CartAdapter
import com.ameen.e_store.databinding.FragmentCartBinding
import com.ameen.e_store.ui.BaseFragment
import com.ameen.e_store.ui.activity.MainActivity
import com.ameen.e_store.viewmodel.ProductViewModel

class CartFragment : BaseFragment<FragmentCartBinding>() {

    private val TAG = "CartFragment"

    //ViewModel
    lateinit var productViewModel: ProductViewModel

    //Adapter
    lateinit var cartAdapter: CartAdapter

    override val bindingInflater: (LayoutInflater, ViewGroup?, Boolean) -> FragmentCartBinding
        get() = FragmentCartBinding::inflate

    override fun setupOnViewCreated() {
        initViewModel()
        initFragment()
    }

    private fun initViewModel() {
        productViewModel = (activity as MainActivity).viewModel
        productViewModel.getCartData()

        productViewModel.cartData.observe(viewLifecycleOwner, Observer { resultList ->
            cartAdapter.diff.submitList(resultList)

            var totalPrice = 0

            for (itemPrice in resultList) {
                totalPrice += itemPrice.productPrice * itemPrice.productCountInCart
            }

            Log.i(TAG, "onViewCreated: Price: $totalPrice")
            Log.i(TAG, "onViewCreated: $resultList")
            binding!!.totalPriceText.text = "$" + totalPrice.toString()
        })
    }

    private fun initFragment() {
        cartAdapter = CartAdapter(viewModel = productViewModel)
        binding!!.cartItemRecycler.apply {
            adapter = cartAdapter
            layoutManager = LinearLayoutManager(activity, LinearLayoutManager.VERTICAL, false)
        }
    }
}