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

import androidx.compose.ui.Modifier
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
            .fillMaxWidth()
            .padding(MainPadding)
            .clickable { onClick() }
            .background(Color.White, shape = RoundedCornerShape(8.dp)),
        shape = RoundedCornerShape(8.dp),
    ) {
        Column(
            modifier = Modifier.padding(0.dp).background(Color.White).fillMaxSize(),
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

