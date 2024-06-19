package com.mysteriouscoder.physicalandmentalhealth.ui.screens.exercise.component

import android.os.Build
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.DisposableEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import coil.ImageLoader
import coil.compose.rememberAsyncImagePainter
import coil.decode.GifDecoder
import coil.decode.ImageDecoderDecoder
import coil.request.ImageRequest
import coil.size.Size
import com.mysteriouscoder.physicalandmentalhealth.dataclass.ExType
import com.mysteriouscoder.physicalandmentalhealth.dataclass.exTypes
import java.util.Timer
import kotlin.concurrent.scheduleAtFixedRate


@Composable
fun ExerciseCard(
    index: Int,
    onClick: (index: Int) -> Unit = {}
) {
    val ex = exTypes[index]
    Box(
        modifier = Modifier
            .fillMaxWidth()
            .height(100.dp)
            .background(MaterialTheme.colorScheme.primary, RoundedCornerShape(10.dp))
            .clickable {
                onClick(index)
            }
            .padding(10.dp)
    ) {
        Row(
            modifier = Modifier.fillMaxSize(),
//            verticalAlignment = Alignment.CenterVertically
        ) {
            Image(
                painter = painterResource(id = ex.image),
                contentScale = ContentScale.FillBounds,
                contentDescription = "Meditation",
                modifier = Modifier
                    .width(80.dp)
                    .height(80.dp)
                    .clip(RoundedCornerShape(10.dp)),
            )

            Spacer(modifier = Modifier.width(10.dp))

            Column {
                Text(
                    text = ex.title,
//                    style = MaterialTheme.typography.displayLarge,
                    color = MaterialTheme.colorScheme.onPrimary,
                    fontSize = 20.sp
                )

                Spacer(modifier = Modifier.height(5.dp))

                Text(
                    text = ex.duration,
//                    style = MaterialTheme.typography.displayLarge,
                    color = MaterialTheme.colorScheme.onPrimary,
                    fontSize = 15.sp
                )

                Spacer(modifier = Modifier.height(8.dp))

                Row {
                    // Display rounded colored badges beside duration
                    ex.badges.forEach { badge ->
                        Row(
                            verticalAlignment = Alignment.CenterVertically,
                            modifier = Modifier.padding(end = 14.dp)
                        ) {
                            Box(
                                modifier = Modifier
                                    .size(12.dp)
                                    .background(badge.color, RoundedCornerShape(8.dp))
                            )
                            Spacer(modifier = Modifier.width(6.dp))
                            Text(
                                text = badge.text,
                                style = MaterialTheme.typography.bodyMedium,
                                color = MaterialTheme.colorScheme.onPrimary
                            )
                        }
                    }

                }
            }
        }
    }
}

@Composable
fun ExerciseDetail(
    exType: ExType
) {
    var remainingTime by remember { mutableStateOf(30) } // Initial remaining time is 30 seconds

    // Countdown timer to update the time display
    DisposableEffect(Unit) {
        val timer = Timer()
        timer.scheduleAtFixedRate(1000L, 1000L) {
            if (remainingTime > 0) {
                remainingTime -= 1
            } else {
                timer.cancel() // Stop the timer when it reaches 0:00
            }
        }

        onDispose {
            timer.cancel() // Cancel the timer when the composable is disposed
        }
    }

    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(Color.White)
    ) {
        val imageLoader = ImageLoader.Builder(LocalContext.current)
            .components {
                if (Build.VERSION.SDK_INT >= 28) {
                    add(ImageDecoderDecoder.Factory())
                } else {
                    add(GifDecoder.Factory())
                }
            }
            .build()
        val painter = rememberAsyncImagePainter(
            ImageRequest.Builder(LocalContext.current).data(data = exType.gif)
                .apply(block = {
                    size(Size.ORIGINAL)
                }).build(), imageLoader = imageLoader
        )
        Box()
        {
            Image(
                painter = painter,
                contentDescription = "Exercise",
                modifier = Modifier
                    .fillMaxWidth()
                    .height(300.dp),
                contentScale = ContentScale.FillBounds
            )

            // Display remaining time over the GIF image
            Text(
                text = String.format("%02d:%02d", remainingTime / 60, remainingTime % 60),
                style = MaterialTheme.typography.headlineLarge,
                color = Color.Black,
                textAlign = TextAlign.Center,
                fontWeight = FontWeight.Bold,
                modifier = Modifier
                    .padding(start = 16.dp)
//                    .align(Alignment.TopStart),
            )
        }

        Column(
            modifier = Modifier.padding(16.dp)
        ) {
            Text(
                text = exType.title,
                style = MaterialTheme.typography.titleLarge,
//                color = MaterialTheme.colorScheme.onPrimary,
                color = Color.Black,
                fontWeight = FontWeight.Normal
            )

            Spacer(modifier = Modifier.height(8.dp))

            Text(
                text = exType.duration,
                style = MaterialTheme.typography.bodyMedium,
//                color = MaterialTheme.colorScheme.onPrimary,
                color = Color.Black,
                fontWeight = FontWeight.Normal
            )

            Spacer(modifier = Modifier.height(8.dp))

            exType.badges.forEach { badge ->
                Row(
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    Box(
                        modifier = Modifier
                            .size(12.dp)
                            .background(badge.color, RoundedCornerShape(8.dp))
                    )
                    Spacer(modifier = Modifier.width(6.dp))
                    Text(
                        text = badge.text,
                        style = MaterialTheme.typography.bodyMedium,
//                        color = MaterialTheme.colorScheme.onPrimary
                        color = Color.Black,
                    )
                }
            }
        }
    }
}



