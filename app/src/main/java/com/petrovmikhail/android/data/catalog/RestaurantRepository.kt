package com.petrovmikhail.android.data.catalog

import io.ktor.client.HttpClient
import io.ktor.client.call.body
import io.ktor.client.request.request
import io.ktor.http.HttpMethod
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import java.lang.Exception
import javax.inject.Inject

class RestaurantRepository @Inject constructor(
    private val httpClient: HttpClient,
    private val restaurantDao: RestaurantDao
) {

    suspend fun fetchCatalog(): Flow<CatalogResponse> {
        return flow {
            val nearestCache = restaurantDao.getNearest()
            val popularCache = restaurantDao.getPopular()
            if (nearestCache.isNotEmpty() || popularCache.isNotEmpty()) {
                emit(
                    CatalogResponse(
                        nearest = nearestCache.map { it.mapToRemoteRestaurant() },
                        popular = popularCache.map { it.mapToRemoteRestaurant() }
                    )
                )
            }

            try {
                val response = httpClient.request("http://195.2.84.138:8081/catalog") {
                    method = HttpMethod.Get
                }.body<CatalogResponse>()

                restaurantDao.insertAll(*response.popular.map {
                    it.mapToRestaurantEntity("popular")
                }.toTypedArray())

                restaurantDao.insertAll(*response.nearest.map {
                    it.mapToRestaurantEntity("nearest")
                }.toTypedArray())

                emit(response)
            } catch (e: Exception) {
                // Handle error
            }
        }
    }
}