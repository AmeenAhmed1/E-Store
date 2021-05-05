package com.ameen.e_store.data

import com.ameen.e_store.R
import com.ameen.e_store.data.model.CategoriesModel
import com.ameen.e_store.data.model.ProductModel

object DummyData {

    lateinit var categoriesList: MutableList<CategoriesModel>
    lateinit var productsList: MutableList<ProductModel>

    //Populate the Categories Data
    fun getCategoriesData(): MutableList<CategoriesModel> {

        categoriesList = mutableListOf()

        categoriesList.addAll(
            listOf(
                CategoriesModel(R.drawable.icon_mens_shoe, "Men"),
                CategoriesModel(R.drawable.icon_womens_shoe, "Women"),
                CategoriesModel(R.drawable.icon_devices, "Devices"),
                CategoriesModel(R.drawable.icon_gadgets, "Gadgets"),
                CategoriesModel(R.drawable.icon_gaming, "Gaming")
            )
        )

        return categoriesList
    }


    //Populate the product list
    fun getProducts(): MutableList<ProductModel> {

        productsList = mutableListOf()

        productsList.addAll(
            listOf(
                ProductModel(R.drawable.image_explore, "BeoPlay Speaker", "Bang and Olufsen", 755),
                ProductModel(
                    R.drawable.image_explore_watch,
                    "Leather Wristwatch",
                    "Tag Heuer",
                    450,
                    productStateNew = true
                ),
                ProductModel(R.drawable.image_explore, "BeoPlay Speaker", "Bang and Olufsen", 755)
            )
        )

        return productsList
    }
}