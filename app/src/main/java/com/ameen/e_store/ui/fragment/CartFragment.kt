package com.ameen.e_store.ui.fragment

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.recyclerview.widget.LinearLayoutManager
import com.ameen.e_store.R
import com.ameen.e_store.adapter.CartAdapter
import com.ameen.e_store.databinding.FragmentCartBinding
import com.ameen.e_store.ui.activity.MainActivity
import com.ameen.e_store.viewmodel.ProductViewModel

class CartFragment : Fragment(R.layout.fragment_cart) {

    private val TAG = "CartFragment"

    private var _binding: FragmentCartBinding? = null
    val binding get() = _binding

    //ViewModel
    lateinit var productViewModel: ProductViewModel

    //Adapter
    lateinit var cartAdapter: CartAdapter

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        productViewModel = (activity as MainActivity).viewModel
        productViewModel.getCartData()

        _binding = FragmentCartBinding.inflate(inflater, container, false)
        return binding!!.root
    }

    override fun onDestroy() {
        super.onDestroy()
        _binding = null
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        _binding = FragmentCartBinding.bind(view)

        initFragment()

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