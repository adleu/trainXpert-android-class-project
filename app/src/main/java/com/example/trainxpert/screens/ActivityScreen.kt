package com.example.trainxpert.screens

import LocalActivityViewModel
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.example.trainxpert.components.ActivitySection
import com.example.trainxpert.model.ActivityItem
//import com.example.trainxpert.viewmodels.LocalActivityViewModel

@Composable
fun ActivityScreen(
    modifier: Modifier = Modifier,
   // viewModel: ActivityViewModel,
   onNavigateToDetails: (ActivityItem) -> Unit,
    onNavigateToAdd: () -> Unit
) {
    val viewModel = LocalActivityViewModel.current
    // Charger les activités par catégorie à partir du ViewModel
    val activitiesByCategory by viewModel.activitiesByCategory.collectAsState()

    // Charger les données une seule fois lorsque le composable est affiché
    LaunchedEffect(Unit) {
        viewModel.loadActivities()
    }

    // Présentation de l'écran
    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp),
        verticalArrangement = Arrangement.Top,
        horizontalAlignment = Alignment.Start
    ) {
        // Bouton pour ajouter une activité
        Button(onClick = onNavigateToAdd, modifier = Modifier.fillMaxWidth()) {
            Text("Ajouter une activité")
        }

        Spacer(modifier = Modifier.height(16.dp))

        // Liste des catégories et des activités
        LazyColumn(
            modifier = Modifier.fillMaxSize()
        ) {
            activitiesByCategory.forEach { (category, activities) ->
                item {
                    ActivitySection(
                        title = category,
                        activities = activities,
                        onActivityClick = onNavigateToDetails // Passe la gestion du clic
                    )
                }
            }
        }

    }
}
