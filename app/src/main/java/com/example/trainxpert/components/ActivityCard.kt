package com.example.trainxpert.components

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.runtime.Composable
import androidx.compose.ui.unit.dp
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Text
import androidx.compose.ui.Alignment

import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.trainxpert.ui.theme.MainPadding

@Composable
fun ActivityCard(
    title: String,
    subtitle: String,
    imageResId: Int,
    onClick: () -> Unit, // Gestionnaire de clic ajouté
    modifier: Modifier = Modifier
) {
    Card(
        modifier = Modifier
            .fillMaxSize() // Taille carrée pour la carte
            .padding(8.dp) // Réduit l'espacement entre les cartes
            .clickable { onClick() },
        shape = RoundedCornerShape(8.dp),
        colors = CardDefaults.cardColors(containerColor = Color.White)
    ) {
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(10.dp), // Ajout de padding interne
            verticalArrangement = Arrangement.SpaceBetween, // Répartit l'espace entre les éléments
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            // Affichage de l'image
            Image(
                painter = painterResource(id = imageResId),
                contentDescription = title,
                modifier = Modifier
                    .height(125.dp)
                    .width(125.dp)// Taille réduite de l'image pour libérer de l'espace pour les textes
                    .clip(RoundedCornerShape(8.dp)),
                contentScale = ContentScale.Crop
            )

            // Texte du titre
            Text(
                text = title,
                fontSize = 16.sp, // Taille ajustée pour le titre
                color = Color.Black,
                maxLines = 1 // Limitez à une ligne pour éviter le débordement
            )

            // Texte du sous-titre
            Text(
                text = subtitle,
                fontSize = 14.sp, // Taille plus petite pour le sous-titre
                color = Color.Gray,
                maxLines = 1 // Limitez à une ligne pour éviter le débordement
            )
        }
    }
}
