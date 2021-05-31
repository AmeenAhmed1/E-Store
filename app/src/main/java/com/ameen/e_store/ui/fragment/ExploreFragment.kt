package com.ameen.e_store.ui.fragment

import android.os.Bundle
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.lifecycle.Observer
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import com.ameen.e_store.R
import com.ameen.e_store.adapter.BrandsAdapter
import com.ameen.e_store.adapter.CategoriesAdapter
import com.ameen.e_store.adapter.ProductsAdapter
import com.ameen.e_store.data.model.ProductModel
import com.ameen.e_store.databinding.FragmentExploreBinding
import com.ameen.e_store.ui.BaseFragment
import com.ameen.e_store.ui.activity.MainActivity
import com.ameen.e_store.viewmodel.ProductViewModel

class ExploreFragment : BaseFragment<FragmentExploreBinding>() {

    private val TAG = "ExploreFragment"

    //ViewModels
    lateinit var productsViewModel: ProductViewModel

    //RecyclerView
    private lateinit var categoriesAdapter: CategoriesAdapter
    private lateinit var productsAdapter: ProductsAdapter
    private lateinit var brandsAdapter: BrandsAdapter
    private lateinit var recommendedAdapter: ProductsAdapter

    override val bindingInflater: (LayoutInflater, ViewGroup?, Boolean) -> FragmentExploreBinding
        get() = FragmentExploreBinding::inflate

    override fun setupOnViewCreated() {
        productsViewModel = (activity as MainActivity).viewModel
        initViewModel()
        initRecycler()
    }

    private fun initViewModel() {

        //init ViewModels
        productsViewModel.categoriesData.observe(viewLifecycleOwner, Observer { resultList ->
            categoriesAdapter.differ.submitList(resultList)
        })

        productsViewModel.productsData.observe(viewLifecycleOwner, Observer { resultList ->
            productsAdapter.differ.submitList(resultList)
        })

        productsViewModel.brandsData.observe(viewLifecycleOwner, Observer { resultList ->
            brandsAdapter.differ.submitList(resultList)
        })

        productsViewModel.recommendedData.observe(viewLifecycleOwner, Observer { resultList ->
            recommendedAdapter.differ.submitList(resultList)
        })
    }


    private fun initRecycler() {

        //init Recycler
        categoriesAdapter = CategoriesAdapter()
        binding.categoriesRecycler.apply {
            layoutManager = LinearLayoutManager(activity, LinearLayoutManager.HORIZONTAL, false)
            adapter = categoriesAdapter
            hasFixedSize()
        }

        //init best selling recycler
        productsAdapter = ProductsAdapter()
        productsAdapter.onItemClicked {
            navigateToDetails(it)
        }

        //init bestSelling recycler
        binding.bestSellingRecyclerView.apply {
            layoutManager = LinearLayoutManager(activity, LinearLayoutManager.HORIZONTAL, false)
            adapter = productsAdapter
        }

        //init brands recycler
        brandsAdapter = BrandsAdapter()
        binding.brandsRecycler.apply {
            layoutManager = LinearLayoutManager(activity, LinearLayoutManager.HORIZONTAL, false)
            adapter = brandsAdapter
        }

        //init the recommended recycler
        recommendedAdapter = ProductsAdapter()
        recommendedAdapter.onItemClicked {
            navigateToDetails(it)
        }
        binding.recommendRecyclerView.apply {
            layoutManager = LinearLayoutManager(activity, LinearLayoutManager.HORIZONTAL, false)
            adapter = recommendedAdapter
        }
    }

    private fun navigateToDetails(product: ProductModel) {
        val bundle = Bundle()
        bundle.putSerializable("productDetails", product)
        findNavController().navigate(
            R.id.action_exploreFragment_to_detailsFragment,
            bundle
        )
    }
}
