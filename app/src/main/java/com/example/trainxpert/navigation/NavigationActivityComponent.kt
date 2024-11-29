package com.example.trainxpert.navigation

import androidx.compose.runtime.Composable
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.rememberNavController

@Composable
fun NavigationComponent(){
    val navController = rememberNavController()
    //viewModel

NavHost(navController = navController, startDestination = ScreensActivity.HomeScreen.name) {
    //composable()
}

}