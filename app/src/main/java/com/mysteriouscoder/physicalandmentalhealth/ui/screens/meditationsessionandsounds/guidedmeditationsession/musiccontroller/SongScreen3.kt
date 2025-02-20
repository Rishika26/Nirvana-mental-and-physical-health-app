package com.mysteriouscoder.physicalandmentalhealth.ui.screens.meditationsessionandsounds.guidedmeditationsession.musiccontroller

import android.net.Uri
import android.util.Log
import androidx.activity.compose.BackHandler
import androidx.compose.animation.AnimatedContent
import androidx.compose.animation.ExperimentalAnimationApi
import androidx.compose.animation.core.Animatable
import androidx.compose.animation.core.LinearEasing
import androidx.compose.animation.core.LinearOutSlowInEasing
import androidx.compose.animation.core.RepeatMode
import androidx.compose.animation.core.infiniteRepeatable
import androidx.compose.animation.core.tween
import androidx.compose.animation.fadeIn
import androidx.compose.animation.fadeOut
import androidx.compose.animation.scaleIn
import androidx.compose.animation.scaleOut
import androidx.compose.animation.togetherWith
import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.aspectRatio
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.pager.HorizontalPager
import androidx.compose.foundation.pager.PageSize
import androidx.compose.foundation.pager.rememberPagerState
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Slider
import androidx.compose.material3.SliderDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.DisposableEffect
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableFloatStateOf
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.mutableLongStateOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.draw.rotate
import androidx.compose.ui.geometry.Rect
import androidx.compose.ui.geometry.Size
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.Outline
import androidx.compose.ui.graphics.Path
import androidx.compose.ui.graphics.PathOperation
import androidx.compose.ui.graphics.Shape
import androidx.compose.ui.graphics.painter.Painter
import androidx.compose.ui.platform.LocalConfiguration
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.Density
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.LayoutDirection
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.media3.common.MediaItem
import androidx.media3.exoplayer.ExoPlayer
import com.mysteriouscoder.physicalandmentalhealth.PreferenceManager
import com.mysteriouscoder.physicalandmentalhealth.R
import com.mysteriouscoder.physicalandmentalhealth.ads.BannerAds
import kotlinx.coroutines.delay

