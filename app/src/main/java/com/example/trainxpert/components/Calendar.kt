import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.BasicText
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material.icons.filled.ArrowForward
import androidx.compose.material.icons.outlined.ArrowForward
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.trainxpert.ui.theme.BackgroundCalendarCurrentDay
import com.example.trainxpert.ui.theme.BackgroundCalendarDateSport
import com.example.trainxpert.ui.theme.CardTitle
import com.example.trainxpert.ui.theme.MainPadding
import com.example.trainxpert.ui.theme.TitleCardFontSize
import java.io.InputStreamReader
import java.time.LocalDate
import java.time.format.DateTimeFormatter

// Définition du modèle de données pour les sessions de sport
data class SportSessions(val sessions: List<String>)


@Composable
fun SportCalendarScreen() {
//    val context = LocalContext.current

    // Charger le JSON des sessions sportives
//    val sportSessions = remember {
//        loadSportSessionsFromJson(context)
//    }

    var currentDate by remember { mutableStateOf(LocalDate.now()) }
    Card(
        modifier = Modifier
            .fillMaxWidth()
            .padding(MainPadding)
            .background(Color.White, shape = RoundedCornerShape(8.dp)),
        shape = RoundedCornerShape(8.dp),
    ) {
        Column(
            modifier = Modifier
                .padding(0.dp)
                .background(Color.White),
        ) {
            Text(
                text = "Date des dernières sessions",
                fontSize = TitleCardFontSize,
                fontWeight = FontWeight.Bold,
                color = CardTitle,
                modifier = Modifier
                    .padding(MainPadding)
                    .align(Alignment.Start)
            )

            Spacer(modifier = Modifier.height(16.dp))

            Row(
                modifier = Modifier
                    .align(Alignment.CenterHorizontally),
                horizontalArrangement = Arrangement.Center,
                verticalAlignment = Alignment.CenterVertically
            ){
                IconButton(onClick = { currentDate = currentDate.minusMonths(1) }) {
                    Icon(Icons.Filled.ArrowBack, contentDescription = Icons.Filled.ArrowBack.name)
                }

                Text(
                    text = currentDate.month.toString() + " " + currentDate.year.toString(),
                    color = Color.Gray,
                    fontSize = 15.sp,
                )

                IconButton(onClick = { currentDate = currentDate.plusMonths(1) }) {
                    Icon(Icons.Filled.ArrowForward, contentDescription = Icons.Filled.ArrowForward.name)
                }
            }

            Spacer(modifier = Modifier.height(16.dp))


            CalendarView(currentDate)
        }
    }
}

@Composable
fun CalendarView(currentDate: LocalDate) {
    val daysInMonth = currentDate.lengthOfMonth()

    // Afficher les jours du mois
    Column(
        modifier = Modifier
            .background(Color.White)
            .fillMaxWidth()
    ) {
        for (week in 0 until daysInMonth / 7 + 1) {
            Row {
                for (dayOfWeek in 0 until 7) {
                    val day = (week * 7) + dayOfWeek + 1
                    if (day <= daysInMonth) {
//                        val isSportDay = sportSessions.sessions.contains(currentDate.withDayOfMonth(day).toString())
                          val isSportDay = (day-5)%3 != 0
                        CalendarDay(day, isSportDay)
                    }
                }
            }
        }
    }
}

@Composable
fun CalendarDay(day: Int, isSportDay: Boolean) {
    var color = if (isSportDay) BackgroundCalendarDateSport else Color.White
    if (day == LocalDate.now().dayOfMonth) color = BackgroundCalendarCurrentDay
    val texColor = if (isSportDay) Color.White else Color.Black
    Box(
        modifier = Modifier
            .padding(4.dp)
            .size(40.dp)
            .background(color, shape = CircleShape)
            .wrapContentSize(Alignment.Center)
            .clip(CircleShape)
        ,
    ) {
        Text(text = day.toString(), color = texColor)
    }
}

// Charger les sessions sportives depuis le fichier JSON
//fun loadSportSessionsFromJson(context: Context): SportSessions {
//    val inputStream = context.assets.open("sport_sessions.json")
//    val reader = InputStreamReader(inputStream)
//    val sportSessionsType = object : TypeToken<SportSessions>() {}.type
//    return Gson().fromJson(reader, sportSessionsType)
//}

@Preview(showBackground = false)
@Composable
fun DefaultPreview() {
    SportCalendarScreen()
}
