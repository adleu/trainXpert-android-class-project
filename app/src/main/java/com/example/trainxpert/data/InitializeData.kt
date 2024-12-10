package com.example.trainxpert.data

import ActivityViewModel
import com.example.trainxpert.R
import com.example.trainxpert.model.ActivityItem

suspend fun InitializeData(activityViewModel: ActivityViewModel) {

        activityViewModel.addActivity(
            ActivityItem(
                title = "Football",
                subtitle = "Jouer au football",
                imageResId = R.drawable.football,
                category = "Sport",
                pratique = "Entraînez-vous régulièrement sur le terrain.",
                conseil = "Portez des chaussures adaptées pour éviter les blessures."
            )
        )
        activityViewModel.addActivity(
            ActivityItem(
                title = "Basketball",
                subtitle = "Jouer au basketball",
                imageResId = R.drawable.basket,
                category = "Sport",
                pratique = "Faites des exercices de dribble et de shoot.",
                conseil = "Hydratez-vous fréquemment pendant les sessions."
            )
        )
        activityViewModel.addActivity(
            ActivityItem(
                title = "Tennis",
                subtitle = "Jouer au tennis",
                imageResId = R.drawable.tennis,
                category = "Sport",
                pratique = "Travaillez votre revers et votre service.",
                conseil = "Échauffez-vous avant chaque partie pour éviter les tensions musculaires."
            )
        )

        activityViewModel.addActivity(
            ActivityItem(
                title = "Nutrition",
                subtitle = "Conseils nutrition",
                imageResId = R.drawable.nutrition,
                category = "Conseil",
                pratique = "Privilégiez les repas équilibrés riches en légumes.",
                conseil = "Évitez les aliments transformés et riches en sucre."
            )
        )
        activityViewModel.addActivity(
            ActivityItem(
                title = "Santé mentale",
                subtitle = "Conseils santé mentale",
                imageResId = R.drawable.santemental,
                category = "Conseil",
                pratique = "Pratiquez la gratitude tous les matins.",
                conseil = "Consultez un thérapeute en cas de stress prolongé."
            )
        )
        activityViewModel.addActivity(
            ActivityItem(
                title = "Hygiène",
                subtitle = "Conseils hygiène",
                imageResId = R.drawable.hygiene,
                category = "Conseil",
                pratique = "Lavez-vous les mains fréquemment.",
                conseil = "Changez de brosse à dents tous les trois mois."
            )
        )

        activityViewModel.addActivity(
            ActivityItem(
                title = "Méditation guidée",
                subtitle = "Apprenez à méditer",
                imageResId = R.drawable.meditation,
                category = "Méditation",
                pratique = "Suivez des méditations guidées en ligne.",
                conseil = "Créez un espace calme dédié à votre pratique."
            )
        )
        activityViewModel.addActivity(
            ActivityItem(
                title = "Respiration",
                subtitle = "Techniques de respiration",
                imageResId = R.drawable.respiration,
                category = "Méditation",
                pratique = "Essayez la respiration 4-7-8 pour vous détendre.",
                conseil = "Respirez lentement et profondément pour réduire l'anxiété."
            )
        )
        activityViewModel.addActivity(
            ActivityItem(
                title = "Yoga",
                subtitle = "Pratique du yoga",
                imageResId = R.drawable.joga,
                category = "Méditation",
                pratique = "Commencez avec des postures simples comme l’arbre.",
                conseil = "Utilisez un tapis confortable pour éviter les glissements."
            )
        )


    }