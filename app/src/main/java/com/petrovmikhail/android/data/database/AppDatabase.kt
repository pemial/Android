package com.petrovmikhail.android.data.database

import androidx.room.Database
import androidx.room.RoomDatabase
import com.petrovmikhail.android.data.catalog.RestaurantDao
import com.petrovmikhail.android.data.catalog.RestaurantEntity

@Database(entities = [RestaurantEntity::class], version = 1)
abstract class AppDatabase : RoomDatabase() {
    abstract fun restaurantDao(): RestaurantDao
}