package com.example.trainxpert.screens

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import com.example.trainxpert.components.ActivitySection
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.ui.unit.dp
import com.example.trainxpert.models.ActivityItem
import com.example.trainxpert.R
@Composable
fun ActivityScreen(modifier: Modifier = Modifier) {
    Column(
        modifier = modifier
            .fillMaxSize()
            .padding(16.dp),
        verticalArrangement = Arrangement.Top,
        horizontalAlignment = Alignment.Start
    ) {
        // Bouton pour ajouter une activité
        Button(onClick = { /* TODO: Ajouter une activité */ }, modifier = Modifier.fillMaxWidth()) {
            Text("Ajouter une activité")
        }

        Spacer(modifier = Modifier.height(16.dp))

        // Sections avec des cartes défilables horizontalement
        ActivitySection(
            title = "Sport",
            activities = listOf(
                ActivityItem("Football", "Jouer au football", R.drawable.joga_image),
                ActivityItem("Tennis", "Jouer au tennis", R.drawable.joga_image),
                ActivityItem("Basketball", "Jouer au basketball", R.drawable.joga_image)
            )
        )

        ActivitySection(
            title = "Conseil",
            activities = listOf(
                ActivityItem("Santé", "Conseils santé", R.drawable.joga_image),
                ActivityItem("Nutrition", "Conseils nutrition", R.drawable.joga_image),
                ActivityItem("Fitness", "Conseils fitness", R.drawable.joga_image)
            )
        )

        ActivitySection(
            title = "Meditation",
            activities = listOf(
                ActivityItem("Relaxation", "Apprenez à vous relaxer", R.drawable.joga_image),
                ActivityItem("Focus", "Améliorez votre concentration", R.drawable.joga_image),
                ActivityItem("Bien-être", "Prenez soin de votre esprit", R.drawable.joga_image)
            )
        )
    }
}
