package com.petrovmikhail.android.components

import androidx.compose.foundation.Canvas
import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.material.Text
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.petrovmikhail.android.R

@Composable
fun CheckBox(
    text: String,
    isChecked: Boolean,
    onChecked: () -> Unit
)
{
    Row(
        modifier = Modifier
            .clickable { onChecked() },
        verticalAlignment = Alignment.CenterVertically
    ) {
        Box {
            Canvas(
                modifier = Modifier
                    .size(20.dp)
            ) {
                drawCircle(Color(0xFF000000), radius = 10.dp.toPx())
                if (isChecked) {
                    drawCircle(Color(0xFF53E88B), radius = 9.dp.toPx())
                } else {
                    drawCircle(Color(0xFFFFFFFF), radius = 9.dp.toPx())
                }
            }
            if (isChecked) {
                Image(
                    modifier = Modifier
                        .padding(top = 5.dp, start = 5.dp)
                        .width(10.dp)
                        .height(10.dp),
                    painter = painterResource(id = R.drawable.check_mark),
                    contentDescription = "check mark"
                )
            }
        }
        Text(
            modifier = Modifier
                .padding(start = 13.dp),
            text = text,
            fontSize = 13.sp
        )
    }
}