@OptIn(ExperimentalFoundationApi::class, ExperimentalAnimationApi::class)
@Composable
fun SongScreen3(
    state: PlayerState3, onBackPress: () -> Unit
) {

    val packageName = LocalContext.current.packageName
    val context = LocalContext.current
    val player = remember {
        ExoPlayer.Builder(context).build()
    }

    val playList = state.playList
    LaunchedEffect(Unit) {
        playList.forEach {
            val path = "android.resource://" + packageName + "/" + it.music
            val mediaItem = MediaItem.fromUri(Uri.parse(path))
            player.addMediaItem(mediaItem)
        }
    }
    player.prepare()


    val pagerState = rememberPagerState(pageCount = { playList.count() })
    val playingSongIndex = remember {
        mutableIntStateOf(0)
    }

    val isPlaying = remember {
        mutableStateOf(false)
    }
    val isRepeat = remember {
        mutableStateOf(0)
    }

    val currentPosition = remember {
        mutableLongStateOf(0)
    }

    val sliderPosition = remember {
        mutableLongStateOf(0)
    }

    val totalDuration = remember {
        mutableLongStateOf(0)
    }
    val remainTime = rememberSaveable {
        mutableLongStateOf(0)
    }
    val configuration = LocalConfiguration.current
    val prefs = PreferenceManager(LocalContext.current)
    val meditationTimeSpent = prefs.get("meditation")?.toLong() ?: 0
//    on closing the composable

    if (isPlaying.value) {
        val startTime by remember {
            mutableLongStateOf(System.currentTimeMillis())
        }
        DisposableEffect(key1 = true) {
            onDispose {
                val endTime = System.currentTimeMillis()
                val timeSpent = (endTime - startTime) / 1000
                val totalTime = (meditationTimeSpent + timeSpent)
                Log.d(
                    "prefs",
                    "start: $startTime end: $endTime timeSpent: $timeSpent, totalTime: $totalTime"
                )
                prefs.save("meditation", totalTime.toString())
            }
        }
    }

    LaunchedEffect(pagerState.currentPage) {
        playingSongIndex.intValue = pagerState.currentPage
        player.seekTo(pagerState.currentPage, 0)
    }

    LaunchedEffect(player.currentMediaItemIndex) {
        playingSongIndex.intValue = player.currentMediaItemIndex
        pagerState.animateScrollToPage(
            playingSongIndex.intValue,
            animationSpec = tween(500)
        )
    }


    LaunchedEffect(key1 = player.currentPosition, key2 = player.isPlaying) {
        delay(1000)
        currentPosition.longValue = player.currentPosition
    }

    LaunchedEffect(currentPosition.longValue) {
        sliderPosition.longValue = currentPosition.longValue
    }

    LaunchedEffect(player.duration) {
        if (player.duration > 0) {
            totalDuration.longValue = player.duration
            remainTime.longValue = player.duration - player.currentPosition
        }
    }
    LaunchedEffect(key1 = totalDuration) {
        delay(1000)
        val duration = player.duration
        val currPos = player.currentPosition
        remainTime.longValue = duration - currPos
    }


    BackHandler(onBack = {
        if (player.isPlaying) {
            player.pause()
        }
        onBackPress()
    })

    DisposableEffect(key1 = player) {
        onDispose {
            player.release()
        }
    }

    Box(
        modifier = Modifier
            .fillMaxSize(),
        contentAlignment = Alignment.Center
    ) {
        Icon(imageVector = Icons.Filled.ArrowBack,
            contentDescription = "BackPress",
            modifier = Modifier
                .align(Alignment.TopStart)
                .size(52.dp)
                .padding(10.dp)
                .clickable {
                    if (player.isPlaying) {
                        player.pause()
                    }
                    onBackPress()
                }
        )


        Column(horizontalAlignment = Alignment.CenterHorizontally) {


            /***
             * Animated texts includes song name and its artist
             * Animates when the song is switching
             */
            AnimatedContent(targetState = playingSongIndex.intValue, transitionSpec = {
                (scaleIn() + fadeIn()) togetherWith (scaleOut() + fadeOut())
            }, label = "") {
                Text(
                    text = playList[it].name,
                    fontSize = 24.sp,
                    color = MaterialTheme.colorScheme.primary,
                    style = TextStyle(fontWeight = FontWeight.ExtraBold)
                )
            }
            Spacer(modifier = Modifier.height(8.dp))
            AnimatedContent(targetState = playingSongIndex.intValue, transitionSpec = {
                (scaleIn() + fadeIn()) togetherWith (scaleOut() + fadeOut())
            }, label = "") {
                Text(
                    text = playList[it].artist,
                    fontSize = 12.sp,
                    color = MaterialTheme.colorScheme.primary,
                    style = TextStyle(fontWeight = FontWeight.Bold)
                )
            }

            Spacer(modifier = Modifier.height(16.dp))


            /***
             * Includes animated song album cover
             */
            // phone page size = 1.7
            // tablet page size = ?
            val pageSize: Float = if (configuration.screenWidthDp > 600) {
                1.2f
            } else {
                1.7f
            }
            HorizontalPager(
                modifier = Modifier.fillMaxWidth(),
                state = pagerState,
                pageSize = PageSize.Fixed((configuration.screenWidthDp / (pageSize)).dp),
                contentPadding = PaddingValues(horizontal = 85.dp)
            ) { page ->

                val painter = painterResource(id = playList[page].cover)
//                Spacer(modifier = Modifier.width(90.dp))
                if (page == pagerState.currentPage) {
                    VinylAlbumCoverAnimation(isSongPlaying = isPlaying.value, painter = painter)
                } else {
                    VinylAlbumCoverAnimation(isSongPlaying = false, painter = painter)
                }
            }
            Spacer(modifier = Modifier.height(54.dp))
            Column(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(horizontal = 32.dp),
            ) {

                TrackSlider(
                    value = sliderPosition.longValue.toFloat(),
                    onValueChange = {
                        sliderPosition.longValue = it.toLong()
                    },
                    onValueChangeFinished = {
                        currentPosition.longValue = sliderPosition.longValue
                        player.seekTo(sliderPosition.longValue)
                    },
                    songDuration = totalDuration.longValue.toFloat()
                )
                Row(
                    modifier = Modifier.fillMaxWidth(),
                ) {

                    Text(
                        text = (currentPosition.longValue).convertToText(),
                        modifier = Modifier
                            .weight(1f)
                            .padding(8.dp),
                        color = MaterialTheme.colorScheme.primary,
                        style = TextStyle(fontWeight = FontWeight.Bold)
                    )


                    Text(
                        text = if (remainTime.longValue >= 0) remainTime.longValue.convertToText() else "",
                        modifier = Modifier
                            .padding(8.dp),
                        color = MaterialTheme.colorScheme.primary,
                        style = TextStyle(fontWeight = FontWeight.Bold)
                    )
                }
            }
            Spacer(modifier = Modifier.height(24.dp))

            Row {
                Box(modifier = Modifier.fillMaxWidth()) {
//                Spacer(modifier = Modifier.width(50.dp))
                    Row(
                        horizontalArrangement = Arrangement.SpaceEvenly,
                        verticalAlignment = Alignment.CenterVertically,
                        modifier = Modifier.align(Alignment.Center)
                    ) {

                        ControlButton(icon = R.drawable.ic_previous, size = 40.dp, onClick = {
                            player.seekToPreviousMediaItem()
                        })
                        Spacer(modifier = Modifier.width(20.dp))
                        ControlButton(icon = if (isPlaying.value) R.drawable.ic_pause else R.drawable.ic_play,
                            size = 100.dp,
                            onClick = {
                                if (isPlaying.value) {
                                    player.pause()
                                } else {
                                    player.play()
//                            player.repeatMode = ExoPlayer.REPEAT_MODE_ONE
                                }
                                isPlaying.value = player.isPlaying
                            })
                        Spacer(modifier = Modifier.width(20.dp))
                        ControlButton(icon = R.drawable.ic_next, size = 40.dp, onClick = {
                            player.seekToNextMediaItem()
                        })
                    }
                    ControlButton(
                        icon = if (isRepeat.value == 0) R.drawable.ic_repeat_false else if (isRepeat.value == 1) R.drawable.ic_repeat_all else R.drawable.ic_repeat_one,
                        size = 35.dp,
                        onClick = {
//                            isRepeat.value = !isRepeat.value
                            if (isRepeat.value == 0) {
                                player.repeatMode = ExoPlayer.REPEAT_MODE_ALL
                                isRepeat.value = 1
                            } else if (isRepeat.value == 1) {
                                player.repeatMode = ExoPlayer.REPEAT_MODE_ONE
                                isRepeat.value = 2
                            } else {
                                player.repeatMode = ExoPlayer.REPEAT_MODE_OFF
                                isRepeat.value = 0
                            }
                        },
                        modifier = Modifier
                            .align(Alignment.CenterEnd)
                            .padding(end = 20.dp)
                    )
                }
            }

        }

        BannerAds(
            modifier = Modifier
                .fillMaxWidth()
                .align(Alignment.BottomCenter)
        )

    }
}

