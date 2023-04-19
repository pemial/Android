package com.petrovmikhail.android.screens

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.petrovmikhail.android.data.catalog.RemoteRestaurant
import com.petrovmikhail.android.data.catalog.RestaurantRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.launch
import javax.inject.Inject

data class Restaurant(
    val name: String,
    val deliveryTime: String,
    val logo: String
)

fun RemoteRestaurant.mapToRestaurant(): Restaurant {
    return Restaurant(name = name, deliveryTime = deliveryTime, logo = image)
}

sealed class RestaurantsCategory {
    object Popular: RestaurantsCategory()
    object Nearest: RestaurantsCategory()
}

data class CatalogScreenViewState(
    val nearestRestaurant: List<Restaurant> = emptyList(),
    val popularRestaurant: List<Restaurant> = emptyList(),
    val searchQuery: String = "",
    val category: RestaurantsCategory = RestaurantsCategory.Popular
)

sealed class CatalogScreenEvent {
    object SearchButtonClicked: CatalogScreenEvent()
    data class ChangeQuery(val value: String): CatalogScreenEvent()
    data class ChangeRestaurantsCategory(val value: RestaurantsCategory): CatalogScreenEvent()
}

@HiltViewModel
class CatalogViewModel @Inject constructor(private val restaurantRepository: RestaurantRepository) : ViewModel() {
    private val _viewState = MutableStateFlow(CatalogScreenViewState())
    val viewState: StateFlow<CatalogScreenViewState> = _viewState

    fun obtainEvent(event: CatalogScreenEvent) {
        when(event) {
            CatalogScreenEvent.SearchButtonClicked -> clickSearchButton()
            is CatalogScreenEvent.ChangeQuery -> changeQuery(event.value)
            is CatalogScreenEvent.ChangeRestaurantsCategory -> changeRestaurantsCategory(event.value)
        }
    }

    private fun changeQuery(value: String) {
        _viewState.value = _viewState.value.copy(searchQuery = value)
    }

    private fun changeRestaurantsCategory(value: RestaurantsCategory) {
        _viewState.value = _viewState.value.copy(category = value)
    }

    private fun clickSearchButton() {
        viewModelScope.launch(Dispatchers.IO) {
            restaurantRepository.fetchCatalog()
                .collectLatest { response ->
                    _viewState.value = _viewState.value.copy(
                        nearestRestaurant = response.nearest.map { it.mapToRestaurant() },
                        popularRestaurant = response.popular.map { it.mapToRestaurant() }
                    )
                }
        }
    }
}