package com.petrovmikhail.android.data.catalog

import kotlinx.serialization.Serializable

@Serializable
data class CatalogResponse(
    val nearest: List<RemoteRestaurant>,
    val popular: List<RemoteRestaurant>,
    val commercial: RemoteCommercial? = null
)

@Serializable
data class RemoteRestaurant (
    val id: Int,
    val name: String,
    val deliveryTime: String,
    val image: String
)

@Serializable
data class RemoteCommercial (
    val picture: String,
    val url: String
)