/**
 * Tracks and visualizes the song playing actions.
 */
@Composable
fun TrackSlider(
    value: Float,
    onValueChange: (newValue: Float) -> Unit,
    onValueChangeFinished: () -> Unit,
    songDuration: Float,
) {
    Slider(value = value,
        onValueChange = {
            onValueChange(it)
        }, onValueChangeFinished = {

            onValueChangeFinished()

        }, valueRange = 0f..songDuration, colors = SliderDefaults.colors(
            thumbColor = MaterialTheme.colorScheme.primary,
            activeTrackColor = Color.DarkGray,
            inactiveTrackColor = Color.Gray,
        )
    )
}

/***
 * Player control button
 */
@Composable
fun ControlButton(icon: Int, size: Dp, onClick: () -> Unit, modifier: Modifier = Modifier) {
    Box(
        modifier = modifier
            .size(size)
            .clip(CircleShape)
            .clickable {
                onClick()
            }, contentAlignment = Alignment.Center
    ) {
        Icon(
            modifier = Modifier.size(size / 1.5f),
            painter = painterResource(id = icon),
            tint = MaterialTheme.colorScheme.onSurface,
            contentDescription = null
        )
    }
}

@Composable
fun VinylAlbumCoverAnimation(
    modifier: Modifier = Modifier,
    isSongPlaying: Boolean = true,
    painter: Painter,
) {
    var currentRotation by remember {
        mutableFloatStateOf(0f)
    }

    val rotation = remember {
        Animatable(currentRotation)
    }

    LaunchedEffect(isSongPlaying) {
        if (isSongPlaying) {
            rotation.animateTo(
                targetValue = currentRotation + 360f, animationSpec = infiniteRepeatable(
                    animation = tween(3000, easing = LinearEasing), repeatMode = RepeatMode.Restart
                )
            ) {
                currentRotation = value
            }
        } else {
            if (currentRotation > 0f) {
                rotation.animateTo(
                    targetValue = currentRotation + 50, animationSpec = tween(
                        1250, easing = LinearOutSlowInEasing
                    )
                ) {
                    currentRotation = value
                }
            }
        }
    }

    VinylAlbumCover(
        painter = painter, rotationDegrees = rotation.value
    )
}

