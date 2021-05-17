package com.ameen.e_store.ui.fragment

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.navigation.fragment.NavHostFragment
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.LinearLayoutManager
import com.ameen.e_store.R
import com.ameen.e_store.adapter.ProductsAdapter
import com.ameen.e_store.adapter.BrandsAdapter
import com.ameen.e_store.adapter.CategoriesAdapter
import com.ameen.e_store.data.model.ProductModel
import com.ameen.e_store.databinding.FragmentExploreBinding
import com.ameen.e_store.ui.activity.MainActivity
import com.ameen.e_store.viewmodel.ProductViewModel

class ExploreFragment : Fragment(R.layout.fragment_explore) {

    private val TAG = "ExploreFragment"

    //ViewModels
    lateinit var productsViewModel: ProductViewModel

    //Binding
    private var _binding: FragmentExploreBinding? = null
    private val binding get() = _binding!!

    //RecyclerView
    private lateinit var categoriesAdapter: CategoriesAdapter
    private lateinit var productsAdapter: ProductsAdapter
    private lateinit var brandsAdapter: BrandsAdapter
    private lateinit var recommendedAdapter: ProductsAdapter


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