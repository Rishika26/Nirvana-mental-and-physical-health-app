package com.mysteriouscoder.physicalandmentalhealth


import android.os.Build
import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.foundation.layout.Arrangement
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
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Divider
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.painter.Painter
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.SpanStyle
import androidx.compose.ui.text.buildAnnotatedString
import androidx.compose.ui.text.font.Font
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.text.withStyle
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavHostController
import coil.ImageLoader
import coil.compose.rememberAsyncImagePainter
import coil.decode.GifDecoder
import coil.decode.ImageDecoderDecoder
import coil.request.ImageRequest
import coil.size.Size
import com.mysteriouscoder.physicalandmentalhealth.ui.theme.LightOrange
import com.mysteriouscoder.physicalandmentalhealth.ui.theme.LightPurple
import com.mysteriouscoder.physicalandmentalhealth.ui.theme.OrangeStart
import com.mysteriouscoder.physicalandmentalhealth.ui.theme.WhiteGray
import com.syedwaqarul.tipcalculator.Navigation.Navigationitems

@Composable
fun App_icon_component() {

    Image(
        painter = painterResource(id = R.drawable.appicon), contentDescription = "",
        modifier = Modifier
            .size(130.dp)
            .clip(CircleShape)

            .border(2.dp, MaterialTheme.colorScheme.onSurface, shape = CircleShape),

        )

}



@Composable
fun ImageCard(
    title: String = "3km Running",
    subtitle: String = "The basic of running",
    image: Int = R.drawable.gym1,
    modifier: Modifier = Modifier
) {


    Card(
        modifier = Modifier
            .fillMaxWidth()
            .width(260.dp)
            .height(220.dp)
            .padding(10.dp)
        ,
        shape = RoundedCornerShape(15.dp),
        elevation = CardDefaults.cardElevation(5.dp)


    )
    {

        Box(modifier = Modifier.height(200.dp)) {
            Image(
                painter = painterResource(id = image),
                contentDescription = "",
                modifier = modifier.size(400.dp),
                contentScale = ContentScale.Crop,
            )

           Box(modifier = Modifier
               .fillMaxSize()
               .background(
                   Brush.verticalGradient(
                       colors = listOf(
                           Color.Transparent,
                           Color.Black
                       ),
                       startY = 100f
                   )
               ))


            Column(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(10.dp)
                    .width(220.dp),

                horizontalAlignment = Alignment.CenterHorizontally

            ) {
                Spacer(modifier = Modifier.weight(1f))

              Text(
                    text = title.uppercase(),
                    style = MaterialTheme.typography.bodyLarge,
                    fontWeight = FontWeight.Bold,
                    fontSize = 15.sp,
                    color = Color.White,
                    textAlign = TextAlign.Center
                )

            Spacer(modifier = Modifier.height(8.dp))

            Text(
                text = subtitle,
                fontSize =10.sp,
                color = Color.White,

                )

                Spacer(modifier = Modifier.height(16.dp))


            }
        }
    }

}


@Composable
fun ChallengesImageCard(
    title: String = "3km Running",
    subtitle: String = "The basic of running",
    image: Int = R.drawable.gym1,
    modifier: Modifier = Modifier
) {


    Card(
        modifier = Modifier
            .fillMaxWidth()
            .width(260.dp)
            .height(220.dp)
            .padding(10.dp)
        ,
        shape = RoundedCornerShape(15.dp),
        elevation = CardDefaults.cardElevation(5.dp)


    )
    {

        Box(modifier = Modifier.height(200.dp)) {
            Image(
                painter = painterResource(id = image),
                contentDescription = "",
                modifier = modifier.size(400.dp),
                contentScale = ContentScale.Crop,
            )
            Box(modifier = Modifier
                .fillMaxSize()
                .background(
                    Brush.verticalGradient(
                        colors = listOf(
                            Color.Transparent,
                            Color.Black
                        ),
                        startY = 300f
                    )
                ))



            Column(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(10.dp)
                    .width(220.dp),

                horizontalAlignment = Alignment.CenterHorizontally

            ) {
                Spacer(modifier = Modifier.weight(1f))




                Text(
                    text = title.uppercase(),
                    style = MaterialTheme.typography.bodyLarge,
                    fontWeight = FontWeight.Bold,
                    fontSize = 15.sp,
                    color = Color.White
                )
                Spacer(modifier = Modifier.height(8.dp))

                Text(
                    text = subtitle,
                    fontSize = 12.sp,
                    color = Color.White,

                    )

                Spacer(modifier = Modifier.height(16.dp))


            }
        }
    }

}

