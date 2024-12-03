package com.example.trainxpert.navigation

import androidx.compose.animation.Crossfade
import androidx.compose.animation.core.tween
import androidx.compose.foundation.layout.padding
import androidx.compose.runtime.Composable
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.Modifier
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.navigation.navArgument
import com.example.trainxpert.model.SportSession
import com.example.trainxpert.screens.HistoryDetailScreen
import com.example.trainxpert.screens.HistoryScreen
import com.example.trainxpert.viewmodels.LocalSportSessionViewModel
import com.example.trainxpert.viewmodels.SportSessionViewModel

@Composable
fun NavigationHistoryComponent(modifier: Modifier) {
    val historyNavController = rememberNavController()
    val viewModel = LocalSportSessionViewModel.current

    NavHost(
        navController = historyNavController,
        startDestination = ScreensHistory.HistoryScreen.name
    ) {
        composable(ScreensHistory.HistoryScreen.name) {
            HistoryScreen(
                modifier = modifier,
                onDetails = { session ->
                    historyNavController.navigate("${ScreensHistory.HistoryDetailScreen.name}/${session.id}")
                }
            )
        }

        composable(
            route = "${ScreensHistory.HistoryDetailScreen.name}/{sessionId}",
            arguments = listOf(navArgument("sessionId") { type = NavType.StringType })
        ) { backStackEntry ->
            val sessionId = backStackEntry.arguments?.getString("sessionId")
            val session = viewModel.sessions.collectAsState(initial = emptyList())
                .value.firstOrNull { it.id == sessionId?.toInt() }

            if (session != null) {
                HistoryDetailScreen(backAction = {
                    if (historyNavController.previousBackStackEntry != null) {
                        historyNavController.popBackStack()
                    }
                },session = session, navController = historyNavController)
            }
        }
    }
}


//@Composable
//fun NavigationHistoryComponent(modifier : Modifier){
//    val historyNavController = rememberNavController()
//
//
//    NavHost(navController = historyNavController, startDestination = ScreensHistory.HistoryScreen.name){
//        composable(ScreensHistory.HistoryScreen.name){
//            HistoryScreen(modifier = modifier,
//                onDetails = {
////                    session = session,
//                    historyNavController.navigate(ScreensHistory.HistoryDetailScreen.name)
//                }
//            )
//        }
//
//        composable(
//            route = ScreensHistory.HistoryDetailScreen.name
//        ){
//            HistoryDetailScreen(session)
//        }
//    }
//}