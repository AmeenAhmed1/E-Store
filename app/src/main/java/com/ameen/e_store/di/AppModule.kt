package com.ameen.e_store.di

import android.content.Context
import androidx.room.Room
import com.ameen.e_store.data.local.CartDatabase
import com.ameen.e_store.repository.ProductRepository
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ActivityComponent
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object AppModule {

    @Provides
    @Singleton
    fun provideRoomDatabase(@ApplicationContext context: Context) =
        Room.databaseBuilder(
            context,
            CartDatabase::class.java,
            "cart.db"
        ).fallbackToDestructiveMigration().build()

    @Provides
    @Singleton
    fun provideRepository(db: CartDatabase) =
        ProductRepository(db)

}