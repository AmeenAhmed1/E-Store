package com.ameen.e_store.ui.fragment

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.activity.viewModels
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.LinearLayoutManager
import com.ameen.e_store.R
import com.ameen.e_store.adapter.BestSellingAdapter
import com.ameen.e_store.adapter.RecyclerAdapter
import com.ameen.e_store.databinding.FragmentExploreBinding
import com.ameen.e_store.ui.MainActivity
import com.ameen.e_store.viewmodel.ProductViewModel

class ExploreFragment : Fragment(R.layout.fragment_explore) {

    private val TAG = "ExploreFragment"

    //ViewModels
    lateinit var productsViewModel: ProductViewModel

    //Binding
    private var _binding: FragmentExploreBinding? = null
    private val binding get() = _binding!!

    //RecyclerView
    private lateinit var recyclerAdapter: RecyclerAdapter
    private lateinit var recyclerBestSellingAdapter: BestSellingAdapter


    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentExploreBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onDestroy() {
        super.onDestroy()
        _binding = null
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        Log.i(TAG, "onViewCreated: ")

        _binding = FragmentExploreBinding.bind(view)

        productsViewModel = (activity as MainActivity).viewModel

        //init ViewModels
        productsViewModel.categoriesData.observe(viewLifecycleOwner, Observer { resultList ->
            recyclerAdapter.differ.submitList(resultList)
        })

        productsViewModel.productsData.observe(viewLifecycleOwner, Observer { resultList ->
            recyclerBestSellingAdapter.differ.submitList(resultList)
        })

        //init Recycler
        recyclerAdapter = RecyclerAdapter()
        binding.categoriesRecycler.apply {
            layoutManager = LinearLayoutManager(activity, LinearLayoutManager.HORIZONTAL, false)
            adapter = recyclerAdapter
            hasFixedSize()
        }

        //init best selling recycler
        recyclerBestSellingAdapter = BestSellingAdapter()
        binding.bestSellingRecyclerView.apply {
            layoutManager = GridLayoutManager(activity, 2)
            adapter = recyclerBestSellingAdapter
        }
    }
}