
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.KeyboardArrowLeft
import androidx.compose.material.icons.filled.KeyboardArrowRight
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
import com.example.trainxpert.components.HistoryCard
import com.example.trainxpert.model.SportSession
import com.example.trainxpert.navigation.NavigationHistoryComponent
import com.example.trainxpert.ui.theme.BackgroundCalendarCurrentDay
import com.example.trainxpert.ui.theme.BackgroundCalendarDateSport
import com.example.trainxpert.ui.theme.CalendarArrowTint
import com.example.trainxpert.ui.theme.CardTitle
import com.example.trainxpert.ui.theme.MainPadding
import com.example.trainxpert.ui.theme.TitleCardFontSize
import com.example.trainxpert.viewmodels.LocalSportSessionViewModel
import ufr.mim.netfloux.components.RawButton
import java.time.DayOfWeek
import java.time.LocalDate


data class SportSessions(val sessions: List<String>)


@Composable
fun SportCalendarScreen(onDetails: ((SportSession) -> Unit)) {
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

            Spacer(modifier = Modifier.height(8.dp))

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

            CalendarView(currentDate, onDetails)
        }
    }
}

@Composable
fun CalendarView(currentDate: LocalDate, onDetails: ((SportSession) -> Unit)) {
    val viewModel = LocalSportSessionViewModel.current
    val sessions by viewModel.sessions.collectAsState(initial = emptyList())

    val daysInMonth = currentDate.lengthOfMonth()

    // Afficher les jours du mois
    Column(
        modifier = Modifier
            .background(Color.White)
            .fillMaxWidth()
    ) {
        CalendarDayNames(getFirstDayOfWeekIndex(currentDate.year.toInt(),currentDate.monthValue))
        for (week in 0 until daysInMonth / 7 + 1) {
            Row {
                for (dayOfWeek in 0 until 7) {
                    val day = (week * 7) + dayOfWeek + 1
                    if (day <= daysInMonth) {
//                        val isSportDay = sportSessions.sessions.contains(currentDate.withDayOfMonth(day).toString())
                        val isSportDay = sessions.any { session ->
                            session.dateTime.toLocalDate() == currentDate.withDayOfMonth(day)
                        }
                        val sessionOfDay = if (isSportDay) {
                            sessions.find { session ->
                                session.dateTime.toLocalDate() == currentDate.withDayOfMonth(day)
                            }
                        } else {
                            null
                        }


//                        val isSportDay = Random.nextInt(1, 10) == 1

                        CalendarDay(day, isSportDay,currentDate == LocalDate.now(),session = sessionOfDay, onDetails = onDetails)
                    }
                }
            }
        }
    }
}

@Composable
fun CalendarDayNames(startDayIndex : Int){
    val weekDays = listOf("Mon", "Tue", "Wed", "Thu", "Fri", "Sat", "Sun")
    val shiftedWeekDays = weekDays.drop(startDayIndex) + weekDays.take(startDayIndex)
    Row {
        shiftedWeekDays.forEach { jour ->
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
fun CalendarDay(day: Int, isSportDay: Boolean, isCurrentDateNow : Boolean,session : SportSession?= null, onDetails: ((SportSession) -> Unit)) {
    var color = if (isSportDay) BackgroundCalendarDateSport else Color.White
    var texColor = if (isSportDay or (isCurrentDateNow && day == LocalDate.now().dayOfMonth)) Color.White else Color.Black
    if (isCurrentDateNow && day == LocalDate.now().dayOfMonth){
        color = BackgroundCalendarCurrentDay
        texColor = Color.White
    }

    if(session != null){
        RawButton(onClick = {
            onDetails(session)
        }){
            calendarBoxByDay(day, color, texColor)
        }
    }else{
        calendarBoxByDay(day, color, texColor)
    }


}

@Composable
fun calendarBoxByDay(day : Int, color : Color, texColor: Color){
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

fun getFirstDayOfWeekIndex(year: Int, month: Int): Int {
    val firstDayOfMonth = LocalDate.of(year, month, 1)

    val dayOfWeek = firstDayOfMonth.dayOfWeek

    return when (dayOfWeek) {
        DayOfWeek.MONDAY -> 0
        DayOfWeek.TUESDAY -> 1
        DayOfWeek.WEDNESDAY -> 2
        DayOfWeek.THURSDAY -> 3
        DayOfWeek.FRIDAY -> 4
        DayOfWeek.SATURDAY -> 5
        DayOfWeek.SUNDAY -> 6
    }
}

//@Preview(showBackground = false)
//@Composable
//fun DefaultPreview() {
//    SportCalendarScreen()
//}
