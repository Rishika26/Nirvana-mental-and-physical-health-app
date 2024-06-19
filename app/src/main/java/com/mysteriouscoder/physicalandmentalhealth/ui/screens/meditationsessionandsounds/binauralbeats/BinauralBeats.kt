package com.mysteriouscoder.physicalandmentalhealth.ui.screens.meditationsessionandsounds.binauralbeats
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.itemsIndexed
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowDownward
import androidx.compose.material3.Icon
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.rotate
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import com.mysteriouscoder.physicalandmentalhealth.CardForMusicScreen
import com.mysteriouscoder.physicalandmentalhealth.Heading
import com.mysteriouscoder.physicalandmentalhealth.ads.BannerAds
import com.mysteriouscoder.physicalandmentalhealth.dataclass.binauralbeatslist
import com.mysteriouscoder.physicalandmentalhealth.ui.theme.GreyBg


@Composable
fun BinauralBeats(
    onCardSelect: (idx: Int) -> Unit,
    navigationController: NavController

) {

    Box(modifier = Modifier.fillMaxSize())
    {

        LazyColumn(
            horizontalAlignment = Alignment.CenterHorizontally,
            modifier = Modifier
                .background(GreyBg)
                .fillMaxSize()
                .padding(horizontal = 10.dp)

        )
        {


            item {

                Spacer(modifier = Modifier.height(10.dp))

                Row(
                    verticalAlignment =Alignment.CenterVertically,
                    horizontalArrangement = Arrangement.Center){
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
                        title = "Binaural Beats",
                        modifier = Modifier
                            .fillMaxWidth()

                    )
                }
                Spacer(modifier = Modifier.height(5.dp))

            }

            itemsIndexed(binauralbeatslist) { idx, task ->
                CardForMusicScreen(
                    title = task.title,
                    time = task.time,
                    image = task.image,
                    idx = idx,
                ) {
                    onCardSelect(idx)
                }
                Spacer(modifier = Modifier.height(10.dp))
            }
            item {

                Spacer(modifier = Modifier.height(38.dp))

            }

        }
        BannerAds(
            modifier = Modifier
                .fillMaxWidth()
                .align(Alignment.BottomCenter)
        )

    }


}

//@Preview(showSystemUi = true)
//@Composable
//fun BinauralBeatsPreview() {
//    BinauralBeats(onCardSelect = {},
//    )
//
//
//}