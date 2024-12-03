import androidx.compose.animation.Crossfade
import androidx.compose.animation.core.tween
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.DateRange
import androidx.compose.material.icons.filled.Home
import androidx.compose.material.icons.filled.Person
import androidx.compose.material.icons.outlined.DateRange
import androidx.compose.material.icons.outlined.Home
import androidx.compose.material.icons.outlined.Person
import androidx.compose.material3.BottomAppBar
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.unit.dp
import com.example.trainxpert.navigation.NavigationHistoryComponent
import com.example.trainxpert.screens.ActivityScreen
import com.example.trainxpert.screens.HistoryScreen
import com.example.trainxpert.screens.HomeScreen
import com.example.trainxpert.ui.theme.MainGreyBackground

data class MyIcon(
    val index: Int,
    val action: () -> Unit,
    val selectedIcon: ImageVector,
    val unselectedIcon: ImageVector
)

@Composable
fun BottomBar() {
    var selectedTab by remember { mutableIntStateOf(0) }
    val icons: List<MyIcon> = listOf(
        MyIcon(0, {selectedTab = 0}, Icons.Filled.Home, Icons.Outlined.Home),
        MyIcon(1, {selectedTab = 1}, Icons.Filled.Person, Icons.Outlined.Person),
        MyIcon(2, {selectedTab = 2}, Icons.Filled.DateRange, Icons.Outlined.DateRange),
    )

    Scaffold(
//        modifier = Modifier.background(MainGreyBackground),

//        floatingActionButton =  {
//            if (selectedTab == 0) {
//                FloatingActionButton(onClick = { println("action")}) {
//                    Icon(Icons.Outlined.Add, Icons.Outlined.Add.name)
//                }
//            }
//        },
        bottomBar = {
            BottomAppBar(
                modifier = Modifier.height(100.dp),
                containerColor = Color.White
            ) {
                icons.forEach { icon ->
                    IconButton(onClick = icon.action, modifier = Modifier.weight(1f)) {
                        Column(horizontalAlignment = Alignment.CenterHorizontally) {
                            val iconn = if (selectedTab == icon.index) icon.selectedIcon else icon.unselectedIcon
                            Icon(iconn, iconn.name)
                            Text(
                                text = when (icon.index) {
                                    0 -> "Home"
                                    1 -> "Activity"
                                    2 -> "History"
                                    else -> ""
                                },
                                style = MaterialTheme.typography.bodySmall,
                                color = if (selectedTab == icon.index) Color.Blue else Color.Gray
                            )
                        }
                    }
                }
            }
        }
    ) { scaffoldPadding ->
        Crossfade(
            targetState = selectedTab,
            animationSpec = tween(500),
            label = "Crossfade selected tab",
        ) { tabIndex ->
            when(tabIndex) {
                0 -> HomeScreen(modifier = Modifier.padding(scaffoldPadding))
                1 -> ActivityScreen(modifier = Modifier.padding(scaffoldPadding))
                2 -> NavigationHistoryComponent(modifier = Modifier.padding(scaffoldPadding))
            }
        }
    }
}
