
package com.example.trainxpert.navigation

import LocalActivityViewModel
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.Modifier
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.navigation.navArgument
import com.example.trainxpert.navigation.ScreensActivity.AddActivityScreen
import com.example.trainxpert.screens.ActivityDetailScreen
import com.example.trainxpert.screens.ActivityScreen
import com.example.trainxpert.screens.AddActivityScreen


@Composable
fun NavigationActivityComponent(modifier: Modifier = Modifier) {
    val navController = rememberNavController()
    val viewModel = LocalActivityViewModel.current

    NavHost(
        navController = navController,
        startDestination = ScreensActivity.ActivityScreen.name
    ) {
        // Écran principal : ActivityScreen
        composable(ScreensActivity.ActivityScreen.name) {
            ActivityScreen(
                modifier = modifier,
                onNavigateToAdd = {
                    navController.navigate(ScreensActivity.AddActivityScreen.name)
                },
                onNavigateToDetails = { activity ->
                    navController.navigate("${ScreensActivity.ActivityDetailScreen.name}/${activity.id}")
                }
            )
        }

        // Écran pour ajouter une activité
        composable(ScreensActivity.AddActivityScreen.name) {
            AddActivityScreen(
                backAction = {
                    if (navController.previousBackStackEntry != null) {
                        navController.popBackStack()
                    }
                }
            )
        }

        // Écran de détails d'une activité
        composable(
            route = "${ScreensActivity.ActivityDetailScreen.name}/{activityId}",
            arguments = listOf(navArgument("activityId") { type = NavType.StringType })
        ) { backStackEntry ->
            val activityId = backStackEntry.arguments?.getString("activityId")
            val activity = viewModel.activities.collectAsState(initial = emptyList())
                .value.firstOrNull { it.id == activityId?.toInt() }

            if (activity != null) {
                ActivityDetailScreen(
                    activity = activity,
                    backAction = {
                        if (navController.previousBackStackEntry != null) {
                            navController.popBackStack()
                        }
                    }
                )
            }
        }
    }
}
