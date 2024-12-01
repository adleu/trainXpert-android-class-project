package com.example.trainxpert.model

import androidx.room.Entity
import androidx.room.PrimaryKey
import java.time.LocalDateTime
import java.util.Date

@Entity(tableName = "sport_sessions")
data class SportSession(
    @PrimaryKey(autoGenerate = true) val id: Int = 0, // ID unique pour chaque session
    val activityName: String, // Nom de l'activité
    val dateTime: LocalDateTime, // Date et heure de la session
    val durationInMinutes: Int, // Durée en minutes
    val distanceInKm: Double? = null, // Distance en kilomètres (optionnel)
    val caloriesBurned: Int? = null // Calories brûlées (optionnel)
)