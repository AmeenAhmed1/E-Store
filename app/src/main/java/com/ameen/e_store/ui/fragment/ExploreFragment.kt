package com.ameen.e_store.ui.fragment

import android.graphics.drawable.Drawable
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.ameen.e_store.R
import com.ameen.e_store.adapter.BestSellingAdapter
import com.ameen.e_store.adapter.RecyclerAdapter
import com.ameen.e_store.databinding.FragmentExploreBinding

class ExploreFragment : Fragment(R.layout.fragment_explore) {

    private val TAG = "ExploreFragment"

    private val imageListDrawable = listOf(
        R.drawable.icon_mens_shoe,
        R.drawable.icon_womens_shoe,
        R.drawable.icon_devices,
        R.drawable.icon_gadgets,
        R.drawable.icon_gaming
    )

    private val imageListSelling = listOf(
        R.drawable.image_explore,
        R.drawable.image_explore_watch,
        R.drawable.image_explore_watch,
    )


    //Binding
    private var _binding: FragmentExploreBinding? = null
    private val binding get() = _binding!!

    //RecyclerView
    private lateinit var recyclerAdapter: RecyclerAdapter
    private lateinit var imageList: List<Int>
    private lateinit var nameList: Array<String>


    private lateinit var recyclerBestSellingAdapter: BestSellingAdapter
    private lateinit var imageExploreList: List<Int>
    private lateinit var titleExploreList: Array<String>
    private lateinit var decExploreList: Array<String>
    private lateinit var priceExploreList: Array<String>

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        populateData()

        _binding = FragmentExploreBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onDestroy() {
        super.onDestroy()
        _binding = null
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        Log.i(TAG, "onViewCreated: $imageList")

        //init Recycler
        recyclerAdapter = RecyclerAdapter(imageList, nameList)
        binding.categoriesRecycler.apply {
            layoutManager = LinearLayoutManager(activity, LinearLayoutManager.HORIZONTAL, false)
            adapter = recyclerAdapter
            hasFixedSize()
        }

        //init best selling recycler
        recyclerBestSellingAdapter =
            BestSellingAdapter(imageExploreList, titleExploreList, decExploreList, priceExploreList)
        binding.bestSellingRecyclerView.apply {
            layoutManager = GridLayoutManager(activity, 2)
            adapter = recyclerBestSellingAdapter
        }
    }


    private fun populateData() {
        //Populate the data into the Array
        nameList = activity?.resources?.getStringArray(R.array.categoriesTitles) as Array<String>
        imageList = imageListDrawable


        titleExploreList =
            activity?.resources?.getStringArray(R.array.bestSellingTitles) as Array<String>
        decExploreList =
            activity?.resources?.getStringArray(R.array.bestSellingDescription) as Array<String>
        priceExploreList =
            activity?.resources?.getStringArray(R.array.bestSellingPrice) as Array<String>
        imageExploreList = imageListSelling
    }
}