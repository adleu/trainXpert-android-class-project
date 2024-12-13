package com.example.trainxpert

//mport com.example.trainxpert.viewmodels.ActivityViewModel
//import com.example.trainxpert.viewmodels.ActivityViewModelFactory
import ActivityViewModel
import ActivityViewModelFactory
import BottomBar
import LocalActivityViewModel
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.activity.viewModels
import androidx.compose.runtime.CompositionLocalProvider
import androidx.lifecycle.lifecycleScope
import com.example.trainxpert.data.AppDatabase
import com.example.trainxpert.data.InitializeData
import com.example.trainxpert.model.ActivityItem
import com.example.trainxpert.ui.theme.TrainXpertTheme
import com.example.trainxpert.viewmodels.LocalSportSessionViewModel
import com.example.trainxpert.viewmodels.SportSessionViewModel
import com.example.trainxpert.viewmodels.SportSessionViewModelFactory
import kotlinx.coroutines.launch
import java.time.LocalDateTime

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()

        val database = AppDatabase.getDatabase(this)
        val sessionDao = database.sportSessionDao()
        val activityDao = database.activityDao()

        val viewModel: SportSessionViewModel by viewModels {
            SportSessionViewModelFactory(sessionDao)
        }
        val activityViewModel: ActivityViewModel by viewModels {
            ActivityViewModelFactory(activityDao)
        }



        lifecycleScope.launch {
            viewModel.loadSessions()
//            viewModel.deleteAllSessions()
//            viewModel.addSession("golf", LocalDateTime.of(2024, 11, 10, 10, 10, 10), 10, 10.0, 10)
//            viewModel.addSession("volley", LocalDateTime.of(2024, 11, 25, 10, 10, 10), 10, 10.0, 10)
//            viewModel.addSession("amongus", LocalDateTime.of(2024, 12, 1, 10, 10, 10), 10, 10.0, 10)
//            viewModel.loadSessions()
            activityViewModel.loadActivities()
            activityViewModel.deleteAllActivities()
            activityViewModel.loadActivities()
            InitializeData(activityViewModel)
            activityViewModel.loadActivities()
        }



        setContent {
            //passe LocalSportSessionViewModel a tous les composant fils
            CompositionLocalProvider(
                LocalSportSessionViewModel provides viewModel,
                LocalActivityViewModel provides activityViewModel
            ) {
                TrainXpertTheme {
                    BottomBar()
                }
            }

        }
    }
}