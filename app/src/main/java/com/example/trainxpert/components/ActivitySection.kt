package com.example.trainxpert.components
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.trainxpert.models.ActivityItem
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.ui.graphics.Color

@Composable
fun ActivitySection(title: String, activities: List<ActivityItem>, modifier: Modifier = Modifier) {
    Card(
        modifier = modifier
            .fillMaxWidth()
            .padding(8.dp), // Padding autour de la section
        shape = RoundedCornerShape(4.dp), // Coins arrondis
        colors = CardDefaults.cardColors(
            containerColor = Color.White // Fond blanc
        ),
        elevation = CardDefaults.cardElevation(
            defaultElevation = 4.dp // Optionnel : ajouter une ombre
        )
    ) {
        Column(
            modifier = Modifier.padding(8.dp) // Padding interne
        ) {
            // Titre de la section
            Text(
                text = title,
                fontSize = 20.sp,
                color = Color.Black, // Couleur du texte
                modifier = Modifier.padding(bottom = 8.dp)
            )

            // Liste horizontale des cartes
            LazyRow(
                horizontalArrangement = Arrangement.spacedBy(8.dp),
                modifier = Modifier.fillMaxWidth()
            ) {
                items(activities) { activity ->
                    ActivityCard(
                        title = activity.title,
                        subtitle = activity.subtitle,
                        imageResId = activity.imageResId
                    )
                }
            }
        }
    }
}
