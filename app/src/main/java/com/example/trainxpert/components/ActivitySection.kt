package com.example.trainxpert.components
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.trainxpert.model.ActivityItem
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.ui.graphics.Color
import com.example.trainxpert.ui.theme.CardTitle
import com.example.trainxpert.ui.theme.MainPadding
import com.example.trainxpert.ui.theme.activitiesSectionPadding

@Composable
fun ActivitySection(
    title: String,
    activities: List<ActivityItem>,
    onActivityClick: (ActivityItem) -> Unit, // Gestionnaire de clic ajouté
    modifier: Modifier = Modifier
) {
    Card(
        modifier = modifier
        .fillMaxWidth().padding(activitiesSectionPadding),
        shape = RoundedCornerShape(8.dp),
        colors = CardDefaults.cardColors(containerColor = Color.White
        )
    ) {
        Column(
            modifier = Modifier.padding(0.dp) // Padding interne
        ) {
            // Titre de la section
            Text(
                text = title,
                fontSize = 20.sp,
                color = CardTitle, // Couleur du texte
                modifier = Modifier.padding(8.dp)
            )

            // Liste horizontale des cartes
            LazyRow(
                horizontalArrangement = Arrangement.spacedBy(0.dp),
                modifier = Modifier.fillMaxWidth()
            ) {
                items(activities) { activity ->
                    ActivityCard(
                        title = activity.title,
                        subtitle = activity.subtitle,
                        imageResId = activity.imageResId,
                        onClick = { onActivityClick(activity) } // Passer l'activité cliquée
                    )
                }
            }
        }
    }
}
