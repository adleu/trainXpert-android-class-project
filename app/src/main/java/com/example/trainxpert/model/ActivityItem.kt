package com.example.trainxpert.model

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "activities")
data class ActivityItem(
    @PrimaryKey(autoGenerate = true) val id: Int = 0,
    val title: String,
    val subtitle: String,
    val imageResId: Int,
    val category: String,
    val pratique: String,  
    val conseil: String
)

