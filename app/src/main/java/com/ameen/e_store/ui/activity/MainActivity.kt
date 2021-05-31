package com.ameen.e_store.ui.activity

import android.os.Bundle
import android.util.Log
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.NavController
import androidx.navigation.NavDestination
import androidx.navigation.fragment.NavHostFragment
import androidx.navigation.fragment.findNavController
import androidx.navigation.ui.setupWithNavController
import com.ameen.e_store.R
import com.ameen.e_store.data.DummyData
import com.ameen.e_store.data.local.CartDatabase
import com.ameen.e_store.data.model.ProductModel
import com.ameen.e_store.databinding.ActivityMainBinding
import com.ameen.e_store.repository.ProductRepository
import com.ameen.e_store.viewmodel.ProductViewModel
import com.ameen.e_store.viewmodel.ViewModelProductProvider
import com.google.android.material.bottomnavigation.BottomNavigationView
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch
import javax.inject.Inject

@AndroidEntryPoint
class MainActivity : AppCompatActivity(), NavController.OnDestinationChangedListener {

    private val TAG = "MainActivity"

    //Binding
    private var _binding: ActivityMainBinding? = null
    private val binding get() = _binding

    //BottomBar
    private lateinit var navHostFragment: NavHostFragment
    private lateinit var navBottomBar: BottomNavigationView
    private lateinit var navController: NavController

    //ViewModel
    lateinit var viewModel: ProductViewModel

    @Inject
    lateinit var repository: ProductRepository

    @Inject
    lateinit var db: CartDatabase

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        _binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding!!.root)

        //init NavBar & Setting it up
        navBottomBar = binding!!.navBottom
        navHostFragment =
            supportFragmentManager.findFragmentById(R.id.main_nav_host_fragment) as NavHostFragment
        navController = navHostFragment.navController
        navBottomBar.setupWithNavController(navHostFragment.findNavController())
        navController.addOnDestinationChangedListener(this)
        navBottomBar.itemIconTintList = null


        //init ViewModel
        //db = CartDatabase(this)
        repository = ProductRepository(db)
        viewModel = ViewModelProvider(
            this,
            ViewModelProductProvider(repository)
        ).get(ProductViewModel::class.java)

        GlobalScope.launch {
            Log.i(TAG, "onCreate: GlobalScope")

            viewModel.insertBrands(DummyData.getBrands())
            viewModel.insertCategories(DummyData.getCategoriesData())
            viewModel.insertUser(DummyData.getUserData())
            viewModel.saveCartItem(
                ProductModel(
                    1,
                    R.drawable.image_explore,
                    "BeoPlay Speaker",
                    "Bang and Olufsen",
                    755,
                    productBrand = 1,
                    productCategory = 1,
                )
            )
            viewModel.insertReviews(DummyData.getReviews())

            Log.i(TAG, "addToCart: $viewModel")
        }

        //Badge
        // TODO: 5/20/2021 Handle Number Badge
        navBottomBar.getOrCreateBadge(R.id.cartFragment).apply {
            maxCharacterCount = 3
            number = 0
            isVisible = true
        }

        viewModel.cartData.observe(this, Observer {
            var counter = 0
            for (item in it) {
                counter += item.productCountInCart
            }
            Log.i(TAG, "onCreate: $counter")
            navBottomBar.getBadge(R.id.cartFragment)!!.number = counter
        })
    }

    override fun onDestinationChanged(
        controller: NavController,
        destination: NavDestination,
        arguments: Bundle?
    ) {

        when (destination.id) {
            R.id.exploreFragment -> {
                binding!!.editTextSearch.visibility = View.VISIBLE
                binding!!.cameraImageButton.visibility = View.VISIBLE
                binding!!.navBottom.visibility = View.VISIBLE
            }

            R.id.cartFragment, R.id.accountFragment, R.id.cardsFragment, R.id.addressFragment -> {
                binding!!.editTextSearch.visibility = View.GONE
                binding!!.cameraImageButton.visibility = View.GONE
                binding!!.navBottom.visibility = View.VISIBLE
            }

            else -> {
                binding!!.editTextSearch.visibility = View.GONE
                binding!!.cameraImageButton.visibility = View.GONE
                binding!!.navBottom.visibility = View.GONE
            }
        }

    }
}