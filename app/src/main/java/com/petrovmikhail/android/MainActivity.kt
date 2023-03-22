package com.petrovmikhail.android

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.*
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Alignment.Companion.Center
import androidx.compose.ui.Alignment.Companion.CenterHorizontally
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.petrovmikhail.android.ui.theme.AndroidTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            AndroidTheme {
                // A surface container using the 'background' color from the theme
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colors.background
                ) {
                    Image(
                        alignment = Alignment.TopStart,
                        contentScale = ContentScale.FillWidth,
                        painter = painterResource(id = R.drawable.pattern),
                        contentDescription = "pattern"
                    )
                    Column {
                        Logotype()
                        Spacer(modifier = Modifier.weight(1f))
                        SingUpForm()
                    }
                }
            }
        }
    }
}

@Composable
fun Logotype() {
    Column(
        modifier = Modifier
            .padding(start = 120.dp, end = 120.dp),
        horizontalAlignment = CenterHorizontally
    ) {
        Image(
            modifier = Modifier
                .fillMaxWidth()
                .height(140.dp),
            painter = painterResource(id = R.drawable.logo),
            contentDescription = "Logo",
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

@Composable
fun SingUpForm() {
    Column(
        modifier = Modifier
            .padding(start = 15.dp, end = 15.dp),
        verticalArrangement = Arrangement.spacedBy(20.dp),
        horizontalAlignment = CenterHorizontally,
    ) {
        Text(
            text = "Sign Up For Free",
            fontSize = 20.sp,
            fontWeight = FontWeight.Bold,
            textAlign = TextAlign.Center
        )
        Field(painterId = R.drawable.profile, "login", false)
        Field(painterId = R.drawable.message, "email", false)
        Field(painterId = R.drawable.lock, "password", true)
        Column(
            modifier = Modifier
                .fillMaxWidth(),
            verticalArrangement = Arrangement.spacedBy(20.dp)
        ) {
            CheckBox("Keep Me Signed In")
            CheckBox("Email Me About Special Pricing")
        }
        Box(
            modifier = Modifier
                .clip(RoundedCornerShape(10.dp))
                .background(Color(0xFF53E88B))
                .height(50.dp)
                .width(200.dp)
                .align(CenterHorizontally)
                .clickable {},
            contentAlignment = Center
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
                .clickable {},
            text = "already have an account?",
            fontSize = 14.sp,
            color = Color(0xFF53E88B)
        )
    }
}

@Composable
fun Field(painterId: Int, label: String, canBeHidden: Boolean) {
    var value by remember { mutableStateOf("") }
    var hiddenValue by remember { mutableStateOf("") }
    var isHidden by remember { mutableStateOf(canBeHidden) }
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
                                isHidden = !isHidden
                                value = hiddenValue.also { hiddenValue = value }
                            },
                        painter = painterResource(id = R.drawable.show),
                        contentDescription = "Show/Hide",
                    )
                }
            },
            value = value,
            onValueChange = {
                if (isHidden) {
                    if (hiddenValue.length < it.length) {
                        hiddenValue += it[it.length - 1]
                        value += "*"
                    } else {
                        hiddenValue = hiddenValue.dropLast(1)
                        value = value.dropLast(1)
                    }
                } else {
                    if (hiddenValue.length < it.length) {
                        value += it[it.length - 1]
                        hiddenValue += "*"
                    } else {
                        value = value.dropLast(1)
                        hiddenValue = hiddenValue.dropLast(1)
                    }
                }
            },
            colors = TextFieldDefaults.textFieldColors(
                backgroundColor = Color(0xFFF4F4F4)
            ),
            placeholder = { Text(label, color = Color(0x44000000)) },
        )
    }
}

@Composable
fun CheckBox(text: String) {
    var isChecked by remember { mutableStateOf(false) }
    Row(
        modifier = Modifier
            .clickable {
                isChecked = !isChecked
            },
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

@Preview(showBackground = true)
@Composable
fun DefaultPreview() {
    AndroidTheme {
        CheckBox("123")
    }
}