package com.ameen.e_store.data

import android.util.Log
import com.ameen.e_store.R
import com.ameen.e_store.data.model.*

object DummyData {

    lateinit var categoriesList: MutableList<CategoriesModel>
    lateinit var productsList: MutableList<ProductModel>
    lateinit var brandsList: MutableList<BrandModel>
    lateinit var reviewList: MutableList<ReviewModel>
    lateinit var userModel: UserModel
    lateinit var cartList: MutableList<ProductModel>

    private const val TAG = "DummyData"

    //Populate the Categories Data
    fun getCategoriesData(): MutableList<CategoriesModel> {

        categoriesList = mutableListOf()

        categoriesList.addAll(
            listOf(
                CategoriesModel(1, R.drawable.icon_mens_shoe, "Men"),
                CategoriesModel(2, R.drawable.icon_womens_shoe, "Women"),
                CategoriesModel(3, R.drawable.icon_devices, "Devices"),
                CategoriesModel(4, R.drawable.icon_gadgets, "Gadgets"),
                CategoriesModel(5, R.drawable.icon_gaming, "Gaming")
            )
        )

        return categoriesList
    }


    //Populate the product list
    fun getProducts(): MutableList<ProductModel> {

        productsList = mutableListOf()

        productsList.addAll(
            listOf(
                ProductModel(
                    1,
                    R.drawable.image_explore,
                    "BeoPlay Speaker",
                    "Bang and Olufsen",
                    755,
                    productBrand = 1,
                    productCategory = 1,
                    //productReviews = 1
                ),
                ProductModel(
                    2,
                    R.drawable.image_explore_watch,
                    "Leather Wristwatch",
                    "Tag Heuer",
                    450,
                    productStateNew = true,
                    productBrand = 1,
                    productCategory = 1,
                    //productReviews = 1
                ),
                ProductModel(
                    3,
                    R.drawable.image_explore,
                    "BeoPlay Speaker",
                    "Bang and Olufsen",
                    755,
                    productBrand = 1,
                    productCategory = 1,
                    //productReviews = 1
                ),
                ProductModel(
                    4,
                    R.drawable.image_explore,
                    "BeoPlay Speaker",
                    "Bang and Olufsen",
                    755,
                    productStateNew = true,
                    productCountInCart = 1,
                    productBrand = 1,
                    productCategory = 1,
                    //productReviews = 1
                ),
                ProductModel(
                    5,
                    R.drawable.image_explore,
                    "BeoPlay Speaker",
                    "Bang and Olufsen",
                    755,
                    productStateNew = true,
                    productBrand = 1,
                    productCategory = 1,
                    //productReviews = 1
                ),
                ProductModel(
                    6,
                    R.drawable.image_airpods,
                    "Air pods",
                    "Air Pods Pro 2",
                    1050,
                    productStateNew = true,
                    productCountInCart = 3,
                    productBrand = 1,
                    productCategory = 1,
                    //productReviews = 1
                ),
            )
        )

        return productsList
    }

    //Populate brands data
    fun getBrands(): MutableList<BrandModel> {

        brandsList = mutableListOf()

        brandsList.addAll(
            listOf(
                BrandModel(1, R.drawable.icon_beats, "Beats", 1124),
                BrandModel(2, R.drawable.icon_apple, "Apple Inc.", 5693),
                BrandModel(3, R.drawable.icon_b_o, "B&o", 3614)
                //BrandModel(R.drawable.icon_b_o, "B&o", 3614)
            )
        )

        return brandsList
    }

    //Populate product reviews
    fun getReviews(): MutableList<ReviewModel> {

        reviewList = mutableListOf()

        reviewList.addAll(
            listOf(
                ReviewModel(
                    //UserModel("123", "Ameen", "Ameen", R.drawable.ic_account_profile),
                    reviewUserId = 1,
                    reviewBody = "Review Body 1",
                    reviewRating = 3f,
                    reviewProductId = 1
                ),
                ReviewModel(
                    //UserModel("123", "Test User", "Test User", R.drawable.ic_account_profile),
                    reviewUserId = 1,
                    reviewBody = "Test Review Body 2",
                    reviewRating = 4f,
                    reviewProductId = 1
                ),
                ReviewModel(
                    //UserModel("123", "Ahmed", "Ahmed", R.drawable.ic_account_profile),
                    reviewUserId = 1,
                    reviewBody = "Review Body 3",
                    reviewRating = 5f,
                    reviewProductId = 1
                ),
                ReviewModel(
                    //UserModel("123", "Username", "Email", R.drawable.ic_account_profile),
                    reviewUserId = 1,
                    reviewBody = "Review Body 4",
                    reviewRating = 5f,
                    reviewProductId = 1
                )
            )
        )

        return reviewList
    }


    fun getUserData(): UserModel {
        val card = CardModel(1233467581234987, "Ameen Essa", "12 - 9", CardTypeEnum.MASTER_CARD)
        val address1 = AddressModel("Home", "Home Address Description Flat No.50", true)
        val address2 = AddressModel("Work", "Work Address 5002 St, Floor No.2, Flat No.12")
        userModel = UserModel(
            1,
            "Ameen Essa",
            "ameen.mobiledev@gmail.com",
            R.drawable.ic_account_profile,
//            listOf(card),
//            listOf(address1, address2)
        )

        return userModel
    }


    fun getCartData(productsCart: MutableList<ProductModel>): MutableList<ProductModel> {

        cartList = mutableListOf()

        for (isItemInCart in productsCart)
            if (isItemInCart.productCountInCart != 0) {
                Log.i(TAG, "getCartData: ${isItemInCart.productId}")
                cartList.add(isItemInCart)
            }

        return cartList
    }

}