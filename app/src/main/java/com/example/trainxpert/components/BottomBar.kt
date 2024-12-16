package com.example.trainxpert.components

import androidx.compose.animation.Crossfade
import androidx.compose.animation.core.tween
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
import com.example.trainxpert.navigation.NavigationActivityComponent
import com.example.trainxpert.navigation.NavigationHistoryComponent
import com.example.trainxpert.navigation.NavigationHomeComponent
import com.example.trainxpert.ui.theme.BottomBarHeight

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
        bottomBar = {
            BottomAppBar(
                modifier = Modifier.height(BottomBarHeight),
                containerColor = Color.White
            ) {
                icons.forEach { icon ->
                    IconButton(onClick = icon.action, modifier = Modifier.weight(1f)) {
                        Column(horizontalAlignment = Alignment.CenterHorizontally) {
                            val iconn = if (selectedTab == icon.index) icon.selectedIcon else icon.unselectedIcon
                            // Idée : donner la même couleur au texte et à l'icône quand la tab est sélectionnée
                            // Color.Black ou une autre couleur de votre thème est préférable à Color.Blue, qui est trop tape à l'oeil
                            val tabColor = if (selectedTab == icon.index) Color.Black else Color.Gray
                            Icon(iconn, iconn.name, tint = tabColor)
                            Text(
                                text = when (icon.index) {
                                    0 -> "Accueil"
                                    1 -> "Activité"
                                    2 -> "Historique"
                                    else -> ""
                                },
                                style = MaterialTheme.typography.bodySmall,
                                color = tabColor
                            )
                        }
                    }
                }
            }
        }
    ) { scaffoldPadding ->
        Crossfade(
            targetState = selectedTab,
            animationSpec = tween(0),
            label = "Crossfade selected tab",
            // Il vaut mieux utiliser le Scaffold padding directement sur le Crossfade
            // pour éviter d'avoir à le réécrire sur tous les autres composants
            modifier = Modifier.padding(scaffoldPadding)
        ) { tabIndex ->
            when(tabIndex) {
                0 -> NavigationHomeComponent()
                1 -> NavigationActivityComponent()
                2 -> NavigationHistoryComponent()
            }
        }
    }
}
