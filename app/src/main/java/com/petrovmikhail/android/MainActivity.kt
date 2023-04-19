package com.petrovmikhail.android

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.*
import androidx.compose.material.*
import androidx.compose.ui.Modifier
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.petrovmikhail.android.screens.*
import com.petrovmikhail.android.ui.theme.AndroidTheme
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            val navController = rememberNavController()

            AndroidTheme {
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colors.background
                ) {
                    NavHost(navController = navController, startDestination = "signup") {
                        composable("signup") {
                            val viewModel = hiltViewModel<SignUpViewModel>()
                            SignUpScreen(signUpViewModel = viewModel, navController = navController)
                        }

                        composable("catalog") {
                            val viewModel = hiltViewModel<CatalogViewModel>()
                            CatalogScreen(catalogViewModel = viewModel, navController = navController)
                        }

                        composable("detail/{name}") { backStackEntry ->
                            DetailScreen(backStackEntry.arguments?.getString("name").orEmpty(), navController = navController)
                        }
                    }
                }
            }
        }
    }
}
