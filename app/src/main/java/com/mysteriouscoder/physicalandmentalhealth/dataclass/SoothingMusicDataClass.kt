package com.mysteriouscoder.physicalandmentalhealth.dataclass

import com.mysteriouscoder.physicalandmentalhealth.R

data class SoothingMusicDataClass(
    val title: String,
    val time: String,
    val image: Int
)

val soothingmusiclist = listOf(
    SoothingMusicDataClass(
        title = "Indian Classical Music",
        time = "4 min",
        image = R.drawable.indianclassicalmusic
    ),
    SoothingMusicDataClass(
        title = "Meditative & healing music",
        time = "4 min",
        image = R.drawable.meditationandhealingmusic
    ),
    SoothingMusicDataClass(
        title = "Nature Sounds",
        time = "4 min",
        image = R.drawable.naturesounds
    ),
    SoothingMusicDataClass(
        title = "Chillout Music",
        time = "4 min",
        image = R.drawable.chilloutmusic
    ),
    SoothingMusicDataClass(
        title = "Ambient Music",
        time = "4 min",
        image = R.drawable.ambientmusic
    ),
    SoothingMusicDataClass(
        title = "New Age Music",
        time = "4 min",
        image = R.drawable.newagemusic
    ),
    SoothingMusicDataClass(
        title = "Instrumental Music",
        time = "4 min",
        image = R.drawable.instrumentalmusic
    ),

    SoothingMusicDataClass(
        title = "Piano Instrumentals",
        time = "4 min",
        image = R.drawable.pianoinstrumentals
    ),
    SoothingMusicDataClass(
        title = "Minimalism Music",
        time = "4 min",
        image = R.drawable.minimalismmusic
    ),
    SoothingMusicDataClass(
        title = "Sound Baths",
        time = "4 min",
        image = R.drawable.soundbath
    ),
    SoothingMusicDataClass(
        title = "Jazz Music",
        time = "4 min",
        image = R.drawable.jazzmusic
    ),
    SoothingMusicDataClass(
        title = "Spa Music",
        time = "4 min",
        image = R.drawable.spamusic
    ),
    SoothingMusicDataClass(
        title = "lOFI hip hop music",
        time = "4 min",
        image = R.drawable.lofihiphop
    ),
    SoothingMusicDataClass(
        title = "Acoustic Music",
        time = "4 min",
        image = R.drawable.acousticmusic
    ),
    SoothingMusicDataClass(
        title = "Soundtracks & film scores",
        time = "4 min",
        image = R.drawable.soundtrackandfilmscore
    ),

)
