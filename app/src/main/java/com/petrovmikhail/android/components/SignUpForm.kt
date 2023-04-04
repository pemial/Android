package com.petrovmikhail.android.components

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.petrovmikhail.android.R
import com.petrovmikhail.android.screens.SignUpScreenEvent
import com.petrovmikhail.android.screens.SignUpViewModel

@Composable
fun SignUpForm(viewModel: SignUpViewModel) {
    val viewState by viewModel.viewState.collectAsState()

    Column(
        modifier = Modifier
            .padding(start = 15.dp, end = 15.dp),
        verticalArrangement = Arrangement.spacedBy(20.dp),
        horizontalAlignment = Alignment.CenterHorizontally,
    ) {
        Text(
            text = "Sign Up For Free",
            fontSize = 20.sp,
            fontWeight = FontWeight.Bold,
            textAlign = TextAlign.Center
        )
        Field(
            painterId = R.drawable.profile,
            label = "login",
            value = viewState.login,
            onValueChange = { value -> viewModel.obtainEvent(SignUpScreenEvent.LoginChanged(value)) },
            canBeHidden = false
        )
        Field(
            painterId = R.drawable.message,
            label = "email",
            value = viewState.email,
            onValueChange = { value -> viewModel.obtainEvent(SignUpScreenEvent.EmailChanged(value)) },
            canBeHidden = false
        )
        Field(
            painterId = R.drawable.lock,
            label = "password",
            value = viewState.password,
            onValueChange = { value -> viewModel.obtainEvent(SignUpScreenEvent.PasswordChanged(value)) },
            canBeHidden = true,
            isHidden = viewState.isPasswordHidden,
            onHide = { viewModel.obtainEvent(SignUpScreenEvent.PasswordVisibilityChanged) }
        )
        Column(
            modifier = Modifier
                .fillMaxWidth(),
            verticalArrangement = Arrangement.spacedBy(20.dp)
        ) {
            CheckBox(
                text = "Keep Me Signed In",
                isChecked = viewState.isKeepMeSignedInChecked,
                onChecked = { viewModel.obtainEvent(SignUpScreenEvent.KeepMeSignedInChecked) }
            )
            CheckBox(
                text = "Email Me About Special Pricing",
                isChecked = viewState.isEmailMeChecked,
                onChecked = { viewModel.obtainEvent(SignUpScreenEvent.EmailMeChecked) }
            )
        }
        Box(
            modifier = Modifier
                .clip(RoundedCornerShape(10.dp))
                .background(Color(0xFF53E88B))
                .height(50.dp)
                .width(200.dp)
                .align(Alignment.CenterHorizontally)
                .clickable { viewModel.obtainEvent(SignUpScreenEvent.CreateAccountClicked) },
            contentAlignment = Alignment.Center
        ) {
            Text(
                text = "Create Account",
                color = Color.White,
                fontWeight = FontWeight.Bold,
                fontSize = 20.sp
            )
        }
        Text(
            modifier = Modifier
                .padding(bottom = 10.dp)
                .clickable { viewModel.obtainEvent(SignUpScreenEvent.HaveAccountClicked) },
            text = "already have an account?",
            fontSize = 14.sp,
            color = Color(0xFF53E88B)
        )
    }
}