package com.example.trainxpert.data

import androidx.room.*
import com.example.trainxpert.model.SportSession

@Dao
interface SportSessionDao {
    @Insert
    suspend fun insertSession(session: SportSession)

    @Query("SELECT * FROM sport_sessions ORDER BY dateTime DESC")
    suspend fun getAllSessions(): List<SportSession>

    @Delete
    suspend fun deleteSession(session: SportSession)

    @Query("SELECT * FROM sport_sessions WHERE dateTime BETWEEN :dateStart AND :dateEnd")
    suspend fun getSessionsByDate(dateStart: Long, dateEnd: Long): List<SportSession>

    @Query("DELETE FROM sport_sessions")
    suspend fun deleteAllSessions()
}