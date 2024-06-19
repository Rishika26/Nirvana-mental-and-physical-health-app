package com.mysteriouscoder.physicalandmentalhealth.ui.screens.meditationsessionandsounds

import androidx.activity.ComponentActivity
import androidx.activity.compose.BackHandler
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.navigation.NavHostController
import androidx.navigation.compose.rememberNavController
import com.mysteriouscoder.physicalandmentalhealth.Heading
import com.mysteriouscoder.physicalandmentalhealth.ImageCard
import com.mysteriouscoder.physicalandmentalhealth.ImageCardForAllExercises
import com.mysteriouscoder.physicalandmentalhealth.R
import com.mysteriouscoder.physicalandmentalhealth.ads.BannerAds
import com.mysteriouscoder.physicalandmentalhealth.dataclass.meditationList
import com.mysteriouscoder.physicalandmentalhealth.ui.theme.GreyBg
import com.mysteriouscoder.physicalandmentalhealth.ui.theme.PhysicalAndMentalHealthTheme
import com.syedwaqarul.tipcalculator.Navigation.Navigationitems

@Composable
fun MeditationAndSounds(navHostController: NavHostController) {
    val context = LocalContext.current
    val activity = remember(context) { context as? ComponentActivity }

    Box(modifier =Modifier.fillMaxSize())
    {
    LazyColumn(
        horizontalAlignment = Alignment.CenterHorizontally,
        modifier = Modifier
            .background(GreyBg)
            .fillMaxSize()

    )
    {

        item {
            Row(
                verticalAlignment = Alignment.CenterVertically,
                horizontalArrangement = Arrangement.Start,
                modifier = Modifier.fillMaxSize()
            ) {
                Heading(title = "Meditation & Sounds",
            modifier= Modifier
                .padding(start = 10.dp)
                .padding(top = 10.dp)
                    )

            }
            Spacer(modifier = Modifier.height(20.dp))

        }

        item{
            Text(
                text = "Meditation",
                style = MaterialTheme.typography.bodyLarge,
                fontWeight = FontWeight.Bold,
                fontFamily = FontFamily.SansSerif,
                fontStyle = FontStyle.Italic,
                modifier = Modifier
                    .padding(start = 20.dp)
                    .fillMaxWidth()
            )
            Row {
                Spacer(modifier = Modifier.width(10.dp))
                ImageCardForAllExercises(
                    title = "Guided Meditation Session",
                    image = painterResource(id = R.drawable.meditation),
                    navHostController = navHostController,
                    navigationitems = Navigationitems.MeditationTypes,

                )
            }
            Spacer(modifier = Modifier.height(10.dp))
        }
        item {

            Text(
                text = "Types of Meditation",
                style = MaterialTheme.typography.bodyLarge,
                fontWeight = FontWeight.Bold,
                fontFamily = FontFamily.SansSerif,
                fontStyle = FontStyle.Italic,
                modifier = Modifier
                    .padding(start = 20.dp)
                    .fillMaxWidth()
            )

            Spacer(modifier = Modifier.height(10.dp))


        }
        item {
            LazyRow()
            {
                items(meditationList.size)
                {index->

                    if(index== 0)
                    {
                        Spacer(modifier = Modifier.width(10.dp))
                    }

                    ImageCard(
                        title = meditationList[index].title,
                        subtitle = meditationList[index].subtitle,
                        image = meditationList[index].image
                    )

                    if(index== meditationList.size-1)
                    {
                        Spacer(modifier = Modifier.width(10.dp))
                    }
                }


            }

            Spacer(modifier = Modifier.height(20.dp))

        }
        item {
            Text(
                text = "Soothing Music & Binaural Beats",
                style = MaterialTheme.typography.bodyLarge,
                fontWeight = FontWeight.Bold,
                fontFamily = FontFamily.SansSerif,
                fontStyle = FontStyle.Italic,
                modifier = Modifier
                    .padding(start = 20.dp)
                    .fillMaxWidth()
            )
            Spacer(modifier = Modifier.height(10.dp))
        }
        item(
        ){
            Column(
                modifier=Modifier.padding(start=10.dp)
            ) {

                ImageCardForAllExercises(
                    title = "BINAURAL BEATS",
                    image = painterResource(id = R.drawable.b5),
                    navHostController = navHostController,
                    navigationitems = Navigationitems.BinauralBeats,

                    )
                Spacer(modifier = Modifier.height(10.dp))


                ImageCardForAllExercises(
                    title = "Soothing Music",
                    image = painterResource(id = R.drawable.soothingmusic),
                    navHostController = navHostController


                )
                Spacer(modifier = Modifier.height(10.dp))
            }
        }

        item{

            Spacer(modifier = Modifier.height(45.dp))

        }

    }
        BannerAds(modifier = Modifier
            .fillMaxWidth()
            .align(Alignment.BottomCenter))

    }
    BackHandler(onBack = {
        activity?.finishAffinity()
    })

}

@Preview(showSystemUi = true)
@Composable
fun MindPreview() {
    PhysicalAndMentalHealthTheme {
        MeditationAndSounds(rememberNavController())
    }

}
