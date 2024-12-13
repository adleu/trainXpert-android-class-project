package com.example.trainxpert.components

import androidx.compose.foundation.background
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
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.SpanStyle
import androidx.compose.ui.text.buildAnnotatedString
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.withStyle
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.trainxpert.ui.theme.CardTitle
import com.example.trainxpert.ui.theme.MainPadding
import com.example.trainxpert.ui.theme.TitleCardFontSize
import com.example.trainxpert.viewmodels.LocalSportSessionViewModel

@Composable
fun StatCard(){
    val viewModel = LocalSportSessionViewModel.current
    val sessions by viewModel.sessions.collectAsState(initial = emptyList())
    var totalDuration : Int = 0
    var totalCalories : Int = 0
    var totalSession : Int = 0

    for(session in sessions){
        session.caloriesBurned?.let { totalCalories += it }
        session.durationInMinutes.let { totalDuration += it }
        totalSession++
    }


    Card(
        modifier = Modifier
            .fillMaxWidth()
            .padding(MainPadding),
        shape = RoundedCornerShape(8.dp),
    ) {
        Column(
            modifier = Modifier
                .background(Color.White)
                .padding(MainPadding)
        ) {
            Row(verticalAlignment = Alignment.CenterVertically) {
                Text(
                    text = "Vos statistiques totales",
                    fontSize = TitleCardFontSize,
                    fontWeight = FontWeight.Bold,
                    color = CardTitle
                )
                Spacer(modifier = Modifier.width(8.dp))
            }
            Spacer(modifier = Modifier.height(24.dp))


            Text(
                text = buildAnnotatedString {
                    withStyle(style = SpanStyle(fontWeight = FontWeight.Bold, color = Color.Black)) {
                        append(totalSession.toString())
                    }
                        append(" sessions    ")

                    withStyle(style = SpanStyle(fontWeight = FontWeight.Bold, color = Color.Black)) {
                        append(totalCalories.toString())
                    }
                        append(" Kcal   ")


                    withStyle(style = SpanStyle(fontWeight = FontWeight.Bold, color = Color.Black)) {
                        append(totalDuration.toString())
                    }
                        append(" min   ")

                },
                fontSize = 22.sp,
                fontWeight = FontWeight.Normal,
                color = Color.Black,
                modifier = Modifier.fillMaxWidth()
            )
        }
    }
}