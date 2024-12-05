package com.example.trainxpert.components

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Delete
import androidx.compose.material.icons.sharp.Delete
import androidx.compose.material3.Card
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
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
import androidx.compose.ui.text.style.LineHeightStyle
import androidx.compose.ui.text.withStyle
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import androidx.navigation.compose.rememberNavController
import com.example.trainxpert.model.SportSession
import com.example.trainxpert.navigation.NavigationHistoryComponent
import com.example.trainxpert.screens.HistoryDetailScreen
import com.example.trainxpert.ui.theme.BackgroundCalendarCurrentDay
import com.example.trainxpert.ui.theme.CardTitle
import com.example.trainxpert.ui.theme.HistoryCardFontSize
import com.example.trainxpert.ui.theme.MainPadding
import com.example.trainxpert.ui.theme.TitleCardFontSize
import com.example.trainxpert.viewmodels.LocalSportSessionViewModel
import java.time.format.TextStyle
import java.util.Locale

@Composable
fun SessionDetailsCard(navController: NavController, session: SportSession) {
    val viewModel = LocalSportSessionViewModel.current

    Card(
        modifier = Modifier
//            .background(Color.White,  shape = RoundedCornerShape(8.dp))
            .fillMaxWidth()
//            .fillMaxHeight()
//            .padding(MainPadding, MainPadding, MainPadding, bottom = 150.dp),
            .padding(MainPadding),
        shape = RoundedCornerShape(8.dp),
    ) {

        Column(
            modifier = Modifier
                .background(Color.White)
                .padding(MainPadding)
                .fillMaxWidth()
//                .fillMaxHeight()
        ) {
            Row(
                modifier = Modifier
                    .fillMaxWidth(),
                horizontalArrangement = Arrangement.SpaceBetween
            ){
                Text(
                    text = session.dateTime.dayOfMonth.toString() +
                            " ${
                                session.dateTime.month.getDisplayName(TextStyle.FULL, Locale.FRENCH)
                                    .replaceFirstChar { it.uppercase() }
                            }" +
                            " ${session.dateTime.year}",
                    fontSize = 25.sp,
                    fontWeight = FontWeight.Bold,
                    color = Color.Black,
                    modifier = Modifier.padding(top = 10.dp)
                )

                IconButton(onClick = {

                    viewModel.deleteSession(session)
                    navController.popBackStack()
//                NavigationHistoryComponent(Modifier)

                }) {
                    Icon(
                        imageVector = Icons.Sharp.Delete,
                        contentDescription = "Delete",
//                        modifier = Modifier.weight(1f)
                    )
                }

            }
            Text(
                text = session.dateTime.hour.toString() + "h" + session.dateTime.minute.toString(),
                fontSize = 20.sp,
                fontWeight = FontWeight.Bold,
                color = Color.Black,
                modifier = Modifier.padding(top = 10.dp)
            )


            Spacer(modifier = Modifier.height(50.dp))

            Text(
                text = session.activityName,
                fontSize = 30.sp,
                fontWeight = FontWeight.Bold,
                color = Color.Black,
//                modifier = Modifier.fillMaxWidth().fillMaxHeight()
            )

            Spacer(modifier = Modifier.height(10.dp))

            Text(
                text = buildAnnotatedString {
                    append("Votre session a duré ")
                    withStyle(style = SpanStyle(fontWeight = FontWeight.Bold, color = CardTitle)) {
                        append(session.durationInMinutes.toString() + " minutes")
                    }
                    append(".")
                },
                fontSize = 19.sp,
                fontWeight = FontWeight.Normal,
                color = Color.Black,
                modifier = Modifier.fillMaxWidth()
            )

            session.caloriesBurned?.let {
                Spacer(modifier = Modifier.height(10.dp))

                Text(
                    text = buildAnnotatedString {
                        append("Vous avez brûlé ")
                        withStyle(style = SpanStyle(fontWeight = FontWeight.Bold, color = CardTitle)) {
                            append(session.caloriesBurned.toString() + " Kcal")
                        }
                        append(".")
                    },
                    fontSize = 19.sp,
                    fontWeight = FontWeight.Normal,
                    color = Color.Black,
                    modifier = Modifier.fillMaxWidth()
                )
            }



            session.distanceInKm?.let {
                Spacer(modifier = Modifier.height(10.dp))

                Text(
                    text = buildAnnotatedString {
                        append("Vous avez parcouru ")
                        withStyle(style = SpanStyle(fontWeight = FontWeight.Bold, color = CardTitle)) {
                            append(session.distanceInKm.toString() + " Kcal")
                        }
                        append(".")
                    },
                    fontSize = 19.sp,
                    fontWeight = FontWeight.Normal,
                    color = Color.Black,
                    modifier = Modifier.fillMaxWidth()
                )
            }
        }


    }
}