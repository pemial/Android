package com.petrovmikhail.android.components

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Text
import androidx.compose.material.TextField
import androidx.compose.material.TextFieldDefaults
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.text.input.VisualTransformation
import androidx.compose.ui.unit.dp
import com.petrovmikhail.android.R

@Composable
fun Field(
    painterId: Int,
    label: String,
    value: String,
    onValueChange: (String) -> Unit,
    canBeHidden: Boolean,
    isHidden: Boolean = false,
    onHide: () -> Unit = {}
) {
    Box(
        modifier = Modifier
            .clip(RoundedCornerShape(15.dp))
            .fillMaxWidth()
            .height(54.dp)
            .background(Color(0xFFF4F4F4)),
    ) {
        TextField(
            modifier = Modifier
                .fillMaxSize(),
            leadingIcon = {
                Image(
                    modifier = Modifier
                        .width(20.dp)
                        .height(20.dp),
                    painter = painterResource(id = painterId),
                    contentDescription = "Email"
                )
            },
            trailingIcon = {
                if (canBeHidden) {
                    Image(
                        modifier = Modifier
                            .width(20.dp)
                            .height(20.dp)
                            .clickable {
                                onHide()
                            },
                        painter = painterResource(id = R.drawable.show),
                        contentDescription = "Show/Hide",
                    )
                }
            },
            value = value,
            onValueChange = onValueChange,
            visualTransformation = if (isHidden) PasswordVisualTransformation()
                                    else VisualTransformation.None,
            colors = TextFieldDefaults.textFieldColors(
                backgroundColor = Color(0xFFF4F4F4)
            ),
            placeholder = { Text(label, color = Color(0x44000000)) },
        )
    }
}
