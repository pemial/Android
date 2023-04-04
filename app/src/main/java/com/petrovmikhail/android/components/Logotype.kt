package com.petrovmikhail.android.components

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.petrovmikhail.android.R

@Composable
fun Logotype() {
    Column(
        modifier = Modifier
            .fillMaxWidth(),
        horizontalAlignment = Alignment.CenterHorizontally,
    ) {
        Image(
            modifier = Modifier
                .fillMaxWidth()
                .height(140.dp),
            painter = painterResource(id = R.drawable.logo),
            contentDescription = "Logo"
        )
        Text(
            text = "FoodNinja",
            color = Color(0xFF53E88B),
            fontSize = 37.sp,
            fontWeight = FontWeight.Bold
        )
        Text(
            text = "Deliver Favorite Food",
            fontSize = 13.sp,
            fontWeight = FontWeight.Bold
        )
    }
}
