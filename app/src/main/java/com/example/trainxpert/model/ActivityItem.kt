package com.example.trainxpert.model

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "activities")
data class ActivityItem(
    @PrimaryKey(autoGenerate = true) val id: Int = 0, // ID unique pour chaque activité
    val title: String, // Titre de l'activité
    val subtitle: String, // Sous-titre ou description de l'activité
    val imageResId: Int, // Référence à l'image de l'activité
    val category: String // Catégorie de l'activité (ex. Sport, Conseil, Meditation)
)
