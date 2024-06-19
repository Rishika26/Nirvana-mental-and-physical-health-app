package com.mysteriouscoder.physicalandmentalhealth.dataclass

import com.mysteriouscoder.physicalandmentalhealth.R


data class BinauralBeatsDataClass(
    val title: String,
    val time: String,
    val image: Int
)

val binauralbeatslist = listOf(
    BinauralBeatsDataClass(
        title = "Deep Sleep",
        time = "4 min",
        image = R.drawable.deepsleepdelta
    ),
    BinauralBeatsDataClass(
        title = "Creativity Boost",
        time = "4 min",
        image = R.drawable.creativityboost
    ),
    BinauralBeatsDataClass(
        title = "Memory Boost",
        time = "4 min",
        image = R.drawable.memoryboost
    ),
    BinauralBeatsDataClass(
        title = "Unlock hidden genius",
        time = "4 min",
        image = R.drawable.unlockhiddengenius
    ),
    BinauralBeatsDataClass(
        title = "Nirvana Meditation Aid",
        time = "4 min",
        image = R.drawable.nirvanameditationaid
    ),
    BinauralBeatsDataClass(
        title = "Enhanced Focus",
        time = "4 min",
        image = R.drawable.enhancedfocus
    ),
    BinauralBeatsDataClass(
        title = "Mood Enhancement",
        time = "4 min",
        image = R.drawable.moodenhancement
    ),
    BinauralBeatsDataClass(
        title = "Relaxation & Stress Relief",
        time = "4 min",
        image = R.drawable.relaxationandstressrelief
    ),
    BinauralBeatsDataClass(
        title = "Improved Brain Sync",
        time = "4 min",
        image = R.drawable.improvedbrainsync
    ),
    BinauralBeatsDataClass(
        title = "Deep Concentration (Gamma)",
        time = "4 min",
        image = R.drawable.deepconcentrationgamma
    ),
    BinauralBeatsDataClass(
        title = "Alertness & Energy (Beta)",
        time = "4 min",
        image = R.drawable.alertnessandenergybeta
    ),

    BinauralBeatsDataClass(
        title = "Relaxed & Awake (Alpha)",
        time = "4 min",
        image = R.drawable.relaxedandawakealpha
    ),

    BinauralBeatsDataClass(
        title = "Deep Relaxation (Theta)",
        time = "4 min",
        image = R.drawable.deeprelaxationtheta
    ),

    BinauralBeatsDataClass(
        title = "Lucid Dreaming (Alpha & Theta)",
        time = "4 min",
        image = R.drawable.luciddreamingalphaandtheta
    ),

    BinauralBeatsDataClass(
        title = "Astral Projection (Delta)",
        time = "4 min",
        image = R.drawable.astralprojectiondelta
    ),
)
