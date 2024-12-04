package com.example.trainxpert.screens

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.DateRange
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.trainxpert.components.HistoryCard
import com.example.trainxpert.model.ActivityItem
import com.example.trainxpert.model.SportSession
import com.example.trainxpert.viewmodels.LocalSportSessionViewModel
import ufr.mim.netfloux.components.RawButton

@Composable
fun ActivityDetailScreen(modifier: Modifier = Modifier, activity: ActivityItem , backAction: () -> Unit) {

  //  val viewModel = LocalSportSessionViewModel.current
   // val sessions by viewModel.sessions.collectAsState(initial = emptyList())

    Column(
        modifier = modifier
            .fillMaxWidth()
            .padding(16.dp),
        verticalArrangement = Arrangement.Top,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        // Texte de bienvenue
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(bottom = 32.dp, end = 16.dp),
            horizontalArrangement = Arrangement.Center
        ) {
//            Icons.Filled.DateRange



            Text(
                text = " Details de l'activité",
                style = TextStyle(
                    fontWeight = FontWeight.SemiBold,
                    fontSize = 22.sp,
                ),
                modifier = Modifier.align(Alignment.CenterVertically)

            )
        }


    }
}

