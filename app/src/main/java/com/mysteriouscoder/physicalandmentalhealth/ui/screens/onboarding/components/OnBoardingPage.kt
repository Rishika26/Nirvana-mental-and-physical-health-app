package com.mysteriouscoder.physicalandmentalhealth.ui.screens.onboarding.components

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.mysteriouscoder.physicalandmentalhealth.dataclass.Page
import com.mysteriouscoder.physicalandmentalhealth.dataclass.pages

@Composable
fun OnBoardingPage(
    modifier: Modifier = Modifier,
    page: Page = pages[0]
) {
    Column(
        modifier = modifier
    ) {

            Image(
                modifier = Modifier
                    .fillMaxWidth()
//                    .height(550.dp)
                .fillMaxHeight(fraction = 0.6f),
                painter = painterResource(id = page.image), contentDescription = null,
                contentScale = ContentScale.Crop
            )
            Column(modifier=Modifier.fillMaxHeight(fraction=0.5f)) {
            Spacer(modifier = Modifier.height(10.dp))

                Text(
                    text = page.title,
                    modifier = Modifier
                        .padding(horizontal = 20.dp),
                    style = MaterialTheme.typography.headlineLarge.copy(fontWeight = FontWeight.Bold),
                    color = MaterialTheme.colorScheme.onSurface,
                    fontSize = 20.sp
                )
                Text(
                    text = page.description,
                    modifier = Modifier
                        .padding(horizontal = 20.dp),
                    style = MaterialTheme.typography.bodyMedium,
                    color = MaterialTheme.colorScheme.onSurfaceVariant
//                    color = colorResource(id = R.color.text_medium)
                )
            }
        }

    }

//@Preview(
////    uiMode = Configuration.UI_MODE_NIGHT_YES,
//    showSystemUi = true)
//@Composable
//fun OnBoardingPagePrevie()
//{
// PhysicalAndMentalHealthTheme {
//     OnBoardingPage( )
// }
//}
