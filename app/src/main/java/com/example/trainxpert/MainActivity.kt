package com.example.trainxpert

import ActivityViewModel
import ActivityViewModelFactory
import BottomBar
import LocalActivityViewModel
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.activity.result.launch
import androidx.activity.viewModels
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.CompositionLocalProvider
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.lifecycleScope
import com.example.trainxpert.ui.theme.TrainXpertTheme
import com.example.trainxpert.viewmodels.LocalSportSessionViewModel
import com.example.trainxpert.viewmodels.SportSessionViewModel
//mport com.example.trainxpert.viewmodels.ActivityViewModel
//import com.example.trainxpert.viewmodels.ActivityViewModelFactory
import com.example.trainxpert.viewmodels.SportSessionViewModelFactory
import com.example.trainxpert.data.AppDatabase
import com.example.trainxpert.data.SportSessionDao
import kotlinx.coroutines.launch
import java.time.LocalDateTime
import java.util.Date

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()

        val database = AppDatabase.getDatabase(this)
        val dao = database.sportSessionDao()

        val activityDao = database.activityDao()




        val viewModel: SportSessionViewModel by viewModels {
            SportSessionViewModelFactory(dao)
        }
        val activityViewModel :ActivityViewModel   by viewModels {
            ActivityViewModelFactory(activityDao)
        }


        viewModel.deleteAllSessions()
        viewModel.addSession("golf", LocalDateTime.of(2024, 11,10,10,10,10) , 10, 10.0,10)
        viewModel.addSession("volley", LocalDateTime.of(2024, 11,25,10,10,10) , 10, 10.0,10)
        viewModel.addSession("amongus", LocalDateTime.of(2024, 12,1,10,10,10) , 10, 10.0,10)
        viewModel.loadSessions()
        lifecycleScope.launch {
            viewModel.deleteAllSessions()
            viewModel.addSession("golf", LocalDateTime.of(2024, 11,10,10,10,10) , 10, 10.0,10)
            viewModel.addSession("volley", LocalDateTime.of(2024, 11,25,10,10,10) , 10, 10.0,10)
            viewModel.addSession("amongus", LocalDateTime.of(2024, 12,1,10,10,10) , 10, 10.0,10)
            viewModel.loadSessions()



            activityViewModel.addActivity(
                com.example.trainxpert.model.ActivityItem(
                    title = "Football",
                    subtitle = "Jouer au football",
                    imageResId = R.drawable.joga,
                    category = "Sport"
                )
            )
            activityViewModel.addActivity(
                com.example.trainxpert.model.ActivityItem(
                    title = "Santé",
                    subtitle = "Conseils santé",
                    imageResId = R.drawable.joga,
                    category = "Conseil"
                )
            )
            activityViewModel.loadActivities()



        }




        setContent {
            //passe LocalSportSessionViewModel a tous les composant fils
            CompositionLocalProvider(LocalSportSessionViewModel provides viewModel ,  LocalActivityViewModel provides activityViewModel){
                TrainXpertTheme {
                    BottomBar()
                }
            }

        }
    }
}




@Composable
fun Greeting(name: String, modifier: Modifier = Modifier) {
    Text(
        text = "Hello $name!",
        modifier = modifier
    )
}

@Preview(showBackground = true)
@Composable
fun GreetingPreview() {
    TrainXpertTheme {
        Greeting("Android")
    }
}