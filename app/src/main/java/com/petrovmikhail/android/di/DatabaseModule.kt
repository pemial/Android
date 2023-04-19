package com.petrovmikhail.android.di

import android.content.Context
import androidx.room.Room
import com.petrovmikhail.android.data.catalog.RestaurantDao
import com.petrovmikhail.android.data.database.AppDatabase
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent

@Module
@InstallIn(SingletonComponent::class)
class DatabaseModule {

    @Provides
    fun provideDatabase(@ApplicationContext context: Context): AppDatabase = Room.databaseBuilder(
        context,
        AppDatabase::class.java, "food_delivery"
    ).fallbackToDestructiveMigration().build()

    @Provides
    fun provideRestaurantsDao(appDatabase: AppDatabase): RestaurantDao =
        appDatabase.restaurantDao()
}