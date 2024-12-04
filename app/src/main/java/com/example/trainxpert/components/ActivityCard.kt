package com.example.trainxpert.components

import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.runtime.Composable
import androidx.compose.ui.unit.dp
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Text

import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp

@Composable
fun ActivityCard(
    title: String,
    subtitle: String,
    imageResId: Int,
    onClick: () -> Unit, // Gestionnaire de clic ajouté
    modifier: Modifier = Modifier
) {
    Card(
        modifier = modifier
            .padding(8.dp)
            .width(150.dp) // Largeur ajustée pour le défilement horizontal
            .height(200.dp) // Hauteur de la carte
            .clickable { onClick() }, // Ajout du clic
        shape = RoundedCornerShape(8.dp),
        colors = CardDefaults.cardColors(
            containerColor = Color.White
        )
    ) {
        Column(
            modifier = Modifier.padding(8.dp),
            verticalArrangement = Arrangement.Center
        ) {
            // Affichage de l'image
            Image(
                painter = painterResource(id = imageResId),
                contentDescription = title,
                modifier = Modifier
                    .fillMaxWidth()
                    .height(100.dp), // Ajustez la hauteur selon vos besoins
                contentScale = ContentScale.Crop
            )
            Spacer(modifier = Modifier.height(8.dp))

            // Affichage du titre
            Text(text = title, fontSize = 16.sp, color = Color.Black)

            // Affichage du sous-titre
            Text(text = subtitle, fontSize = 12.sp, color = Color.DarkGray)
        }
    }
}

