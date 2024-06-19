package com.mysteriouscoder.physicalandmentalhealth.ui.screens.profile

import android.annotation.SuppressLint
import android.util.Log
import androidx.activity.ComponentActivity
import androidx.activity.compose.BackHandler
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Arrangement
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
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material3.Divider
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.ktx.auth
import com.google.firebase.ktx.Firebase
import com.mysteriouscoder.physicalandmentalhealth.PreferenceManager
import com.mysteriouscoder.physicalandmentalhealth.R
import com.mysteriouscoder.physicalandmentalhealth.TotalTimeReport
import com.mysteriouscoder.physicalandmentalhealth.ui.screens.profile.component.ProgressTracker
import com.mysteriouscoder.physicalandmentalhealth.ui.theme.GreyBg
import com.mysteriouscoder.physicalandmentalhealth.ui.theme.PhysicalAndMentalHealthTheme


@SuppressLint("RememberReturnType")
@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun Profile() {
    Log.d("Profile", "Profile")
    val auth: FirebaseAuth = Firebase.auth
    val username = auth.currentUser?.displayName ?: "Guest"
    val email = auth.currentUser?.email ?: "Guest@gmail.com"
    val context = LocalContext.current
    val prefs = remember(context) { PreferenceManager(context) }
    val meditation = prefs.get("meditation")
    val exercise = prefs.get("exercise")
    val soothingmusic = prefs.get("soothingmusic")
    val binauralbeats = prefs.get("binauralbeats")
    val totaltime =
        meditation.toString().toInt() + exercise.toString().toInt() + soothingmusic.toString()
            .toInt() + binauralbeats.toString().toInt()

    val activity = remember(context) { context as? ComponentActivity }
    LazyColumn(
        horizontalAlignment = Alignment.CenterHorizontally,
        modifier = Modifier
            .background(GreyBg)
            .fillMaxSize()
            .padding(horizontal = 20.dp)
    )
    {

        item {
            Spacer(modifier = Modifier.height(10.dp))

            Row(
                verticalAlignment = Alignment.CenterVertically,
                horizontalArrangement = Arrangement.spacedBy(10.dp),
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(end = 10.dp)
                    .padding(vertical = 10.dp)

            )
            {

                Image(
                    painter = painterResource(id = R.drawable.profile),
                    contentDescription = "Profile",
                    modifier = Modifier
                        .size(72.dp)
                        .border(2.dp, MaterialTheme.colorScheme.primary, CircleShape)
                )
                Spacer(modifier = Modifier.width(8.dp))


                Column {
                    Text(
                        text = username,
                        style = MaterialTheme.typography.headlineSmall,
                        fontWeight = FontWeight.Bold,
                        fontFamily = FontFamily.SansSerif,
                        fontSize = 24.sp,
                    )
                    Text(
                        text = email,
                        style = MaterialTheme.typography.bodySmall,
                        fontWeight = FontWeight.Bold,
                        fontFamily = FontFamily.SansSerif,
                        fontSize = 12.sp,
                        color = Color.Gray
                    )
                    Text(
                        text = "Start your fulfillment journey",
                        style = MaterialTheme.typography.bodySmall,
                        fontWeight = FontWeight.Bold,
                        fontFamily = FontFamily.SansSerif,
                        fontSize = 12.sp,
                        color = Color.Gray
                    )
                }


            }
            Spacer(modifier = Modifier.height(10.dp))

            ProgressTracker(
                meditation?.toInt() ?: 0,
                exercise?.toInt() ?: 0,
                soothingmusic?.toInt() ?: 0,
                binauralbeats
                    ?.toInt() ?: 0
            )

            Spacer(modifier = Modifier.height(10.dp))
            Divider(
                modifier = Modifier
                    .fillMaxWidth()
                    .background(Color.LightGray)
            )


            Spacer(modifier = Modifier.height(10.dp))
            TotalTimeReport(totaltime = totaltime)
            Spacer(modifier = Modifier.height(10.dp))


        }
    }
    BackHandler(onBack = {
        activity?.finishAffinity()
    })
}

@Preview(showSystemUi = true)
@Composable
fun ProfilePreview() {
    PhysicalAndMentalHealthTheme {
        Profile()
    }
}
