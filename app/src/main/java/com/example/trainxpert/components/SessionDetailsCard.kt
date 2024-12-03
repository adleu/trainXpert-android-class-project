package com.example.trainxpert.components

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Delete
import androidx.compose.material3.Card
import androidx.compose.material3.IconButton
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.trainxpert.model.SportSession
import com.example.trainxpert.navigation.NavigationHistoryComponent
import com.example.trainxpert.screens.HistoryDetailScreen
import com.example.trainxpert.ui.theme.BackgroundCalendarCurrentDay
import com.example.trainxpert.ui.theme.CardTitle
import com.example.trainxpert.ui.theme.HistoryCardFontSize
import com.example.trainxpert.ui.theme.MainPadding
import com.example.trainxpert.viewmodels.LocalSportSessionViewModel
import java.time.format.TextStyle
import java.util.Locale

@Composable
fun SessionDetailsCard(session : SportSession){
    val viewModel = LocalSportSessionViewModel.current

    Card(
        modifier = Modifier
//            .background(Color.White,  shape = RoundedCornerShape(8.dp))
            .fillMaxWidth()
            .fillMaxHeight()
            .padding(MainPadding, MainPadding, MainPadding, bottom = 150.dp),
        shape = RoundedCornerShape(8.dp),
    ) {

        Column(
            modifier = Modifier
                .background(Color.White)
                .padding(MainPadding)
        ){
            Text(
                text = session.dateTime.dayOfMonth.toString() +
                        " ${session.dateTime.month.getDisplayName(TextStyle.FULL, Locale.FRENCH)
                            .replaceFirstChar { it.uppercase() }}" +
                        " ${session.dateTime.year}",
                fontSize = 25.sp,
                fontWeight = FontWeight.Bold,
                color = CardTitle,
//                modifier = Modifier.fillMaxWidth().fillMaxHeight()
            )

            Spacer(modifier = Modifier.height(50.dp))

            Text(
                text = session.activityName,
                fontSize = 30.sp,
                fontWeight = FontWeight.Bold,
                color = Color.Black,
//                modifier = Modifier.fillMaxWidth().fillMaxHeight()
            )

            Spacer(modifier = Modifier.height(20.dp))

            Text(
                text = session.durationInMinutes.toString() + " minutes",
                fontSize = 20.sp,
                fontWeight = FontWeight.Normal,
                color = Color.Black,
//                modifier = Modifier.fillMaxWidth().fillMaxHeight()
            )

            session.caloriesBurned?.let{
                Spacer(modifier = Modifier.height(20.dp))

                Text(
                    text = session.caloriesBurned.toString() + " Kcal",
                    fontSize = 20.sp,
                    fontWeight = FontWeight.Normal,
                    color = Color.Black,
                )
            }


            Spacer(modifier = Modifier.height(20.dp))

            session.distanceInKm?.let{
                Text(
                    text = session.distanceInKm.toString() + " km",
                    fontSize = 20.sp,
                    fontWeight = FontWeight.Normal,
                    color = Color.Black,
                    modifier = Modifier.fillMaxWidth().fillMaxHeight()
                )
            }

            IconButton(onClick = {
                viewModel.deleteSession(session)
//                NavigationHistoryComponent(Modifier)
            }) {
                Icons.Filled.Delete
            }
        }


    }
}