@Composable
fun ImageCardForAllExercises(
    title: String = "3km Running",

    image: Painter = painterResource(id = R.drawable.gym1),
    modifier: Modifier = Modifier,
    navHostController: NavHostController,
    navigationitems: Navigationitems = Navigationitems.SoothingMusic
) {


    Card(
        modifier = Modifier
            .fillMaxWidth()
            .height(120.dp)
            .then(modifier)
            .padding(10.dp)
            .clickable {
                navHostController.navigate(navigationitems.route)

            },
        shape = RoundedCornerShape(15.dp),
        elevation = CardDefaults.cardElevation(5.dp)


    )
    {

        Box(modifier = Modifier.height(200.dp)) {
            Image(
                painter = image,
                contentDescription = "",
                modifier = modifier.fillMaxWidth(),
//                modifier = modifier.size(400.dp),
                contentScale = ContentScale.Crop,
            )

            Box(modifier = Modifier
                .fillMaxSize()
                .background(
                    Brush.verticalGradient(
                        colors = listOf(
                            Color.Transparent,
                            Color.Black
                        ),
                        startY = 100f
                    )
                ))

            Column(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(10.dp)
                    .width(220.dp),

                horizontalAlignment = Alignment.CenterHorizontally

            ) {
                Spacer(modifier = Modifier.weight(1f))
                Text(
                    text = title.uppercase(),
                    style = MaterialTheme.typography.bodyLarge,
                    fontWeight = FontWeight.Bold,
                    fontSize = 15.sp,
                    color = Color.White,
                    textAlign = TextAlign.Center
                )
                Spacer(modifier = Modifier.height(8.dp))


            }
        }
    }

}


@Composable
fun NewsButton(
    text: String = "Hello",
    onClick: () -> Unit
) {
    Button(
        onClick = onClick,
        colors = ButtonDefaults.buttonColors(
            containerColor = MaterialTheme.colorScheme.primary,
            contentColor = Color.White
        ),
        shape = MaterialTheme.shapes.extraLarge
//        shape = RoundedCornerShape(size = 6.dp)
    )
    {
        Text(
            text = text,
            style = MaterialTheme.typography.labelMedium.copy(fontWeight = FontWeight.SemiBold)
        )
    }

}

@Composable
fun NewsTextButton(
    text: String = "Hello",
    onClick: () -> Unit
) {
    TextButton(onClick = onClick)
    {
        Text(
            text = text,
            style = MaterialTheme.typography.labelMedium.copy(fontWeight = FontWeight.SemiBold),
            color = WhiteGray
        )
    }

}


