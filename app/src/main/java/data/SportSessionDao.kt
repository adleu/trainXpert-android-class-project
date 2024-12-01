package data

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
}