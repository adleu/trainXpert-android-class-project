package com.example.trainxpert.screens

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowBack
import androidx.compose.material.icons.automirrored.filled.KeyboardArrowLeft
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material.icons.filled.DateRange
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarColors
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import com.example.trainxpert.components.SessionDetailsCard
import com.example.trainxpert.model.SportSession
import com.example.trainxpert.ui.theme.MainGreyBackground
import com.example.trainxpert.ui.theme.MainPadding
import com.example.trainxpert.viewmodels.LocalSportSessionViewModel

@Composable
fun HistoryDetailScreen(
    backAction: () -> Unit,
    session: SportSession?,
    navController: NavController
) {
    // Pas besoin de tout remettre dans un Scaffold, car il y en a déjà un à la racine de l'app (BottomBar)
    // Si vous voulez avoir un bouton retour, faites un composant à part
    // Ça évite d'avoir à tout remettre dans un Scaffold
    Column(
        modifier = Modifier
            .fillMaxWidth()
            .padding(MainPadding)
            .background(MainGreyBackground),
        verticalArrangement = Arrangement.Top,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        // Back button
        Row(
            modifier = Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.spacedBy(16.dp, alignment = Alignment.Start)
        ) {
            IconButton(onClick = backAction) {
                Icon(
                    imageVector = Icons.AutoMirrored.Filled.KeyboardArrowLeft,
                    contentDescription = Icons.Default.ArrowBack.name,
                    tint = Color.Black
                )
            }

            Text(
                text = "Ma session",
                style = TextStyle(
                    fontWeight = FontWeight.SemiBold,
                    fontSize = 22.sp,
                ),
                modifier = Modifier.align(Alignment.CenterVertically)
            )
        }
        session?.let {
            SessionDetailsCard(session = session, navController = navController)
        }
    }
}

