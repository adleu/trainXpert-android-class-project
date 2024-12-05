package com.example.trainxpert.screens

import androidx.compose.foundation.layout.Column
import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material.icons.filled.ArrowDropDown
import androidx.compose.material.icons.filled.DateRange
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.trainxpert.model.ActivityItem
import java.time.LocalDateTime

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun AddActivityScreen(
    activities: List<ActivityItem>,
    onCancel: () -> Unit,
   onSave: (String, LocalDateTime, Int, Double?, Int?) -> Unit,
    backAction : () -> Unit
) {
    var selectedActivity by remember { mutableStateOf<ActivityItem?>(null) }
    var date by remember { mutableStateOf("") }
    var time by remember { mutableStateOf("") }
    var duration by remember { mutableStateOf("") }
    var distance by remember { mutableStateOf("") }
    var calories by remember { mutableStateOf("") }
    var dropdownExpanded by remember { mutableStateOf(false) }

    Scaffold(
        topBar = {
            TopAppBar(
                title = { Text("Ajouter une activité", fontSize = 20.sp) },
                navigationIcon = {
                    IconButton(onClick = onCancel) {
                        Icon(
                            imageVector = Icons.Default.ArrowBack,
                            contentDescription = "Retour"
                        )
                    }
                }
            )
        }
    ) { innerPadding ->
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(innerPadding)
                .padding(16.dp),
            verticalArrangement = Arrangement.Top,
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            // Dropdown for activity selection
            TextField(
                value = selectedActivity?.title ?: "",
                onValueChange = {},
                readOnly = true,
                label = { Text("Activité") },
                trailingIcon = {
                    IconButton(onClick = { dropdownExpanded = true }) {
                        Icon(Icons.Default.ArrowDropDown, contentDescription = "Dropdown")
                    }
                },
                modifier = Modifier.fillMaxWidth()
            )

            // Dropdown Menu
            DropdownMenu(
                expanded = dropdownExpanded,
                onDismissRequest = { dropdownExpanded = false },
                modifier = Modifier.fillMaxWidth()
            ) {
                activities.forEach { activity ->
                    DropdownMenuItem(
                        text = { Text(activity.title) },
                        onClick = {
                            selectedActivity = activity
                            dropdownExpanded = false
                        }
                    )
                }
            }

            Spacer(modifier = Modifier.height(16.dp))

            // Date input
            TextField(
                value = date,
                onValueChange = { date = it },
                label = { Text("Date") },
                placeholder = { Text("YYYY-MM-DD") },
                trailingIcon = {
                    Icon(imageVector = Icons.Default.DateRange, contentDescription = "Date Picker")
                },
                modifier = Modifier.fillMaxWidth()
            )

            Spacer(modifier = Modifier.height(16.dp))

            // Time input
            TextField(
                value = time,
                onValueChange = { time = it },
                label = { Text("Heure") },
                placeholder = { Text("HH:MM") },
                trailingIcon = {
                    Icon(imageVector = Icons.Default.DateRange, contentDescription = "Time Picker")
                },
                modifier = Modifier.fillMaxWidth()
            )

            Spacer(modifier = Modifier.height(16.dp))

            // Duration input
            TextField(
                value = duration,
                onValueChange = { duration = it },
                label = { Text("Durée (min)") },
                keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Number),
                modifier = Modifier.fillMaxWidth()
            )

            Spacer(modifier = Modifier.height(16.dp))

            // Distance input
            TextField(
                value = distance,
                onValueChange = { distance = it },
                label = { Text("Distance (km)") },
                keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Number),
                modifier = Modifier.fillMaxWidth()
            )

            Spacer(modifier = Modifier.height(16.dp))

            // Calories input
            TextField(
                value = calories,
                onValueChange = { calories = it },
                label = { Text("Calories brûlées") },
                keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Number),
                modifier = Modifier.fillMaxWidth()
            )

            Spacer(modifier = Modifier.height(32.dp))

            // Buttons
            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.SpaceEvenly
            ) {
                Button(onClick = onCancel, colors = ButtonDefaults.buttonColors(containerColor = Color.Red)) {
                    Text("Annuler", color = Color.White)
                }
                Button(onClick = {
                    if (selectedActivity != null && date.isNotBlank() && time.isNotBlank()) {
                        val localDateTime = LocalDateTime.parse("$date $time".replace(" ", "T"))
                        onSave(
                            selectedActivity!!.title,
                            localDateTime,
                            duration.toIntOrNull() ?: 0,
                            distance.toDoubleOrNull(),
                            calories.toIntOrNull()
                        )
                    }
                }) {
                    Text("Enregistrer", color = Color.White)
                }
            }
        }
    }
}

