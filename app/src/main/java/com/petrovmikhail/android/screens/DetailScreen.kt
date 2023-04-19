package com.petrovmikhail.android.screens

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController

@Composable
fun DetailScreen(name: String, navController: NavController) {
    Column {
        Text(
            modifier = Modifier
                .clickable {
                    navController.navigate("catalog")
                },
            text = "<- Back",
            fontSize = 24.sp
        )
        Box(modifier = Modifier.fillMaxSize()) {
            Text("Hello, $name", fontWeight = FontWeight.Medium, fontSize = 24.sp)
        }
    }
}