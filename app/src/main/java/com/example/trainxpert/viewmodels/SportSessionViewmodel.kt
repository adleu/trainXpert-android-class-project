package com.example.trainxpert.viewmodels

import androidx.compose.runtime.compositionLocalOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.viewModelScope
import com.example.trainxpert.model.SportSession
import com.example.trainxpert.data.SportSessionDao
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.launch
import java.time.LocalDate
import java.time.LocalDateTime
import java.time.ZoneId
import java.util.Date

val LocalSportSessionViewModel = compositionLocalOf<SportSessionViewModel> { error("No SportSessionViewModel provided") }

class SportSessionViewModel(private val dao: SportSessionDao) : ViewModel() {
    val sessions = MutableStateFlow<List<SportSession>>(emptyList())

    fun loadSessions() {
        viewModelScope.launch {
            sessions.value = dao.getAllSessions()
        }
    }

    fun addSession(session: SportSession) {
        viewModelScope.launch {
            dao.insertSession(session)
            loadSessions() // Recharger les sessions après insertion
        }
    }

    fun deleteSession(session: SportSession) {
        viewModelScope.launch {
            dao.deleteSession(session)
            loadSessions() // Recharger après suppression
        }
    }

    fun addSession(
        activityName: String,
        date: LocalDateTime,
        durationInMinutes: Int,
        distanceInKm: Double?,
        caloriesBurned: Int?
    ) {
        viewModelScope.launch {
            val newSportSession = SportSession(
                activityName = activityName,
                dateTime = date,
                durationInMinutes = durationInMinutes,
                distanceInKm = distanceInKm,
                caloriesBurned = caloriesBurned
            )
            dao.insertSession(newSportSession)
            loadSessions()
        }
    }

    suspend fun isDatePresent(date: LocalDate): Boolean {
        // Convertir LocalDate en Date
        val dateStart = date.atStartOfDay(ZoneId.systemDefault()).toInstant().toEpochMilli()
        val dateEnd = date.plusDays(1).atStartOfDay(ZoneId.systemDefault()).toInstant().toEpochMilli()

        // Vérifier si des sessions existent pour cette date
        return dao.getSessionsByDate(dateStart, dateEnd).isNotEmpty()
    }

    fun deleteAllSessions() {
        viewModelScope.launch(Dispatchers.IO) {
            dao.deleteAllSessions()
        }
    }
}

class SportSessionViewModelFactory(private val dao: SportSessionDao) : ViewModelProvider.Factory {
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        if (modelClass.isAssignableFrom(SportSessionViewModel::class.java)) {
            @Suppress("UNCHECKED_CAST")
            return SportSessionViewModel(dao) as T
        }
        throw IllegalArgumentException("Unknown ViewModel class")
    }
}