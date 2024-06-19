package com.mysteriouscoder.physicalandmentalhealth.dataclass

import androidx.compose.ui.graphics.Color
import com.mysteriouscoder.physicalandmentalhealth.R
import com.mysteriouscoder.physicalandmentalhealth.ui.theme.OrangeStart


data class Badge(
    val text: String,
    val color: Color
)

data class ExType(
    val title: String,
    val duration: String,
    val image: Int,
    val badges: List<Badge>,
    val gif: Int = R.drawable.frogcrunches_motion,
)

val exTypes = listOf(
    //belly
    ExType(
        title = "CROSS CRUNCHES",
        duration = "00:30",
        image = R.drawable.crosscrunches,
        badges = listOf(
            Badge("Abs", Color.Red), // Example badge with green color
            Badge("Core", Color.Yellow)  // Example badge with blue color
        ),
        gif = R.drawable.crosscrunches_motion
    ),
    ExType(
        title = "JUMPING ROPE",
        duration = "00:30",
        image = R.drawable.jumpingrope,
        gif = R.drawable.jumpingrope_motion,
        badges = listOf(
            Badge("Cardio", Color.Green), // Example badge with green color
            Badge("Endurance", Color.Blue)  // Example badge with blue color
        )
    ),
    ExType(
        title = "FROG CRUNCHES",
        duration = "00:30",
        image = R.drawable.frogcrunches,
        gif = R.drawable.frogcrunches_motion,
        badges = listOf(
            Badge("Core", Color.Yellow), // Example badge with green color
            Badge("Flexibilty", Color.Magenta)  // Example badge with blue color
        )
    ),
    ExType(
        title = "HIGH KNEES",
        duration = "00:30",
        image = R.drawable.highknees,
        gif = R.drawable.highknees_motion,
        badges = listOf(
            Badge("Cardio", Color.Green), // Example badge with green color
            Badge("Lower Body", OrangeStart)  // Example badge with blue color
        )
    ),

    ExType(
        title = "PLANK",
        duration = "00:30",
        image = R.drawable.plank,
        gif = R.drawable.plank_motion,
        badges = listOf(
            Badge("Core", Color.Yellow), // Example badge with green color
            Badge("Stability", Color.Gray)  // Example badge with blue color
        )
    ),
//
//    //arm
    ExType(
        title = "PUSH-UPS",
        duration = "00:30",
        image = R.drawable.pushup,
        gif = R.drawable.pushup_motion,
        badges = listOf(
            Badge("Upper Body", Color.Black), // Example badge with green color
            Badge("Endurance", Color.Blue)  // Example badge with blue color
        )
    ),
    ExType(
        title = "STANDING SIDE BEND",
        duration = "00:30",
        image = R.drawable.standingsidebend,
        gif = R.drawable.standingsidebend_motion,
        badges = listOf(
            Badge("Core", Color.Yellow), // Example badge with green color
            Badge("Flexibility", Color.Magenta)  // Example badge with blue color
        )

    ),
    ExType(
        title = "ARM CIRCLES",
        duration = "00:30",
        image = R.drawable.armcircle,
        gif = R.drawable.armcircle_motion,
        badges = listOf(
            Badge("Shoulder Strength", Color.Red), // Example badge with green color
            Badge("Flexibility", Color.Magenta)  // Example badge with blue color
        )
    ),
    ExType(
        title = "BIG ARM CIRCLES",
        duration = "00:30",
        image = R.drawable.bigarmcircles,
        gif = R.drawable.bigarmcircles_motion,
        badges = listOf(
            Badge("Shoulder Strength", Color.Red), // Example badge with green color
            Badge("Flexibility", Color.Magenta)  // Example badge with blue color
        )
    ),
    ExType(
        title = "PLANK SHOULDER TAPS",
        duration = "00:30",
        image = R.drawable.plankshouldertaps,
        gif = R.drawable.plankshouldertaps_motion,
        badges = listOf(
            Badge("Core", Color.Yellow), // Example badge with green color
            Badge("Stability", Color.Gray)  // Example badge with blue color
        )
    ),
//    //leg
    ExType(
        title = "WALKING LUNGES",
        duration = "00:30",
        image = R.drawable.walkinglunges,
        gif = R.drawable.walkinglunges_motion,
        badges = listOf(
            Badge("Lower Body", OrangeStart), // Example badge with green color
            Badge("Endurance", Color.Blue)  // Example badge with blue color
        )
    ),
    ExType(
        title = "COSSACK SQUATS",
        duration = "00:30",
        image = R.drawable.cossacksquat,
        gif = R.drawable.cossacksquat_motion,
        badges = listOf(
            Badge("Lower Body", OrangeStart), // Example badge with green color
            Badge("Flexibility", Color.Magenta)  // Example badge with blue color
        )
    ),
    ExType(
        title = "CRAB KICKS",
        duration = "00:30",
        image = R.drawable.crabkicks,
        gif = R.drawable.crabkicks_motion,
        badges = listOf(
            Badge("Lower Body", OrangeStart), // Example badge with green color
            Badge("Cardio", Color.Green)  // Example badge with blue color
        )
    ),
    ExType(
        title = "MARCH IN PLACE",
        duration = "00:30",
        image = R.drawable.marchinplace,
        gif = R.drawable.marchinplace_motion,
        badges = listOf(
            Badge("Lower Body", OrangeStart), // Example badge with green color
            Badge("Cardio", Color.Green)  // Example badge with blue color
        )
    ),
    ExType(
        title = "SHRIMP SQUATS",
        duration = "00:30",
        image = R.drawable.shrimpsquat,
        gif = R.drawable.shrimpsquat_motion,
        badges = listOf(
            Badge("Lower Body", OrangeStart), // Example badge with green color
            Badge("Flexibility", Color.Magenta)  // Example badge with blue color
        )
    ),
//
//    //chest
    ExType(
        title = "ASYMMETRIC PUSH-UPS",
        duration = "00:30",
        image = R.drawable.asymmetricpushup,
        gif = R.drawable.asymmetricpushup_motion,
        badges = listOf(
            Badge("Upper Body", Color.Black), // Example badge with green color
            Badge("Endurance", Color.Blue)  // Example badge with blue color
        )
    ),
    ExType(
        title = "PIKE PUSH-UPS",
        duration = "00:30",
        image = R.drawable.pikepushup,
        gif = R.drawable.pikepushup_motion,
        badges = listOf(
            Badge("Upper Body", Color.Black), // Example badge with green color
            Badge("Endurance", Color.Blue)  // Example badge with blue color
        )
    ),
//
    ExType(
        title = "SIDE PLANK",
        duration = "00:30",
        image = R.drawable.sideplank,
        gif = R.drawable.sideplank_motion,
        badges = listOf(
            Badge("Core", Color.Yellow), // Example badge with green color
            Badge("Stability", Color.Gray)  // Example badge with blue color
        )
    ),
    ExType(
        title = "SIT-UPS",
        duration = "00:30",
        image = R.drawable.situp,
        gif = R.drawable.situp_motion,
        badges = listOf(
            Badge("Abs", Color.Red), // Example badge with green color
            Badge("Core", Color.Yellow)  // Example badge with blue color
        )
    ),

    )
