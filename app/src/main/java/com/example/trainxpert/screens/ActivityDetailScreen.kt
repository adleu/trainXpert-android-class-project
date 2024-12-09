package com.example.trainxpert.screens

import android.speech.tts.TextToSpeech
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material.icons.filled.DateRange
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.trainxpert.model.ActivityItem
import com.example.trainxpert.ui.theme.CardTitle
import java.util.Locale

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun ActivityDetailScreen(
    backAction: () -> Unit,
    activity: ActivityItem
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
            tts!!.stop()
            tts!!.shutdown()
        }
    }

    Scaffold(
        topBar = {
            TopAppBar(
                colors = TopAppBarDefaults.topAppBarColors(
                    containerColor = Color.White,
                    navigationIconContentColor = Color.Black,
                    titleContentColor = Color.Black
                ),
                title = {
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
                            style = TextStyle(
                                fontWeight = FontWeight.SemiBold,
                                fontSize = 20.sp
                            )
                        )
                    }
                },
                navigationIcon = {
                    IconButton(onClick = backAction) {
                        Icon(
                            imageVector = Icons.Default.ArrowBack,
                            contentDescription = "Retour",
                            tint = Color.Black
                        )
                    }
                }
            )
        },
        content = { innerPadding ->
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
                    elevation = CardDefaults.cardElevation(defaultElevation = 4.dp)
                ) {
                    Column(modifier = Modifier.padding(16.dp)) {
                        // Section "Pratique"
                        Text(
                            text = "Pratique",
                            style = TextStyle(
                                fontWeight = FontWeight.SemiBold,
                                fontSize = 18.sp,
                                color = CardTitle
                            )
                        )
                        Spacer(modifier = Modifier.height(8.dp))
                        Text(
                            text = activity.pratique,
                            style = TextStyle(fontSize = 14.sp, color = Color.Gray)
                        )

                        Spacer(modifier = Modifier.height(16.dp))

                        // Section "Conseil"
                        Text(
                            text = "Conseil",
                            style = TextStyle(
                                fontWeight = FontWeight.SemiBold,
                                fontSize = 18.sp,
                                color = CardTitle
                            )
                        )
                        Spacer(modifier = Modifier.height(8.dp))
                        Text(
                            text = activity.conseil,
                            style = TextStyle(fontSize = 14.sp, color = Color.Gray)
                        )

                        // Bouton pour lire le texte si la catégorie est "Conseil"
                        if (activity.category == "Conseil") {
                            Spacer(modifier = Modifier.height(16.dp))
                            Button(onClick = {
                                val textToRead = "Pratique: ${activity.pratique}. Conseil: ${activity.conseil}"
                                tts!!.speak(textToRead, TextToSpeech.QUEUE_FLUSH, null, null)
                            }) {
                                Text(text = "Lire le texte")
                            }
                        }
                    }
                }
            }
        }
    )
}
