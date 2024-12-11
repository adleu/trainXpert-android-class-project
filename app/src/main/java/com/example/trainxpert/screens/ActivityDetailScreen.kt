package com.example.trainxpert.screens

import android.speech.tts.TextToSpeech
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowBack
import androidx.compose.material.icons.filled.DateRange
import androidx.compose.material.icons.filled.PlayArrow
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.FloatingActionButton
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.DisposableEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.trainxpert.model.ActivityItem
import com.example.trainxpert.ui.theme.ActivityCardFontSize
import com.example.trainxpert.ui.theme.BottomBarHeight
import com.example.trainxpert.ui.theme.ButtonColor
import com.example.trainxpert.ui.theme.CardTitle
import com.example.trainxpert.ui.theme.TitleCardFontSize
import java.util.Locale

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun ActivityDetailScreen(
    backAction: () -> Unit, activity: ActivityItem
) {
    val context = LocalContext.current // Obtenez le contexte local

    // Déclarez TTS comme une variable mutable à l'intérieur de remember
    var tts: TextToSpeech? by remember { mutableStateOf(null) }
    tts = TextToSpeech(context) { status ->
        if (status == TextToSpeech.SUCCESS) {
            tts?.language = Locale.getDefault() // Configurez la langue
        }
    }

    // Disposez du TTS lors de la sortie de ce composable
    DisposableEffect(Unit) {
        onDispose {
            if (tts?.isSpeaking == true) {
                tts?.stop() // Stop le TTS s'il parle
            }
            tts?.shutdown() // Ferme proprement le TTS
        }
    }

    Scaffold(topBar = {
        TopAppBar(colors = TopAppBarDefaults.topAppBarColors(
            containerColor = Color.White,
            navigationIconContentColor = Color.Black,
            titleContentColor = Color.Black
        ), title = {
            Row(
                horizontalArrangement = Arrangement.Center,
                verticalAlignment = Alignment.CenterVertically
            ) {
                Icon(
                    imageVector = Icons.Default.DateRange,
                    contentDescription = "Activity Icon",
                    modifier = Modifier.size(24.dp)
                )
                Spacer(modifier = Modifier.width(8.dp))
                Text(
                    text = "${activity.category} - ${activity.title}",
                    fontWeight = FontWeight.SemiBold,
                    fontSize = 20.sp

                )
            }
        }, navigationIcon = {
            IconButton(onClick = {
                tts?.stop() // Arrête l'audio
                backAction() // Exécute l'action de retour
            }) {
                Icon(
                    imageVector = Icons.AutoMirrored.Filled.ArrowBack,
                    contentDescription = "Retour",
                    tint = Color.Black
                )
            }
        })
    }, floatingActionButton = {
        // Bouton flottant pour lire le texte
        FloatingActionButton(
            onClick = {
                val textToRead = "Pratique: ${activity.pratique}. Conseil: ${activity.conseil}"
                if (tts?.isSpeaking == true) {
                    tts?.stop() // Arrête le TTS s'il parle déjà
                } else {
                    tts?.speak(textToRead, TextToSpeech.QUEUE_FLUSH, null, null)
                }
            },
            //containerColor = Color(0xFF6200EE),
            containerColor = ButtonColor,
            contentColor = Color.White,
            modifier = Modifier.padding(bottom = BottomBarHeight) // Ajoutez un padding en bas pour le remonter
        ) {
            Icon(
                imageVector = Icons.Default.PlayArrow, contentDescription = "Lire le texte"
            )
        }
    }, content = { innerPadding ->
        Column(
            modifier = Modifier
                .fillMaxWidth()
                .padding(innerPadding)
                .padding(horizontal = 16.dp),
            verticalArrangement = Arrangement.Top,
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            // Image de l'activité
            Image(
                painter = painterResource(id = activity.imageResId),
                contentDescription = activity.title,
                modifier = Modifier
                    .fillMaxWidth()
                    .height(200.dp)
                    .clip(RoundedCornerShape(8.dp)),
                contentScale = ContentScale.Crop
            )

            Spacer(modifier = Modifier.height(16.dp))

            // Carte combinée "Pratique" et "Conseil"
            Card(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(vertical = 8.dp),
                shape = RoundedCornerShape(8.dp),
                colors = CardDefaults.cardColors(containerColor = Color.White),
            ) {
                Column(modifier = Modifier.padding(16.dp)) {
                    // Section "Pratique"
                    Text(
                        text = "Pratique",
                        fontWeight = FontWeight.SemiBold,
                        fontSize = TitleCardFontSize,
                        color = CardTitle

                    )
                    Spacer(modifier = Modifier.height(8.dp))

                    Text(
                        text = activity.pratique,
                        fontSize = ActivityCardFontSize,
                        color = Color.Black
                    )

                    Spacer(modifier = Modifier.height(16.dp))

                    // Section "Conseil"
                    Text(
                        text = "Conseil",
                        fontWeight = FontWeight.SemiBold,
                        fontSize = TitleCardFontSize,
                        color = CardTitle

                    )
                    Spacer(modifier = Modifier.height(8.dp))
                    Text(
                        text = activity.conseil,
                        fontSize = ActivityCardFontSize,
                        color = Color.Black
                    )
                }
            }
        }
    })
}
