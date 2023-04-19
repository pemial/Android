package com.petrovmikhail.android.data.catalog

import androidx.room.*

@Dao
interface RestaurantDao {
    @Query("SELECT * FROM restaurants WHERE category = 'nearest'")
    suspend fun getNearest(): List<RestaurantEntity>

    @Query("SELECT * FROM restaurants WHERE category = 'popular'")
    suspend fun getPopular(): List<RestaurantEntity>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertAll(vararg restaurants: RestaurantEntity)

    @Delete
    suspend fun delete(restaurantEntity: RestaurantEntity)
}