@Composable
fun VinylAlbumCover(
    modifier: Modifier = Modifier,
    rotationDegrees: Float = 0f,
    painter: Painter,
) {

    /**
     * Creates a custom outline for a rounded shape
     */
    val roundedShape = object : Shape {
        override fun createOutline(
            size: Size,
            layoutDirection: LayoutDirection,
            density: Density,
        ): Outline {
            val p1 = Path().apply {
                addOval(Rect(4f, 3f, size.width - 1, size.height - 1))
            }
            val thickness = size.height / 2.10f
            val p2 = Path().apply {
                addOval(
                    Rect(
                        thickness, thickness, size.width - thickness, size.height - thickness
                    )
                )
            }
            val p3 = Path()
            p3.op(p1, p2, PathOperation.Difference)

            return Outline.Generic(p3)
        }
    }

    /**
     * Container defining the layout for a vinyl-themed UI element.
     */
    Box(
        modifier = modifier
            .aspectRatio(1.0f)
            .clip(roundedShape)
    ) {

        /**
         * Vinyl background image
         */
        /**
         * Vinyl background image
         */
        Image(
            modifier = Modifier
                .fillMaxSize()
                .rotate(rotationDegrees),
            painter = painterResource(id = R.drawable.vinyl_background),
            contentDescription = "Vinyl Background"
        )


        /**
         * Song album cover image overlaid on the vinyl background image
         */


        /**
         * Song album cover image overlaid on the vinyl background image
         */
        Image(
            modifier = Modifier
                .fillMaxSize(0.5f)
                .rotate(rotationDegrees)
                .aspectRatio(1.0f)
                .align(Alignment.Center)
                .clip(roundedShape),
            painter = painter,
            contentDescription = "Song cover"
        )
    }
}

/***
 * Convert the millisecond to String text
 */
private fun Long.convertToText(): String {
    //    Log.d("TAG", "convertToText: $this")
    val sec = this / 1000
    val minutes = sec / 60
    val seconds = sec % 60

    val minutesString = if (minutes < 10) {
        "0$minutes"
    } else {
        minutes.toString()
    }
    val secondsString = if (seconds < 10) {
        "0$seconds"
    } else {
        seconds.toString()
    }
    return "$minutesString:$secondsString"
}


