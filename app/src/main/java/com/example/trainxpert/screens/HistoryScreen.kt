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
import com.example.trainxpert.model.SportSession
import com.example.trainxpert.viewmodels.LocalSportSessionViewModel
import ufr.mim.netfloux.components.RawButton

@Composable
fun HistoryScreen(modifier: Modifier, onDetails: ((SportSession) -> Unit)) {

    val viewModel = LocalSportSessionViewModel.current
    val sessions by viewModel.sessions.collectAsState(initial = emptyList())

    Column(
        modifier = modifier
            .fillMaxWidth()
            .padding(16.dp),
        verticalArrangement = Arrangement.Top,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(bottom = 32.dp, end = 16.dp),
            horizontalArrangement = Arrangement.Center
        ) {
            Icon(
                imageVector = Icons.Filled.DateRange,
                contentDescription = Icons.Filled.DateRange.name,
                modifier = Modifier.size(48.dp),
            )


            Text(
                text = " Historique",
                style = TextStyle(
                    fontWeight = FontWeight.SemiBold,
                    fontSize = 22.sp,
                ),
                modifier = Modifier.align(Alignment.CenterVertically)

            )
        }

        LazyColumn(
            modifier = Modifier
                .fillMaxSize()
                // Pas besoin de répéter le padding horizontal,
                // il est déjà défini dans la colonne principale
                .padding(vertical = 16.dp)
        ) {
            items(sessions) { session ->
                RawButton(onClick = {
                    onDetails(session)
                }){
                    // Bien d'avoir mis la Card dans un composant à part
                    HistoryCard(session)
                }
            }
        }
    }
}