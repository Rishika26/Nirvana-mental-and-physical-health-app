package com.mysteriouscoder.physicalandmentalhealth.dataclass

import androidx.annotation.DrawableRes
import com.mysteriouscoder.physicalandmentalhealth.R

data  class Page(
    val title:String,
    val description:String,
    @DrawableRes val image:Int
)

val pages= listOf(

    Page(
        title = "Meditation",
        description="" +
                "This section enables users to access and play various meditation sounds for relaxation and focus during meditation sessions.",
        image = R.drawable.meditationmainscreen
    ),

    Page(
        title = "Exercises",
        description="This section enables users to access to a library of exercise video for different workouts and fitness routines, facilitating physical activity.",
        image = R.drawable.femalelegworkout
    ),

    Page(
        title = "Soothing Music & Binaural Beats",
        description="This section enables users to explore and listen to binaural beats & soothing music designed to enhance productivity,focus and creativity through unique blends of frequencies & calming sounds.",
        image = R.drawable.soothingmusicmainscreen
    ),
    Page(
        title = "AI Friend",
        description="This section facilitates user interaction with an AI chat bot companion, providing support, guidance, and engaging conversations on health-related topics. ",
        image = R.drawable.aimainscreen
    ),
    Page(
        title = "Progress Tracking",
        description="This section includes visual dashboards and progress charts for users to record and analyze their progress in meditation, exercise, and other wellness activities over time.",
        image = R.drawable.progresstrackingphonewallpaper
    )

)