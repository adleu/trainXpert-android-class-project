package com.example.trainxpert.components

import android.app.AlertDialog
import android.widget.Toast
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.sharp.Delete
import androidx.compose.material.icons.sharp.Edit
import androidx.compose.material3.AlertDialog
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonColors
import androidx.compose.material3.Card
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.material3.TextField
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.SpanStyle
import androidx.compose.ui.text.buildAnnotatedString
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.withStyle
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import com.example.trainxpert.model.SportSession
import com.example.trainxpert.ui.theme.ButtonColor
import com.example.trainxpert.ui.theme.CardTitle
import com.example.trainxpert.ui.theme.MainPadding
import com.example.trainxpert.viewmodels.LocalSportSessionViewModel
import java.time.format.TextStyle
import java.util.Locale

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun SessionDetailsCard(navController: NavController, session: SportSession) {
    val viewModel = LocalSportSessionViewModel.current
    val context = LocalContext.current

    var showDialog by remember { mutableStateOf(false) }


    val builder = AlertDialog.Builder(context)
    builder.setTitle("Confirmation")
    builder.setMessage("Vous allez supprimer cette session?")
    builder.setPositiveButton("Supprimer") { dialog, which ->
        viewModel.deleteSession(session)
        navController.popBackStack()
        Toast.makeText(context, "Session supprimée", Toast.LENGTH_SHORT).show()
    }
    builder.setNegativeButton("Annuler") { dialog, which ->
    }
    val dialog = builder.create()

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
            Row {
                Text(text = session.dateTime.dayOfMonth.toString() + " ${
                    session.dateTime.month.getDisplayName(TextStyle.FULL, Locale.FRENCH)
                        .replaceFirstChar { it.uppercase() }
                }" + " ${session.dateTime.year}  ",
                    fontSize = 25.sp,
                    fontWeight = FontWeight.Bold,
                    color = Color.Black,
                    modifier = Modifier.padding(top = 10.dp))


//                Spacer(modifier = Modifier.weight(1f))
                Row {
                    IconButton(onClick = {
                        showDialog = true
                    }) {
                        Icon(
                            imageVector = Icons.Sharp.Edit,
                            contentDescription = "Edit",
//                        modifier = Modifier.weight(1f)
                        )
                    }

                    IconButton(onClick = {
                        dialog.show()
                    }) {
                        Icon(
                            imageVector = Icons.Sharp.Delete,
                            contentDescription = "Delete",
//                        modifier = Modifier.weight(1f)
                        )
                    }
                }


            }
            Row {

                Text(
                    text = session.dateTime.hour.toString() + "h" + session.dateTime.minute.toString()
                        .padStart(2, '0'),
                    fontSize = 20.sp,
                    fontWeight = FontWeight.Bold,
                    color = Color.Black,
                    modifier = Modifier.padding(top = 10.dp)
                )
            }



            Spacer(modifier = Modifier.height(50.dp))
            Row {
                Text(
                    text = session.activityName,
                    fontSize = 30.sp,
                    fontWeight = FontWeight.Bold,
                    color = Color.Black,
                )
            }


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

            session.caloriesBurned?.takeIf { it != 0 }?.let {


                Spacer(modifier = Modifier.height(10.dp))

                Text(
                    text = buildAnnotatedString {
                        append("Vous avez brûlé ")
                        withStyle(
                            style = SpanStyle(
                                fontWeight = FontWeight.Bold, color = CardTitle
                            )
                        ) {
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



            session.distanceInKm?.takeIf { it != 0.0 }?.let {
                Spacer(modifier = Modifier.height(10.dp))

                Text(
                    text = buildAnnotatedString {
                        append("Vous avez parcouru ")
                        withStyle(
                            style = SpanStyle(
                                fontWeight = FontWeight.Bold, color = CardTitle
                            )
                        ) {
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
    var newActivityName by remember { mutableStateOf(session.activityName) }
    var newDuration by remember { mutableStateOf(session.durationInMinutes) }
    var newDistance by remember { mutableStateOf(session.distanceInKm) }
    var newCalories by remember { mutableStateOf(session.caloriesBurned) }
    var newDateTime by remember { mutableStateOf(session.dateTime) }
    if (showDialog) {
        AlertDialog(onDismissRequest = { showDialog = false },
            title = { Text("Edition de la session") },
            text = {
                Column {
                    Text("Veuillez entrer vos modifications :")
                    Spacer(modifier = Modifier.height(8.dp))
                    TextField(value = newActivityName,
                        onValueChange = { newActivityName = it },
                        label = { Text("Nom de l'activité") },
                        singleLine = true
                    )
                    TextField(
                        value = newDuration.toString(),
                        onValueChange = { newDuration = it.toIntOrNull() ?: 0 },
                        label = { Text("Durée (min)") },
                        keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Number),
                    )
                    TextField(
                        value = newDistance.toString(),
                        onValueChange = { newDistance = it.toDoubleOrNull() ?: 0.0 },
                        label = { Text("Distance (km)") },
                        keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Number),
                    )
                    TextField(
                        value = newCalories.toString(),
                        onValueChange = { newCalories = it.toIntOrNull() ?: 0 },
                        label = { Text("Calories (kcal)") },
                        keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Number),
                    )
                    DateTimePickerField(
                        session.dateTime,
                        onDateTimeSelected = { newDateTime = it },
                        1
                    )
                }
            },
            confirmButton = {
                Button(colors = ButtonColors(
                    ButtonColor, Color.White, ButtonColor, ButtonColor
                ), onClick = {
                    if (newActivityName.isNotBlank()) {
                        // Ajouter l'activité ou effectuer une action
                        println("session modifiée : $newActivityName")
                        viewModel.updateSportSession(
                            session.copy(
                                activityName = newActivityName,
                                durationInMinutes = newDuration,
                                distanceInKm = newDistance,
                                caloriesBurned = newCalories,
                                dateTime = newDateTime
                            ),

                            )
                        showDialog = false
                    }
                }) {
                    Text("Valider")
                }
            },
            dismissButton = {
                TextButton(onClick = {
                    newActivityName = session.activityName
                    newDuration = session.durationInMinutes
                    newDistance = session.distanceInKm ?: 0.0
                    newCalories = session.caloriesBurned
                    newDateTime = session.dateTime
                    showDialog = false
                }) {
                    Text("Annuler")
                }
            })
    }
}