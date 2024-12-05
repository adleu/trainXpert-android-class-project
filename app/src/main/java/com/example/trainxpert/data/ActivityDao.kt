package com.example.trainxpert.data

import androidx.room.*
import com.example.trainxpert.model.ActivityItem

@Dao
interface ActivityDao {

    // Récupérer toutes les activités
    @Query("SELECT * FROM activities")
    suspend fun getAllActivities(): List<ActivityItem>

    // Récupérer toutes les catégories distinctes
    @Query("SELECT DISTINCT category FROM activities")
    suspend fun getAllCategories(): List<String>

    // Récupérer les activités par catégorie
    @Query("SELECT * FROM activities WHERE category = :category")
    suspend fun getActivitiesByCategory(category: String): List<ActivityItem>

    // Insérer une seule activité
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertActivity(activity: ActivityItem)

    // Insérer plusieurs activités
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertAll(activities: List<ActivityItem>)

    // Supprimer une activité spécifique
    @Delete
    suspend fun deleteActivity(activity: ActivityItem)

    // Supprimer toutes les activités
    @Query("DELETE FROM activities")
    suspend fun deleteAllActivities()

    // Mettre à jour une activité
    @Update
    suspend fun updateActivity(activity: ActivityItem)
}
