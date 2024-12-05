package com.example.trainxpert.screens

import SportCalendarScreen
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.trainxpert.R
import com.example.trainxpert.components.DailyTipCard
import com.example.trainxpert.components.StatCard
import com.example.trainxpert.model.SportSession
import ufr.mim.netfloux.components.RawButton


@Composable
fun HomeScreen(modifier: Modifier,onDetails: ((SportSession) -> Unit)){

    Column(
        modifier = modifier
            .fillMaxWidth()
            .padding(16.dp),
        verticalArrangement = Arrangement.Top,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        // Texte de bienvenue
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(bottom = 32.dp, end = 16.dp),
            horizontalArrangement = Arrangement.Center
        ){
            Image(
                painter = painterResource(id = R.drawable.icon_xpert),
                contentDescription = "",
                Modifier.size(108.dp)
            )

            Text(
                text = "Bienvenue sur TrainXpert",
                style = TextStyle(
                    fontWeight = FontWeight.SemiBold,
                    fontSize = 22.sp,
                ),
                modifier = Modifier.align(Alignment.CenterVertically)

            )
        }

        var showTips by remember { mutableStateOf(true) }
        RawButton(onClick = {showTips = !showTips }) {

            when(showTips){
                true -> DailyTipCard()
                false -> StatCard()
            }
        }

        SportCalendarScreen(onDetails)
    }

}