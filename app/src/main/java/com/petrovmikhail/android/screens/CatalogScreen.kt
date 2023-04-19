package com.petrovmikhail.android.screens

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.compose.foundation.lazy.items
import androidx.compose.material.TextField
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import coil.compose.AsyncImage

@Composable
fun CatalogScreen() {
    val catalogViewModel: CatalogViewModel = viewModel()
    val viewState by catalogViewModel.viewState.collectAsState()

    Column {
        TextField(
            value = viewState.searchQuery,
            onValueChange = { catalogViewModel.obtainEvent(CatalogScreenEvent.ChangeQuery(it)) }
        )
        Box(
            modifier = Modifier
                .clickable { catalogViewModel.obtainEvent(CatalogScreenEvent.SearchButtonClicked) }
                .background(Color(0xFF53E88B))
        ) {
            Text(text = "Search")
        }
        Row() {
            val popularColor = if (viewState.category == RestaurantsCategory.Popular) Color(0xFF444444) else Color(0xFFBBBBBB)
            val nearestColor = if (viewState.category == RestaurantsCategory.Nearest) Color(0xFF444444) else Color(0xFFBBBBBB)
            Box(
                modifier = Modifier
                    .background(popularColor)
                    .clickable { catalogViewModel.obtainEvent(CatalogScreenEvent.ChangeRestaurantsCategory(RestaurantsCategory.Popular)) }
            ) {
                Text(text = "Popular")
            }
            Box(
                modifier = Modifier
                    .background(nearestColor)
                    .clickable { catalogViewModel.obtainEvent(CatalogScreenEvent.ChangeRestaurantsCategory(RestaurantsCategory.Nearest)) }
            ) {
                Text(text = "Nearest")
            }
        }
        when (viewState.category) {
            RestaurantsCategory.Popular -> Catalog(viewState.popularRestaurant)
            RestaurantsCategory.Nearest -> Catalog(viewState.nearestRestaurant)
        }
    }
}

@Composable
fun Catalog(restaurants: List<Restaurant>) {
    LazyColumn() {
        items(restaurants) { restaurant ->
            AsyncImage(
                modifier = Modifier.size(200.dp, 200.dp),
                model = restaurant.logo,
                contentDescription = null
            )
            Text(text = restaurant.name)
            Text(text = restaurant.deliveryTime)
        }
    }
}