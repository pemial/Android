package com.petrovmikhail.android.data.catalog

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "restaurants")
data class RestaurantEntity(
    @PrimaryKey val id: Int,
    @ColumnInfo(name = "name") val name: String,
    @ColumnInfo(name = "logo") val logo: String,
    @ColumnInfo(name = "deliveryTime") val time: String,
    @ColumnInfo val category: String
)

fun RemoteRestaurant.mapToRestaurantEntity(category: String): RestaurantEntity =
    RestaurantEntity(id = id, name = name, logo = image, time = deliveryTime, category = category)

fun RestaurantEntity.mapToRemoteRestaurant(): RemoteRestaurant =
    RemoteRestaurant(id = id, name = name, image = logo, deliveryTime = time)