@Composable
fun CardForProgressReport(
    backgroundColor: Color = LightOrange,
    title: String = "Binaural Beats",
    emoji: String = "🎯",
    gif: Int=R.drawable.musicgif,
    value: Int=0,
    modifier: Modifier = Modifier
) {
    var seconds by rememberSaveable {
        mutableStateOf(value%60)
    }
    var minutes by rememberSaveable {
        mutableStateOf(value/60%60)
    }
    var hour by rememberSaveable {
        mutableStateOf(minutes/60)
    }
    Card(
        modifier = Modifier
            .fillMaxWidth()
//            .width(180.dp)
            .height(220.dp)
            .padding(10.dp),
        shape = RoundedCornerShape(15.dp),
        elevation = CardDefaults.cardElevation(5.dp)
    )
    {

        Box(
            modifier = Modifier
                .height(220.dp)
                .background(if (isSystemInDarkTheme()) backgroundColor else backgroundColor)
                .padding(10.dp)
        ) {


            Column(
                modifier = Modifier
                    .padding(10.dp)
                    .fillMaxWidth()
                    .height(220.dp),

                horizontalAlignment = Alignment.CenterHorizontally,
                verticalArrangement = Arrangement.SpaceBetween

            ) {

                Row(
                    verticalAlignment = Alignment.CenterVertically,
                    horizontalArrangement = Arrangement.SpaceBetween,
                    modifier = Modifier.fillMaxWidth()


                ) {

                    Text(
                        text = title,
                        style = MaterialTheme.typography.headlineSmall,
                        fontWeight = FontWeight.Bold,
                        color=if (isSystemInDarkTheme()) Color.Black else Color.Black,
                        fontSize = 13.sp


                        )

                    Text(
                        text = emoji,
                        style = MaterialTheme.typography.bodyLarge,
                        fontWeight = FontWeight.Bold,
                        fontSize = 14.sp

                    )
                }
                Spacer(modifier = Modifier.height(10.dp))
                Row(horizontalArrangement = Arrangement.Center,
                    modifier = Modifier.fillMaxWidth())
                {
                    GIFLoading(gif,
                        modifier=modifier
                            .padding(horizontal = 12.dp)
                            .height(70.dp)
//                            .fillMaxWidth()
                            .align(Alignment.CenterVertically)
                    )
                }
                    Spacer(modifier = Modifier.height(10.dp))
                Row(verticalAlignment = Alignment.CenterVertically,
                    horizontalArrangement = Arrangement.Center){
                    Text(
                        text = buildAnnotatedString {
                            withStyle(
                                style = SpanStyle(
                                    fontSize = 14.sp,
                                    color = Color.Black,

                                    )
                            ) {
                                append("${hour} ")
                            }
                            append("h")

                        },

                        style = MaterialTheme.typography.bodyLarge,
                        fontWeight = FontWeight.Bold,
                        color = Color.Black,
                        fontSize = 11.sp


                    )
                    Text(
                        text = buildAnnotatedString {
                            withStyle(
                                style = SpanStyle(
                                    fontSize = 14.sp,
                                    color = Color.Black
                                    )
                            ) {
                                append("  ${minutes} ")
                            }
                            append("m")

                        },

                        style = MaterialTheme.typography.bodyLarge,
                        fontWeight = FontWeight.Bold,
                        color = Color.Black,
                        fontSize = 11.sp


                    )
                    Text(
                        text = buildAnnotatedString {
                            withStyle(
                                style = SpanStyle(
                                    fontSize = 14.sp,
                                    color = Color.Black
                                    )
                            ) {
                                append("  ${seconds} ")
                            }
                            append("s")

                        },

                        style = MaterialTheme.typography.bodyLarge,
                        fontWeight = FontWeight.Bold,
                        color = Color.Black,
                        fontSize = 11.sp
                    )

                }
            }
        }
    }

}

