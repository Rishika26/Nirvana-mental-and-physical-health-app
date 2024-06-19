package com.mysteriouscoder.physicalandmentalhealth.ui.screens.meditationsessionandsounds.guidedmeditationsession

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
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
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.itemsIndexed
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowDownward
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.draw.rotate
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import com.mysteriouscoder.physicalandmentalhealth.Heading
import com.mysteriouscoder.physicalandmentalhealth.R
import com.mysteriouscoder.physicalandmentalhealth.ads.BannerAds
import com.mysteriouscoder.physicalandmentalhealth.dataclass.MedType


val medTypes = listOf(
    MedType(
        title = "Stress Reduction",
        sub = "Step into a sanctuary of serenity as we embark on a meditation journey to dissolve stress and nurture inner peace.",
        image = R.drawable.stress
    ),
    MedType(
        title = "Better Sleep",
        sub = "Drift into a restful night's sleep with our meditation session tailored to enhance sleep quality, inviting serenity and deep relaxation.",
        image = R.drawable.sleep
    ),
    MedType(
        title = "Enhance Mindfulness",
        sub = "Cultivate mindfulness and awareness with our meditation session designed to help you stay present and grounded.",
        image = R.drawable.mindfulness
    ),
    MedType(
        title = "Anxiety Reduction",
        sub = "Find calm amidst the chaos with our meditation session designed to alleviate anxiety and cultivate a sense of peace.",
        image = R.drawable.anxiety
    ),

    MedType(
        title = "Personal Growth",
        sub = "Embark on a journey of self-discovery and personal growth with our meditation session designed to help you unlock your full potential.",
        image = R.drawable.growth
    ),
    MedType(
        title = "Cultivating Happiness",
        sub = "Discover the key to happiness with our meditation session designed to uplift your spirits and cultivate a sense of joy.",
        image = R.drawable.happy
    ),
    MedType(
        title = "Enhancing Relaxation",
        sub = "Indulge in a moment of tranquility with our meditation session designed to enhance relaxation and invite peace.",
        image = R.drawable.relaxation

    ),
    MedType(
        title = "Enhance Confidence",
        sub = "Boost your self-esteem and confidence with our meditation session designed to help you feel empowered and self-assured.",
        image = R.drawable.confidence
    ),
    MedType(
        title = "Gratitude",
        sub = "Embarking on a journey of gratitude meditation, we unlock the treasures of the soul, finding solace in the whispers of thankfulness.",
        image = R.drawable.gratitude
    ),
    MedType(
        title = "Build Focus",
        sub = "Enhance your focus and concentration with our meditation session designed to help you stay present and attentive.",
        image = R.drawable.focus
    )
)

@Composable
fun Meditation(
    onCardSelect: (idx: Int) -> Unit,
    navigationController: NavController
) {
    Box(modifier = Modifier.fillMaxSize())
    {
        Column(
            modifier = Modifier
                .fillMaxSize()
                .background(MaterialTheme.colorScheme.onPrimary)
                .padding(horizontal = 20.dp),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {

            Spacer(modifier = Modifier.height(10.dp))

            Row(
                verticalAlignment =Alignment.CenterVertically,
                horizontalArrangement = Arrangement.Center
            ){
                Icon(
                    imageVector = Icons.Default.ArrowDownward,
                    contentDescription = "Back Button",
                    modifier = Modifier
                        .rotate(90f)
                        .clickable {
                            navigationController.popBackStack()
                        }
                        .size(28.dp)
                )
                Heading(
                    title = "Meditation",
                    modifier = Modifier
                        .fillMaxWidth()

                )
            }

            Spacer(modifier = Modifier.height(10.dp))

            LazyColumn(
                verticalArrangement = Arrangement.spacedBy(10.dp),
            ) {
                itemsIndexed(medTypes) { idx, task->
                    MeditationCard(
                        index = idx,
                        idx = idx,
                    ) {
                        onCardSelect(idx)
                    }
                }
                item {
                    Spacer(modifier = Modifier.height(45.dp))
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

@Composable
fun MeditationCard(
    index: Int,
    idx: Int = 0,
    onClick: (idx: Int) -> Unit = {}
) {
    val med = medTypes[index]
    Box(
        modifier = Modifier
            .fillMaxWidth()
            .height(100.dp)
            .background(MaterialTheme.colorScheme.primary, RoundedCornerShape(10.dp))
            .clickable {
                onClick(idx)
            }
            .padding(10.dp)
    ) {
        Row(
            modifier = Modifier.fillMaxSize(),
//            verticalAlignment = Alignment.CenterVertically
        ) {
            Image(
                painter = painterResource(id = med.image),
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
                    text = med.title,
                    style = MaterialTheme.typography.displayLarge,
                    color = MaterialTheme.colorScheme.onPrimary,
                    fontSize = 20.sp
                )

                Spacer(modifier = Modifier.height(5.dp))

                Text(
                    text = med.sub,
                    style = MaterialTheme.typography.displayLarge,
                    color = MaterialTheme.colorScheme.onPrimary,
                    fontSize = 15.sp
                )
            }

        }
    }
}
