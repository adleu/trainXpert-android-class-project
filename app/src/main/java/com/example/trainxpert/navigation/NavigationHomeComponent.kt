package com.example.trainxpert.navigation


import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.Modifier
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.navigation.navArgument
import com.example.trainxpert.screens.HistoryDetailScreen
import com.example.trainxpert.screens.HistoryScreen
import com.example.trainxpert.screens.HomeScreen
import com.example.trainxpert.viewmodels.LocalSportSessionViewModel

@Composable
fun NavigationHomeComponent(modifier: Modifier = Modifier) {
    val navController = rememberNavController()
    val viewModel = LocalSportSessionViewModel.current

    NavHost(
        navController = navController,
        startDestination = ScreensHome.HomeScreen.name
    ) {
        composable(ScreensHome.HomeScreen.name) {
            HomeScreen(
                modifier = modifier,
                onDetails = { session ->
                    navController.navigate("${ScreensHome.HistoryDetailScreen.name}/${session.id}")
                }
            )
        }

        composable(
            route = "${ScreensHome.HistoryDetailScreen.name}/{sessionId}",
            arguments = listOf(navArgument("sessionId") { type = NavType.StringType })
        ) { backStackEntry ->
            val sessionId = backStackEntry.arguments?.getString("sessionId")
            val session = viewModel.sessions.collectAsState(initial = emptyList())
                .value.firstOrNull { it.id == sessionId?.toInt() }

            if (session != null) {
                HistoryDetailScreen(backAction = {
                    if (navController.previousBackStackEntry != null) {
                        navController.popBackStack()
                    }
                },session = session, navController = navController)

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