@Composable
fun TotalTimeReport(
    totaltime: Int=0

) {
    var seconds by rememberSaveable {
        mutableStateOf(totaltime%60)
    }
    var minutes by rememberSaveable {
        mutableStateOf(totaltime/60%60)
    }
    var hour by rememberSaveable {
        mutableStateOf(minutes/60)
    }
    Card(
        modifier = Modifier
            .fillMaxWidth()
            .height(220.dp)
            .padding(10.dp),
        shape = RoundedCornerShape(15.dp),
        elevation = CardDefaults.cardElevation(5.dp)
    )
    {


        Box(
            modifier = Modifier
                .height(220.dp)
                .fillMaxWidth()
                .background(OrangeStart)
                .padding(10.dp)

        ) {
            Column(
                modifier = Modifier
                    .padding(10.dp)
                    .fillMaxWidth(),
            ) {
                Row(
                    verticalAlignment = Alignment.CenterVertically,
                    horizontalArrangement = Arrangement.SpaceBetween,
                    modifier = Modifier.fillMaxWidth()
                ) {
                    Text(
                        text = "Total Time Spent",
                        style = MaterialTheme.typography.headlineMedium,
                        fontWeight = FontWeight.Bold,
                        color = Color.White
                    )
                    Text(
                        text = "⏰",
                        style = MaterialTheme.typography.headlineMedium,
                    )
                }

                Spacer(modifier =Modifier.height(20.dp))
                Row(
                    modifier = Modifier
                        .fillMaxWidth(),
                    verticalAlignment = Alignment.CenterVertically

                ) {


                    GIFLoading(gif=R.drawable.clockgif,
                        modifier= Modifier
                            .height(75.dp)
                            .width(100.dp)
                            .clip(CircleShape)
                            .border(2.dp, Color.White, CircleShape)
                    )

                    Spacer(modifier = Modifier.weight(1f))
                    Row(verticalAlignment = Alignment.CenterVertically,
                        horizontalArrangement = Arrangement.Center){
                        Text(
                            text = buildAnnotatedString {
                                withStyle(
                                    style = SpanStyle(
                                        fontSize = 20.sp,
                                        color = Color.White
                                    )
                                ) {
                                    append("${hour} ")
                                }
                                append("h")

                            },

                            style = MaterialTheme.typography.bodyLarge,
                            fontWeight = FontWeight.Bold,
                            color = Color.White,
                            fontSize = 14.sp


                        )
                        Text(
                            text = buildAnnotatedString {
                                withStyle(
                                    style = SpanStyle(
                                        fontSize = 20.sp,
                                        color = Color.White
                                    )
                                ) {
                                    append("  ${minutes} ")
                                }
                                append("m")

                            },

                            style = MaterialTheme.typography.bodyLarge,
                            fontWeight = FontWeight.Bold,
                            color = Color.White,
                            fontSize = 14.sp


                        )
                        Text(
                            text = buildAnnotatedString {
                                withStyle(
                                    style = SpanStyle(
                                        fontSize = 20.sp,
                                        color = Color.White
                                    )
                                ) {
                                    append("  ${seconds} ")
                                }
                                append("s")

                            },

                            style = MaterialTheme.typography.bodyLarge,
                            fontWeight = FontWeight.Bold,
                            color = Color.White,
                            fontSize = 14.sp
                        )

                    }
                    Spacer(modifier = Modifier.weight(1f))
                }
            }
        }
    }

}

@Composable
fun Heading(
    title: String = "Binaural Beats FAQ",
    modifier: Modifier = Modifier,
    fontSize: Int = 22,
    textAlign: TextAlign= TextAlign.Start
) {
    Text(
        text = title,
        fontFamily = FontFamily(Font(R.font.nunito_extrabold)),
        fontSize = fontSize.sp,
        modifier = modifier.padding(start = 10.dp),
        textAlign = textAlign
    )

}

