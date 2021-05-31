package com.ameen.e_store.ui.fragment

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.lifecycleScope
import androidx.navigation.fragment.findNavController
import androidx.navigation.fragment.navArgs
import androidx.recyclerview.widget.LinearLayoutManager
import com.ameen.e_store.R
import com.ameen.e_store.adapter.ReviewAdapter
import com.ameen.e_store.data.DummyData
import com.ameen.e_store.data.model.ProductModel
import com.ameen.e_store.databinding.FragmentProductDetailsBinding
import com.ameen.e_store.ui.BaseFragment
import com.ameen.e_store.ui.activity.MainActivity
import com.bumptech.glide.Glide
import kotlinx.coroutines.launch

class DetailsFragment : BaseFragment<FragmentProductDetailsBinding>() {

    private val TAG = "DetailsFragment"

    //Sent args
    private val args: DetailsFragmentArgs by navArgs()
    private lateinit var selectedProduct: ProductModel

    //Adapter
    private lateinit var reviewAdapter: ReviewAdapter


    override val bindingInflater: (LayoutInflater, ViewGroup?, Boolean) -> FragmentProductDetailsBinding
        get() = FragmentProductDetailsBinding::inflate

    override fun setupOnViewCreated() {

        selectedProduct = args.productDetails

        Log.i(TAG, "onViewCreated: $selectedProduct")

        //if (selectedProduct.productReviews != null)
        binding.detailsContent.emptyReviewText.visibility = View.GONE

        if (selectedProduct.productStateNew)
            binding.detailsContent.productState.exploreItemNewTag.visibility = View.VISIBLE
        else
            binding.detailsContent.productState.exploreItemNewTag.visibility = View.INVISIBLE

        //For the image, price & cart button
        binding.apply {
            Glide.with(binding.root)
                .load(selectedProduct.productImage)
                .centerCrop()
                .into(productDetailsImage)
            totalPriceText.text = "$" + selectedProduct.productPrice.toString()
            addToCartButton.setOnClickListener {
                addToCart()
            }
            backArrowButton.setOnClickListener {
                findNavController().popBackStack()
            }
        }

        //For the details and reviews item
        binding.detailsContent.apply {
            productTitleHeadLine.text = selectedProduct.productTitle
            productDescription.text = selectedProduct.productDescription
            productWriteReview.setOnClickListener {
                val writeReviewSheet = WriteReviewFragment()
                writeReviewSheet.show(parentFragmentManager, "BottomSheet")
            }
        }

        //init the reviews
        initReviewRatingRecycler()
    }

    private fun initReviewRatingRecycler() {
        reviewAdapter = ReviewAdapter()
        //reviewAdapter.differ.submitList(selectedProduct.productReviews)
        binding.detailsContent.reviewsRecycler.apply {
            layoutManager = LinearLayoutManager(activity, LinearLayoutManager.VERTICAL, false)
            adapter = reviewAdapter
            hasFixedSize()
        }
    }


    private fun addToCart() {
        val model = (activity as MainActivity).viewModel
        model.insertBrands(DummyData.getBrands())
        model.insertCategories(DummyData.getCategoriesData())
        model.insertUser(DummyData.getUserData())
        model.saveCartItem(selectedProduct)
        model.insertReviews(DummyData.getReviews())

        Log.i(TAG, "addToCart: $model")
    }
}