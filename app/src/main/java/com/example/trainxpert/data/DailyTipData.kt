package com.example.trainxpert.data

import java.time.LocalDateTime

val dailyTips = listOf(
    "Buvez au moins 1,5 litre d'eau par jour pour rester hydraté.",
    "Faites 30 minutes d'exercice par jour pour maintenir votre forme physique.",
    "Échauffez-vous avant chaque séance de sport pour éviter les blessures.",
    "Étirez-vous après l'entraînement pour améliorer la récupération musculaire.",
    "Dormez au moins 7 à 8 heures par nuit pour permettre à votre corps de se régénérer.",
    "Consommez des fruits et légumes à chaque repas pour un apport optimal en vitamines.",
    "Réduisez votre consommation de sucre pour prévenir les maladies métaboliques.",
    "Privilégiez les protéines maigres pour soutenir la croissance musculaire.",
    "Évitez les boissons énergétiques, elles contiennent souvent trop de sucre.",
    "Pratiquez une activité que vous aimez pour rester motivé à long terme.",
    "Prenez des pauses régulières si vous travaillez assis pour éviter les douleurs dorsales.",
    "Marchez au moins 10 000 pas par jour pour améliorer votre endurance.",
    "Testez différentes activités physiques pour trouver ce qui vous convient le mieux.",
    "Écoutez votre corps : reposez-vous si vous ressentez de la fatigue ou des douleurs.",
    "Planifiez vos repas à l'avance pour éviter les choix malsains de dernière minute.",
    "Intégrez des exercices de renforcement musculaire au moins deux fois par semaine.",
    "Réduisez votre consommation d'aliments transformés pour une meilleure santé.",
    "Mangez lentement pour mieux digérer et ressentir la satiété.",
    "Adoptez une posture correcte pour protéger vos articulations pendant le sport.",
    "Pratiquez la méditation ou le yoga pour réduire le stress et améliorer votre bien-être.",
    "Ne sautez pas le petit-déjeuner, c'est essentiel pour démarrer la journée en énergie.",
    "Remplacez les boissons gazeuses par de l'eau infusée avec des fruits pour un goût naturel.",
    "Intégrez des graisses saines comme les avocats ou les noix dans votre alimentation.",
    "Faites attention à votre respiration pendant l'exercice pour maximiser vos performances.",
    "Prenez le temps de marcher en plein air pour améliorer votre humeur et votre énergie.",
    "Fixez-vous des objectifs réalistes pour progresser à votre rythme.",
    "Essayez des exercices de gainage pour renforcer vos muscles profonds.",
    "Alternez les intensités pendant vos entraînements pour brûler plus de calories.",
    "Limitez le temps passé devant les écrans pour favoriser un sommeil de qualité.",
    "Prenez un jour de repos par semaine pour permettre à votre corps de récupérer.",
    "Utilisez un rouleau de massage (foam roller) pour soulager les tensions musculaires."
)

fun getDailyTip(): String{
    return dailyTips[LocalDateTime.now().dayOfMonth] ?: return dailyTips.random()
}