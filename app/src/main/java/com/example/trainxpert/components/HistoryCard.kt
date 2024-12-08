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
import androidx.compose.material3.Card
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
            .padding(HistoryCardPadding)
            .background(Color.White, shape = RoundedCornerShape(8.dp)),
        shape = RoundedCornerShape(8.dp),
    ) {
        Column(
            modifier = Modifier
                .background(Color.White)
                .padding(MainPadding)
                .fillMaxWidth()
        ) {
            Row(
                Modifier.fillMaxWidth()
            ) {
                Text(
                    text = session.dateTime.dayOfMonth.toString().padStart(2, '0') +
                            "/${session.dateTime.monthValue}" +
                            "/${session.dateTime.year}",
                    fontSize = HistoryCardFontSize,
                    fontWeight = FontWeight.Bold,
                    color = CardTitle,
                )
//                Spacer(modifier = Modifier.width(8.dp))
            }
//            Spacer(modifier = Modifier.height(24.dp))

            Row(){
                Text(
                    text = "temps : ",
                    fontSize = HistoryCardFontSize,
                    fontWeight = FontWeight.Bold,
                    color = Color(0xFF000000) // Noir
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
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    Text(
                        text = "activité : ",
                        fontSize = HistoryCardFontSize,
                        fontWeight = FontWeight.Bold,
                        color = Color(0xFF000000)
                    )
                    Text(
                        text = session.activityName,
                        fontSize = HistoryCardFontSize,
                        fontWeight = FontWeight.Normal,
                        color = Color(0xFF000000)
                    )
                }

                Row(
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    session.caloriesBurned?.let{
                        Text(
                            text = "Kcal : ",
                            fontSize = HistoryCardFontSize,
                            fontWeight = FontWeight.Bold,
                            color = Color(0xFF000000),
                            textAlign = TextAlign.End,
                        )
                        Text(
                            text = session.caloriesBurned.toString(),
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