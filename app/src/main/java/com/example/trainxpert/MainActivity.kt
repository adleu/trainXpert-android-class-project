package com.example.trainxpert

import BottomBar
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.activity.viewModels
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.CompositionLocalProvider
import androidx.compose.runtime.compositionLocalOf
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.room.Room
import com.example.trainxpert.model.SportSession
import com.example.trainxpert.ui.theme.TrainXpertTheme
import com.example.trainxpert.viewmodels.LocalSportSessionViewModel
import com.example.trainxpert.viewmodels.SportSessionViewModel
import com.example.trainxpert.viewmodels.SportSessionViewModelFactory
import data.AppDatabase
import data.SportSessionDao
import java.time.LocalDate
import java.time.LocalDateTime
import java.util.Date

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()

        val database = AppDatabase.getDatabase(this)
        val dao = database.sportSessionDao()

        val viewModel: SportSessionViewModel by viewModels {
            SportSessionViewModelFactory(dao)
        }

        viewModel.loadSessions()
//        viewModel.addSession("Test_1", LocalDate.of(2023, 12, 5) , 10, 10.0,10)
        setContent {
            //passe LocalSportSessionViewModel a tous les composant fils
            CompositionLocalProvider(LocalSportSessionViewModel provides viewModel){
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