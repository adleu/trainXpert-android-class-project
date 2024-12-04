package com.example.trainxpert.screens

import androidx.compose.foundation.layout.Column
import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable

@Composable
fun AddActivityScreen(backAction: () -> Unit) {
    Column {
        Button(onClick = backAction) {
            Text("Retour")
        }
        Text("Écran pour ajouter une activité")
    }
}


