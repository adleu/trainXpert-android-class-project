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
import androidx.compose.material.icons.filled.KeyboardArrowLeft
import androidx.compose.material.icons.filled.KeyboardArrowRight
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
import com.example.trainxpert.ui.theme.CalendarArrowTint
import com.example.trainxpert.ui.theme.CardTitle
import com.example.trainxpert.ui.theme.MainPadding
import com.example.trainxpert.ui.theme.TitleCardFontSize
import java.io.InputStreamReader
import java.time.LocalDate
import java.time.format.DateTimeFormatter
import kotlin.random.Random

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
                    .fillMaxWidth(),
                horizontalArrangement = Arrangement.SpaceBetween,
                verticalAlignment = Alignment.CenterVertically
            ){
                IconButton(onClick = { currentDate = currentDate.minusMonths(1) }) {
                    Icon(Icons.Filled.KeyboardArrowLeft, contentDescription = Icons.Filled.KeyboardArrowLeft.name, tint = CalendarArrowTint)
                }

                Text(
                    text = currentDate.month.toString().lowercase().replaceFirstChar { it.uppercase() } + " " + currentDate.year.toString(),
                    color = Color.Black,
                    fontSize = 15.sp,
                )

                IconButton(onClick = { currentDate = currentDate.plusMonths(1) }) {
                    Icon(Icons.Filled.KeyboardArrowRight, contentDescription = Icons.Filled.KeyboardArrowRight.name, tint = CalendarArrowTint)
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
        CalendarDayNames()
        for (week in 0 until daysInMonth / 7 + 1) {
            Row {
                for (dayOfWeek in 0 until 7) {
                    val day = (week * 7) + dayOfWeek + 1
                    if (day <= daysInMonth) {
//                        val isSportDay = sportSessions.sessions.contains(currentDate.withDayOfMonth(day).toString())
                          val isSportDay = Random.nextInt(1, 10) == 1
                        CalendarDay(day, isSportDay,currentDate == LocalDate.now())
                    }
                }
            }
        }
    }
}

@Composable
fun CalendarDayNames(){
    val weekDays = listOf("Mon", "Tue", "Wed", "Thu", "Fri", "Sat", "Sun")
    Row {
        weekDays.forEach { jour ->
            Box(
                modifier = Modifier
                    .padding(4.dp)
                    .size(40.dp)
                    .background(Color.White)
                    .wrapContentSize(Alignment.Center)
            ) {
                Text(text = jour, color = Color.Gray)
            }
        }
    }
}

@Composable
fun CalendarDay(day: Int, isSportDay: Boolean, isCurrentDateNow : Boolean) {
    var color = if (isSportDay) BackgroundCalendarDateSport else Color.White
    var texColor = if (isSportDay or (isCurrentDateNow && day == LocalDate.now().dayOfMonth)) Color.White else Color.Black
    if (isCurrentDateNow && day == LocalDate.now().dayOfMonth){
        color = BackgroundCalendarCurrentDay
        texColor = Color.White
    }
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
