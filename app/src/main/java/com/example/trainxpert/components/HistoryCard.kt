package com.example.trainxpert.components

import android.annotation.SuppressLint
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.outlined.FitnessCenter
import androidx.compose.material.icons.outlined.HourglassEmpty
import androidx.compose.material.icons.outlined.LocalFireDepartment
import androidx.compose.material.icons.outlined.SportsBasketball
import androidx.compose.material.icons.outlined.SportsSoccer
import androidx.compose.material3.Card
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.trainxpert.model.SportSession
import com.example.trainxpert.ui.theme.CardTitle
import com.example.trainxpert.ui.theme.HistoryCardFontSize
import com.example.trainxpert.ui.theme.HistoryCardFontSizeDate
import com.example.trainxpert.ui.theme.HistoryCardPadding
import com.example.trainxpert.ui.theme.MainPadding
import com.example.trainxpert.ui.theme.TitleCardFontSize
import java.text.SimpleDateFormat
import java.time.Instant
import java.time.LocalDateTime
import java.time.ZoneId

@SuppressLint("SimpleDateFormat")
@Composable
fun HistoryCard(session : SportSession){
    Card(
        modifier = Modifier
            .fillMaxWidth()
            // Pas besoin de padding ici, car celui-ci est déjà défini dans la Lazy Column
            .background(Color.White, shape = RoundedCornerShape(8.dp)),
        shape = RoundedCornerShape(8.dp),
    ) {
        Column(
            modifier = Modifier
                .background(Color.White)
                // Ici OK, car c'est le padding intérieur de la Card
                .padding(MainPadding)
                .fillMaxWidth(),
            // On met un léger espacement pour aérer le composant
            verticalArrangement = Arrangement.spacedBy(8.dp)
        ) {
            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    // Espace entre le titre et le reste
                    .padding(bottom = 8.dp)
            ) {
                Text(
                    text = session.dateTime.dayOfMonth.toString().padStart(2, '0') +
                            "/${session.dateTime.monthValue}" +
                            "/${session.dateTime.year}",
                    fontSize = HistoryCardFontSizeDate,
                    fontWeight = FontWeight.Bold,
                    color = CardTitle,
                )
            }

            Row(
                // Espacement entre l'icône et le texte
                horizontalArrangement = Arrangement.spacedBy(8.dp)
            ) {
                // Utiliser une icône au lieu d'un texte rend la Card plus "user-friendly"
                Icon(
                    imageVector = Icons.Outlined.HourglassEmpty,
                    contentDescription = Icons.Outlined.HourglassEmpty.name,
                    tint = Color(0xFF000000) // Noir
                )

                Text(
                    text = session.durationInMinutes.toString() + " min",
                    fontSize = HistoryCardFontSize,
                    fontWeight = FontWeight.Normal,
                    color = Color(0xFF000000) // Noir
                )
            }

            Row(
                modifier = Modifier
                    .fillMaxWidth(),
                horizontalArrangement = Arrangement.SpaceBetween,
                verticalAlignment = Alignment.CenterVertically
            ) {
                Row(
                    horizontalArrangement = Arrangement.spacedBy(8.dp),
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    Icon(
                        imageVector = when(session.activityName.lowercase()) {
                            "basketball" -> Icons.Outlined.SportsBasketball
                            "football" -> Icons.Outlined.SportsSoccer
                            // etc..., on pourrait changer l'icône en fonction de l'activité
                            // et en mettre une par défaut
                            else -> Icons.Outlined.FitnessCenter
                        },
                        contentDescription = "sport icon",
                        tint = Color(0xFF000000)
                    )
                    Text(
                        text = session.activityName,
                        fontSize = HistoryCardFontSize,
                        fontWeight = FontWeight.Normal,
                        color = Color(0xFF000000)
                    )
                }

                Row(
                    horizontalArrangement = Arrangement.spacedBy(8.dp),
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    session.caloriesBurned?.let{
                        Icon(
                            imageVector = Icons.Outlined.LocalFireDepartment,
                            contentDescription = Icons.Outlined.LocalFireDepartment.name,
                            tint = Color(0xFF000000)
                        )
                        Text(
                            text = session.caloriesBurned.toString() + " kcal",
                            fontSize = HistoryCardFontSize,
                            fontWeight = FontWeight.Normal,
                            color = Color(0xFF000000),
                            textAlign = TextAlign.End,
                            modifier = Modifier.padding(end = 30.dp)
                        )
                }

                }
            }

        }
    }
}