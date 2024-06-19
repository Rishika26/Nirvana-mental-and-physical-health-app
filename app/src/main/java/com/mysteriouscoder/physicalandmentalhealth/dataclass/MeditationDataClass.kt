package com.mysteriouscoder.physicalandmentalhealth.dataclass

import com.mysteriouscoder.physicalandmentalhealth.R

data class MeditationDataClass(
    val title: String,
    val subtitle: String,
    val image: Int
)

data class MedType(
    val title: String,
    val sub: String,
    val image: Int
)



val meditationList = listOf(
    MeditationDataClass(
        title = "Transcendental\n Meditation (TM)",
        subtitle = " Involves silently repeating a mantra to achieve a state of relaxed awareness.",
        image = R.drawable.meditation2,

        ),
    MeditationDataClass(
        title = "Loving-Kindness\n Meditation (Metta)",
        subtitle = " Focuses on cultivating feelings of love and compassion towards oneself.",
//        subtitle = " Focuses on cultivating feelings of love and compassion towards oneself and others.",
        image = R.drawable.meditation3,

        ),

    MeditationDataClass(
        title = "Zen\n Meditation (Zazen)",
        subtitle = "Emphasizes sitting in a specific posture and observing thoughts to reach a state.",
//        subtitle = "Emphasizes sitting in a specific posture and observing thoughts to reach a state of insight and enlightenment.",
        image = R.drawable.meditation4
    ),


    MeditationDataClass(
        title = "Guided\n Meditation",
        subtitle = "Involves a guide leading the practitioner through a visualization or a series.",
//        subtitle = "Involves a guide leading the practitioner through a visualization or a series of mental images to achieve a specific outcome.",
        image = R.drawable.meditation5
    ),

    MeditationDataClass(
        title = "Chakra\n Meditation",
        subtitle = "Focuses on aligning and balancing the body's energy centers (chakras).",
//        subtitle = "Focuses on aligning and balancing the body's energy centers (chakras) through visualization and breath control.",
        image = R.drawable.meditation6
    ),

    MeditationDataClass(
        title = "Mindfulness\n Meditation",
        subtitle = "A form of guided relaxation that leads to a state of conscious relaxation.",
//        subtitle = "A form of guided relaxation that leads to a state of conscious relaxation between wakefulness and sleep.",
        image = R.drawable.meditation7
    ),

    )
