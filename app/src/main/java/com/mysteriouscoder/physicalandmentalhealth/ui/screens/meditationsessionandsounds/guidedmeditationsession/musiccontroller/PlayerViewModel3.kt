package com.mysteriouscoder.physicalandmentalhealth.ui.screens.meditationsessionandsounds.guidedmeditationsession.musiccontroller

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.mysteriouscoder.physicalandmentalhealth.R
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import java.util.Collections


/***
 * Data class to represent a music in the list
 */
data class Music3(
    val name: String = "",
    val artist: String = "",
    val music: Int = 0,
    val cover: Int = 0,
)

/***
 * Sealed class to represent the player events
 */
sealed class PlayerEvent3 {
    data object Play : PlayerEvent3()
    data object Pause : PlayerEvent3()
    data object Stop : PlayerEvent3()
    data object Resume : PlayerEvent3()
    data object Next : PlayerEvent3()
    data object Previous : PlayerEvent3()
    data class UpdateCurrentSong(val song: Music3) : PlayerEvent3()
}

/***
 * Data class to represent the player state
 */
data class PlayerState3(
    var currentSong: Music3 = Music3(),
    var playList: List<Music3> = emptyList(),
    var currentPosition: Long = 0,
    var isPlaying: Boolean = false,
) {
    fun playNext() {
        val currentIndex = playList.indexOf(currentSong)
        val nextIndex = (currentIndex + 1) % playList.size
        currentSong = playList[nextIndex]
    }

    fun playPrevious() {
        val currentIndex = playList.indexOf(currentSong)
        val previousIndex = (currentIndex - 1) % playList.size
        currentSong = playList[previousIndex]
    }

    fun pause() {
        isPlaying = false
    }

    fun resume() {
        isPlaying = true
    }

    fun stop() {
        isPlaying = false
        currentPosition = 0
    }
}

/***
 * ViewModel to handle the player state and events
 */
@Suppress("UNCHECKED_CAST")
class MusicPlayerViewModel3(
    private val startIdx: Int
) : ViewModel() {

    private val _state = MutableStateFlow(PlayerState3())
    val playerState: StateFlow<PlayerState3> = _state.asStateFlow()

    init {
        val playList = getPlayList()
        Collections.swap(playList, 0, startIdx)
        _state.value = PlayerState3(playList = playList)
    }

    fun handlePlayerEvent(event: PlayerEvent3) {
        when (event) {
            is PlayerEvent3.Play -> _state.value.resume()
            is PlayerEvent3.Pause -> _state.value.pause()
            is PlayerEvent3.Stop -> _state.value.stop()
            is PlayerEvent3.Resume -> _state.value.resume()
            is PlayerEvent3.UpdateCurrentSong -> _state.value.currentSong = event.song
            PlayerEvent3.Next -> _state.value.playNext()
            PlayerEvent3.Previous -> _state.value.playPrevious()
        }
    }

    /***
     * Return a play list of type Music data class
     */


    private fun getPlayList(): List<Music3> {
        return listOf(

            Music3(
                name ="Stress Reduction",
                artist = "Nirvana",
                cover = R.drawable.stress,
                music = R.raw.stressreduction
            ),
            Music3(
                name = "Better Sleep",
                artist = "Nirvana",
                cover = R.drawable.sleep,
                music = R.raw.bettersleep
            ),
            Music3(
                name = "Enhance Mindfulness",
                artist = "Nirvana",
                cover = R.drawable.mindfulness,
                music = R.raw.enhancemindfulness
            ),
            Music3(
                name = "Anxiety Reduction",
                artist = "Nirvana",
                cover = R.drawable.anxiety,
                music = R.raw.anxietyreduction

            ),
            Music3(
                name = "Personal Growth",
                artist = "Nirvana",
                cover = R.drawable.growth,
                music = R.raw.personalgrowth

            ),
           Music3(
                name = "Cultivating Happiness",
                artist = "Nirvana",
                cover = R.drawable.happy,
                music = R.raw.cultivatinghappiness

            ),
            Music3(
                name = "Enhancing Relaxation",
                artist = "Nirvana",
                cover = R.drawable.relaxation,
                music = R.raw.enhancingrelaxation
            ),
            Music3(
                name = "Enhance Confidence",
                artist = "Nirvana",
                cover = R.drawable.confidence,
                music = R.raw.enhanceconfidence

            ),
            Music3(
                name = "Gratitude",
                artist = "Nirvana",
                cover = R.drawable.gratitude,
                music = R.raw.gratitude

            ),
            Music3(
                name = "Build Focus",
                artist = "Nirvana",
                cover = R.drawable.focus,
                music = R.raw.buildfocus

            )
        )
    }

    fun updateIndex(selectedIdx: Int) {
        // make the selectedIndex first
        _state.value = _state.value.apply {
            playList.toMutableList().swap(0, selectedIdx)
        }
    }

    private fun <T> MutableList<T>.swap(idx1: Int, idx2: Int): MutableList<T> = apply {
        val t = this[idx1]
        this[idx1] = this[idx2]
        this[idx2] = t
    }

    class Factory(private val startIdx: Int) : ViewModelProvider.Factory {
        override fun <T : ViewModel> create(modelClass: Class<T>): T {
            return MusicPlayerViewModel3(startIdx) as T
        }
    }
}