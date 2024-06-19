package com.syedwaqarul.tipcalculator.Navigation

sealed class Navigationitems(var route:String) {
    object BinauralBeats: Navigationitems("BinauralBeats")
    object Meditation: Navigationitems("Meditation")
    object MeditationTypes: Navigationitems("MeditationTypes")
    object AI_friend: Navigationitems("AI friend")
    object Profile: Navigationitems("Profile")

    object acountCreateScreen: Navigationitems("AccountCreate")
    object LoginScreen: Navigationitems("LoginScreen")
    object Exercise: Navigationitems("Exercise")
    object SoothingMusic: Navigationitems("SoothingMusic")

    object  MusicPlayerScreen: Navigationitems("MusicPlayerScreen")
    object  MusicPlayerScreen2: Navigationitems("MusicPlayerScreen2")
    object  MusicPlayerScreen3: Navigationitems("MusicPlayerScreen3")
    object MusicPlayerWithIndex: Navigationitems("MusicPlayerWithIndex/{idx}")
    object MusicPlayerWithIndex2: Navigationitems("MusicPlayerWithIndex2/{idx}")
    object MusicPlayerWithIndex3: Navigationitems("MusicPlayerWithIndex3/{idx}")
    object  FAQ: Navigationitems("FAQ")

}

//3 36