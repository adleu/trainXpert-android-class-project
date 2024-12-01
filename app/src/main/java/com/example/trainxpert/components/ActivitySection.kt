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
@Composable
fun ActivitySection(title: String, activities: List<ActivityItem>, modifier: Modifier = Modifier) {
    Column(modifier = modifier.padding(16.dp)) {
        // Titre de la section
        Text(
            text = title,
            fontSize = 20.sp,
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

