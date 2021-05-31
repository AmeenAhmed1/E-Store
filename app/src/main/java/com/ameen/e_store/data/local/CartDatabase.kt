package com.ameen.e_store.data.local

import android.content.Context
import androidx.room.*
import com.ameen.e_store.data.model.*

@Database(
    entities = [ProductModel::class, BrandModel::class, CategoriesModel::class, ReviewModel::class, UserModel::class],
    version = 4,
)
abstract class CartDatabase : RoomDatabase() {

    abstract fun getCartDao(): CartDao

//    companion object {
//        @Volatile
//        private var instanse: CartDatabase? = null
//        private val LOCK = Any()
//
//        operator fun invoke(context: Context) = instanse ?: synchronized(LOCK) {
//            instanse ?: createDataBase(context).also { instanse = it }
//        }
//
//        private fun createDataBase(context: Context) =
//            Room.databaseBuilder(
//                context.applicationContext,
//                CartDatabase::class.java,
//                "cart.db"
//            ).fallbackToDestructiveMigration().build()
//    }
}