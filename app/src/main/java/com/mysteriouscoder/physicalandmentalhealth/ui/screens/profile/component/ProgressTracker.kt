package com.mysteriouscoder.physicalandmentalhealth.ui.screens.profile.component

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import com.mysteriouscoder.physicalandmentalhealth.CardForProgressReport
import com.mysteriouscoder.physicalandmentalhealth.R
import com.mysteriouscoder.physicalandmentalhealth.ui.theme.LightBlue
import com.mysteriouscoder.physicalandmentalhealth.ui.theme.LightPurple
import com.mysteriouscoder.physicalandmentalhealth.ui.theme.LightRed

@Composable
fun ProgressTracker(meditation: Int,
                    exercise: Int,
                    soothingmusic:Int,
                    binauralBeats:Int ) {

    Column(modifier=Modifier.fillMaxSize()){
        Text(
            text = "Progress Tracker",
            style = MaterialTheme.typography.headlineLarge,
            fontWeight = FontWeight.Bold,
            modifier = Modifier.fillMaxWidth()
        )

        Spacer(modifier = Modifier.height(10.dp))
        Row (modifier = Modifier.fillMaxWidth(1f)){
            Column(
                modifier = Modifier.fillMaxWidth(0.5f)
            )
            {
                CardForProgressReport(
                    title="Exercise",
                    value = exercise,
                    gif = R.drawable.running
                )

                CardForProgressReport(
                    title = "Soothing music",
                    value=soothingmusic,
                    backgroundColor = LightBlue,
                    modifier = Modifier.fillMaxWidth()
                )
            }
            Column()
            {
            CardForProgressReport(
                    backgroundColor = LightPurple,
                    value=binauralBeats,
                    modifier = Modifier.fillMaxWidth()
                    )

                CardForProgressReport(
                    title="Meditation",
                    value = meditation,
                    gif=R.drawable.meditationgif,
                    backgroundColor =  LightRed,
                    modifier = Modifier.fillMaxWidth()
                    )
            }

        }

    }

}