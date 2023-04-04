package com.petrovmikhail.android.screens

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.lifecycle.viewmodel.compose.viewModel
import com.petrovmikhail.android.R
import com.petrovmikhail.android.components.Logotype
import com.petrovmikhail.android.components.SignUpForm

@Composable
fun SignUpScreen() {
    val signUpViewModel: SignUpViewModel = viewModel()

    Image(
        alignment = Alignment.TopStart,
        contentScale = ContentScale.FillWidth,
        painter = painterResource(id = R.drawable.pattern),
        contentDescription = "pattern"
    )
    Column {
        Logotype()
        Spacer(modifier = Modifier.weight(1f))
        SignUpForm(signUpViewModel)
    }
}