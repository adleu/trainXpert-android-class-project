package com.example.trainxpert.components

import android.annotation.SuppressLint
import android.app.DatePickerDialog
import android.app.TimePickerDialog
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.AlertDialogDefaults.shape
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonColors
import androidx.compose.material3.Card
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.trainxpert.ui.theme.ButtonColor
import com.example.trainxpert.ui.theme.CardTitle
import com.example.trainxpert.ui.theme.FormFontSize
import com.example.trainxpert.ui.theme.HistoryCardFontSize
import com.example.trainxpert.ui.theme.MainPadding
import com.example.trainxpert.ui.theme.TextFieldFontSizeData
import com.example.trainxpert.ui.theme.formBackground
import java.time.LocalDateTime
import java.util.Calendar

@SuppressLint("DefaultLocale")
@Composable
fun DateTimePickerField(
    initialDateTime: LocalDateTime? = null,
    onDateTimeSelected: (LocalDateTime) -> Unit
) {
    var selectedDateTime by remember {
        mutableStateOf(initialDateTime ?: LocalDateTime.now())
    }

    val context = LocalContext.current
    val calendar = Calendar.getInstance()

    Button(
        onClick = {
            DatePickerDialog(
                context,
                { _, year, month, dayOfMonth ->
                    calendar.set(Calendar.YEAR, year)
                    calendar.set(Calendar.MONTH, month)
                    calendar.set(Calendar.DAY_OF_MONTH, dayOfMonth)

                    TimePickerDialog(
                        context,
                        { _, hourOfDay, minute ->
                            calendar.set(Calendar.HOUR_OF_DAY, hourOfDay)
                            calendar.set(Calendar.MINUTE, minute)

                            val selectedDate = LocalDateTime.of(
                                year,
                                month + 1,
                                dayOfMonth,
                                hourOfDay,
                                minute
                            )
                            selectedDateTime = selectedDate
                            onDateTimeSelected(selectedDate)
                        },
                        selectedDateTime.hour,
                        selectedDateTime.minute,
                        true
                    ).show()
                },
                selectedDateTime.year,
                selectedDateTime.monthValue - 1,
                selectedDateTime.dayOfMonth
            ).show()
        },
        modifier = Modifier
            .padding(16.dp)
            .fillMaxWidth(),
        colors = ButtonColors(formBackground, Color.Black, Color.White, Color.White),
        shape = RoundedCornerShape(5.dp),
        contentPadding = PaddingValues(MainPadding)


    ) {
        Row(
            modifier = Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.Start
        ){
            Text(
//            text = selectedDateTime.toString().replace("T", " "),
//            style = MaterialTheme.typography.bodyMedium,
                text = String.format(
                    "%02d/%02d/%04d    %02dh%02d",
                    selectedDateTime.dayOfMonth,
                    selectedDateTime.monthValue,
                    selectedDateTime.year,
                    selectedDateTime.hour,
                    selectedDateTime.minute
                ),
                color = CardTitle,
                style = TextStyle(
//                    fontWeight = FontWeight.Normal,
                    fontSize = TextFieldFontSizeData,
                ),
            )
        }

    }
}