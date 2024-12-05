import androidx.compose.runtime.compositionLocalOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.viewModelScope
import com.example.trainxpert.model.ActivityItem
import com.example.trainxpert.data.ActivityDao
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.launch

val LocalActivityViewModel = compositionLocalOf<ActivityViewModel> { error("No ActivityViewModel provided") }

class ActivityViewModel(private val dao: ActivityDao) : ViewModel() {

    // Flux d'état pour toutes les activités
    val activities = MutableStateFlow<List<ActivityItem>>(emptyList())

    // Flux d'état pour les activités organisées par catégorie
    val activitiesByCategory = MutableStateFlow<Map<String, List<ActivityItem>>>(emptyMap())

    // Charger toutes les activités
    fun loadActivities() {
        viewModelScope.launch(Dispatchers.IO) {
            activities.value = dao.getAllActivities()
            organizeActivitiesByCategory() // Mettre à jour les activités par catégorie
        }
    }

    // Ajouter une activité
    fun addActivity(activity: ActivityItem) {
        viewModelScope.launch(Dispatchers.IO) {
            dao.insertActivity(activity)
            loadActivities() // Recharger après insertion
        }
    }

    // Supprimer une activité
    fun deleteActivity(activity: ActivityItem) {
        viewModelScope.launch(Dispatchers.IO) {
            dao.deleteActivity(activity)
            loadActivities() // Recharger après suppression
        }
    }

    // Mettre à jour une activité
    fun updateActivity(activity: ActivityItem) {
        viewModelScope.launch(Dispatchers.IO) {
            dao.updateActivity(activity)
            loadActivities() // Recharger après mise à jour
        }
    }

    // Supprimer toutes les activités
    fun deleteAllActivities() {
        viewModelScope.launch(Dispatchers.IO) {
            dao.deleteAllActivities()
            loadActivities() // Recharger après suppression
        }
    }

    // Charger les activités par catégorie
    private suspend fun organizeActivitiesByCategory() {
        val categories = dao.getAllCategories()
        val activitiesMap = categories.associateWith { category ->
            dao.getActivitiesByCategory(category)
        }
        activitiesByCategory.value = activitiesMap
    }

    // Charger les activités d'une catégorie spécifique
    fun loadActivitiesByCategory(category: String): List<ActivityItem> {
        return activitiesByCategory.value[category] ?: emptyList()
    }
}

class ActivityViewModelFactory(private val dao: ActivityDao) : ViewModelProvider.Factory {
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        if (modelClass.isAssignableFrom(ActivityViewModel::class.java)) {
            @Suppress("UNCHECKED_CAST")
            return ActivityViewModel(dao) as T
        }
        throw IllegalArgumentException("Unknown ViewModel class")
    }
}