@Composable
fun FAQComponent(
    question: String = "What is Binaural Beats?",
    answer: String = "Binaural beats are a form of soundwave therapy. The idea is that when exposed to two different frequencies at the same time through stereo headphones, the brain perceives a beat at the frequency equal to the difference between the two frequencies.",
    color: Color = LightPurple,
    modifier: Modifier = Modifier
) {


    Row(
        modifier = modifier
            .fillMaxWidth()
            .padding(start = 20.dp),
        horizontalArrangement = Arrangement.spacedBy(16.dp),
        verticalAlignment = Alignment.CenterVertically
    ) {

        Row(verticalAlignment = Alignment.CenterVertically) {

            Box(
                modifier = Modifier
                    .size(16.dp)
//                    .clip(CircleShape)
                    .border(
                        border = BorderStroke(
                            3.dp,
                            MaterialTheme.colorScheme.onSurface
                        ),
                        shape = CircleShape
                    )

            )

            Divider(
                modifier = Modifier.width(6.dp),
                color = MaterialTheme.colorScheme.onSurface

            )


            Row(
                modifier = Modifier.fillMaxWidth(),
                verticalAlignment = Alignment.CenterVertically
            ) {
                Column(
                    modifier = Modifier
                        .clip(RoundedCornerShape(14.dp))
                        .background(color)
                        .weight(0.9f)
                        .padding(end = 8.dp),
                    verticalArrangement = Arrangement.spacedBy(8.dp)

                ) {
                    Text(
                        modifier = Modifier
                            .padding(
                                top = 12.dp,
                                start = 12.dp
                            ),
                        text = "Q: $question",
                        fontFamily = FontFamily(
                            Font(R.font.nunito_bold)
                        ),
                        color = if(isSystemInDarkTheme()) Color.Black else Color.Black,
                    )
                    Text(
                        modifier = Modifier
                            .padding(
                                start = 12.dp
                            ),
                        color = Color.Gray,
                        text = "Ans: $answer",
                        fontFamily = FontFamily(Font(R.font.nunito_bold))
                    )
                    Spacer(modifier = Modifier.height(8.dp))
                }


                Divider(
                    modifier = Modifier
                        .width(6.dp)
                        .weight(0.1f),
                    color = MaterialTheme.colorScheme.onSurface
                )
            }

        }

    }
}
@Composable
fun CardForMusicScreen(
    title: String = "3km Running",
    time: String = "4 min",
    image: Int = R.drawable.relaxationandstressrelief,
    modifier: Modifier = Modifier,
    idx: Int = 0,
    onClick: (idx: Int) -> Unit = {}
) {

    Card(
        shape = RoundedCornerShape(15.dp),
        modifier = modifier
            .fillMaxWidth()
            .height(150.dp)
            .padding(10.dp)
            .clickable {
                onClick(idx)
            },
        elevation = CardDefaults.cardElevation(5.dp)
    )
    {

        Box(
            modifier = Modifier
                .height(200.dp)
        ) {
            Image(
                painter = painterResource(id = image),
                contentDescription = "",
                modifier = modifier.fillMaxWidth(),
                contentScale = ContentScale.Crop,
            )

            Box(
                modifier = Modifier
                    .fillMaxSize()
                    .background(
                        Brush.verticalGradient(
                            colors = listOf(
                                Color.Transparent,
                                Color.Black
                            ),
                            startY = 200f
                        )
                    )
            )

            Column {
                Spacer(modifier = Modifier.weight(1f))
                Row(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(start = 30.dp)
                        .padding(bottom = 10.dp)
                    ,
                    verticalAlignment = Alignment.Bottom,
                    horizontalArrangement = Arrangement.Center
                ) {
                    Text(
                        text = title.uppercase(),
                        style = MaterialTheme.typography.bodyLarge,
                        fontWeight = FontWeight.Bold,
                        fontSize = 13.sp,
                        color =Color.White,
                        fontFamily = FontFamily(Font(R.font.nunito_extrabold)),
                    )
                    Spacer(modifier = Modifier.weight(1f))
                    Text(
                        text = time,
                        fontSize = 12.sp,
                        fontWeight = FontWeight.Bold,
                        color = Color.White,
                        fontFamily = FontFamily(Font(R.font.nunito_extrabold)),
                    )

                    Spacer(modifier = Modifier.width(20.dp))
                }
            }
        }
    }
}


@Composable
fun GIFLoading(gif:Int=R.drawable.running,
               modifier:Modifier=Modifier
){
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
        ImageRequest.Builder(LocalContext.current).data(data =gif)
            .apply(block = {
                size(Size.ORIGINAL)
            }).build(), imageLoader = imageLoader
    )
            Image(
                painter = painter,
                contentDescription = "Exercise",
                modifier = modifier
                    .width(50.dp)
                    .height(50.dp),
                contentScale = ContentScale.FillBounds
            )

}